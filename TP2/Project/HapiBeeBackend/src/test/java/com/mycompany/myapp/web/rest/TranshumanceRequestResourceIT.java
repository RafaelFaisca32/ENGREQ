package com.mycompany.myapp.web.rest;

import static com.mycompany.myapp.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.Apiary;
import com.mycompany.myapp.domain.Hive;
import com.mycompany.myapp.domain.TranshumanceRequest;
import com.mycompany.myapp.domain.enumeration.TranshumanceRequestState;
import com.mycompany.myapp.repository.TranshumanceRequestRepository;
import com.mycompany.myapp.service.TranshumanceRequestService;
import com.mycompany.myapp.service.criteria.TranshumanceRequestCriteria;
import com.mycompany.myapp.service.dto.TranshumanceRequestDTO;
import com.mycompany.myapp.service.mapper.TranshumanceRequestMapper;
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
 * Integration tests for the {@link TranshumanceRequestResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class TranshumanceRequestResourceIT {

    private static final ZonedDateTime DEFAULT_REQUEST_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_REQUEST_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final ZonedDateTime SMALLER_REQUEST_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(-1L), ZoneOffset.UTC);

    private static final TranshumanceRequestState DEFAULT_STATE = TranshumanceRequestState.NOT_APPROVED;
    private static final TranshumanceRequestState UPDATED_STATE = TranshumanceRequestState.APPROVED;

    private static final String ENTITY_API_URL = "/api/transhumance-requests";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TranshumanceRequestRepository transhumanceRequestRepository;

    @Mock
    private TranshumanceRequestRepository transhumanceRequestRepositoryMock;

    @Autowired
    private TranshumanceRequestMapper transhumanceRequestMapper;

    @Mock
    private TranshumanceRequestService transhumanceRequestServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTranshumanceRequestMockMvc;

    private TranshumanceRequest transhumanceRequest;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TranshumanceRequest createEntity(EntityManager em) {
        TranshumanceRequest transhumanceRequest = new TranshumanceRequest().requestDate(DEFAULT_REQUEST_DATE).state(DEFAULT_STATE);
        return transhumanceRequest;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TranshumanceRequest createUpdatedEntity(EntityManager em) {
        TranshumanceRequest transhumanceRequest = new TranshumanceRequest().requestDate(UPDATED_REQUEST_DATE).state(UPDATED_STATE);
        return transhumanceRequest;
    }

    @BeforeEach
    public void initTest() {
        transhumanceRequest = createEntity(em);
    }


    @Test
    @Transactional
    void createTranshumanceRequestWithExistingId() throws Exception {
        // Create the TranshumanceRequest with an existing ID
        transhumanceRequest.setId(1L);
        TranshumanceRequestDTO transhumanceRequestDTO = transhumanceRequestMapper.toDto(transhumanceRequest);

        int databaseSizeBeforeCreate = transhumanceRequestRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTranshumanceRequestMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(transhumanceRequestDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TranshumanceRequest in the database
        List<TranshumanceRequest> transhumanceRequestList = transhumanceRequestRepository.findAll();
        assertThat(transhumanceRequestList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTranshumanceRequests() throws Exception {
        // Initialize the database
        transhumanceRequestRepository.saveAndFlush(transhumanceRequest);

        // Get all the transhumanceRequestList
        restTranshumanceRequestMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(transhumanceRequest.getId().intValue())))
            .andExpect(jsonPath("$.[*].requestDate").value(hasItem(sameInstant(DEFAULT_REQUEST_DATE))))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE.toString())));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllTranshumanceRequestsWithEagerRelationshipsIsEnabled() throws Exception {
        when(transhumanceRequestServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restTranshumanceRequestMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(transhumanceRequestServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllTranshumanceRequestsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(transhumanceRequestServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restTranshumanceRequestMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(transhumanceRequestRepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @Transactional
    void getTranshumanceRequest() throws Exception {
        // Initialize the database
        transhumanceRequestRepository.saveAndFlush(transhumanceRequest);

        // Get the transhumanceRequest
        restTranshumanceRequestMockMvc
            .perform(get(ENTITY_API_URL_ID, transhumanceRequest.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(transhumanceRequest.getId().intValue()))
            .andExpect(jsonPath("$.requestDate").value(sameInstant(DEFAULT_REQUEST_DATE)))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE.toString()));
    }

    @Test
    @Transactional
    void getTranshumanceRequestsByIdFiltering() throws Exception {
        // Initialize the database
        transhumanceRequestRepository.saveAndFlush(transhumanceRequest);

        Long id = transhumanceRequest.getId();

        defaultTranshumanceRequestShouldBeFound("id.equals=" + id);
        defaultTranshumanceRequestShouldNotBeFound("id.notEquals=" + id);

        defaultTranshumanceRequestShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultTranshumanceRequestShouldNotBeFound("id.greaterThan=" + id);

        defaultTranshumanceRequestShouldBeFound("id.lessThanOrEqual=" + id);
        defaultTranshumanceRequestShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllTranshumanceRequestsByRequestDateIsEqualToSomething() throws Exception {
        // Initialize the database
        transhumanceRequestRepository.saveAndFlush(transhumanceRequest);

        // Get all the transhumanceRequestList where requestDate equals to DEFAULT_REQUEST_DATE
        defaultTranshumanceRequestShouldBeFound("requestDate.equals=" + DEFAULT_REQUEST_DATE);

        // Get all the transhumanceRequestList where requestDate equals to UPDATED_REQUEST_DATE
        defaultTranshumanceRequestShouldNotBeFound("requestDate.equals=" + UPDATED_REQUEST_DATE);
    }

    @Test
    @Transactional
    void getAllTranshumanceRequestsByRequestDateIsInShouldWork() throws Exception {
        // Initialize the database
        transhumanceRequestRepository.saveAndFlush(transhumanceRequest);

        // Get all the transhumanceRequestList where requestDate in DEFAULT_REQUEST_DATE or UPDATED_REQUEST_DATE
        defaultTranshumanceRequestShouldBeFound("requestDate.in=" + DEFAULT_REQUEST_DATE + "," + UPDATED_REQUEST_DATE);

        // Get all the transhumanceRequestList where requestDate equals to UPDATED_REQUEST_DATE
        defaultTranshumanceRequestShouldNotBeFound("requestDate.in=" + UPDATED_REQUEST_DATE);
    }

    @Test
    @Transactional
    void getAllTranshumanceRequestsByRequestDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        transhumanceRequestRepository.saveAndFlush(transhumanceRequest);

        // Get all the transhumanceRequestList where requestDate is not null
        defaultTranshumanceRequestShouldBeFound("requestDate.specified=true");

        // Get all the transhumanceRequestList where requestDate is null
        defaultTranshumanceRequestShouldNotBeFound("requestDate.specified=false");
    }

    @Test
    @Transactional
    void getAllTranshumanceRequestsByRequestDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        transhumanceRequestRepository.saveAndFlush(transhumanceRequest);

        // Get all the transhumanceRequestList where requestDate is greater than or equal to DEFAULT_REQUEST_DATE
        defaultTranshumanceRequestShouldBeFound("requestDate.greaterThanOrEqual=" + DEFAULT_REQUEST_DATE);

        // Get all the transhumanceRequestList where requestDate is greater than or equal to UPDATED_REQUEST_DATE
        defaultTranshumanceRequestShouldNotBeFound("requestDate.greaterThanOrEqual=" + UPDATED_REQUEST_DATE);
    }

    @Test
    @Transactional
    void getAllTranshumanceRequestsByRequestDateIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        transhumanceRequestRepository.saveAndFlush(transhumanceRequest);

        // Get all the transhumanceRequestList where requestDate is less than or equal to DEFAULT_REQUEST_DATE
        defaultTranshumanceRequestShouldBeFound("requestDate.lessThanOrEqual=" + DEFAULT_REQUEST_DATE);

        // Get all the transhumanceRequestList where requestDate is less than or equal to SMALLER_REQUEST_DATE
        defaultTranshumanceRequestShouldNotBeFound("requestDate.lessThanOrEqual=" + SMALLER_REQUEST_DATE);
    }

    @Test
    @Transactional
    void getAllTranshumanceRequestsByRequestDateIsLessThanSomething() throws Exception {
        // Initialize the database
        transhumanceRequestRepository.saveAndFlush(transhumanceRequest);

        // Get all the transhumanceRequestList where requestDate is less than DEFAULT_REQUEST_DATE
        defaultTranshumanceRequestShouldNotBeFound("requestDate.lessThan=" + DEFAULT_REQUEST_DATE);

        // Get all the transhumanceRequestList where requestDate is less than UPDATED_REQUEST_DATE
        defaultTranshumanceRequestShouldBeFound("requestDate.lessThan=" + UPDATED_REQUEST_DATE);
    }

    @Test
    @Transactional
    void getAllTranshumanceRequestsByRequestDateIsGreaterThanSomething() throws Exception {
        // Initialize the database
        transhumanceRequestRepository.saveAndFlush(transhumanceRequest);

        // Get all the transhumanceRequestList where requestDate is greater than DEFAULT_REQUEST_DATE
        defaultTranshumanceRequestShouldNotBeFound("requestDate.greaterThan=" + DEFAULT_REQUEST_DATE);

        // Get all the transhumanceRequestList where requestDate is greater than SMALLER_REQUEST_DATE
        defaultTranshumanceRequestShouldBeFound("requestDate.greaterThan=" + SMALLER_REQUEST_DATE);
    }

    @Test
    @Transactional
    void getAllTranshumanceRequestsByStateIsEqualToSomething() throws Exception {
        // Initialize the database
        transhumanceRequestRepository.saveAndFlush(transhumanceRequest);

        // Get all the transhumanceRequestList where state equals to DEFAULT_STATE
        defaultTranshumanceRequestShouldBeFound("state.equals=" + DEFAULT_STATE);

        // Get all the transhumanceRequestList where state equals to UPDATED_STATE
        defaultTranshumanceRequestShouldNotBeFound("state.equals=" + UPDATED_STATE);
    }

    @Test
    @Transactional
    void getAllTranshumanceRequestsByStateIsInShouldWork() throws Exception {
        // Initialize the database
        transhumanceRequestRepository.saveAndFlush(transhumanceRequest);

        // Get all the transhumanceRequestList where state in DEFAULT_STATE or UPDATED_STATE
        defaultTranshumanceRequestShouldBeFound("state.in=" + DEFAULT_STATE + "," + UPDATED_STATE);

        // Get all the transhumanceRequestList where state equals to UPDATED_STATE
        defaultTranshumanceRequestShouldNotBeFound("state.in=" + UPDATED_STATE);
    }

    @Test
    @Transactional
    void getAllTranshumanceRequestsByStateIsNullOrNotNull() throws Exception {
        // Initialize the database
        transhumanceRequestRepository.saveAndFlush(transhumanceRequest);

        // Get all the transhumanceRequestList where state is not null
        defaultTranshumanceRequestShouldBeFound("state.specified=true");

        // Get all the transhumanceRequestList where state is null
        defaultTranshumanceRequestShouldNotBeFound("state.specified=false");
    }

    @Test
    @Transactional
    void getAllTranshumanceRequestsByApiaryIsEqualToSomething() throws Exception {
        Apiary apiary;
        if (TestUtil.findAll(em, Apiary.class).isEmpty()) {
            transhumanceRequestRepository.saveAndFlush(transhumanceRequest);
            apiary = ApiaryResourceIT.createEntity(em);
        } else {
            apiary = TestUtil.findAll(em, Apiary.class).get(0);
        }
        em.persist(apiary);
        em.flush();
        transhumanceRequest.setApiary(apiary);
        transhumanceRequestRepository.saveAndFlush(transhumanceRequest);
        Long apiaryId = apiary.getId();

        // Get all the transhumanceRequestList where apiary equals to apiaryId
        defaultTranshumanceRequestShouldBeFound("apiaryId.equals=" + apiaryId);

        // Get all the transhumanceRequestList where apiary equals to (apiaryId + 1)
        defaultTranshumanceRequestShouldNotBeFound("apiaryId.equals=" + (apiaryId + 1));
    }

    @Test
    @Transactional
    void getAllTranshumanceRequestsByHiveIsEqualToSomething() throws Exception {
        Hive hive;
        if (TestUtil.findAll(em, Hive.class).isEmpty()) {
            transhumanceRequestRepository.saveAndFlush(transhumanceRequest);
            hive = HiveResourceIT.createEntity(em);
        } else {
            hive = TestUtil.findAll(em, Hive.class).get(0);
        }
        em.persist(hive);
        em.flush();
        transhumanceRequest.addHive(hive);
        transhumanceRequestRepository.saveAndFlush(transhumanceRequest);
        Long hiveId = hive.getId();

        // Get all the transhumanceRequestList where hive equals to hiveId
        defaultTranshumanceRequestShouldBeFound("hiveId.equals=" + hiveId);

        // Get all the transhumanceRequestList where hive equals to (hiveId + 1)
        defaultTranshumanceRequestShouldNotBeFound("hiveId.equals=" + (hiveId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultTranshumanceRequestShouldBeFound(String filter) throws Exception {
        restTranshumanceRequestMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(transhumanceRequest.getId().intValue())))
            .andExpect(jsonPath("$.[*].requestDate").value(hasItem(sameInstant(DEFAULT_REQUEST_DATE))))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE.toString())));

        // Check, that the count call also returns 1
        restTranshumanceRequestMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultTranshumanceRequestShouldNotBeFound(String filter) throws Exception {
        restTranshumanceRequestMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restTranshumanceRequestMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingTranshumanceRequest() throws Exception {
        // Get the transhumanceRequest
        restTranshumanceRequestMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTranshumanceRequest() throws Exception {
        // Initialize the database
        transhumanceRequestRepository.saveAndFlush(transhumanceRequest);

        int databaseSizeBeforeUpdate = transhumanceRequestRepository.findAll().size();

        // Update the transhumanceRequest
        TranshumanceRequest updatedTranshumanceRequest = transhumanceRequestRepository.findById(transhumanceRequest.getId()).get();
        // Disconnect from session so that the updates on updatedTranshumanceRequest are not directly saved in db
        em.detach(updatedTranshumanceRequest);
        updatedTranshumanceRequest.requestDate(UPDATED_REQUEST_DATE).state(UPDATED_STATE);
        TranshumanceRequestDTO transhumanceRequestDTO = transhumanceRequestMapper.toDto(updatedTranshumanceRequest);

        restTranshumanceRequestMockMvc
            .perform(
                put(ENTITY_API_URL_ID, transhumanceRequestDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(transhumanceRequestDTO))
            )
            .andExpect(status().isOk());

        // Validate the TranshumanceRequest in the database
        List<TranshumanceRequest> transhumanceRequestList = transhumanceRequestRepository.findAll();
        assertThat(transhumanceRequestList).hasSize(databaseSizeBeforeUpdate);
        TranshumanceRequest testTranshumanceRequest = transhumanceRequestList.get(transhumanceRequestList.size() - 1);
        assertThat(testTranshumanceRequest.getRequestDate()).isEqualTo(UPDATED_REQUEST_DATE);
        assertThat(testTranshumanceRequest.getState()).isEqualTo(UPDATED_STATE);
    }

    @Test
    @Transactional
    void putNonExistingTranshumanceRequest() throws Exception {
        int databaseSizeBeforeUpdate = transhumanceRequestRepository.findAll().size();
        transhumanceRequest.setId(count.incrementAndGet());

        // Create the TranshumanceRequest
        TranshumanceRequestDTO transhumanceRequestDTO = transhumanceRequestMapper.toDto(transhumanceRequest);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTranshumanceRequestMockMvc
            .perform(
                put(ENTITY_API_URL_ID, transhumanceRequestDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(transhumanceRequestDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TranshumanceRequest in the database
        List<TranshumanceRequest> transhumanceRequestList = transhumanceRequestRepository.findAll();
        assertThat(transhumanceRequestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTranshumanceRequest() throws Exception {
        int databaseSizeBeforeUpdate = transhumanceRequestRepository.findAll().size();
        transhumanceRequest.setId(count.incrementAndGet());

        // Create the TranshumanceRequest
        TranshumanceRequestDTO transhumanceRequestDTO = transhumanceRequestMapper.toDto(transhumanceRequest);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTranshumanceRequestMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(transhumanceRequestDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TranshumanceRequest in the database
        List<TranshumanceRequest> transhumanceRequestList = transhumanceRequestRepository.findAll();
        assertThat(transhumanceRequestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTranshumanceRequest() throws Exception {
        int databaseSizeBeforeUpdate = transhumanceRequestRepository.findAll().size();
        transhumanceRequest.setId(count.incrementAndGet());

        // Create the TranshumanceRequest
        TranshumanceRequestDTO transhumanceRequestDTO = transhumanceRequestMapper.toDto(transhumanceRequest);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTranshumanceRequestMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(transhumanceRequestDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TranshumanceRequest in the database
        List<TranshumanceRequest> transhumanceRequestList = transhumanceRequestRepository.findAll();
        assertThat(transhumanceRequestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTranshumanceRequestWithPatch() throws Exception {
        // Initialize the database
        transhumanceRequestRepository.saveAndFlush(transhumanceRequest);

        int databaseSizeBeforeUpdate = transhumanceRequestRepository.findAll().size();

        // Update the transhumanceRequest using partial update
        TranshumanceRequest partialUpdatedTranshumanceRequest = new TranshumanceRequest();
        partialUpdatedTranshumanceRequest.setId(transhumanceRequest.getId());

        partialUpdatedTranshumanceRequest.requestDate(UPDATED_REQUEST_DATE).state(UPDATED_STATE);

        restTranshumanceRequestMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTranshumanceRequest.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTranshumanceRequest))
            )
            .andExpect(status().isOk());

        // Validate the TranshumanceRequest in the database
        List<TranshumanceRequest> transhumanceRequestList = transhumanceRequestRepository.findAll();
        assertThat(transhumanceRequestList).hasSize(databaseSizeBeforeUpdate);
        TranshumanceRequest testTranshumanceRequest = transhumanceRequestList.get(transhumanceRequestList.size() - 1);
        assertThat(testTranshumanceRequest.getRequestDate()).isEqualTo(UPDATED_REQUEST_DATE);
        assertThat(testTranshumanceRequest.getState()).isEqualTo(UPDATED_STATE);
    }

    @Test
    @Transactional
    void fullUpdateTranshumanceRequestWithPatch() throws Exception {
        // Initialize the database
        transhumanceRequestRepository.saveAndFlush(transhumanceRequest);

        int databaseSizeBeforeUpdate = transhumanceRequestRepository.findAll().size();

        // Update the transhumanceRequest using partial update
        TranshumanceRequest partialUpdatedTranshumanceRequest = new TranshumanceRequest();
        partialUpdatedTranshumanceRequest.setId(transhumanceRequest.getId());

        partialUpdatedTranshumanceRequest.requestDate(UPDATED_REQUEST_DATE).state(UPDATED_STATE);

        restTranshumanceRequestMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTranshumanceRequest.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTranshumanceRequest))
            )
            .andExpect(status().isOk());

        // Validate the TranshumanceRequest in the database
        List<TranshumanceRequest> transhumanceRequestList = transhumanceRequestRepository.findAll();
        assertThat(transhumanceRequestList).hasSize(databaseSizeBeforeUpdate);
        TranshumanceRequest testTranshumanceRequest = transhumanceRequestList.get(transhumanceRequestList.size() - 1);
        assertThat(testTranshumanceRequest.getRequestDate()).isEqualTo(UPDATED_REQUEST_DATE);
        assertThat(testTranshumanceRequest.getState()).isEqualTo(UPDATED_STATE);
    }

    @Test
    @Transactional
    void patchNonExistingTranshumanceRequest() throws Exception {
        int databaseSizeBeforeUpdate = transhumanceRequestRepository.findAll().size();
        transhumanceRequest.setId(count.incrementAndGet());

        // Create the TranshumanceRequest
        TranshumanceRequestDTO transhumanceRequestDTO = transhumanceRequestMapper.toDto(transhumanceRequest);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTranshumanceRequestMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, transhumanceRequestDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(transhumanceRequestDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TranshumanceRequest in the database
        List<TranshumanceRequest> transhumanceRequestList = transhumanceRequestRepository.findAll();
        assertThat(transhumanceRequestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTranshumanceRequest() throws Exception {
        int databaseSizeBeforeUpdate = transhumanceRequestRepository.findAll().size();
        transhumanceRequest.setId(count.incrementAndGet());

        // Create the TranshumanceRequest
        TranshumanceRequestDTO transhumanceRequestDTO = transhumanceRequestMapper.toDto(transhumanceRequest);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTranshumanceRequestMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(transhumanceRequestDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TranshumanceRequest in the database
        List<TranshumanceRequest> transhumanceRequestList = transhumanceRequestRepository.findAll();
        assertThat(transhumanceRequestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTranshumanceRequest() throws Exception {
        int databaseSizeBeforeUpdate = transhumanceRequestRepository.findAll().size();
        transhumanceRequest.setId(count.incrementAndGet());

        // Create the TranshumanceRequest
        TranshumanceRequestDTO transhumanceRequestDTO = transhumanceRequestMapper.toDto(transhumanceRequest);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTranshumanceRequestMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(transhumanceRequestDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TranshumanceRequest in the database
        List<TranshumanceRequest> transhumanceRequestList = transhumanceRequestRepository.findAll();
        assertThat(transhumanceRequestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTranshumanceRequest() throws Exception {
        // Initialize the database
        transhumanceRequestRepository.saveAndFlush(transhumanceRequest);

        int databaseSizeBeforeDelete = transhumanceRequestRepository.findAll().size();

        // Delete the transhumanceRequest
        restTranshumanceRequestMockMvc
            .perform(delete(ENTITY_API_URL_ID, transhumanceRequest.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TranshumanceRequest> transhumanceRequestList = transhumanceRequestRepository.findAll();
        assertThat(transhumanceRequestList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
