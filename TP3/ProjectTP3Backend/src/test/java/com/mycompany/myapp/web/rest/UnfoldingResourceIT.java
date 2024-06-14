package com.mycompany.myapp.web.rest;

import static com.mycompany.myapp.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.Frame;
import com.mycompany.myapp.domain.Hive;
import com.mycompany.myapp.domain.Unfolding;
import com.mycompany.myapp.domain.enumeration.UnfoldingState;
import com.mycompany.myapp.repository.UnfoldingRepository;
import com.mycompany.myapp.service.UnfoldingService;
import com.mycompany.myapp.service.criteria.UnfoldingCriteria;
import com.mycompany.myapp.service.dto.UnfoldingDTO;
import com.mycompany.myapp.service.mapper.UnfoldingMapper;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link UnfoldingResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class UnfoldingResourceIT {

    private static final String DEFAULT_OBSERVATIONS = "AAAAAAAAAA";
    private static final String UPDATED_OBSERVATIONS = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final ZonedDateTime SMALLER_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(-1L), ZoneOffset.UTC);

    private static final UnfoldingState DEFAULT_STATE = UnfoldingState.REGISTERED;
    private static final UnfoldingState UPDATED_STATE = UnfoldingState.ANNULLED;

    private static final String ENTITY_API_URL = "/api/unfoldings";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private UnfoldingRepository unfoldingRepository;

    @Mock
    private UnfoldingRepository unfoldingRepositoryMock;

    @Autowired
    private UnfoldingMapper unfoldingMapper;

    @Mock
    private UnfoldingService unfoldingServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUnfoldingMockMvc;

    private Unfolding unfolding;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Unfolding createEntity(EntityManager em) {
        Unfolding unfolding = new Unfolding().observations(DEFAULT_OBSERVATIONS).date(DEFAULT_DATE).state(DEFAULT_STATE);
        return unfolding;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Unfolding createUpdatedEntity(EntityManager em) {
        Unfolding unfolding = new Unfolding().observations(UPDATED_OBSERVATIONS).date(UPDATED_DATE).state(UPDATED_STATE);
        return unfolding;
    }

    @BeforeEach
    public void initTest() {
        unfolding = createEntity(em);
    }

    @Test
    @Transactional
    void createUnfoldingWithExistingId() throws Exception {
        // Create the Unfolding with an existing ID
        unfolding.setId(1L);
        UnfoldingDTO unfoldingDTO = unfoldingMapper.toDto(unfolding);

        int databaseSizeBeforeCreate = unfoldingRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restUnfoldingMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(unfoldingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Unfolding in the database
        List<Unfolding> unfoldingList = unfoldingRepository.findAll();
        assertThat(unfoldingList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = unfoldingRepository.findAll().size();
        // set the field null
        unfolding.setDate(null);

        // Create the Unfolding, which fails.
        UnfoldingDTO unfoldingDTO = unfoldingMapper.toDto(unfolding);

        restUnfoldingMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(unfoldingDTO)))
            .andExpect(status().isBadRequest());

        List<Unfolding> unfoldingList = unfoldingRepository.findAll();
        assertThat(unfoldingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkStateIsRequired() throws Exception {
        int databaseSizeBeforeTest = unfoldingRepository.findAll().size();
        // set the field null
        unfolding.setState(null);

        // Create the Unfolding, which fails.
        UnfoldingDTO unfoldingDTO = unfoldingMapper.toDto(unfolding);

        restUnfoldingMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(unfoldingDTO)))
            .andExpect(status().isBadRequest());

        List<Unfolding> unfoldingList = unfoldingRepository.findAll();
        assertThat(unfoldingList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllUnfoldings() throws Exception {
        // Initialize the database
        unfoldingRepository.saveAndFlush(unfolding);

        // Get all the unfoldingList
        restUnfoldingMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(unfolding.getId().intValue())))
            .andExpect(jsonPath("$.[*].observations").value(hasItem(DEFAULT_OBSERVATIONS)))
            .andExpect(jsonPath("$.[*].date").value(hasItem(sameInstant(DEFAULT_DATE))))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE.toString())));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllUnfoldingsWithEagerRelationshipsIsEnabled() throws Exception {
        when(unfoldingServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restUnfoldingMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(unfoldingServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllUnfoldingsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(unfoldingServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restUnfoldingMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(unfoldingRepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @Transactional
    void getUnfolding() throws Exception {
        // Initialize the database
        unfoldingRepository.saveAndFlush(unfolding);

        // Get the unfolding
        restUnfoldingMockMvc
            .perform(get(ENTITY_API_URL_ID, unfolding.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(unfolding.getId().intValue()))
            .andExpect(jsonPath("$.observations").value(DEFAULT_OBSERVATIONS))
            .andExpect(jsonPath("$.date").value(sameInstant(DEFAULT_DATE)))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE.toString()));
    }

    @Test
    @Transactional
    void getUnfoldingsByIdFiltering() throws Exception {
        // Initialize the database
        unfoldingRepository.saveAndFlush(unfolding);

        Long id = unfolding.getId();

        defaultUnfoldingShouldBeFound("id.equals=" + id);
        defaultUnfoldingShouldNotBeFound("id.notEquals=" + id);

        defaultUnfoldingShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultUnfoldingShouldNotBeFound("id.greaterThan=" + id);

        defaultUnfoldingShouldBeFound("id.lessThanOrEqual=" + id);
        defaultUnfoldingShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllUnfoldingsByObservationsIsEqualToSomething() throws Exception {
        // Initialize the database
        unfoldingRepository.saveAndFlush(unfolding);

        // Get all the unfoldingList where observations equals to DEFAULT_OBSERVATIONS
        defaultUnfoldingShouldBeFound("observations.equals=" + DEFAULT_OBSERVATIONS);

        // Get all the unfoldingList where observations equals to UPDATED_OBSERVATIONS
        defaultUnfoldingShouldNotBeFound("observations.equals=" + UPDATED_OBSERVATIONS);
    }

    @Test
    @Transactional
    void getAllUnfoldingsByObservationsIsInShouldWork() throws Exception {
        // Initialize the database
        unfoldingRepository.saveAndFlush(unfolding);

        // Get all the unfoldingList where observations in DEFAULT_OBSERVATIONS or UPDATED_OBSERVATIONS
        defaultUnfoldingShouldBeFound("observations.in=" + DEFAULT_OBSERVATIONS + "," + UPDATED_OBSERVATIONS);

        // Get all the unfoldingList where observations equals to UPDATED_OBSERVATIONS
        defaultUnfoldingShouldNotBeFound("observations.in=" + UPDATED_OBSERVATIONS);
    }

    @Test
    @Transactional
    void getAllUnfoldingsByObservationsIsNullOrNotNull() throws Exception {
        // Initialize the database
        unfoldingRepository.saveAndFlush(unfolding);

        // Get all the unfoldingList where observations is not null
        defaultUnfoldingShouldBeFound("observations.specified=true");

        // Get all the unfoldingList where observations is null
        defaultUnfoldingShouldNotBeFound("observations.specified=false");
    }

    @Test
    @Transactional
    void getAllUnfoldingsByObservationsContainsSomething() throws Exception {
        // Initialize the database
        unfoldingRepository.saveAndFlush(unfolding);

        // Get all the unfoldingList where observations contains DEFAULT_OBSERVATIONS
        defaultUnfoldingShouldBeFound("observations.contains=" + DEFAULT_OBSERVATIONS);

        // Get all the unfoldingList where observations contains UPDATED_OBSERVATIONS
        defaultUnfoldingShouldNotBeFound("observations.contains=" + UPDATED_OBSERVATIONS);
    }

    @Test
    @Transactional
    void getAllUnfoldingsByObservationsNotContainsSomething() throws Exception {
        // Initialize the database
        unfoldingRepository.saveAndFlush(unfolding);

        // Get all the unfoldingList where observations does not contain DEFAULT_OBSERVATIONS
        defaultUnfoldingShouldNotBeFound("observations.doesNotContain=" + DEFAULT_OBSERVATIONS);

        // Get all the unfoldingList where observations does not contain UPDATED_OBSERVATIONS
        defaultUnfoldingShouldBeFound("observations.doesNotContain=" + UPDATED_OBSERVATIONS);
    }

    @Test
    @Transactional
    void getAllUnfoldingsByDateIsEqualToSomething() throws Exception {
        // Initialize the database
        unfoldingRepository.saveAndFlush(unfolding);

        // Get all the unfoldingList where date equals to DEFAULT_DATE
        defaultUnfoldingShouldBeFound("date.equals=" + DEFAULT_DATE);

        // Get all the unfoldingList where date equals to UPDATED_DATE
        defaultUnfoldingShouldNotBeFound("date.equals=" + UPDATED_DATE);
    }

    @Test
    @Transactional
    void getAllUnfoldingsByDateIsInShouldWork() throws Exception {
        // Initialize the database
        unfoldingRepository.saveAndFlush(unfolding);

        // Get all the unfoldingList where date in DEFAULT_DATE or UPDATED_DATE
        defaultUnfoldingShouldBeFound("date.in=" + DEFAULT_DATE + "," + UPDATED_DATE);

        // Get all the unfoldingList where date equals to UPDATED_DATE
        defaultUnfoldingShouldNotBeFound("date.in=" + UPDATED_DATE);
    }

    @Test
    @Transactional
    void getAllUnfoldingsByDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        unfoldingRepository.saveAndFlush(unfolding);

        // Get all the unfoldingList where date is not null
        defaultUnfoldingShouldBeFound("date.specified=true");

        // Get all the unfoldingList where date is null
        defaultUnfoldingShouldNotBeFound("date.specified=false");
    }

    @Test
    @Transactional
    void getAllUnfoldingsByDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        unfoldingRepository.saveAndFlush(unfolding);

        // Get all the unfoldingList where date is greater than or equal to DEFAULT_DATE
        defaultUnfoldingShouldBeFound("date.greaterThanOrEqual=" + DEFAULT_DATE);

        // Get all the unfoldingList where date is greater than or equal to UPDATED_DATE
        defaultUnfoldingShouldNotBeFound("date.greaterThanOrEqual=" + UPDATED_DATE);
    }

    @Test
    @Transactional
    void getAllUnfoldingsByDateIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        unfoldingRepository.saveAndFlush(unfolding);

        // Get all the unfoldingList where date is less than or equal to DEFAULT_DATE
        defaultUnfoldingShouldBeFound("date.lessThanOrEqual=" + DEFAULT_DATE);

        // Get all the unfoldingList where date is less than or equal to SMALLER_DATE
        defaultUnfoldingShouldNotBeFound("date.lessThanOrEqual=" + SMALLER_DATE);
    }

    @Test
    @Transactional
    void getAllUnfoldingsByDateIsLessThanSomething() throws Exception {
        // Initialize the database
        unfoldingRepository.saveAndFlush(unfolding);

        // Get all the unfoldingList where date is less than DEFAULT_DATE
        defaultUnfoldingShouldNotBeFound("date.lessThan=" + DEFAULT_DATE);

        // Get all the unfoldingList where date is less than UPDATED_DATE
        defaultUnfoldingShouldBeFound("date.lessThan=" + UPDATED_DATE);
    }

    @Test
    @Transactional
    void getAllUnfoldingsByDateIsGreaterThanSomething() throws Exception {
        // Initialize the database
        unfoldingRepository.saveAndFlush(unfolding);

        // Get all the unfoldingList where date is greater than DEFAULT_DATE
        defaultUnfoldingShouldNotBeFound("date.greaterThan=" + DEFAULT_DATE);

        // Get all the unfoldingList where date is greater than SMALLER_DATE
        defaultUnfoldingShouldBeFound("date.greaterThan=" + SMALLER_DATE);
    }

    @Test
    @Transactional
    void getAllUnfoldingsByStateIsEqualToSomething() throws Exception {
        // Initialize the database
        unfoldingRepository.saveAndFlush(unfolding);

        // Get all the unfoldingList where state equals to DEFAULT_STATE
        defaultUnfoldingShouldBeFound("state.equals=" + DEFAULT_STATE);

        // Get all the unfoldingList where state equals to UPDATED_STATE
        defaultUnfoldingShouldNotBeFound("state.equals=" + UPDATED_STATE);
    }

    @Test
    @Transactional
    void getAllUnfoldingsByStateIsInShouldWork() throws Exception {
        // Initialize the database
        unfoldingRepository.saveAndFlush(unfolding);

        // Get all the unfoldingList where state in DEFAULT_STATE or UPDATED_STATE
        defaultUnfoldingShouldBeFound("state.in=" + DEFAULT_STATE + "," + UPDATED_STATE);

        // Get all the unfoldingList where state equals to UPDATED_STATE
        defaultUnfoldingShouldNotBeFound("state.in=" + UPDATED_STATE);
    }

    @Test
    @Transactional
    void getAllUnfoldingsByStateIsNullOrNotNull() throws Exception {
        // Initialize the database
        unfoldingRepository.saveAndFlush(unfolding);

        // Get all the unfoldingList where state is not null
        defaultUnfoldingShouldBeFound("state.specified=true");

        // Get all the unfoldingList where state is null
        defaultUnfoldingShouldNotBeFound("state.specified=false");
    }

    @Test
    @Transactional
    void getAllUnfoldingsByCreatedHiveIsEqualToSomething() throws Exception {
        Hive createdHive;
        if (TestUtil.findAll(em, Hive.class).isEmpty()) {
            unfoldingRepository.saveAndFlush(unfolding);
            createdHive = HiveResourceIT.createEntity(em);
        } else {
            createdHive = TestUtil.findAll(em, Hive.class).get(0);
        }
        em.persist(createdHive);
        em.flush();
        unfolding.setCreatedHive(createdHive);
        unfoldingRepository.saveAndFlush(unfolding);
        Long createdHiveId = createdHive.getId();

        // Get all the unfoldingList where createdHive equals to createdHiveId
        defaultUnfoldingShouldBeFound("createdHiveId.equals=" + createdHiveId);

        // Get all the unfoldingList where createdHive equals to (createdHiveId + 1)
        defaultUnfoldingShouldNotBeFound("createdHiveId.equals=" + (createdHiveId + 1));
    }

    @Test
    @Transactional
    void getAllUnfoldingsByHiveIsEqualToSomething() throws Exception {
        Hive hive;
        if (TestUtil.findAll(em, Hive.class).isEmpty()) {
            unfoldingRepository.saveAndFlush(unfolding);
            hive = HiveResourceIT.createEntity(em);
        } else {
            hive = TestUtil.findAll(em, Hive.class).get(0);
        }
        em.persist(hive);
        em.flush();
        unfolding.setHive(hive);
        unfoldingRepository.saveAndFlush(unfolding);
        Long hiveId = hive.getId();

        // Get all the unfoldingList where hive equals to hiveId
        defaultUnfoldingShouldBeFound("hiveId.equals=" + hiveId);

        // Get all the unfoldingList where hive equals to (hiveId + 1)
        defaultUnfoldingShouldNotBeFound("hiveId.equals=" + (hiveId + 1));
    }

    @Test
    @Transactional
    void getAllUnfoldingsByRemovedFramesOldHiveIsEqualToSomething() throws Exception {
        Frame removedFramesOldHive;
        if (TestUtil.findAll(em, Frame.class).isEmpty()) {
            unfoldingRepository.saveAndFlush(unfolding);
            removedFramesOldHive = FrameResourceIT.createEntity(em);
        } else {
            removedFramesOldHive = TestUtil.findAll(em, Frame.class).get(0);
        }
        em.persist(removedFramesOldHive);
        em.flush();
        unfolding.addRemovedFramesOldHive(removedFramesOldHive);
        unfoldingRepository.saveAndFlush(unfolding);
        Long removedFramesOldHiveId = removedFramesOldHive.getId();

        // Get all the unfoldingList where removedFramesOldHive equals to removedFramesOldHiveId
        defaultUnfoldingShouldBeFound("removedFramesOldHiveId.equals=" + removedFramesOldHiveId);

        // Get all the unfoldingList where removedFramesOldHive equals to (removedFramesOldHiveId + 1)
        defaultUnfoldingShouldNotBeFound("removedFramesOldHiveId.equals=" + (removedFramesOldHiveId + 1));
    }

    @Test
    @Transactional
    void getAllUnfoldingsByInsertedFramesOldHiveIsEqualToSomething() throws Exception {
        Frame insertedFramesOldHive;
        if (TestUtil.findAll(em, Frame.class).isEmpty()) {
            unfoldingRepository.saveAndFlush(unfolding);
            insertedFramesOldHive = FrameResourceIT.createEntity(em);
        } else {
            insertedFramesOldHive = TestUtil.findAll(em, Frame.class).get(0);
        }
        em.persist(insertedFramesOldHive);
        em.flush();
        unfolding.addInsertedFramesOldHive(insertedFramesOldHive);
        unfoldingRepository.saveAndFlush(unfolding);
        Long insertedFramesOldHiveId = insertedFramesOldHive.getId();

        // Get all the unfoldingList where insertedFramesOldHive equals to insertedFramesOldHiveId
        defaultUnfoldingShouldBeFound("insertedFramesOldHiveId.equals=" + insertedFramesOldHiveId);

        // Get all the unfoldingList where insertedFramesOldHive equals to (insertedFramesOldHiveId + 1)
        defaultUnfoldingShouldNotBeFound("insertedFramesOldHiveId.equals=" + (insertedFramesOldHiveId + 1));
    }

    @Test
    @Transactional
    void getAllUnfoldingsByInsertedFramesNewHiveIsEqualToSomething() throws Exception {
        Frame insertedFramesNewHive;
        if (TestUtil.findAll(em, Frame.class).isEmpty()) {
            unfoldingRepository.saveAndFlush(unfolding);
            insertedFramesNewHive = FrameResourceIT.createEntity(em);
        } else {
            insertedFramesNewHive = TestUtil.findAll(em, Frame.class).get(0);
        }
        em.persist(insertedFramesNewHive);
        em.flush();
        unfolding.addInsertedFramesNewHive(insertedFramesNewHive);
        unfoldingRepository.saveAndFlush(unfolding);
        Long insertedFramesNewHiveId = insertedFramesNewHive.getId();

        // Get all the unfoldingList where insertedFramesNewHive equals to insertedFramesNewHiveId
        defaultUnfoldingShouldBeFound("insertedFramesNewHiveId.equals=" + insertedFramesNewHiveId);

        // Get all the unfoldingList where insertedFramesNewHive equals to (insertedFramesNewHiveId + 1)
        defaultUnfoldingShouldNotBeFound("insertedFramesNewHiveId.equals=" + (insertedFramesNewHiveId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultUnfoldingShouldBeFound(String filter) throws Exception {
        restUnfoldingMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(unfolding.getId().intValue())))
            .andExpect(jsonPath("$.[*].observations").value(hasItem(DEFAULT_OBSERVATIONS)))
            .andExpect(jsonPath("$.[*].date").value(hasItem(sameInstant(DEFAULT_DATE))))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE.toString())));

        // Check, that the count call also returns 1
        restUnfoldingMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultUnfoldingShouldNotBeFound(String filter) throws Exception {
        restUnfoldingMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restUnfoldingMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingUnfolding() throws Exception {
        // Get the unfolding
        restUnfoldingMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingUnfolding() throws Exception {
        // Initialize the database
        unfoldingRepository.saveAndFlush(unfolding);

        int databaseSizeBeforeUpdate = unfoldingRepository.findAll().size();

        // Update the unfolding
        Unfolding updatedUnfolding = unfoldingRepository.findById(unfolding.getId()).get();
        // Disconnect from session so that the updates on updatedUnfolding are not directly saved in db
        em.detach(updatedUnfolding);
        updatedUnfolding.observations(UPDATED_OBSERVATIONS).date(UPDATED_DATE).state(UPDATED_STATE);
        UnfoldingDTO unfoldingDTO = unfoldingMapper.toDto(updatedUnfolding);

        restUnfoldingMockMvc
            .perform(
                put(ENTITY_API_URL_ID, unfoldingDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(unfoldingDTO))
            )
            .andExpect(status().isOk());

        // Validate the Unfolding in the database
        List<Unfolding> unfoldingList = unfoldingRepository.findAll();
        assertThat(unfoldingList).hasSize(databaseSizeBeforeUpdate);
        Unfolding testUnfolding = unfoldingList.get(unfoldingList.size() - 1);
        assertThat(testUnfolding.getObservations()).isEqualTo(UPDATED_OBSERVATIONS);
        assertThat(testUnfolding.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testUnfolding.getState()).isEqualTo(UPDATED_STATE);
    }

    @Test
    @Transactional
    void putNonExistingUnfolding() throws Exception {
        int databaseSizeBeforeUpdate = unfoldingRepository.findAll().size();
        unfolding.setId(count.incrementAndGet());

        // Create the Unfolding
        UnfoldingDTO unfoldingDTO = unfoldingMapper.toDto(unfolding);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUnfoldingMockMvc
            .perform(
                put(ENTITY_API_URL_ID, unfoldingDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(unfoldingDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Unfolding in the database
        List<Unfolding> unfoldingList = unfoldingRepository.findAll();
        assertThat(unfoldingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchUnfolding() throws Exception {
        int databaseSizeBeforeUpdate = unfoldingRepository.findAll().size();
        unfolding.setId(count.incrementAndGet());

        // Create the Unfolding
        UnfoldingDTO unfoldingDTO = unfoldingMapper.toDto(unfolding);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUnfoldingMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(unfoldingDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Unfolding in the database
        List<Unfolding> unfoldingList = unfoldingRepository.findAll();
        assertThat(unfoldingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamUnfolding() throws Exception {
        int databaseSizeBeforeUpdate = unfoldingRepository.findAll().size();
        unfolding.setId(count.incrementAndGet());

        // Create the Unfolding
        UnfoldingDTO unfoldingDTO = unfoldingMapper.toDto(unfolding);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUnfoldingMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(unfoldingDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Unfolding in the database
        List<Unfolding> unfoldingList = unfoldingRepository.findAll();
        assertThat(unfoldingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateUnfoldingWithPatch() throws Exception {
        // Initialize the database
        unfoldingRepository.saveAndFlush(unfolding);

        int databaseSizeBeforeUpdate = unfoldingRepository.findAll().size();

        // Update the unfolding using partial update
        Unfolding partialUpdatedUnfolding = new Unfolding();
        partialUpdatedUnfolding.setId(unfolding.getId());

        partialUpdatedUnfolding.observations(UPDATED_OBSERVATIONS);

        restUnfoldingMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUnfolding.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedUnfolding))
            )
            .andExpect(status().isOk());

        // Validate the Unfolding in the database
        List<Unfolding> unfoldingList = unfoldingRepository.findAll();
        assertThat(unfoldingList).hasSize(databaseSizeBeforeUpdate);
        Unfolding testUnfolding = unfoldingList.get(unfoldingList.size() - 1);
        assertThat(testUnfolding.getObservations()).isEqualTo(UPDATED_OBSERVATIONS);
        assertThat(testUnfolding.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testUnfolding.getState()).isEqualTo(DEFAULT_STATE);
    }

    @Test
    @Transactional
    void fullUpdateUnfoldingWithPatch() throws Exception {
        // Initialize the database
        unfoldingRepository.saveAndFlush(unfolding);

        int databaseSizeBeforeUpdate = unfoldingRepository.findAll().size();

        // Update the unfolding using partial update
        Unfolding partialUpdatedUnfolding = new Unfolding();
        partialUpdatedUnfolding.setId(unfolding.getId());

        partialUpdatedUnfolding.observations(UPDATED_OBSERVATIONS).date(UPDATED_DATE).state(UPDATED_STATE);

        restUnfoldingMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUnfolding.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedUnfolding))
            )
            .andExpect(status().isOk());

        // Validate the Unfolding in the database
        List<Unfolding> unfoldingList = unfoldingRepository.findAll();
        assertThat(unfoldingList).hasSize(databaseSizeBeforeUpdate);
        Unfolding testUnfolding = unfoldingList.get(unfoldingList.size() - 1);
        assertThat(testUnfolding.getObservations()).isEqualTo(UPDATED_OBSERVATIONS);
        assertThat(testUnfolding.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testUnfolding.getState()).isEqualTo(UPDATED_STATE);
    }

    @Test
    @Transactional
    void patchNonExistingUnfolding() throws Exception {
        int databaseSizeBeforeUpdate = unfoldingRepository.findAll().size();
        unfolding.setId(count.incrementAndGet());

        // Create the Unfolding
        UnfoldingDTO unfoldingDTO = unfoldingMapper.toDto(unfolding);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUnfoldingMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, unfoldingDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(unfoldingDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Unfolding in the database
        List<Unfolding> unfoldingList = unfoldingRepository.findAll();
        assertThat(unfoldingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchUnfolding() throws Exception {
        int databaseSizeBeforeUpdate = unfoldingRepository.findAll().size();
        unfolding.setId(count.incrementAndGet());

        // Create the Unfolding
        UnfoldingDTO unfoldingDTO = unfoldingMapper.toDto(unfolding);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUnfoldingMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(unfoldingDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Unfolding in the database
        List<Unfolding> unfoldingList = unfoldingRepository.findAll();
        assertThat(unfoldingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamUnfolding() throws Exception {
        int databaseSizeBeforeUpdate = unfoldingRepository.findAll().size();
        unfolding.setId(count.incrementAndGet());

        // Create the Unfolding
        UnfoldingDTO unfoldingDTO = unfoldingMapper.toDto(unfolding);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUnfoldingMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(unfoldingDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Unfolding in the database
        List<Unfolding> unfoldingList = unfoldingRepository.findAll();
        assertThat(unfoldingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteUnfolding() throws Exception {
        // Initialize the database
        unfoldingRepository.saveAndFlush(unfolding);

        int databaseSizeBeforeDelete = unfoldingRepository.findAll().size();

        // Delete the unfolding
        restUnfoldingMockMvc
            .perform(delete(ENTITY_API_URL_ID, unfolding.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Unfolding> unfoldingList = unfoldingRepository.findAll();
        assertThat(unfoldingList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
