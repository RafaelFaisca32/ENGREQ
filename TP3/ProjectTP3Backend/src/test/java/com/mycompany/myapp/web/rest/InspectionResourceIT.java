package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.Disease;
import com.mycompany.myapp.domain.Hive;
import com.mycompany.myapp.domain.Inspection;
import com.mycompany.myapp.domain.enumeration.InspectionState;
import com.mycompany.myapp.domain.enumeration.InspectionType;
import com.mycompany.myapp.repository.InspectionRepository;
import com.mycompany.myapp.service.InspectionService;
import com.mycompany.myapp.service.criteria.InspectionCriteria;
import com.mycompany.myapp.service.dto.InspectionDTO;
import com.mycompany.myapp.service.mapper.InspectionMapper;
import java.time.LocalDate;
import java.time.ZoneId;
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
 * Integration tests for the {@link InspectionResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class InspectionResourceIT {

    private static final LocalDate DEFAULT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_DATE = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_NOTE = "AAAAAAAAAA";
    private static final String UPDATED_NOTE = "BBBBBBBBBB";

    private static final InspectionType DEFAULT_TYPE = InspectionType.EXTERNAL;
    private static final InspectionType UPDATED_TYPE = InspectionType.INTERNAL;

    private static final InspectionState DEFAULT_STATE = InspectionState.REGISTERED;
    private static final InspectionState UPDATED_STATE = InspectionState.ANNULLED;

    private static final String ENTITY_API_URL = "/api/inspections";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private InspectionRepository inspectionRepository;

    @Mock
    private InspectionRepository inspectionRepositoryMock;

    @Autowired
    private InspectionMapper inspectionMapper;

    @Mock
    private InspectionService inspectionServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInspectionMockMvc;

    private Inspection inspection;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Inspection createEntity(EntityManager em) {
        Inspection inspection = new Inspection().date(DEFAULT_DATE).note(DEFAULT_NOTE).type(DEFAULT_TYPE).state(DEFAULT_STATE);
        return inspection;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Inspection createUpdatedEntity(EntityManager em) {
        Inspection inspection = new Inspection().date(UPDATED_DATE).note(UPDATED_NOTE).type(UPDATED_TYPE).state(UPDATED_STATE);
        return inspection;
    }

    @BeforeEach
    public void initTest() {
        inspection = createEntity(em);
    }

    @Test
    @Transactional
    void createInspection() throws Exception {
        int databaseSizeBeforeCreate = inspectionRepository.findAll().size();
        // Create the Inspection
        InspectionDTO inspectionDTO = inspectionMapper.toDto(inspection);
        restInspectionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(inspectionDTO)))
            .andExpect(status().isCreated());

        // Validate the Inspection in the database
        List<Inspection> inspectionList = inspectionRepository.findAll();
        assertThat(inspectionList).hasSize(databaseSizeBeforeCreate + 1);
        Inspection testInspection = inspectionList.get(inspectionList.size() - 1);
        assertThat(testInspection.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testInspection.getNote()).isEqualTo(DEFAULT_NOTE);
        assertThat(testInspection.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testInspection.getState()).isEqualTo(DEFAULT_STATE);
    }

    @Test
    @Transactional
    void createInspectionWithExistingId() throws Exception {
        // Create the Inspection with an existing ID
        inspection.setId(1L);
        InspectionDTO inspectionDTO = inspectionMapper.toDto(inspection);

        int databaseSizeBeforeCreate = inspectionRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restInspectionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(inspectionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Inspection in the database
        List<Inspection> inspectionList = inspectionRepository.findAll();
        assertThat(inspectionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = inspectionRepository.findAll().size();
        // set the field null
        inspection.setDate(null);

        // Create the Inspection, which fails.
        InspectionDTO inspectionDTO = inspectionMapper.toDto(inspection);

        restInspectionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(inspectionDTO)))
            .andExpect(status().isBadRequest());

        List<Inspection> inspectionList = inspectionRepository.findAll();
        assertThat(inspectionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkStateIsRequired() throws Exception {
        int databaseSizeBeforeTest = inspectionRepository.findAll().size();
        // set the field null
        inspection.setState(null);

        // Create the Inspection, which fails.
        InspectionDTO inspectionDTO = inspectionMapper.toDto(inspection);

        restInspectionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(inspectionDTO)))
            .andExpect(status().isBadRequest());

        List<Inspection> inspectionList = inspectionRepository.findAll();
        assertThat(inspectionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllInspections() throws Exception {
        // Initialize the database
        inspectionRepository.saveAndFlush(inspection);

        // Get all the inspectionList
        restInspectionMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(inspection.getId().intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].note").value(hasItem(DEFAULT_NOTE)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE.toString())));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllInspectionsWithEagerRelationshipsIsEnabled() throws Exception {
        when(inspectionServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restInspectionMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(inspectionServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllInspectionsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(inspectionServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restInspectionMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(inspectionRepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @Transactional
    void getInspection() throws Exception {
        // Initialize the database
        inspectionRepository.saveAndFlush(inspection);

        // Get the inspection
        restInspectionMockMvc
            .perform(get(ENTITY_API_URL_ID, inspection.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(inspection.getId().intValue()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.note").value(DEFAULT_NOTE))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE.toString()));
    }

    @Test
    @Transactional
    void getInspectionsByIdFiltering() throws Exception {
        // Initialize the database
        inspectionRepository.saveAndFlush(inspection);

        Long id = inspection.getId();

        defaultInspectionShouldBeFound("id.equals=" + id);
        defaultInspectionShouldNotBeFound("id.notEquals=" + id);

        defaultInspectionShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultInspectionShouldNotBeFound("id.greaterThan=" + id);

        defaultInspectionShouldBeFound("id.lessThanOrEqual=" + id);
        defaultInspectionShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllInspectionsByDateIsEqualToSomething() throws Exception {
        // Initialize the database
        inspectionRepository.saveAndFlush(inspection);

        // Get all the inspectionList where date equals to DEFAULT_DATE
        defaultInspectionShouldBeFound("date.equals=" + DEFAULT_DATE);

        // Get all the inspectionList where date equals to UPDATED_DATE
        defaultInspectionShouldNotBeFound("date.equals=" + UPDATED_DATE);
    }

    @Test
    @Transactional
    void getAllInspectionsByDateIsInShouldWork() throws Exception {
        // Initialize the database
        inspectionRepository.saveAndFlush(inspection);

        // Get all the inspectionList where date in DEFAULT_DATE or UPDATED_DATE
        defaultInspectionShouldBeFound("date.in=" + DEFAULT_DATE + "," + UPDATED_DATE);

        // Get all the inspectionList where date equals to UPDATED_DATE
        defaultInspectionShouldNotBeFound("date.in=" + UPDATED_DATE);
    }

    @Test
    @Transactional
    void getAllInspectionsByDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        inspectionRepository.saveAndFlush(inspection);

        // Get all the inspectionList where date is not null
        defaultInspectionShouldBeFound("date.specified=true");

        // Get all the inspectionList where date is null
        defaultInspectionShouldNotBeFound("date.specified=false");
    }

    @Test
    @Transactional
    void getAllInspectionsByDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        inspectionRepository.saveAndFlush(inspection);

        // Get all the inspectionList where date is greater than or equal to DEFAULT_DATE
        defaultInspectionShouldBeFound("date.greaterThanOrEqual=" + DEFAULT_DATE);

        // Get all the inspectionList where date is greater than or equal to UPDATED_DATE
        defaultInspectionShouldNotBeFound("date.greaterThanOrEqual=" + UPDATED_DATE);
    }

    @Test
    @Transactional
    void getAllInspectionsByDateIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        inspectionRepository.saveAndFlush(inspection);

        // Get all the inspectionList where date is less than or equal to DEFAULT_DATE
        defaultInspectionShouldBeFound("date.lessThanOrEqual=" + DEFAULT_DATE);

        // Get all the inspectionList where date is less than or equal to SMALLER_DATE
        defaultInspectionShouldNotBeFound("date.lessThanOrEqual=" + SMALLER_DATE);
    }

    @Test
    @Transactional
    void getAllInspectionsByDateIsLessThanSomething() throws Exception {
        // Initialize the database
        inspectionRepository.saveAndFlush(inspection);

        // Get all the inspectionList where date is less than DEFAULT_DATE
        defaultInspectionShouldNotBeFound("date.lessThan=" + DEFAULT_DATE);

        // Get all the inspectionList where date is less than UPDATED_DATE
        defaultInspectionShouldBeFound("date.lessThan=" + UPDATED_DATE);
    }

    @Test
    @Transactional
    void getAllInspectionsByDateIsGreaterThanSomething() throws Exception {
        // Initialize the database
        inspectionRepository.saveAndFlush(inspection);

        // Get all the inspectionList where date is greater than DEFAULT_DATE
        defaultInspectionShouldNotBeFound("date.greaterThan=" + DEFAULT_DATE);

        // Get all the inspectionList where date is greater than SMALLER_DATE
        defaultInspectionShouldBeFound("date.greaterThan=" + SMALLER_DATE);
    }

    @Test
    @Transactional
    void getAllInspectionsByNoteIsEqualToSomething() throws Exception {
        // Initialize the database
        inspectionRepository.saveAndFlush(inspection);

        // Get all the inspectionList where note equals to DEFAULT_NOTE
        defaultInspectionShouldBeFound("note.equals=" + DEFAULT_NOTE);

        // Get all the inspectionList where note equals to UPDATED_NOTE
        defaultInspectionShouldNotBeFound("note.equals=" + UPDATED_NOTE);
    }

    @Test
    @Transactional
    void getAllInspectionsByNoteIsInShouldWork() throws Exception {
        // Initialize the database
        inspectionRepository.saveAndFlush(inspection);

        // Get all the inspectionList where note in DEFAULT_NOTE or UPDATED_NOTE
        defaultInspectionShouldBeFound("note.in=" + DEFAULT_NOTE + "," + UPDATED_NOTE);

        // Get all the inspectionList where note equals to UPDATED_NOTE
        defaultInspectionShouldNotBeFound("note.in=" + UPDATED_NOTE);
    }

    @Test
    @Transactional
    void getAllInspectionsByNoteIsNullOrNotNull() throws Exception {
        // Initialize the database
        inspectionRepository.saveAndFlush(inspection);

        // Get all the inspectionList where note is not null
        defaultInspectionShouldBeFound("note.specified=true");

        // Get all the inspectionList where note is null
        defaultInspectionShouldNotBeFound("note.specified=false");
    }

    @Test
    @Transactional
    void getAllInspectionsByNoteContainsSomething() throws Exception {
        // Initialize the database
        inspectionRepository.saveAndFlush(inspection);

        // Get all the inspectionList where note contains DEFAULT_NOTE
        defaultInspectionShouldBeFound("note.contains=" + DEFAULT_NOTE);

        // Get all the inspectionList where note contains UPDATED_NOTE
        defaultInspectionShouldNotBeFound("note.contains=" + UPDATED_NOTE);
    }

    @Test
    @Transactional
    void getAllInspectionsByNoteNotContainsSomething() throws Exception {
        // Initialize the database
        inspectionRepository.saveAndFlush(inspection);

        // Get all the inspectionList where note does not contain DEFAULT_NOTE
        defaultInspectionShouldNotBeFound("note.doesNotContain=" + DEFAULT_NOTE);

        // Get all the inspectionList where note does not contain UPDATED_NOTE
        defaultInspectionShouldBeFound("note.doesNotContain=" + UPDATED_NOTE);
    }

    @Test
    @Transactional
    void getAllInspectionsByTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        inspectionRepository.saveAndFlush(inspection);

        // Get all the inspectionList where type equals to DEFAULT_TYPE
        defaultInspectionShouldBeFound("type.equals=" + DEFAULT_TYPE);

        // Get all the inspectionList where type equals to UPDATED_TYPE
        defaultInspectionShouldNotBeFound("type.equals=" + UPDATED_TYPE);
    }

    @Test
    @Transactional
    void getAllInspectionsByTypeIsInShouldWork() throws Exception {
        // Initialize the database
        inspectionRepository.saveAndFlush(inspection);

        // Get all the inspectionList where type in DEFAULT_TYPE or UPDATED_TYPE
        defaultInspectionShouldBeFound("type.in=" + DEFAULT_TYPE + "," + UPDATED_TYPE);

        // Get all the inspectionList where type equals to UPDATED_TYPE
        defaultInspectionShouldNotBeFound("type.in=" + UPDATED_TYPE);
    }

    @Test
    @Transactional
    void getAllInspectionsByTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        inspectionRepository.saveAndFlush(inspection);

        // Get all the inspectionList where type is not null
        defaultInspectionShouldBeFound("type.specified=true");

        // Get all the inspectionList where type is null
        defaultInspectionShouldNotBeFound("type.specified=false");
    }

    @Test
    @Transactional
    void getAllInspectionsByStateIsEqualToSomething() throws Exception {
        // Initialize the database
        inspectionRepository.saveAndFlush(inspection);

        // Get all the inspectionList where state equals to DEFAULT_STATE
        defaultInspectionShouldBeFound("state.equals=" + DEFAULT_STATE);

        // Get all the inspectionList where state equals to UPDATED_STATE
        defaultInspectionShouldNotBeFound("state.equals=" + UPDATED_STATE);
    }

    @Test
    @Transactional
    void getAllInspectionsByStateIsInShouldWork() throws Exception {
        // Initialize the database
        inspectionRepository.saveAndFlush(inspection);

        // Get all the inspectionList where state in DEFAULT_STATE or UPDATED_STATE
        defaultInspectionShouldBeFound("state.in=" + DEFAULT_STATE + "," + UPDATED_STATE);

        // Get all the inspectionList where state equals to UPDATED_STATE
        defaultInspectionShouldNotBeFound("state.in=" + UPDATED_STATE);
    }

    @Test
    @Transactional
    void getAllInspectionsByStateIsNullOrNotNull() throws Exception {
        // Initialize the database
        inspectionRepository.saveAndFlush(inspection);

        // Get all the inspectionList where state is not null
        defaultInspectionShouldBeFound("state.specified=true");

        // Get all the inspectionList where state is null
        defaultInspectionShouldNotBeFound("state.specified=false");
    }

    @Test
    @Transactional
    void getAllInspectionsByHiveIsEqualToSomething() throws Exception {
        Hive hive;
        if (TestUtil.findAll(em, Hive.class).isEmpty()) {
            inspectionRepository.saveAndFlush(inspection);
            hive = HiveResourceIT.createEntity(em);
        } else {
            hive = TestUtil.findAll(em, Hive.class).get(0);
        }
        em.persist(hive);
        em.flush();
        inspection.setHive(hive);
        inspectionRepository.saveAndFlush(inspection);
        Long hiveId = hive.getId();

        // Get all the inspectionList where hive equals to hiveId
        defaultInspectionShouldBeFound("hiveId.equals=" + hiveId);

        // Get all the inspectionList where hive equals to (hiveId + 1)
        defaultInspectionShouldNotBeFound("hiveId.equals=" + (hiveId + 1));
    }

    @Test
    @Transactional
    void getAllInspectionsByDiseaseIsEqualToSomething() throws Exception {
        Disease disease;
        if (TestUtil.findAll(em, Disease.class).isEmpty()) {
            inspectionRepository.saveAndFlush(inspection);
            disease = DiseaseResourceIT.createEntity(em);
        } else {
            disease = TestUtil.findAll(em, Disease.class).get(0);
        }
        em.persist(disease);
        em.flush();
        inspection.addDisease(disease);
        inspectionRepository.saveAndFlush(inspection);
        Long diseaseId = disease.getId();

        // Get all the inspectionList where disease equals to diseaseId
        defaultInspectionShouldBeFound("diseaseId.equals=" + diseaseId);

        // Get all the inspectionList where disease equals to (diseaseId + 1)
        defaultInspectionShouldNotBeFound("diseaseId.equals=" + (diseaseId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultInspectionShouldBeFound(String filter) throws Exception {
        restInspectionMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(inspection.getId().intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].note").value(hasItem(DEFAULT_NOTE)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE.toString())));

        // Check, that the count call also returns 1
        restInspectionMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultInspectionShouldNotBeFound(String filter) throws Exception {
        restInspectionMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restInspectionMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingInspection() throws Exception {
        // Get the inspection
        restInspectionMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingInspection() throws Exception {
        // Initialize the database
        inspectionRepository.saveAndFlush(inspection);

        int databaseSizeBeforeUpdate = inspectionRepository.findAll().size();

        // Update the inspection
        Inspection updatedInspection = inspectionRepository.findById(inspection.getId()).get();
        // Disconnect from session so that the updates on updatedInspection are not directly saved in db
        em.detach(updatedInspection);
        updatedInspection.date(UPDATED_DATE).note(UPDATED_NOTE).type(UPDATED_TYPE).state(UPDATED_STATE);
        InspectionDTO inspectionDTO = inspectionMapper.toDto(updatedInspection);

        restInspectionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, inspectionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionDTO))
            )
            .andExpect(status().isOk());

        // Validate the Inspection in the database
        List<Inspection> inspectionList = inspectionRepository.findAll();
        assertThat(inspectionList).hasSize(databaseSizeBeforeUpdate);
        Inspection testInspection = inspectionList.get(inspectionList.size() - 1);
        assertThat(testInspection.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testInspection.getNote()).isEqualTo(UPDATED_NOTE);
        assertThat(testInspection.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testInspection.getState()).isEqualTo(UPDATED_STATE);
    }

    @Test
    @Transactional
    void putNonExistingInspection() throws Exception {
        int databaseSizeBeforeUpdate = inspectionRepository.findAll().size();
        inspection.setId(count.incrementAndGet());

        // Create the Inspection
        InspectionDTO inspectionDTO = inspectionMapper.toDto(inspection);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInspectionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, inspectionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Inspection in the database
        List<Inspection> inspectionList = inspectionRepository.findAll();
        assertThat(inspectionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchInspection() throws Exception {
        int databaseSizeBeforeUpdate = inspectionRepository.findAll().size();
        inspection.setId(count.incrementAndGet());

        // Create the Inspection
        InspectionDTO inspectionDTO = inspectionMapper.toDto(inspection);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInspectionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(inspectionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Inspection in the database
        List<Inspection> inspectionList = inspectionRepository.findAll();
        assertThat(inspectionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamInspection() throws Exception {
        int databaseSizeBeforeUpdate = inspectionRepository.findAll().size();
        inspection.setId(count.incrementAndGet());

        // Create the Inspection
        InspectionDTO inspectionDTO = inspectionMapper.toDto(inspection);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInspectionMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(inspectionDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Inspection in the database
        List<Inspection> inspectionList = inspectionRepository.findAll();
        assertThat(inspectionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateInspectionWithPatch() throws Exception {
        // Initialize the database
        inspectionRepository.saveAndFlush(inspection);

        int databaseSizeBeforeUpdate = inspectionRepository.findAll().size();

        // Update the inspection using partial update
        Inspection partialUpdatedInspection = new Inspection();
        partialUpdatedInspection.setId(inspection.getId());

        partialUpdatedInspection.date(UPDATED_DATE).type(UPDATED_TYPE).state(UPDATED_STATE);

        restInspectionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInspection.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedInspection))
            )
            .andExpect(status().isOk());

        // Validate the Inspection in the database
        List<Inspection> inspectionList = inspectionRepository.findAll();
        assertThat(inspectionList).hasSize(databaseSizeBeforeUpdate);
        Inspection testInspection = inspectionList.get(inspectionList.size() - 1);
        assertThat(testInspection.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testInspection.getNote()).isEqualTo(DEFAULT_NOTE);
        assertThat(testInspection.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testInspection.getState()).isEqualTo(UPDATED_STATE);
    }

    @Test
    @Transactional
    void fullUpdateInspectionWithPatch() throws Exception {
        // Initialize the database
        inspectionRepository.saveAndFlush(inspection);

        int databaseSizeBeforeUpdate = inspectionRepository.findAll().size();

        // Update the inspection using partial update
        Inspection partialUpdatedInspection = new Inspection();
        partialUpdatedInspection.setId(inspection.getId());

        partialUpdatedInspection.date(UPDATED_DATE).note(UPDATED_NOTE).type(UPDATED_TYPE).state(UPDATED_STATE);

        restInspectionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInspection.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedInspection))
            )
            .andExpect(status().isOk());

        // Validate the Inspection in the database
        List<Inspection> inspectionList = inspectionRepository.findAll();
        assertThat(inspectionList).hasSize(databaseSizeBeforeUpdate);
        Inspection testInspection = inspectionList.get(inspectionList.size() - 1);
        assertThat(testInspection.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testInspection.getNote()).isEqualTo(UPDATED_NOTE);
        assertThat(testInspection.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testInspection.getState()).isEqualTo(UPDATED_STATE);
    }

    @Test
    @Transactional
    void patchNonExistingInspection() throws Exception {
        int databaseSizeBeforeUpdate = inspectionRepository.findAll().size();
        inspection.setId(count.incrementAndGet());

        // Create the Inspection
        InspectionDTO inspectionDTO = inspectionMapper.toDto(inspection);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInspectionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, inspectionDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(inspectionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Inspection in the database
        List<Inspection> inspectionList = inspectionRepository.findAll();
        assertThat(inspectionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchInspection() throws Exception {
        int databaseSizeBeforeUpdate = inspectionRepository.findAll().size();
        inspection.setId(count.incrementAndGet());

        // Create the Inspection
        InspectionDTO inspectionDTO = inspectionMapper.toDto(inspection);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInspectionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(inspectionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Inspection in the database
        List<Inspection> inspectionList = inspectionRepository.findAll();
        assertThat(inspectionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamInspection() throws Exception {
        int databaseSizeBeforeUpdate = inspectionRepository.findAll().size();
        inspection.setId(count.incrementAndGet());

        // Create the Inspection
        InspectionDTO inspectionDTO = inspectionMapper.toDto(inspection);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInspectionMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(inspectionDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Inspection in the database
        List<Inspection> inspectionList = inspectionRepository.findAll();
        assertThat(inspectionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteInspection() throws Exception {
        // Initialize the database
        inspectionRepository.saveAndFlush(inspection);

        int databaseSizeBeforeDelete = inspectionRepository.findAll().size();

        // Delete the inspection
        restInspectionMockMvc
            .perform(delete(ENTITY_API_URL_ID, inspection.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Inspection> inspectionList = inspectionRepository.findAll();
        assertThat(inspectionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
