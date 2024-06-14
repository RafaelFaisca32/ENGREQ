package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.Apiary;
import com.mycompany.myapp.domain.Crest;
import com.mycompany.myapp.domain.Frame;
import com.mycompany.myapp.domain.Hive;
import com.mycompany.myapp.domain.Inspection;
import com.mycompany.myapp.domain.TranshumanceRequest;
import com.mycompany.myapp.domain.Unfolding;
import com.mycompany.myapp.repository.HiveRepository;
import com.mycompany.myapp.service.HiveService;
import com.mycompany.myapp.service.criteria.HiveCriteria;
import com.mycompany.myapp.service.dto.HiveDTO;
import com.mycompany.myapp.service.mapper.HiveMapper;
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
 * Integration tests for the {@link HiveResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class HiveResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/hives";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private HiveRepository hiveRepository;

    @Mock
    private HiveRepository hiveRepositoryMock;

    @Autowired
    private HiveMapper hiveMapper;

    @Mock
    private HiveService hiveServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restHiveMockMvc;

    private Hive hive;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Hive createEntity(EntityManager em) {
        Hive hive = new Hive().code(DEFAULT_CODE);
        return hive;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Hive createUpdatedEntity(EntityManager em) {
        Hive hive = new Hive().code(UPDATED_CODE);
        return hive;
    }

    @BeforeEach
    public void initTest() {
        hive = createEntity(em);
    }

    @Test
    @Transactional
    void createHive() throws Exception {
        int databaseSizeBeforeCreate = hiveRepository.findAll().size();
        // Create the Hive
        HiveDTO hiveDTO = hiveMapper.toDto(hive);
        restHiveMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(hiveDTO)))
            .andExpect(status().isCreated());

        // Validate the Hive in the database
        List<Hive> hiveList = hiveRepository.findAll();
        assertThat(hiveList).hasSize(databaseSizeBeforeCreate + 1);
        Hive testHive = hiveList.get(hiveList.size() - 1);
        assertThat(testHive.getCode()).isEqualTo(DEFAULT_CODE);
    }

    @Test
    @Transactional
    void createHiveWithExistingId() throws Exception {
        // Create the Hive with an existing ID
        hive.setId(1L);
        HiveDTO hiveDTO = hiveMapper.toDto(hive);

        int databaseSizeBeforeCreate = hiveRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restHiveMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(hiveDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Hive in the database
        List<Hive> hiveList = hiveRepository.findAll();
        assertThat(hiveList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = hiveRepository.findAll().size();
        // set the field null
        hive.setCode(null);

        // Create the Hive, which fails.
        HiveDTO hiveDTO = hiveMapper.toDto(hive);

        restHiveMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(hiveDTO)))
            .andExpect(status().isBadRequest());

        List<Hive> hiveList = hiveRepository.findAll();
        assertThat(hiveList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllHives() throws Exception {
        // Initialize the database
        hiveRepository.saveAndFlush(hive);

        // Get all the hiveList
        restHiveMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(hive.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllHivesWithEagerRelationshipsIsEnabled() throws Exception {
        when(hiveServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restHiveMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(hiveServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllHivesWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(hiveServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restHiveMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(hiveRepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @Transactional
    void getHive() throws Exception {
        // Initialize the database
        hiveRepository.saveAndFlush(hive);

        // Get the hive
        restHiveMockMvc
            .perform(get(ENTITY_API_URL_ID, hive.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(hive.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE));
    }

    @Test
    @Transactional
    void getHivesByIdFiltering() throws Exception {
        // Initialize the database
        hiveRepository.saveAndFlush(hive);

        Long id = hive.getId();

        defaultHiveShouldBeFound("id.equals=" + id);
        defaultHiveShouldNotBeFound("id.notEquals=" + id);

        defaultHiveShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultHiveShouldNotBeFound("id.greaterThan=" + id);

        defaultHiveShouldBeFound("id.lessThanOrEqual=" + id);
        defaultHiveShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllHivesByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        hiveRepository.saveAndFlush(hive);

        // Get all the hiveList where code equals to DEFAULT_CODE
        defaultHiveShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the hiveList where code equals to UPDATED_CODE
        defaultHiveShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    void getAllHivesByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        hiveRepository.saveAndFlush(hive);

        // Get all the hiveList where code in DEFAULT_CODE or UPDATED_CODE
        defaultHiveShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the hiveList where code equals to UPDATED_CODE
        defaultHiveShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    void getAllHivesByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        hiveRepository.saveAndFlush(hive);

        // Get all the hiveList where code is not null
        defaultHiveShouldBeFound("code.specified=true");

        // Get all the hiveList where code is null
        defaultHiveShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    void getAllHivesByCodeContainsSomething() throws Exception {
        // Initialize the database
        hiveRepository.saveAndFlush(hive);

        // Get all the hiveList where code contains DEFAULT_CODE
        defaultHiveShouldBeFound("code.contains=" + DEFAULT_CODE);

        // Get all the hiveList where code contains UPDATED_CODE
        defaultHiveShouldNotBeFound("code.contains=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    void getAllHivesByCodeNotContainsSomething() throws Exception {
        // Initialize the database
        hiveRepository.saveAndFlush(hive);

        // Get all the hiveList where code does not contain DEFAULT_CODE
        defaultHiveShouldNotBeFound("code.doesNotContain=" + DEFAULT_CODE);

        // Get all the hiveList where code does not contain UPDATED_CODE
        defaultHiveShouldBeFound("code.doesNotContain=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    void getAllHivesByApiaryIsEqualToSomething() throws Exception {
        Apiary apiary;
        if (TestUtil.findAll(em, Apiary.class).isEmpty()) {
            hiveRepository.saveAndFlush(hive);
            apiary = ApiaryResourceIT.createEntity(em);
        } else {
            apiary = TestUtil.findAll(em, Apiary.class).get(0);
        }
        em.persist(apiary);
        em.flush();
        hive.setApiary(apiary);
        hiveRepository.saveAndFlush(hive);
        Long apiaryId = apiary.getId();

        // Get all the hiveList where apiary equals to apiaryId
        defaultHiveShouldBeFound("apiaryId.equals=" + apiaryId);

        // Get all the hiveList where apiary equals to (apiaryId + 1)
        defaultHiveShouldNotBeFound("apiaryId.equals=" + (apiaryId + 1));
    }

    @Test
    @Transactional
    void getAllHivesByFrameIsEqualToSomething() throws Exception {
        Frame frame;
        if (TestUtil.findAll(em, Frame.class).isEmpty()) {
            hiveRepository.saveAndFlush(hive);
            frame = FrameResourceIT.createEntity(em);
        } else {
            frame = TestUtil.findAll(em, Frame.class).get(0);
        }
        em.persist(frame);
        em.flush();
        hive.addFrame(frame);
        hiveRepository.saveAndFlush(hive);
        Long frameId = frame.getId();

        // Get all the hiveList where frame equals to frameId
        defaultHiveShouldBeFound("frameId.equals=" + frameId);

        // Get all the hiveList where frame equals to (frameId + 1)
        defaultHiveShouldNotBeFound("frameId.equals=" + (frameId + 1));
    }

    @Test
    @Transactional
    void getAllHivesByUnfoldingCreatedHiveIsEqualToSomething() throws Exception {
        Unfolding unfoldingCreatedHive;
        if (TestUtil.findAll(em, Unfolding.class).isEmpty()) {
            hiveRepository.saveAndFlush(hive);
            unfoldingCreatedHive = UnfoldingResourceIT.createEntity(em);
        } else {
            unfoldingCreatedHive = TestUtil.findAll(em, Unfolding.class).get(0);
        }
        em.persist(unfoldingCreatedHive);
        em.flush();
        hive.setUnfoldingCreatedHive(unfoldingCreatedHive);
        unfoldingCreatedHive.setCreatedHive(hive);
        hiveRepository.saveAndFlush(hive);
        Long unfoldingCreatedHiveId = unfoldingCreatedHive.getId();

        // Get all the hiveList where unfoldingCreatedHive equals to unfoldingCreatedHiveId
        defaultHiveShouldBeFound("unfoldingCreatedHiveId.equals=" + unfoldingCreatedHiveId);

        // Get all the hiveList where unfoldingCreatedHive equals to (unfoldingCreatedHiveId + 1)
        defaultHiveShouldNotBeFound("unfoldingCreatedHiveId.equals=" + (unfoldingCreatedHiveId + 1));
    }

    @Test
    @Transactional
    void getAllHivesByCrestIsEqualToSomething() throws Exception {
        Crest crest;
        if (TestUtil.findAll(em, Crest.class).isEmpty()) {
            hiveRepository.saveAndFlush(hive);
            crest = CrestResourceIT.createEntity(em);
        } else {
            crest = TestUtil.findAll(em, Crest.class).get(0);
        }
        em.persist(crest);
        em.flush();
        hive.addCrest(crest);
        hiveRepository.saveAndFlush(hive);
        Long crestId = crest.getId();

        // Get all the hiveList where crest equals to crestId
        defaultHiveShouldBeFound("crestId.equals=" + crestId);

        // Get all the hiveList where crest equals to (crestId + 1)
        defaultHiveShouldNotBeFound("crestId.equals=" + (crestId + 1));
    }

    @Test
    @Transactional
    void getAllHivesByUnfoldingIsEqualToSomething() throws Exception {
        Unfolding unfolding;
        if (TestUtil.findAll(em, Unfolding.class).isEmpty()) {
            hiveRepository.saveAndFlush(hive);
            unfolding = UnfoldingResourceIT.createEntity(em);
        } else {
            unfolding = TestUtil.findAll(em, Unfolding.class).get(0);
        }
        em.persist(unfolding);
        em.flush();
        hive.addUnfolding(unfolding);
        hiveRepository.saveAndFlush(hive);
        Long unfoldingId = unfolding.getId();

        // Get all the hiveList where unfolding equals to unfoldingId
        defaultHiveShouldBeFound("unfoldingId.equals=" + unfoldingId);

        // Get all the hiveList where unfolding equals to (unfoldingId + 1)
        defaultHiveShouldNotBeFound("unfoldingId.equals=" + (unfoldingId + 1));
    }

    @Test
    @Transactional
    void getAllHivesByInspectionIsEqualToSomething() throws Exception {
        Inspection inspection;
        if (TestUtil.findAll(em, Inspection.class).isEmpty()) {
            hiveRepository.saveAndFlush(hive);
            inspection = InspectionResourceIT.createEntity(em);
        } else {
            inspection = TestUtil.findAll(em, Inspection.class).get(0);
        }
        em.persist(inspection);
        em.flush();
        hive.addInspection(inspection);
        hiveRepository.saveAndFlush(hive);
        Long inspectionId = inspection.getId();

        // Get all the hiveList where inspection equals to inspectionId
        defaultHiveShouldBeFound("inspectionId.equals=" + inspectionId);

        // Get all the hiveList where inspection equals to (inspectionId + 1)
        defaultHiveShouldNotBeFound("inspectionId.equals=" + (inspectionId + 1));
    }

    @Test
    @Transactional
    void getAllHivesByTranshumanceRequestIsEqualToSomething() throws Exception {
        TranshumanceRequest transhumanceRequest;
        if (TestUtil.findAll(em, TranshumanceRequest.class).isEmpty()) {
            hiveRepository.saveAndFlush(hive);
            transhumanceRequest = TranshumanceRequestResourceIT.createEntity(em);
        } else {
            transhumanceRequest = TestUtil.findAll(em, TranshumanceRequest.class).get(0);
        }
        em.persist(transhumanceRequest);
        em.flush();
        hive.addTranshumanceRequest(transhumanceRequest);
        hiveRepository.saveAndFlush(hive);
        Long transhumanceRequestId = transhumanceRequest.getId();

        // Get all the hiveList where transhumanceRequest equals to transhumanceRequestId
        defaultHiveShouldBeFound("transhumanceRequestId.equals=" + transhumanceRequestId);

        // Get all the hiveList where transhumanceRequest equals to (transhumanceRequestId + 1)
        defaultHiveShouldNotBeFound("transhumanceRequestId.equals=" + (transhumanceRequestId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultHiveShouldBeFound(String filter) throws Exception {
        restHiveMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(hive.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)));

        // Check, that the count call also returns 1
        restHiveMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultHiveShouldNotBeFound(String filter) throws Exception {
        restHiveMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restHiveMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingHive() throws Exception {
        // Get the hive
        restHiveMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingHive() throws Exception {
        // Initialize the database
        hiveRepository.saveAndFlush(hive);

        int databaseSizeBeforeUpdate = hiveRepository.findAll().size();

        // Update the hive
        Hive updatedHive = hiveRepository.findById(hive.getId()).get();
        // Disconnect from session so that the updates on updatedHive are not directly saved in db
        em.detach(updatedHive);
        updatedHive.code(UPDATED_CODE);
        HiveDTO hiveDTO = hiveMapper.toDto(updatedHive);

        restHiveMockMvc
            .perform(
                put(ENTITY_API_URL_ID, hiveDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(hiveDTO))
            )
            .andExpect(status().isOk());

        // Validate the Hive in the database
        List<Hive> hiveList = hiveRepository.findAll();
        assertThat(hiveList).hasSize(databaseSizeBeforeUpdate);
        Hive testHive = hiveList.get(hiveList.size() - 1);
        assertThat(testHive.getCode()).isEqualTo(UPDATED_CODE);
    }

    @Test
    @Transactional
    void putNonExistingHive() throws Exception {
        int databaseSizeBeforeUpdate = hiveRepository.findAll().size();
        hive.setId(count.incrementAndGet());

        // Create the Hive
        HiveDTO hiveDTO = hiveMapper.toDto(hive);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHiveMockMvc
            .perform(
                put(ENTITY_API_URL_ID, hiveDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(hiveDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Hive in the database
        List<Hive> hiveList = hiveRepository.findAll();
        assertThat(hiveList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchHive() throws Exception {
        int databaseSizeBeforeUpdate = hiveRepository.findAll().size();
        hive.setId(count.incrementAndGet());

        // Create the Hive
        HiveDTO hiveDTO = hiveMapper.toDto(hive);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHiveMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(hiveDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Hive in the database
        List<Hive> hiveList = hiveRepository.findAll();
        assertThat(hiveList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamHive() throws Exception {
        int databaseSizeBeforeUpdate = hiveRepository.findAll().size();
        hive.setId(count.incrementAndGet());

        // Create the Hive
        HiveDTO hiveDTO = hiveMapper.toDto(hive);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHiveMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(hiveDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Hive in the database
        List<Hive> hiveList = hiveRepository.findAll();
        assertThat(hiveList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateHiveWithPatch() throws Exception {
        // Initialize the database
        hiveRepository.saveAndFlush(hive);

        int databaseSizeBeforeUpdate = hiveRepository.findAll().size();

        // Update the hive using partial update
        Hive partialUpdatedHive = new Hive();
        partialUpdatedHive.setId(hive.getId());

        partialUpdatedHive.code(UPDATED_CODE);

        restHiveMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedHive.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedHive))
            )
            .andExpect(status().isOk());

        // Validate the Hive in the database
        List<Hive> hiveList = hiveRepository.findAll();
        assertThat(hiveList).hasSize(databaseSizeBeforeUpdate);
        Hive testHive = hiveList.get(hiveList.size() - 1);
        assertThat(testHive.getCode()).isEqualTo(UPDATED_CODE);
    }

    @Test
    @Transactional
    void fullUpdateHiveWithPatch() throws Exception {
        // Initialize the database
        hiveRepository.saveAndFlush(hive);

        int databaseSizeBeforeUpdate = hiveRepository.findAll().size();

        // Update the hive using partial update
        Hive partialUpdatedHive = new Hive();
        partialUpdatedHive.setId(hive.getId());

        partialUpdatedHive.code(UPDATED_CODE);

        restHiveMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedHive.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedHive))
            )
            .andExpect(status().isOk());

        // Validate the Hive in the database
        List<Hive> hiveList = hiveRepository.findAll();
        assertThat(hiveList).hasSize(databaseSizeBeforeUpdate);
        Hive testHive = hiveList.get(hiveList.size() - 1);
        assertThat(testHive.getCode()).isEqualTo(UPDATED_CODE);
    }

    @Test
    @Transactional
    void patchNonExistingHive() throws Exception {
        int databaseSizeBeforeUpdate = hiveRepository.findAll().size();
        hive.setId(count.incrementAndGet());

        // Create the Hive
        HiveDTO hiveDTO = hiveMapper.toDto(hive);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHiveMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, hiveDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(hiveDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Hive in the database
        List<Hive> hiveList = hiveRepository.findAll();
        assertThat(hiveList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchHive() throws Exception {
        int databaseSizeBeforeUpdate = hiveRepository.findAll().size();
        hive.setId(count.incrementAndGet());

        // Create the Hive
        HiveDTO hiveDTO = hiveMapper.toDto(hive);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHiveMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(hiveDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Hive in the database
        List<Hive> hiveList = hiveRepository.findAll();
        assertThat(hiveList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamHive() throws Exception {
        int databaseSizeBeforeUpdate = hiveRepository.findAll().size();
        hive.setId(count.incrementAndGet());

        // Create the Hive
        HiveDTO hiveDTO = hiveMapper.toDto(hive);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHiveMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(hiveDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Hive in the database
        List<Hive> hiveList = hiveRepository.findAll();
        assertThat(hiveList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteHive() throws Exception {
        // Initialize the database
        hiveRepository.saveAndFlush(hive);

        int databaseSizeBeforeDelete = hiveRepository.findAll().size();

        // Delete the hive
        restHiveMockMvc
            .perform(delete(ENTITY_API_URL_ID, hive.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Hive> hiveList = hiveRepository.findAll();
        assertThat(hiveList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
