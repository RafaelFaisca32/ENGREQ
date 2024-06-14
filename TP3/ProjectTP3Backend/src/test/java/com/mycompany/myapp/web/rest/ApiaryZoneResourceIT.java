package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.Apiary;
import com.mycompany.myapp.domain.ApiaryZone;
import com.mycompany.myapp.domain.enumeration.ApiaryZoneState;
import com.mycompany.myapp.repository.ApiaryZoneRepository;
import com.mycompany.myapp.service.criteria.ApiaryZoneCriteria;
import com.mycompany.myapp.service.dto.ApiaryZoneDTO;
import com.mycompany.myapp.service.mapper.ApiaryZoneMapper;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ApiaryZoneResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ApiaryZoneResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Boolean DEFAULT_CONTROLLED_ZONE = false;
    private static final Boolean UPDATED_CONTROLLED_ZONE = true;

    private static final ApiaryZoneState DEFAULT_STATE = ApiaryZoneState.REGISTERED;
    private static final ApiaryZoneState UPDATED_STATE = ApiaryZoneState.ANNULLED;

    private static final String ENTITY_API_URL = "/api/apiary-zones";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ApiaryZoneRepository apiaryZoneRepository;

    @Autowired
    private ApiaryZoneMapper apiaryZoneMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restApiaryZoneMockMvc;

    private ApiaryZone apiaryZone;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ApiaryZone createEntity(EntityManager em) {
        ApiaryZone apiaryZone = new ApiaryZone().name(DEFAULT_NAME).controlledZone(DEFAULT_CONTROLLED_ZONE).state(DEFAULT_STATE);
        return apiaryZone;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ApiaryZone createUpdatedEntity(EntityManager em) {
        ApiaryZone apiaryZone = new ApiaryZone().name(UPDATED_NAME).controlledZone(UPDATED_CONTROLLED_ZONE).state(UPDATED_STATE);
        return apiaryZone;
    }

    @BeforeEach
    public void initTest() {
        apiaryZone = createEntity(em);
    }

    @Test
    @Transactional
    void createApiaryZone() throws Exception {
        int databaseSizeBeforeCreate = apiaryZoneRepository.findAll().size();
        // Create the ApiaryZone
        ApiaryZoneDTO apiaryZoneDTO = apiaryZoneMapper.toDto(apiaryZone);
        restApiaryZoneMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(apiaryZoneDTO)))
            .andExpect(status().isCreated());

        // Validate the ApiaryZone in the database
        List<ApiaryZone> apiaryZoneList = apiaryZoneRepository.findAll();
        assertThat(apiaryZoneList).hasSize(databaseSizeBeforeCreate + 1);
        ApiaryZone testApiaryZone = apiaryZoneList.get(apiaryZoneList.size() - 1);
        assertThat(testApiaryZone.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testApiaryZone.getControlledZone()).isEqualTo(DEFAULT_CONTROLLED_ZONE);
        assertThat(testApiaryZone.getState()).isEqualTo(DEFAULT_STATE);
    }

    @Test
    @Transactional
    void createApiaryZoneWithExistingId() throws Exception {
        // Create the ApiaryZone with an existing ID
        apiaryZone.setId(1L);
        ApiaryZoneDTO apiaryZoneDTO = apiaryZoneMapper.toDto(apiaryZone);

        int databaseSizeBeforeCreate = apiaryZoneRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restApiaryZoneMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(apiaryZoneDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ApiaryZone in the database
        List<ApiaryZone> apiaryZoneList = apiaryZoneRepository.findAll();
        assertThat(apiaryZoneList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = apiaryZoneRepository.findAll().size();
        // set the field null
        apiaryZone.setName(null);

        // Create the ApiaryZone, which fails.
        ApiaryZoneDTO apiaryZoneDTO = apiaryZoneMapper.toDto(apiaryZone);

        restApiaryZoneMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(apiaryZoneDTO)))
            .andExpect(status().isBadRequest());

        List<ApiaryZone> apiaryZoneList = apiaryZoneRepository.findAll();
        assertThat(apiaryZoneList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkControlledZoneIsRequired() throws Exception {
        int databaseSizeBeforeTest = apiaryZoneRepository.findAll().size();
        // set the field null
        apiaryZone.setControlledZone(null);

        // Create the ApiaryZone, which fails.
        ApiaryZoneDTO apiaryZoneDTO = apiaryZoneMapper.toDto(apiaryZone);

        restApiaryZoneMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(apiaryZoneDTO)))
            .andExpect(status().isBadRequest());

        List<ApiaryZone> apiaryZoneList = apiaryZoneRepository.findAll();
        assertThat(apiaryZoneList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkStateIsRequired() throws Exception {
        int databaseSizeBeforeTest = apiaryZoneRepository.findAll().size();
        // set the field null
        apiaryZone.setState(null);

        // Create the ApiaryZone, which fails.
        ApiaryZoneDTO apiaryZoneDTO = apiaryZoneMapper.toDto(apiaryZone);

        restApiaryZoneMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(apiaryZoneDTO)))
            .andExpect(status().isBadRequest());

        List<ApiaryZone> apiaryZoneList = apiaryZoneRepository.findAll();
        assertThat(apiaryZoneList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllApiaryZones() throws Exception {
        // Initialize the database
        apiaryZoneRepository.saveAndFlush(apiaryZone);

        // Get all the apiaryZoneList
        restApiaryZoneMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(apiaryZone.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].controlledZone").value(hasItem(DEFAULT_CONTROLLED_ZONE.booleanValue())))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE.toString())));
    }

    @Test
    @Transactional
    void getApiaryZone() throws Exception {
        // Initialize the database
        apiaryZoneRepository.saveAndFlush(apiaryZone);

        // Get the apiaryZone
        restApiaryZoneMockMvc
            .perform(get(ENTITY_API_URL_ID, apiaryZone.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(apiaryZone.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.controlledZone").value(DEFAULT_CONTROLLED_ZONE.booleanValue()))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE.toString()));
    }

    @Test
    @Transactional
    void getApiaryZonesByIdFiltering() throws Exception {
        // Initialize the database
        apiaryZoneRepository.saveAndFlush(apiaryZone);

        Long id = apiaryZone.getId();

        defaultApiaryZoneShouldBeFound("id.equals=" + id);
        defaultApiaryZoneShouldNotBeFound("id.notEquals=" + id);

        defaultApiaryZoneShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultApiaryZoneShouldNotBeFound("id.greaterThan=" + id);

        defaultApiaryZoneShouldBeFound("id.lessThanOrEqual=" + id);
        defaultApiaryZoneShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllApiaryZonesByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        apiaryZoneRepository.saveAndFlush(apiaryZone);

        // Get all the apiaryZoneList where name equals to DEFAULT_NAME
        defaultApiaryZoneShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the apiaryZoneList where name equals to UPDATED_NAME
        defaultApiaryZoneShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllApiaryZonesByNameIsInShouldWork() throws Exception {
        // Initialize the database
        apiaryZoneRepository.saveAndFlush(apiaryZone);

        // Get all the apiaryZoneList where name in DEFAULT_NAME or UPDATED_NAME
        defaultApiaryZoneShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the apiaryZoneList where name equals to UPDATED_NAME
        defaultApiaryZoneShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllApiaryZonesByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        apiaryZoneRepository.saveAndFlush(apiaryZone);

        // Get all the apiaryZoneList where name is not null
        defaultApiaryZoneShouldBeFound("name.specified=true");

        // Get all the apiaryZoneList where name is null
        defaultApiaryZoneShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    void getAllApiaryZonesByNameContainsSomething() throws Exception {
        // Initialize the database
        apiaryZoneRepository.saveAndFlush(apiaryZone);

        // Get all the apiaryZoneList where name contains DEFAULT_NAME
        defaultApiaryZoneShouldBeFound("name.contains=" + DEFAULT_NAME);

        // Get all the apiaryZoneList where name contains UPDATED_NAME
        defaultApiaryZoneShouldNotBeFound("name.contains=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllApiaryZonesByNameNotContainsSomething() throws Exception {
        // Initialize the database
        apiaryZoneRepository.saveAndFlush(apiaryZone);

        // Get all the apiaryZoneList where name does not contain DEFAULT_NAME
        defaultApiaryZoneShouldNotBeFound("name.doesNotContain=" + DEFAULT_NAME);

        // Get all the apiaryZoneList where name does not contain UPDATED_NAME
        defaultApiaryZoneShouldBeFound("name.doesNotContain=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllApiaryZonesByControlledZoneIsEqualToSomething() throws Exception {
        // Initialize the database
        apiaryZoneRepository.saveAndFlush(apiaryZone);

        // Get all the apiaryZoneList where controlledZone equals to DEFAULT_CONTROLLED_ZONE
        defaultApiaryZoneShouldBeFound("controlledZone.equals=" + DEFAULT_CONTROLLED_ZONE);

        // Get all the apiaryZoneList where controlledZone equals to UPDATED_CONTROLLED_ZONE
        defaultApiaryZoneShouldNotBeFound("controlledZone.equals=" + UPDATED_CONTROLLED_ZONE);
    }

    @Test
    @Transactional
    void getAllApiaryZonesByControlledZoneIsInShouldWork() throws Exception {
        // Initialize the database
        apiaryZoneRepository.saveAndFlush(apiaryZone);

        // Get all the apiaryZoneList where controlledZone in DEFAULT_CONTROLLED_ZONE or UPDATED_CONTROLLED_ZONE
        defaultApiaryZoneShouldBeFound("controlledZone.in=" + DEFAULT_CONTROLLED_ZONE + "," + UPDATED_CONTROLLED_ZONE);

        // Get all the apiaryZoneList where controlledZone equals to UPDATED_CONTROLLED_ZONE
        defaultApiaryZoneShouldNotBeFound("controlledZone.in=" + UPDATED_CONTROLLED_ZONE);
    }

    @Test
    @Transactional
    void getAllApiaryZonesByControlledZoneIsNullOrNotNull() throws Exception {
        // Initialize the database
        apiaryZoneRepository.saveAndFlush(apiaryZone);

        // Get all the apiaryZoneList where controlledZone is not null
        defaultApiaryZoneShouldBeFound("controlledZone.specified=true");

        // Get all the apiaryZoneList where controlledZone is null
        defaultApiaryZoneShouldNotBeFound("controlledZone.specified=false");
    }

    @Test
    @Transactional
    void getAllApiaryZonesByStateIsEqualToSomething() throws Exception {
        // Initialize the database
        apiaryZoneRepository.saveAndFlush(apiaryZone);

        // Get all the apiaryZoneList where state equals to DEFAULT_STATE
        defaultApiaryZoneShouldBeFound("state.equals=" + DEFAULT_STATE);

        // Get all the apiaryZoneList where state equals to UPDATED_STATE
        defaultApiaryZoneShouldNotBeFound("state.equals=" + UPDATED_STATE);
    }

    @Test
    @Transactional
    void getAllApiaryZonesByStateIsInShouldWork() throws Exception {
        // Initialize the database
        apiaryZoneRepository.saveAndFlush(apiaryZone);

        // Get all the apiaryZoneList where state in DEFAULT_STATE or UPDATED_STATE
        defaultApiaryZoneShouldBeFound("state.in=" + DEFAULT_STATE + "," + UPDATED_STATE);

        // Get all the apiaryZoneList where state equals to UPDATED_STATE
        defaultApiaryZoneShouldNotBeFound("state.in=" + UPDATED_STATE);
    }

    @Test
    @Transactional
    void getAllApiaryZonesByStateIsNullOrNotNull() throws Exception {
        // Initialize the database
        apiaryZoneRepository.saveAndFlush(apiaryZone);

        // Get all the apiaryZoneList where state is not null
        defaultApiaryZoneShouldBeFound("state.specified=true");

        // Get all the apiaryZoneList where state is null
        defaultApiaryZoneShouldNotBeFound("state.specified=false");
    }

    @Test
    @Transactional
    void getAllApiaryZonesByApiaryIsEqualToSomething() throws Exception {
        Apiary apiary;
        if (TestUtil.findAll(em, Apiary.class).isEmpty()) {
            apiaryZoneRepository.saveAndFlush(apiaryZone);
            apiary = ApiaryResourceIT.createEntity(em);
        } else {
            apiary = TestUtil.findAll(em, Apiary.class).get(0);
        }
        em.persist(apiary);
        em.flush();
        apiaryZone.addApiary(apiary);
        apiaryZoneRepository.saveAndFlush(apiaryZone);
        Long apiaryId = apiary.getId();

        // Get all the apiaryZoneList where apiary equals to apiaryId
        defaultApiaryZoneShouldBeFound("apiaryId.equals=" + apiaryId);

        // Get all the apiaryZoneList where apiary equals to (apiaryId + 1)
        defaultApiaryZoneShouldNotBeFound("apiaryId.equals=" + (apiaryId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultApiaryZoneShouldBeFound(String filter) throws Exception {
        restApiaryZoneMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(apiaryZone.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].controlledZone").value(hasItem(DEFAULT_CONTROLLED_ZONE.booleanValue())))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE.toString())));

        // Check, that the count call also returns 1
        restApiaryZoneMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultApiaryZoneShouldNotBeFound(String filter) throws Exception {
        restApiaryZoneMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restApiaryZoneMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingApiaryZone() throws Exception {
        // Get the apiaryZone
        restApiaryZoneMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingApiaryZone() throws Exception {
        // Initialize the database
        apiaryZoneRepository.saveAndFlush(apiaryZone);

        int databaseSizeBeforeUpdate = apiaryZoneRepository.findAll().size();

        // Update the apiaryZone
        ApiaryZone updatedApiaryZone = apiaryZoneRepository.findById(apiaryZone.getId()).get();
        // Disconnect from session so that the updates on updatedApiaryZone are not directly saved in db
        em.detach(updatedApiaryZone);
        updatedApiaryZone.name(UPDATED_NAME).controlledZone(UPDATED_CONTROLLED_ZONE).state(UPDATED_STATE);
        ApiaryZoneDTO apiaryZoneDTO = apiaryZoneMapper.toDto(updatedApiaryZone);

        restApiaryZoneMockMvc
            .perform(
                put(ENTITY_API_URL_ID, apiaryZoneDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(apiaryZoneDTO))
            )
            .andExpect(status().isOk());

        // Validate the ApiaryZone in the database
        List<ApiaryZone> apiaryZoneList = apiaryZoneRepository.findAll();
        assertThat(apiaryZoneList).hasSize(databaseSizeBeforeUpdate);
        ApiaryZone testApiaryZone = apiaryZoneList.get(apiaryZoneList.size() - 1);
        assertThat(testApiaryZone.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testApiaryZone.getControlledZone()).isEqualTo(UPDATED_CONTROLLED_ZONE);
        assertThat(testApiaryZone.getState()).isEqualTo(UPDATED_STATE);
    }

    @Test
    @Transactional
    void putNonExistingApiaryZone() throws Exception {
        int databaseSizeBeforeUpdate = apiaryZoneRepository.findAll().size();
        apiaryZone.setId(count.incrementAndGet());

        // Create the ApiaryZone
        ApiaryZoneDTO apiaryZoneDTO = apiaryZoneMapper.toDto(apiaryZone);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restApiaryZoneMockMvc
            .perform(
                put(ENTITY_API_URL_ID, apiaryZoneDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(apiaryZoneDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ApiaryZone in the database
        List<ApiaryZone> apiaryZoneList = apiaryZoneRepository.findAll();
        assertThat(apiaryZoneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchApiaryZone() throws Exception {
        int databaseSizeBeforeUpdate = apiaryZoneRepository.findAll().size();
        apiaryZone.setId(count.incrementAndGet());

        // Create the ApiaryZone
        ApiaryZoneDTO apiaryZoneDTO = apiaryZoneMapper.toDto(apiaryZone);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restApiaryZoneMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(apiaryZoneDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ApiaryZone in the database
        List<ApiaryZone> apiaryZoneList = apiaryZoneRepository.findAll();
        assertThat(apiaryZoneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamApiaryZone() throws Exception {
        int databaseSizeBeforeUpdate = apiaryZoneRepository.findAll().size();
        apiaryZone.setId(count.incrementAndGet());

        // Create the ApiaryZone
        ApiaryZoneDTO apiaryZoneDTO = apiaryZoneMapper.toDto(apiaryZone);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restApiaryZoneMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(apiaryZoneDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ApiaryZone in the database
        List<ApiaryZone> apiaryZoneList = apiaryZoneRepository.findAll();
        assertThat(apiaryZoneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateApiaryZoneWithPatch() throws Exception {
        // Initialize the database
        apiaryZoneRepository.saveAndFlush(apiaryZone);

        int databaseSizeBeforeUpdate = apiaryZoneRepository.findAll().size();

        // Update the apiaryZone using partial update
        ApiaryZone partialUpdatedApiaryZone = new ApiaryZone();
        partialUpdatedApiaryZone.setId(apiaryZone.getId());

        partialUpdatedApiaryZone.name(UPDATED_NAME).controlledZone(UPDATED_CONTROLLED_ZONE).state(UPDATED_STATE);

        restApiaryZoneMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedApiaryZone.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedApiaryZone))
            )
            .andExpect(status().isOk());

        // Validate the ApiaryZone in the database
        List<ApiaryZone> apiaryZoneList = apiaryZoneRepository.findAll();
        assertThat(apiaryZoneList).hasSize(databaseSizeBeforeUpdate);
        ApiaryZone testApiaryZone = apiaryZoneList.get(apiaryZoneList.size() - 1);
        assertThat(testApiaryZone.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testApiaryZone.getControlledZone()).isEqualTo(UPDATED_CONTROLLED_ZONE);
        assertThat(testApiaryZone.getState()).isEqualTo(UPDATED_STATE);
    }

    @Test
    @Transactional
    void fullUpdateApiaryZoneWithPatch() throws Exception {
        // Initialize the database
        apiaryZoneRepository.saveAndFlush(apiaryZone);

        int databaseSizeBeforeUpdate = apiaryZoneRepository.findAll().size();

        // Update the apiaryZone using partial update
        ApiaryZone partialUpdatedApiaryZone = new ApiaryZone();
        partialUpdatedApiaryZone.setId(apiaryZone.getId());

        partialUpdatedApiaryZone.name(UPDATED_NAME).controlledZone(UPDATED_CONTROLLED_ZONE).state(UPDATED_STATE);

        restApiaryZoneMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedApiaryZone.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedApiaryZone))
            )
            .andExpect(status().isOk());

        // Validate the ApiaryZone in the database
        List<ApiaryZone> apiaryZoneList = apiaryZoneRepository.findAll();
        assertThat(apiaryZoneList).hasSize(databaseSizeBeforeUpdate);
        ApiaryZone testApiaryZone = apiaryZoneList.get(apiaryZoneList.size() - 1);
        assertThat(testApiaryZone.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testApiaryZone.getControlledZone()).isEqualTo(UPDATED_CONTROLLED_ZONE);
        assertThat(testApiaryZone.getState()).isEqualTo(UPDATED_STATE);
    }

    @Test
    @Transactional
    void patchNonExistingApiaryZone() throws Exception {
        int databaseSizeBeforeUpdate = apiaryZoneRepository.findAll().size();
        apiaryZone.setId(count.incrementAndGet());

        // Create the ApiaryZone
        ApiaryZoneDTO apiaryZoneDTO = apiaryZoneMapper.toDto(apiaryZone);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restApiaryZoneMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, apiaryZoneDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(apiaryZoneDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ApiaryZone in the database
        List<ApiaryZone> apiaryZoneList = apiaryZoneRepository.findAll();
        assertThat(apiaryZoneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchApiaryZone() throws Exception {
        int databaseSizeBeforeUpdate = apiaryZoneRepository.findAll().size();
        apiaryZone.setId(count.incrementAndGet());

        // Create the ApiaryZone
        ApiaryZoneDTO apiaryZoneDTO = apiaryZoneMapper.toDto(apiaryZone);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restApiaryZoneMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(apiaryZoneDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ApiaryZone in the database
        List<ApiaryZone> apiaryZoneList = apiaryZoneRepository.findAll();
        assertThat(apiaryZoneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamApiaryZone() throws Exception {
        int databaseSizeBeforeUpdate = apiaryZoneRepository.findAll().size();
        apiaryZone.setId(count.incrementAndGet());

        // Create the ApiaryZone
        ApiaryZoneDTO apiaryZoneDTO = apiaryZoneMapper.toDto(apiaryZone);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restApiaryZoneMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(apiaryZoneDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ApiaryZone in the database
        List<ApiaryZone> apiaryZoneList = apiaryZoneRepository.findAll();
        assertThat(apiaryZoneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteApiaryZone() throws Exception {
        // Initialize the database
        apiaryZoneRepository.saveAndFlush(apiaryZone);

        int databaseSizeBeforeDelete = apiaryZoneRepository.findAll().size();

        // Delete the apiaryZone
        restApiaryZoneMockMvc
            .perform(delete(ENTITY_API_URL_ID, apiaryZone.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ApiaryZone> apiaryZoneList = apiaryZoneRepository.findAll();
        assertThat(apiaryZoneList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
