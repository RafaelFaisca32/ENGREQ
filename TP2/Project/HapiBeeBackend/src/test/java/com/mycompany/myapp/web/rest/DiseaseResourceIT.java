package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.Disease;
import com.mycompany.myapp.domain.Inspection;
import com.mycompany.myapp.repository.DiseaseRepository;
import com.mycompany.myapp.service.criteria.DiseaseCriteria;
import com.mycompany.myapp.service.dto.DiseaseDTO;
import com.mycompany.myapp.service.mapper.DiseaseMapper;
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
 * Integration tests for the {@link DiseaseResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DiseaseResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/diseases";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private DiseaseRepository diseaseRepository;

    @Autowired
    private DiseaseMapper diseaseMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDiseaseMockMvc;

    private Disease disease;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Disease createEntity(EntityManager em) {
        Disease disease = new Disease().name(DEFAULT_NAME).description(DEFAULT_DESCRIPTION);
        return disease;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Disease createUpdatedEntity(EntityManager em) {
        Disease disease = new Disease().name(UPDATED_NAME).description(UPDATED_DESCRIPTION);
        return disease;
    }

    @BeforeEach
    public void initTest() {
        disease = createEntity(em);
    }

    @Test
    @Transactional
    void createDisease() throws Exception {
        int databaseSizeBeforeCreate = diseaseRepository.findAll().size();
        // Create the Disease
        DiseaseDTO diseaseDTO = diseaseMapper.toDto(disease);
        restDiseaseMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(diseaseDTO)))
            .andExpect(status().isCreated());

        // Validate the Disease in the database
        List<Disease> diseaseList = diseaseRepository.findAll();
        assertThat(diseaseList).hasSize(databaseSizeBeforeCreate + 1);
        Disease testDisease = diseaseList.get(diseaseList.size() - 1);
        assertThat(testDisease.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testDisease.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    void createDiseaseWithExistingId() throws Exception {
        // Create the Disease with an existing ID
        disease.setId(1L);
        DiseaseDTO diseaseDTO = diseaseMapper.toDto(disease);

        int databaseSizeBeforeCreate = diseaseRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDiseaseMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(diseaseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Disease in the database
        List<Disease> diseaseList = diseaseRepository.findAll();
        assertThat(diseaseList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDiseases() throws Exception {
        // Initialize the database
        diseaseRepository.saveAndFlush(disease);

        // Get all the diseaseList
        restDiseaseMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(disease.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }

    @Test
    @Transactional
    void getDisease() throws Exception {
        // Initialize the database
        diseaseRepository.saveAndFlush(disease);

        // Get the disease
        restDiseaseMockMvc
            .perform(get(ENTITY_API_URL_ID, disease.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(disease.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }

    @Test
    @Transactional
    void getDiseasesByIdFiltering() throws Exception {
        // Initialize the database
        diseaseRepository.saveAndFlush(disease);

        Long id = disease.getId();

        defaultDiseaseShouldBeFound("id.equals=" + id);
        defaultDiseaseShouldNotBeFound("id.notEquals=" + id);

        defaultDiseaseShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDiseaseShouldNotBeFound("id.greaterThan=" + id);

        defaultDiseaseShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDiseaseShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllDiseasesByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        diseaseRepository.saveAndFlush(disease);

        // Get all the diseaseList where name equals to DEFAULT_NAME
        defaultDiseaseShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the diseaseList where name equals to UPDATED_NAME
        defaultDiseaseShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllDiseasesByNameIsInShouldWork() throws Exception {
        // Initialize the database
        diseaseRepository.saveAndFlush(disease);

        // Get all the diseaseList where name in DEFAULT_NAME or UPDATED_NAME
        defaultDiseaseShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the diseaseList where name equals to UPDATED_NAME
        defaultDiseaseShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllDiseasesByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        diseaseRepository.saveAndFlush(disease);

        // Get all the diseaseList where name is not null
        defaultDiseaseShouldBeFound("name.specified=true");

        // Get all the diseaseList where name is null
        defaultDiseaseShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    void getAllDiseasesByNameContainsSomething() throws Exception {
        // Initialize the database
        diseaseRepository.saveAndFlush(disease);

        // Get all the diseaseList where name contains DEFAULT_NAME
        defaultDiseaseShouldBeFound("name.contains=" + DEFAULT_NAME);

        // Get all the diseaseList where name contains UPDATED_NAME
        defaultDiseaseShouldNotBeFound("name.contains=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllDiseasesByNameNotContainsSomething() throws Exception {
        // Initialize the database
        diseaseRepository.saveAndFlush(disease);

        // Get all the diseaseList where name does not contain DEFAULT_NAME
        defaultDiseaseShouldNotBeFound("name.doesNotContain=" + DEFAULT_NAME);

        // Get all the diseaseList where name does not contain UPDATED_NAME
        defaultDiseaseShouldBeFound("name.doesNotContain=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllDiseasesByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        diseaseRepository.saveAndFlush(disease);

        // Get all the diseaseList where description equals to DEFAULT_DESCRIPTION
        defaultDiseaseShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the diseaseList where description equals to UPDATED_DESCRIPTION
        defaultDiseaseShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    void getAllDiseasesByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        diseaseRepository.saveAndFlush(disease);

        // Get all the diseaseList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultDiseaseShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the diseaseList where description equals to UPDATED_DESCRIPTION
        defaultDiseaseShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    void getAllDiseasesByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        diseaseRepository.saveAndFlush(disease);

        // Get all the diseaseList where description is not null
        defaultDiseaseShouldBeFound("description.specified=true");

        // Get all the diseaseList where description is null
        defaultDiseaseShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    void getAllDiseasesByDescriptionContainsSomething() throws Exception {
        // Initialize the database
        diseaseRepository.saveAndFlush(disease);

        // Get all the diseaseList where description contains DEFAULT_DESCRIPTION
        defaultDiseaseShouldBeFound("description.contains=" + DEFAULT_DESCRIPTION);

        // Get all the diseaseList where description contains UPDATED_DESCRIPTION
        defaultDiseaseShouldNotBeFound("description.contains=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    void getAllDiseasesByDescriptionNotContainsSomething() throws Exception {
        // Initialize the database
        diseaseRepository.saveAndFlush(disease);

        // Get all the diseaseList where description does not contain DEFAULT_DESCRIPTION
        defaultDiseaseShouldNotBeFound("description.doesNotContain=" + DEFAULT_DESCRIPTION);

        // Get all the diseaseList where description does not contain UPDATED_DESCRIPTION
        defaultDiseaseShouldBeFound("description.doesNotContain=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    void getAllDiseasesByInspectionIsEqualToSomething() throws Exception {
        Inspection inspection;
        if (TestUtil.findAll(em, Inspection.class).isEmpty()) {
            diseaseRepository.saveAndFlush(disease);
            inspection = InspectionResourceIT.createEntity(em);
        } else {
            inspection = TestUtil.findAll(em, Inspection.class).get(0);
        }
        em.persist(inspection);
        em.flush();
        disease.addInspection(inspection);
        diseaseRepository.saveAndFlush(disease);
        Long inspectionId = inspection.getId();

        // Get all the diseaseList where inspection equals to inspectionId
        defaultDiseaseShouldBeFound("inspectionId.equals=" + inspectionId);

        // Get all the diseaseList where inspection equals to (inspectionId + 1)
        defaultDiseaseShouldNotBeFound("inspectionId.equals=" + (inspectionId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDiseaseShouldBeFound(String filter) throws Exception {
        restDiseaseMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(disease.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));

        // Check, that the count call also returns 1
        restDiseaseMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDiseaseShouldNotBeFound(String filter) throws Exception {
        restDiseaseMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDiseaseMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingDisease() throws Exception {
        // Get the disease
        restDiseaseMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDisease() throws Exception {
        // Initialize the database
        diseaseRepository.saveAndFlush(disease);

        int databaseSizeBeforeUpdate = diseaseRepository.findAll().size();

        // Update the disease
        Disease updatedDisease = diseaseRepository.findById(disease.getId()).get();
        // Disconnect from session so that the updates on updatedDisease are not directly saved in db
        em.detach(updatedDisease);
        updatedDisease.name(UPDATED_NAME).description(UPDATED_DESCRIPTION);
        DiseaseDTO diseaseDTO = diseaseMapper.toDto(updatedDisease);

        restDiseaseMockMvc
            .perform(
                put(ENTITY_API_URL_ID, diseaseDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(diseaseDTO))
            )
            .andExpect(status().isOk());

        // Validate the Disease in the database
        List<Disease> diseaseList = diseaseRepository.findAll();
        assertThat(diseaseList).hasSize(databaseSizeBeforeUpdate);
        Disease testDisease = diseaseList.get(diseaseList.size() - 1);
        assertThat(testDisease.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testDisease.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    void putNonExistingDisease() throws Exception {
        int databaseSizeBeforeUpdate = diseaseRepository.findAll().size();
        disease.setId(count.incrementAndGet());

        // Create the Disease
        DiseaseDTO diseaseDTO = diseaseMapper.toDto(disease);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDiseaseMockMvc
            .perform(
                put(ENTITY_API_URL_ID, diseaseDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(diseaseDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Disease in the database
        List<Disease> diseaseList = diseaseRepository.findAll();
        assertThat(diseaseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDisease() throws Exception {
        int databaseSizeBeforeUpdate = diseaseRepository.findAll().size();
        disease.setId(count.incrementAndGet());

        // Create the Disease
        DiseaseDTO diseaseDTO = diseaseMapper.toDto(disease);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDiseaseMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(diseaseDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Disease in the database
        List<Disease> diseaseList = diseaseRepository.findAll();
        assertThat(diseaseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDisease() throws Exception {
        int databaseSizeBeforeUpdate = diseaseRepository.findAll().size();
        disease.setId(count.incrementAndGet());

        // Create the Disease
        DiseaseDTO diseaseDTO = diseaseMapper.toDto(disease);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDiseaseMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(diseaseDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Disease in the database
        List<Disease> diseaseList = diseaseRepository.findAll();
        assertThat(diseaseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDiseaseWithPatch() throws Exception {
        // Initialize the database
        diseaseRepository.saveAndFlush(disease);

        int databaseSizeBeforeUpdate = diseaseRepository.findAll().size();

        // Update the disease using partial update
        Disease partialUpdatedDisease = new Disease();
        partialUpdatedDisease.setId(disease.getId());

        restDiseaseMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDisease.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDisease))
            )
            .andExpect(status().isOk());

        // Validate the Disease in the database
        List<Disease> diseaseList = diseaseRepository.findAll();
        assertThat(diseaseList).hasSize(databaseSizeBeforeUpdate);
        Disease testDisease = diseaseList.get(diseaseList.size() - 1);
        assertThat(testDisease.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testDisease.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    void fullUpdateDiseaseWithPatch() throws Exception {
        // Initialize the database
        diseaseRepository.saveAndFlush(disease);

        int databaseSizeBeforeUpdate = diseaseRepository.findAll().size();

        // Update the disease using partial update
        Disease partialUpdatedDisease = new Disease();
        partialUpdatedDisease.setId(disease.getId());

        partialUpdatedDisease.name(UPDATED_NAME).description(UPDATED_DESCRIPTION);

        restDiseaseMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDisease.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDisease))
            )
            .andExpect(status().isOk());

        // Validate the Disease in the database
        List<Disease> diseaseList = diseaseRepository.findAll();
        assertThat(diseaseList).hasSize(databaseSizeBeforeUpdate);
        Disease testDisease = diseaseList.get(diseaseList.size() - 1);
        assertThat(testDisease.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testDisease.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    void patchNonExistingDisease() throws Exception {
        int databaseSizeBeforeUpdate = diseaseRepository.findAll().size();
        disease.setId(count.incrementAndGet());

        // Create the Disease
        DiseaseDTO diseaseDTO = diseaseMapper.toDto(disease);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDiseaseMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, diseaseDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(diseaseDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Disease in the database
        List<Disease> diseaseList = diseaseRepository.findAll();
        assertThat(diseaseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDisease() throws Exception {
        int databaseSizeBeforeUpdate = diseaseRepository.findAll().size();
        disease.setId(count.incrementAndGet());

        // Create the Disease
        DiseaseDTO diseaseDTO = diseaseMapper.toDto(disease);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDiseaseMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(diseaseDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Disease in the database
        List<Disease> diseaseList = diseaseRepository.findAll();
        assertThat(diseaseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDisease() throws Exception {
        int databaseSizeBeforeUpdate = diseaseRepository.findAll().size();
        disease.setId(count.incrementAndGet());

        // Create the Disease
        DiseaseDTO diseaseDTO = diseaseMapper.toDto(disease);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDiseaseMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(diseaseDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Disease in the database
        List<Disease> diseaseList = diseaseRepository.findAll();
        assertThat(diseaseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDisease() throws Exception {
        // Initialize the database
        diseaseRepository.saveAndFlush(disease);

        int databaseSizeBeforeDelete = diseaseRepository.findAll().size();

        // Delete the disease
        restDiseaseMockMvc
            .perform(delete(ENTITY_API_URL_ID, disease.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Disease> diseaseList = diseaseRepository.findAll();
        assertThat(diseaseList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
