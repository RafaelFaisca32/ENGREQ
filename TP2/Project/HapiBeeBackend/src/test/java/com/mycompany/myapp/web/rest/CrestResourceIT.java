package com.mycompany.myapp.web.rest;

import static com.mycompany.myapp.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.Crest;
import com.mycompany.myapp.domain.Hive;
import com.mycompany.myapp.domain.enumeration.CrestState;
import com.mycompany.myapp.repository.CrestRepository;
import com.mycompany.myapp.service.criteria.CrestCriteria;
import com.mycompany.myapp.service.dto.CrestDTO;
import com.mycompany.myapp.service.mapper.CrestMapper;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
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
 * Integration tests for the {@link CrestResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CrestResourceIT {

    private static final Integer DEFAULT_COMB_FRAME_QUANTITY = 1;
    private static final Integer UPDATED_COMB_FRAME_QUANTITY = 2;
    private static final Integer SMALLER_COMB_FRAME_QUANTITY = 1 - 1;

    private static final Float DEFAULT_WAX_WEIGHT = 1F;
    private static final Float UPDATED_WAX_WEIGHT = 2F;
    private static final Float SMALLER_WAX_WEIGHT = 1F - 1F;

    private static final Float DEFAULT_TIME_WASTED_CENTRIFUGE = 1F;
    private static final Float UPDATED_TIME_WASTED_CENTRIFUGE = 2F;
    private static final Float SMALLER_TIME_WASTED_CENTRIFUGE = 1F - 1F;

    private static final ZonedDateTime DEFAULT_INITIAL_DATE_DECANTATION = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_INITIAL_DATE_DECANTATION = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final ZonedDateTime SMALLER_INITIAL_DATE_DECANTATION = ZonedDateTime.ofInstant(
        Instant.ofEpochMilli(-1L),
        ZoneOffset.UTC
    );

    private static final ZonedDateTime DEFAULT_FINAL_DATE_DECANTATION = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_FINAL_DATE_DECANTATION = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final ZonedDateTime SMALLER_FINAL_DATE_DECANTATION = ZonedDateTime.ofInstant(Instant.ofEpochMilli(-1L), ZoneOffset.UTC);

    private static final Float DEFAULT_PRODUCED_HONEY_QUANTITY = 1F;
    private static final Float UPDATED_PRODUCED_HONEY_QUANTITY = 2F;
    private static final Float SMALLER_PRODUCED_HONEY_QUANTITY = 1F - 1F;

    private static final CrestState DEFAULT_STATE = CrestState.DECANTATION;
    private static final CrestState UPDATED_STATE = CrestState.FINALIZED;

    private static final String ENTITY_API_URL = "/api/crests";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CrestRepository crestRepository;

    @Autowired
    private CrestMapper crestMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCrestMockMvc;

    private Crest crest;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Crest createEntity(EntityManager em) {
        Crest crest = new Crest()
            .combFrameQuantity(DEFAULT_COMB_FRAME_QUANTITY)
            .waxWeight(DEFAULT_WAX_WEIGHT)
            .timeWastedCentrifuge(DEFAULT_TIME_WASTED_CENTRIFUGE)
            .initialDateDecantation(DEFAULT_INITIAL_DATE_DECANTATION)
            .finalDateDecantation(DEFAULT_FINAL_DATE_DECANTATION)
            .producedHoneyQuantity(DEFAULT_PRODUCED_HONEY_QUANTITY)
            .state(DEFAULT_STATE);
        return crest;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Crest createUpdatedEntity(EntityManager em) {
        Crest crest = new Crest()
            .combFrameQuantity(UPDATED_COMB_FRAME_QUANTITY)
            .waxWeight(UPDATED_WAX_WEIGHT)
            .timeWastedCentrifuge(UPDATED_TIME_WASTED_CENTRIFUGE)
            .initialDateDecantation(UPDATED_INITIAL_DATE_DECANTATION)
            .finalDateDecantation(UPDATED_FINAL_DATE_DECANTATION)
            .producedHoneyQuantity(UPDATED_PRODUCED_HONEY_QUANTITY)
            .state(UPDATED_STATE);
        return crest;
    }

    @BeforeEach
    public void initTest() {
        crest = createEntity(em);
    }

    @Test
    @Transactional
    void createCrest() throws Exception {
        int databaseSizeBeforeCreate = crestRepository.findAll().size();
        // Create the Crest
        CrestDTO crestDTO = crestMapper.toDto(crest);
        restCrestMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(crestDTO)))
            .andExpect(status().isCreated());

        // Validate the Crest in the database
        List<Crest> crestList = crestRepository.findAll();
        assertThat(crestList).hasSize(databaseSizeBeforeCreate + 1);
        Crest testCrest = crestList.get(crestList.size() - 1);
        assertThat(testCrest.getCombFrameQuantity()).isEqualTo(DEFAULT_COMB_FRAME_QUANTITY);
        assertThat(testCrest.getWaxWeight()).isEqualTo(DEFAULT_WAX_WEIGHT);
        assertThat(testCrest.getTimeWastedCentrifuge()).isEqualTo(DEFAULT_TIME_WASTED_CENTRIFUGE);
        assertThat(testCrest.getInitialDateDecantation()).isEqualTo(DEFAULT_INITIAL_DATE_DECANTATION);
        assertThat(testCrest.getFinalDateDecantation()).isEqualTo(DEFAULT_FINAL_DATE_DECANTATION);
        assertThat(testCrest.getProducedHoneyQuantity()).isEqualTo(DEFAULT_PRODUCED_HONEY_QUANTITY);
        assertThat(testCrest.getState()).isEqualTo(DEFAULT_STATE);
    }

    @Test
    @Transactional
    void createCrestWithExistingId() throws Exception {
        // Create the Crest with an existing ID
        crest.setId(1L);
        CrestDTO crestDTO = crestMapper.toDto(crest);

        int databaseSizeBeforeCreate = crestRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCrestMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(crestDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Crest in the database
        List<Crest> crestList = crestRepository.findAll();
        assertThat(crestList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkCombFrameQuantityIsRequired() throws Exception {
        int databaseSizeBeforeTest = crestRepository.findAll().size();
        // set the field null
        crest.setCombFrameQuantity(null);

        // Create the Crest, which fails.
        CrestDTO crestDTO = crestMapper.toDto(crest);

        restCrestMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(crestDTO)))
            .andExpect(status().isBadRequest());

        List<Crest> crestList = crestRepository.findAll();
        assertThat(crestList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkWaxWeightIsRequired() throws Exception {
        int databaseSizeBeforeTest = crestRepository.findAll().size();
        // set the field null
        crest.setWaxWeight(null);

        // Create the Crest, which fails.
        CrestDTO crestDTO = crestMapper.toDto(crest);

        restCrestMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(crestDTO)))
            .andExpect(status().isBadRequest());

        List<Crest> crestList = crestRepository.findAll();
        assertThat(crestList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTimeWastedCentrifugeIsRequired() throws Exception {
        int databaseSizeBeforeTest = crestRepository.findAll().size();
        // set the field null
        crest.setTimeWastedCentrifuge(null);

        // Create the Crest, which fails.
        CrestDTO crestDTO = crestMapper.toDto(crest);

        restCrestMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(crestDTO)))
            .andExpect(status().isBadRequest());

        List<Crest> crestList = crestRepository.findAll();
        assertThat(crestList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkInitialDateDecantationIsRequired() throws Exception {
        int databaseSizeBeforeTest = crestRepository.findAll().size();
        // set the field null
        crest.setInitialDateDecantation(null);

        // Create the Crest, which fails.
        CrestDTO crestDTO = crestMapper.toDto(crest);

        restCrestMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(crestDTO)))
            .andExpect(status().isBadRequest());

        List<Crest> crestList = crestRepository.findAll();
        assertThat(crestList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkStateIsRequired() throws Exception {
        int databaseSizeBeforeTest = crestRepository.findAll().size();
        // set the field null
        crest.setState(null);

        // Create the Crest, which fails.
        CrestDTO crestDTO = crestMapper.toDto(crest);

        restCrestMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(crestDTO)))
            .andExpect(status().isBadRequest());

        List<Crest> crestList = crestRepository.findAll();
        assertThat(crestList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllCrests() throws Exception {
        // Initialize the database
        crestRepository.saveAndFlush(crest);

        // Get all the crestList
        restCrestMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(crest.getId().intValue())))
            .andExpect(jsonPath("$.[*].combFrameQuantity").value(hasItem(DEFAULT_COMB_FRAME_QUANTITY)))
            .andExpect(jsonPath("$.[*].waxWeight").value(hasItem(DEFAULT_WAX_WEIGHT.doubleValue())))
            .andExpect(jsonPath("$.[*].timeWastedCentrifuge").value(hasItem(DEFAULT_TIME_WASTED_CENTRIFUGE.doubleValue())))
            .andExpect(jsonPath("$.[*].initialDateDecantation").value(hasItem(sameInstant(DEFAULT_INITIAL_DATE_DECANTATION))))
            .andExpect(jsonPath("$.[*].finalDateDecantation").value(hasItem(sameInstant(DEFAULT_FINAL_DATE_DECANTATION))))
            .andExpect(jsonPath("$.[*].producedHoneyQuantity").value(hasItem(DEFAULT_PRODUCED_HONEY_QUANTITY.doubleValue())))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE.toString())));
    }

    @Test
    @Transactional
    void getCrest() throws Exception {
        // Initialize the database
        crestRepository.saveAndFlush(crest);

        // Get the crest
        restCrestMockMvc
            .perform(get(ENTITY_API_URL_ID, crest.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(crest.getId().intValue()))
            .andExpect(jsonPath("$.combFrameQuantity").value(DEFAULT_COMB_FRAME_QUANTITY))
            .andExpect(jsonPath("$.waxWeight").value(DEFAULT_WAX_WEIGHT.doubleValue()))
            .andExpect(jsonPath("$.timeWastedCentrifuge").value(DEFAULT_TIME_WASTED_CENTRIFUGE.doubleValue()))
            .andExpect(jsonPath("$.initialDateDecantation").value(sameInstant(DEFAULT_INITIAL_DATE_DECANTATION)))
            .andExpect(jsonPath("$.finalDateDecantation").value(sameInstant(DEFAULT_FINAL_DATE_DECANTATION)))
            .andExpect(jsonPath("$.producedHoneyQuantity").value(DEFAULT_PRODUCED_HONEY_QUANTITY.doubleValue()))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE.toString()));
    }

    @Test
    @Transactional
    void getCrestsByIdFiltering() throws Exception {
        // Initialize the database
        crestRepository.saveAndFlush(crest);

        Long id = crest.getId();

        defaultCrestShouldBeFound("id.equals=" + id);
        defaultCrestShouldNotBeFound("id.notEquals=" + id);

        defaultCrestShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultCrestShouldNotBeFound("id.greaterThan=" + id);

        defaultCrestShouldBeFound("id.lessThanOrEqual=" + id);
        defaultCrestShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllCrestsByCombFrameQuantityIsEqualToSomething() throws Exception {
        // Initialize the database
        crestRepository.saveAndFlush(crest);

        // Get all the crestList where combFrameQuantity equals to DEFAULT_COMB_FRAME_QUANTITY
        defaultCrestShouldBeFound("combFrameQuantity.equals=" + DEFAULT_COMB_FRAME_QUANTITY);

        // Get all the crestList where combFrameQuantity equals to UPDATED_COMB_FRAME_QUANTITY
        defaultCrestShouldNotBeFound("combFrameQuantity.equals=" + UPDATED_COMB_FRAME_QUANTITY);
    }

    @Test
    @Transactional
    void getAllCrestsByCombFrameQuantityIsInShouldWork() throws Exception {
        // Initialize the database
        crestRepository.saveAndFlush(crest);

        // Get all the crestList where combFrameQuantity in DEFAULT_COMB_FRAME_QUANTITY or UPDATED_COMB_FRAME_QUANTITY
        defaultCrestShouldBeFound("combFrameQuantity.in=" + DEFAULT_COMB_FRAME_QUANTITY + "," + UPDATED_COMB_FRAME_QUANTITY);

        // Get all the crestList where combFrameQuantity equals to UPDATED_COMB_FRAME_QUANTITY
        defaultCrestShouldNotBeFound("combFrameQuantity.in=" + UPDATED_COMB_FRAME_QUANTITY);
    }

    @Test
    @Transactional
    void getAllCrestsByCombFrameQuantityIsNullOrNotNull() throws Exception {
        // Initialize the database
        crestRepository.saveAndFlush(crest);

        // Get all the crestList where combFrameQuantity is not null
        defaultCrestShouldBeFound("combFrameQuantity.specified=true");

        // Get all the crestList where combFrameQuantity is null
        defaultCrestShouldNotBeFound("combFrameQuantity.specified=false");
    }

    @Test
    @Transactional
    void getAllCrestsByCombFrameQuantityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        crestRepository.saveAndFlush(crest);

        // Get all the crestList where combFrameQuantity is greater than or equal to DEFAULT_COMB_FRAME_QUANTITY
        defaultCrestShouldBeFound("combFrameQuantity.greaterThanOrEqual=" + DEFAULT_COMB_FRAME_QUANTITY);

        // Get all the crestList where combFrameQuantity is greater than or equal to UPDATED_COMB_FRAME_QUANTITY
        defaultCrestShouldNotBeFound("combFrameQuantity.greaterThanOrEqual=" + UPDATED_COMB_FRAME_QUANTITY);
    }

    @Test
    @Transactional
    void getAllCrestsByCombFrameQuantityIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        crestRepository.saveAndFlush(crest);

        // Get all the crestList where combFrameQuantity is less than or equal to DEFAULT_COMB_FRAME_QUANTITY
        defaultCrestShouldBeFound("combFrameQuantity.lessThanOrEqual=" + DEFAULT_COMB_FRAME_QUANTITY);

        // Get all the crestList where combFrameQuantity is less than or equal to SMALLER_COMB_FRAME_QUANTITY
        defaultCrestShouldNotBeFound("combFrameQuantity.lessThanOrEqual=" + SMALLER_COMB_FRAME_QUANTITY);
    }

    @Test
    @Transactional
    void getAllCrestsByCombFrameQuantityIsLessThanSomething() throws Exception {
        // Initialize the database
        crestRepository.saveAndFlush(crest);

        // Get all the crestList where combFrameQuantity is less than DEFAULT_COMB_FRAME_QUANTITY
        defaultCrestShouldNotBeFound("combFrameQuantity.lessThan=" + DEFAULT_COMB_FRAME_QUANTITY);

        // Get all the crestList where combFrameQuantity is less than UPDATED_COMB_FRAME_QUANTITY
        defaultCrestShouldBeFound("combFrameQuantity.lessThan=" + UPDATED_COMB_FRAME_QUANTITY);
    }

    @Test
    @Transactional
    void getAllCrestsByCombFrameQuantityIsGreaterThanSomething() throws Exception {
        // Initialize the database
        crestRepository.saveAndFlush(crest);

        // Get all the crestList where combFrameQuantity is greater than DEFAULT_COMB_FRAME_QUANTITY
        defaultCrestShouldNotBeFound("combFrameQuantity.greaterThan=" + DEFAULT_COMB_FRAME_QUANTITY);

        // Get all the crestList where combFrameQuantity is greater than SMALLER_COMB_FRAME_QUANTITY
        defaultCrestShouldBeFound("combFrameQuantity.greaterThan=" + SMALLER_COMB_FRAME_QUANTITY);
    }

    @Test
    @Transactional
    void getAllCrestsByWaxWeightIsEqualToSomething() throws Exception {
        // Initialize the database
        crestRepository.saveAndFlush(crest);

        // Get all the crestList where waxWeight equals to DEFAULT_WAX_WEIGHT
        defaultCrestShouldBeFound("waxWeight.equals=" + DEFAULT_WAX_WEIGHT);

        // Get all the crestList where waxWeight equals to UPDATED_WAX_WEIGHT
        defaultCrestShouldNotBeFound("waxWeight.equals=" + UPDATED_WAX_WEIGHT);
    }

    @Test
    @Transactional
    void getAllCrestsByWaxWeightIsInShouldWork() throws Exception {
        // Initialize the database
        crestRepository.saveAndFlush(crest);

        // Get all the crestList where waxWeight in DEFAULT_WAX_WEIGHT or UPDATED_WAX_WEIGHT
        defaultCrestShouldBeFound("waxWeight.in=" + DEFAULT_WAX_WEIGHT + "," + UPDATED_WAX_WEIGHT);

        // Get all the crestList where waxWeight equals to UPDATED_WAX_WEIGHT
        defaultCrestShouldNotBeFound("waxWeight.in=" + UPDATED_WAX_WEIGHT);
    }

    @Test
    @Transactional
    void getAllCrestsByWaxWeightIsNullOrNotNull() throws Exception {
        // Initialize the database
        crestRepository.saveAndFlush(crest);

        // Get all the crestList where waxWeight is not null
        defaultCrestShouldBeFound("waxWeight.specified=true");

        // Get all the crestList where waxWeight is null
        defaultCrestShouldNotBeFound("waxWeight.specified=false");
    }

    @Test
    @Transactional
    void getAllCrestsByWaxWeightIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        crestRepository.saveAndFlush(crest);

        // Get all the crestList where waxWeight is greater than or equal to DEFAULT_WAX_WEIGHT
        defaultCrestShouldBeFound("waxWeight.greaterThanOrEqual=" + DEFAULT_WAX_WEIGHT);

        // Get all the crestList where waxWeight is greater than or equal to UPDATED_WAX_WEIGHT
        defaultCrestShouldNotBeFound("waxWeight.greaterThanOrEqual=" + UPDATED_WAX_WEIGHT);
    }

    @Test
    @Transactional
    void getAllCrestsByWaxWeightIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        crestRepository.saveAndFlush(crest);

        // Get all the crestList where waxWeight is less than or equal to DEFAULT_WAX_WEIGHT
        defaultCrestShouldBeFound("waxWeight.lessThanOrEqual=" + DEFAULT_WAX_WEIGHT);

        // Get all the crestList where waxWeight is less than or equal to SMALLER_WAX_WEIGHT
        defaultCrestShouldNotBeFound("waxWeight.lessThanOrEqual=" + SMALLER_WAX_WEIGHT);
    }

    @Test
    @Transactional
    void getAllCrestsByWaxWeightIsLessThanSomething() throws Exception {
        // Initialize the database
        crestRepository.saveAndFlush(crest);

        // Get all the crestList where waxWeight is less than DEFAULT_WAX_WEIGHT
        defaultCrestShouldNotBeFound("waxWeight.lessThan=" + DEFAULT_WAX_WEIGHT);

        // Get all the crestList where waxWeight is less than UPDATED_WAX_WEIGHT
        defaultCrestShouldBeFound("waxWeight.lessThan=" + UPDATED_WAX_WEIGHT);
    }

    @Test
    @Transactional
    void getAllCrestsByWaxWeightIsGreaterThanSomething() throws Exception {
        // Initialize the database
        crestRepository.saveAndFlush(crest);

        // Get all the crestList where waxWeight is greater than DEFAULT_WAX_WEIGHT
        defaultCrestShouldNotBeFound("waxWeight.greaterThan=" + DEFAULT_WAX_WEIGHT);

        // Get all the crestList where waxWeight is greater than SMALLER_WAX_WEIGHT
        defaultCrestShouldBeFound("waxWeight.greaterThan=" + SMALLER_WAX_WEIGHT);
    }

    @Test
    @Transactional
    void getAllCrestsByTimeWastedCentrifugeIsEqualToSomething() throws Exception {
        // Initialize the database
        crestRepository.saveAndFlush(crest);

        // Get all the crestList where timeWastedCentrifuge equals to DEFAULT_TIME_WASTED_CENTRIFUGE
        defaultCrestShouldBeFound("timeWastedCentrifuge.equals=" + DEFAULT_TIME_WASTED_CENTRIFUGE);

        // Get all the crestList where timeWastedCentrifuge equals to UPDATED_TIME_WASTED_CENTRIFUGE
        defaultCrestShouldNotBeFound("timeWastedCentrifuge.equals=" + UPDATED_TIME_WASTED_CENTRIFUGE);
    }

    @Test
    @Transactional
    void getAllCrestsByTimeWastedCentrifugeIsInShouldWork() throws Exception {
        // Initialize the database
        crestRepository.saveAndFlush(crest);

        // Get all the crestList where timeWastedCentrifuge in DEFAULT_TIME_WASTED_CENTRIFUGE or UPDATED_TIME_WASTED_CENTRIFUGE
        defaultCrestShouldBeFound("timeWastedCentrifuge.in=" + DEFAULT_TIME_WASTED_CENTRIFUGE + "," + UPDATED_TIME_WASTED_CENTRIFUGE);

        // Get all the crestList where timeWastedCentrifuge equals to UPDATED_TIME_WASTED_CENTRIFUGE
        defaultCrestShouldNotBeFound("timeWastedCentrifuge.in=" + UPDATED_TIME_WASTED_CENTRIFUGE);
    }

    @Test
    @Transactional
    void getAllCrestsByTimeWastedCentrifugeIsNullOrNotNull() throws Exception {
        // Initialize the database
        crestRepository.saveAndFlush(crest);

        // Get all the crestList where timeWastedCentrifuge is not null
        defaultCrestShouldBeFound("timeWastedCentrifuge.specified=true");

        // Get all the crestList where timeWastedCentrifuge is null
        defaultCrestShouldNotBeFound("timeWastedCentrifuge.specified=false");
    }

    @Test
    @Transactional
    void getAllCrestsByTimeWastedCentrifugeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        crestRepository.saveAndFlush(crest);

        // Get all the crestList where timeWastedCentrifuge is greater than or equal to DEFAULT_TIME_WASTED_CENTRIFUGE
        defaultCrestShouldBeFound("timeWastedCentrifuge.greaterThanOrEqual=" + DEFAULT_TIME_WASTED_CENTRIFUGE);

        // Get all the crestList where timeWastedCentrifuge is greater than or equal to UPDATED_TIME_WASTED_CENTRIFUGE
        defaultCrestShouldNotBeFound("timeWastedCentrifuge.greaterThanOrEqual=" + UPDATED_TIME_WASTED_CENTRIFUGE);
    }

    @Test
    @Transactional
    void getAllCrestsByTimeWastedCentrifugeIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        crestRepository.saveAndFlush(crest);

        // Get all the crestList where timeWastedCentrifuge is less than or equal to DEFAULT_TIME_WASTED_CENTRIFUGE
        defaultCrestShouldBeFound("timeWastedCentrifuge.lessThanOrEqual=" + DEFAULT_TIME_WASTED_CENTRIFUGE);

        // Get all the crestList where timeWastedCentrifuge is less than or equal to SMALLER_TIME_WASTED_CENTRIFUGE
        defaultCrestShouldNotBeFound("timeWastedCentrifuge.lessThanOrEqual=" + SMALLER_TIME_WASTED_CENTRIFUGE);
    }

    @Test
    @Transactional
    void getAllCrestsByTimeWastedCentrifugeIsLessThanSomething() throws Exception {
        // Initialize the database
        crestRepository.saveAndFlush(crest);

        // Get all the crestList where timeWastedCentrifuge is less than DEFAULT_TIME_WASTED_CENTRIFUGE
        defaultCrestShouldNotBeFound("timeWastedCentrifuge.lessThan=" + DEFAULT_TIME_WASTED_CENTRIFUGE);

        // Get all the crestList where timeWastedCentrifuge is less than UPDATED_TIME_WASTED_CENTRIFUGE
        defaultCrestShouldBeFound("timeWastedCentrifuge.lessThan=" + UPDATED_TIME_WASTED_CENTRIFUGE);
    }

    @Test
    @Transactional
    void getAllCrestsByTimeWastedCentrifugeIsGreaterThanSomething() throws Exception {
        // Initialize the database
        crestRepository.saveAndFlush(crest);

        // Get all the crestList where timeWastedCentrifuge is greater than DEFAULT_TIME_WASTED_CENTRIFUGE
        defaultCrestShouldNotBeFound("timeWastedCentrifuge.greaterThan=" + DEFAULT_TIME_WASTED_CENTRIFUGE);

        // Get all the crestList where timeWastedCentrifuge is greater than SMALLER_TIME_WASTED_CENTRIFUGE
        defaultCrestShouldBeFound("timeWastedCentrifuge.greaterThan=" + SMALLER_TIME_WASTED_CENTRIFUGE);
    }

    @Test
    @Transactional
    void getAllCrestsByInitialDateDecantationIsEqualToSomething() throws Exception {
        // Initialize the database
        crestRepository.saveAndFlush(crest);

        // Get all the crestList where initialDateDecantation equals to DEFAULT_INITIAL_DATE_DECANTATION
        defaultCrestShouldBeFound("initialDateDecantation.equals=" + DEFAULT_INITIAL_DATE_DECANTATION);

        // Get all the crestList where initialDateDecantation equals to UPDATED_INITIAL_DATE_DECANTATION
        defaultCrestShouldNotBeFound("initialDateDecantation.equals=" + UPDATED_INITIAL_DATE_DECANTATION);
    }

    @Test
    @Transactional
    void getAllCrestsByInitialDateDecantationIsInShouldWork() throws Exception {
        // Initialize the database
        crestRepository.saveAndFlush(crest);

        // Get all the crestList where initialDateDecantation in DEFAULT_INITIAL_DATE_DECANTATION or UPDATED_INITIAL_DATE_DECANTATION
        defaultCrestShouldBeFound("initialDateDecantation.in=" + DEFAULT_INITIAL_DATE_DECANTATION + "," + UPDATED_INITIAL_DATE_DECANTATION);

        // Get all the crestList where initialDateDecantation equals to UPDATED_INITIAL_DATE_DECANTATION
        defaultCrestShouldNotBeFound("initialDateDecantation.in=" + UPDATED_INITIAL_DATE_DECANTATION);
    }

    @Test
    @Transactional
    void getAllCrestsByInitialDateDecantationIsNullOrNotNull() throws Exception {
        // Initialize the database
        crestRepository.saveAndFlush(crest);

        // Get all the crestList where initialDateDecantation is not null
        defaultCrestShouldBeFound("initialDateDecantation.specified=true");

        // Get all the crestList where initialDateDecantation is null
        defaultCrestShouldNotBeFound("initialDateDecantation.specified=false");
    }

    @Test
    @Transactional
    void getAllCrestsByInitialDateDecantationIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        crestRepository.saveAndFlush(crest);

        // Get all the crestList where initialDateDecantation is greater than or equal to DEFAULT_INITIAL_DATE_DECANTATION
        defaultCrestShouldBeFound("initialDateDecantation.greaterThanOrEqual=" + DEFAULT_INITIAL_DATE_DECANTATION);

        // Get all the crestList where initialDateDecantation is greater than or equal to UPDATED_INITIAL_DATE_DECANTATION
        defaultCrestShouldNotBeFound("initialDateDecantation.greaterThanOrEqual=" + UPDATED_INITIAL_DATE_DECANTATION);
    }

    @Test
    @Transactional
    void getAllCrestsByInitialDateDecantationIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        crestRepository.saveAndFlush(crest);

        // Get all the crestList where initialDateDecantation is less than or equal to DEFAULT_INITIAL_DATE_DECANTATION
        defaultCrestShouldBeFound("initialDateDecantation.lessThanOrEqual=" + DEFAULT_INITIAL_DATE_DECANTATION);

        // Get all the crestList where initialDateDecantation is less than or equal to SMALLER_INITIAL_DATE_DECANTATION
        defaultCrestShouldNotBeFound("initialDateDecantation.lessThanOrEqual=" + SMALLER_INITIAL_DATE_DECANTATION);
    }

    @Test
    @Transactional
    void getAllCrestsByInitialDateDecantationIsLessThanSomething() throws Exception {
        // Initialize the database
        crestRepository.saveAndFlush(crest);

        // Get all the crestList where initialDateDecantation is less than DEFAULT_INITIAL_DATE_DECANTATION
        defaultCrestShouldNotBeFound("initialDateDecantation.lessThan=" + DEFAULT_INITIAL_DATE_DECANTATION);

        // Get all the crestList where initialDateDecantation is less than UPDATED_INITIAL_DATE_DECANTATION
        defaultCrestShouldBeFound("initialDateDecantation.lessThan=" + UPDATED_INITIAL_DATE_DECANTATION);
    }

    @Test
    @Transactional
    void getAllCrestsByInitialDateDecantationIsGreaterThanSomething() throws Exception {
        // Initialize the database
        crestRepository.saveAndFlush(crest);

        // Get all the crestList where initialDateDecantation is greater than DEFAULT_INITIAL_DATE_DECANTATION
        defaultCrestShouldNotBeFound("initialDateDecantation.greaterThan=" + DEFAULT_INITIAL_DATE_DECANTATION);

        // Get all the crestList where initialDateDecantation is greater than SMALLER_INITIAL_DATE_DECANTATION
        defaultCrestShouldBeFound("initialDateDecantation.greaterThan=" + SMALLER_INITIAL_DATE_DECANTATION);
    }

    @Test
    @Transactional
    void getAllCrestsByFinalDateDecantationIsEqualToSomething() throws Exception {
        // Initialize the database
        crestRepository.saveAndFlush(crest);

        // Get all the crestList where finalDateDecantation equals to DEFAULT_FINAL_DATE_DECANTATION
        defaultCrestShouldBeFound("finalDateDecantation.equals=" + DEFAULT_FINAL_DATE_DECANTATION);

        // Get all the crestList where finalDateDecantation equals to UPDATED_FINAL_DATE_DECANTATION
        defaultCrestShouldNotBeFound("finalDateDecantation.equals=" + UPDATED_FINAL_DATE_DECANTATION);
    }

    @Test
    @Transactional
    void getAllCrestsByFinalDateDecantationIsInShouldWork() throws Exception {
        // Initialize the database
        crestRepository.saveAndFlush(crest);

        // Get all the crestList where finalDateDecantation in DEFAULT_FINAL_DATE_DECANTATION or UPDATED_FINAL_DATE_DECANTATION
        defaultCrestShouldBeFound("finalDateDecantation.in=" + DEFAULT_FINAL_DATE_DECANTATION + "," + UPDATED_FINAL_DATE_DECANTATION);

        // Get all the crestList where finalDateDecantation equals to UPDATED_FINAL_DATE_DECANTATION
        defaultCrestShouldNotBeFound("finalDateDecantation.in=" + UPDATED_FINAL_DATE_DECANTATION);
    }

    @Test
    @Transactional
    void getAllCrestsByFinalDateDecantationIsNullOrNotNull() throws Exception {
        // Initialize the database
        crestRepository.saveAndFlush(crest);

        // Get all the crestList where finalDateDecantation is not null
        defaultCrestShouldBeFound("finalDateDecantation.specified=true");

        // Get all the crestList where finalDateDecantation is null
        defaultCrestShouldNotBeFound("finalDateDecantation.specified=false");
    }

    @Test
    @Transactional
    void getAllCrestsByFinalDateDecantationIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        crestRepository.saveAndFlush(crest);

        // Get all the crestList where finalDateDecantation is greater than or equal to DEFAULT_FINAL_DATE_DECANTATION
        defaultCrestShouldBeFound("finalDateDecantation.greaterThanOrEqual=" + DEFAULT_FINAL_DATE_DECANTATION);

        // Get all the crestList where finalDateDecantation is greater than or equal to UPDATED_FINAL_DATE_DECANTATION
        defaultCrestShouldNotBeFound("finalDateDecantation.greaterThanOrEqual=" + UPDATED_FINAL_DATE_DECANTATION);
    }

    @Test
    @Transactional
    void getAllCrestsByFinalDateDecantationIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        crestRepository.saveAndFlush(crest);

        // Get all the crestList where finalDateDecantation is less than or equal to DEFAULT_FINAL_DATE_DECANTATION
        defaultCrestShouldBeFound("finalDateDecantation.lessThanOrEqual=" + DEFAULT_FINAL_DATE_DECANTATION);

        // Get all the crestList where finalDateDecantation is less than or equal to SMALLER_FINAL_DATE_DECANTATION
        defaultCrestShouldNotBeFound("finalDateDecantation.lessThanOrEqual=" + SMALLER_FINAL_DATE_DECANTATION);
    }

    @Test
    @Transactional
    void getAllCrestsByFinalDateDecantationIsLessThanSomething() throws Exception {
        // Initialize the database
        crestRepository.saveAndFlush(crest);

        // Get all the crestList where finalDateDecantation is less than DEFAULT_FINAL_DATE_DECANTATION
        defaultCrestShouldNotBeFound("finalDateDecantation.lessThan=" + DEFAULT_FINAL_DATE_DECANTATION);

        // Get all the crestList where finalDateDecantation is less than UPDATED_FINAL_DATE_DECANTATION
        defaultCrestShouldBeFound("finalDateDecantation.lessThan=" + UPDATED_FINAL_DATE_DECANTATION);
    }

    @Test
    @Transactional
    void getAllCrestsByFinalDateDecantationIsGreaterThanSomething() throws Exception {
        // Initialize the database
        crestRepository.saveAndFlush(crest);

        // Get all the crestList where finalDateDecantation is greater than DEFAULT_FINAL_DATE_DECANTATION
        defaultCrestShouldNotBeFound("finalDateDecantation.greaterThan=" + DEFAULT_FINAL_DATE_DECANTATION);

        // Get all the crestList where finalDateDecantation is greater than SMALLER_FINAL_DATE_DECANTATION
        defaultCrestShouldBeFound("finalDateDecantation.greaterThan=" + SMALLER_FINAL_DATE_DECANTATION);
    }

    @Test
    @Transactional
    void getAllCrestsByProducedHoneyQuantityIsEqualToSomething() throws Exception {
        // Initialize the database
        crestRepository.saveAndFlush(crest);

        // Get all the crestList where producedHoneyQuantity equals to DEFAULT_PRODUCED_HONEY_QUANTITY
        defaultCrestShouldBeFound("producedHoneyQuantity.equals=" + DEFAULT_PRODUCED_HONEY_QUANTITY);

        // Get all the crestList where producedHoneyQuantity equals to UPDATED_PRODUCED_HONEY_QUANTITY
        defaultCrestShouldNotBeFound("producedHoneyQuantity.equals=" + UPDATED_PRODUCED_HONEY_QUANTITY);
    }

    @Test
    @Transactional
    void getAllCrestsByProducedHoneyQuantityIsInShouldWork() throws Exception {
        // Initialize the database
        crestRepository.saveAndFlush(crest);

        // Get all the crestList where producedHoneyQuantity in DEFAULT_PRODUCED_HONEY_QUANTITY or UPDATED_PRODUCED_HONEY_QUANTITY
        defaultCrestShouldBeFound("producedHoneyQuantity.in=" + DEFAULT_PRODUCED_HONEY_QUANTITY + "," + UPDATED_PRODUCED_HONEY_QUANTITY);

        // Get all the crestList where producedHoneyQuantity equals to UPDATED_PRODUCED_HONEY_QUANTITY
        defaultCrestShouldNotBeFound("producedHoneyQuantity.in=" + UPDATED_PRODUCED_HONEY_QUANTITY);
    }

    @Test
    @Transactional
    void getAllCrestsByProducedHoneyQuantityIsNullOrNotNull() throws Exception {
        // Initialize the database
        crestRepository.saveAndFlush(crest);

        // Get all the crestList where producedHoneyQuantity is not null
        defaultCrestShouldBeFound("producedHoneyQuantity.specified=true");

        // Get all the crestList where producedHoneyQuantity is null
        defaultCrestShouldNotBeFound("producedHoneyQuantity.specified=false");
    }

    @Test
    @Transactional
    void getAllCrestsByProducedHoneyQuantityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        crestRepository.saveAndFlush(crest);

        // Get all the crestList where producedHoneyQuantity is greater than or equal to DEFAULT_PRODUCED_HONEY_QUANTITY
        defaultCrestShouldBeFound("producedHoneyQuantity.greaterThanOrEqual=" + DEFAULT_PRODUCED_HONEY_QUANTITY);

        // Get all the crestList where producedHoneyQuantity is greater than or equal to UPDATED_PRODUCED_HONEY_QUANTITY
        defaultCrestShouldNotBeFound("producedHoneyQuantity.greaterThanOrEqual=" + UPDATED_PRODUCED_HONEY_QUANTITY);
    }

    @Test
    @Transactional
    void getAllCrestsByProducedHoneyQuantityIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        crestRepository.saveAndFlush(crest);

        // Get all the crestList where producedHoneyQuantity is less than or equal to DEFAULT_PRODUCED_HONEY_QUANTITY
        defaultCrestShouldBeFound("producedHoneyQuantity.lessThanOrEqual=" + DEFAULT_PRODUCED_HONEY_QUANTITY);

        // Get all the crestList where producedHoneyQuantity is less than or equal to SMALLER_PRODUCED_HONEY_QUANTITY
        defaultCrestShouldNotBeFound("producedHoneyQuantity.lessThanOrEqual=" + SMALLER_PRODUCED_HONEY_QUANTITY);
    }

    @Test
    @Transactional
    void getAllCrestsByProducedHoneyQuantityIsLessThanSomething() throws Exception {
        // Initialize the database
        crestRepository.saveAndFlush(crest);

        // Get all the crestList where producedHoneyQuantity is less than DEFAULT_PRODUCED_HONEY_QUANTITY
        defaultCrestShouldNotBeFound("producedHoneyQuantity.lessThan=" + DEFAULT_PRODUCED_HONEY_QUANTITY);

        // Get all the crestList where producedHoneyQuantity is less than UPDATED_PRODUCED_HONEY_QUANTITY
        defaultCrestShouldBeFound("producedHoneyQuantity.lessThan=" + UPDATED_PRODUCED_HONEY_QUANTITY);
    }

    @Test
    @Transactional
    void getAllCrestsByProducedHoneyQuantityIsGreaterThanSomething() throws Exception {
        // Initialize the database
        crestRepository.saveAndFlush(crest);

        // Get all the crestList where producedHoneyQuantity is greater than DEFAULT_PRODUCED_HONEY_QUANTITY
        defaultCrestShouldNotBeFound("producedHoneyQuantity.greaterThan=" + DEFAULT_PRODUCED_HONEY_QUANTITY);

        // Get all the crestList where producedHoneyQuantity is greater than SMALLER_PRODUCED_HONEY_QUANTITY
        defaultCrestShouldBeFound("producedHoneyQuantity.greaterThan=" + SMALLER_PRODUCED_HONEY_QUANTITY);
    }

    @Test
    @Transactional
    void getAllCrestsByStateIsEqualToSomething() throws Exception {
        // Initialize the database
        crestRepository.saveAndFlush(crest);

        // Get all the crestList where state equals to DEFAULT_STATE
        defaultCrestShouldBeFound("state.equals=" + DEFAULT_STATE);

        // Get all the crestList where state equals to UPDATED_STATE
        defaultCrestShouldNotBeFound("state.equals=" + UPDATED_STATE);
    }

    @Test
    @Transactional
    void getAllCrestsByStateIsInShouldWork() throws Exception {
        // Initialize the database
        crestRepository.saveAndFlush(crest);

        // Get all the crestList where state in DEFAULT_STATE or UPDATED_STATE
        defaultCrestShouldBeFound("state.in=" + DEFAULT_STATE + "," + UPDATED_STATE);

        // Get all the crestList where state equals to UPDATED_STATE
        defaultCrestShouldNotBeFound("state.in=" + UPDATED_STATE);
    }

    @Test
    @Transactional
    void getAllCrestsByStateIsNullOrNotNull() throws Exception {
        // Initialize the database
        crestRepository.saveAndFlush(crest);

        // Get all the crestList where state is not null
        defaultCrestShouldBeFound("state.specified=true");

        // Get all the crestList where state is null
        defaultCrestShouldNotBeFound("state.specified=false");
    }

    @Test
    @Transactional
    void getAllCrestsByHiveIsEqualToSomething() throws Exception {
        Hive hive;
        if (TestUtil.findAll(em, Hive.class).isEmpty()) {
            crestRepository.saveAndFlush(crest);
            hive = HiveResourceIT.createEntity(em);
        } else {
            hive = TestUtil.findAll(em, Hive.class).get(0);
        }
        em.persist(hive);
        em.flush();
        crest.setHive(hive);
        crestRepository.saveAndFlush(crest);
        Long hiveId = hive.getId();

        // Get all the crestList where hive equals to hiveId
        defaultCrestShouldBeFound("hiveId.equals=" + hiveId);

        // Get all the crestList where hive equals to (hiveId + 1)
        defaultCrestShouldNotBeFound("hiveId.equals=" + (hiveId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultCrestShouldBeFound(String filter) throws Exception {
        restCrestMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(crest.getId().intValue())))
            .andExpect(jsonPath("$.[*].combFrameQuantity").value(hasItem(DEFAULT_COMB_FRAME_QUANTITY)))
            .andExpect(jsonPath("$.[*].waxWeight").value(hasItem(DEFAULT_WAX_WEIGHT.doubleValue())))
            .andExpect(jsonPath("$.[*].timeWastedCentrifuge").value(hasItem(DEFAULT_TIME_WASTED_CENTRIFUGE.doubleValue())))
            .andExpect(jsonPath("$.[*].initialDateDecantation").value(hasItem(sameInstant(DEFAULT_INITIAL_DATE_DECANTATION))))
            .andExpect(jsonPath("$.[*].finalDateDecantation").value(hasItem(sameInstant(DEFAULT_FINAL_DATE_DECANTATION))))
            .andExpect(jsonPath("$.[*].producedHoneyQuantity").value(hasItem(DEFAULT_PRODUCED_HONEY_QUANTITY.doubleValue())))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE.toString())));

        // Check, that the count call also returns 1
        restCrestMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultCrestShouldNotBeFound(String filter) throws Exception {
        restCrestMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCrestMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingCrest() throws Exception {
        // Get the crest
        restCrestMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCrest() throws Exception {
        // Initialize the database
        crestRepository.saveAndFlush(crest);

        int databaseSizeBeforeUpdate = crestRepository.findAll().size();

        // Update the crest
        Crest updatedCrest = crestRepository.findById(crest.getId()).get();
        // Disconnect from session so that the updates on updatedCrest are not directly saved in db
        em.detach(updatedCrest);
        updatedCrest
            .combFrameQuantity(UPDATED_COMB_FRAME_QUANTITY)
            .waxWeight(UPDATED_WAX_WEIGHT)
            .timeWastedCentrifuge(UPDATED_TIME_WASTED_CENTRIFUGE)
            .initialDateDecantation(UPDATED_INITIAL_DATE_DECANTATION)
            .finalDateDecantation(UPDATED_FINAL_DATE_DECANTATION)
            .producedHoneyQuantity(UPDATED_PRODUCED_HONEY_QUANTITY)
            .state(UPDATED_STATE);
        CrestDTO crestDTO = crestMapper.toDto(updatedCrest);
        crestDTO.setHiveId(0);
        restCrestMockMvc
            .perform(
                put(ENTITY_API_URL_ID, crestDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(crestDTO))
            )
            .andExpect(status().isOk());

        // Validate the Crest in the database
        List<Crest> crestList = crestRepository.findAll();
        assertThat(crestList).hasSize(databaseSizeBeforeUpdate);
        Crest testCrest = crestList.get(crestList.size() - 1);
        assertThat(testCrest.getCombFrameQuantity()).isEqualTo(UPDATED_COMB_FRAME_QUANTITY);
        assertThat(testCrest.getWaxWeight()).isEqualTo(UPDATED_WAX_WEIGHT);
        assertThat(testCrest.getTimeWastedCentrifuge()).isEqualTo(UPDATED_TIME_WASTED_CENTRIFUGE);
        assertThat(testCrest.getInitialDateDecantation()).isEqualTo(UPDATED_INITIAL_DATE_DECANTATION);
        assertThat(testCrest.getFinalDateDecantation()).isEqualTo(UPDATED_FINAL_DATE_DECANTATION);
        assertThat(testCrest.getProducedHoneyQuantity()).isEqualTo(UPDATED_PRODUCED_HONEY_QUANTITY);
        assertThat(testCrest.getState()).isEqualTo(UPDATED_STATE);
    }

    @Test
    @Transactional
    void putNonExistingCrest() throws Exception {
        int databaseSizeBeforeUpdate = crestRepository.findAll().size();
        crest.setId(count.incrementAndGet());

        // Create the Crest
        CrestDTO crestDTO = crestMapper.toDto(crest);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCrestMockMvc
            .perform(
                put(ENTITY_API_URL_ID, crestDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(crestDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Crest in the database
        List<Crest> crestList = crestRepository.findAll();
        assertThat(crestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCrest() throws Exception {
        int databaseSizeBeforeUpdate = crestRepository.findAll().size();
        crest.setId(count.incrementAndGet());

        // Create the Crest
        CrestDTO crestDTO = crestMapper.toDto(crest);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCrestMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(crestDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Crest in the database
        List<Crest> crestList = crestRepository.findAll();
        assertThat(crestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCrest() throws Exception {
        int databaseSizeBeforeUpdate = crestRepository.findAll().size();
        crest.setId(count.incrementAndGet());

        // Create the Crest
        CrestDTO crestDTO = crestMapper.toDto(crest);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCrestMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(crestDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Crest in the database
        List<Crest> crestList = crestRepository.findAll();
        assertThat(crestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCrestWithPatch() throws Exception {
        // Initialize the database
        crestRepository.saveAndFlush(crest);

        int databaseSizeBeforeUpdate = crestRepository.findAll().size();

        // Update the crest using partial update
        Crest partialUpdatedCrest = new Crest();
        partialUpdatedCrest.setId(crest.getId());

        partialUpdatedCrest.state(UPDATED_STATE);

        restCrestMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCrest.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCrest))
            )
            .andExpect(status().isOk());

        // Validate the Crest in the database
        List<Crest> crestList = crestRepository.findAll();
        assertThat(crestList).hasSize(databaseSizeBeforeUpdate);
        Crest testCrest = crestList.get(crestList.size() - 1);
        assertThat(testCrest.getCombFrameQuantity()).isEqualTo(DEFAULT_COMB_FRAME_QUANTITY);
        assertThat(testCrest.getWaxWeight()).isEqualTo(DEFAULT_WAX_WEIGHT);
        assertThat(testCrest.getTimeWastedCentrifuge()).isEqualTo(DEFAULT_TIME_WASTED_CENTRIFUGE);
        assertThat(testCrest.getInitialDateDecantation()).isEqualTo(DEFAULT_INITIAL_DATE_DECANTATION);
        assertThat(testCrest.getFinalDateDecantation()).isEqualTo(DEFAULT_FINAL_DATE_DECANTATION);
        assertThat(testCrest.getProducedHoneyQuantity()).isEqualTo(DEFAULT_PRODUCED_HONEY_QUANTITY);
        assertThat(testCrest.getState()).isEqualTo(UPDATED_STATE);
    }

    @Test
    @Transactional
    void fullUpdateCrestWithPatch() throws Exception {
        // Initialize the database
        crestRepository.saveAndFlush(crest);

        int databaseSizeBeforeUpdate = crestRepository.findAll().size();

        // Update the crest using partial update
        Crest partialUpdatedCrest = new Crest();
        partialUpdatedCrest.setId(crest.getId());

        partialUpdatedCrest
            .combFrameQuantity(UPDATED_COMB_FRAME_QUANTITY)
            .waxWeight(UPDATED_WAX_WEIGHT)
            .timeWastedCentrifuge(UPDATED_TIME_WASTED_CENTRIFUGE)
            .initialDateDecantation(UPDATED_INITIAL_DATE_DECANTATION)
            .finalDateDecantation(UPDATED_FINAL_DATE_DECANTATION)
            .producedHoneyQuantity(UPDATED_PRODUCED_HONEY_QUANTITY)
            .state(UPDATED_STATE);

        restCrestMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCrest.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCrest))
            )
            .andExpect(status().isOk());

        // Validate the Crest in the database
        List<Crest> crestList = crestRepository.findAll();
        assertThat(crestList).hasSize(databaseSizeBeforeUpdate);
        Crest testCrest = crestList.get(crestList.size() - 1);
        assertThat(testCrest.getCombFrameQuantity()).isEqualTo(UPDATED_COMB_FRAME_QUANTITY);
        assertThat(testCrest.getWaxWeight()).isEqualTo(UPDATED_WAX_WEIGHT);
        assertThat(testCrest.getTimeWastedCentrifuge()).isEqualTo(UPDATED_TIME_WASTED_CENTRIFUGE);
        assertThat(testCrest.getInitialDateDecantation()).isEqualTo(UPDATED_INITIAL_DATE_DECANTATION);
        assertThat(testCrest.getFinalDateDecantation()).isEqualTo(UPDATED_FINAL_DATE_DECANTATION);
        assertThat(testCrest.getProducedHoneyQuantity()).isEqualTo(UPDATED_PRODUCED_HONEY_QUANTITY);
        assertThat(testCrest.getState()).isEqualTo(UPDATED_STATE);
    }

    @Test
    @Transactional
    void patchNonExistingCrest() throws Exception {
        int databaseSizeBeforeUpdate = crestRepository.findAll().size();
        crest.setId(count.incrementAndGet());

        // Create the Crest
        CrestDTO crestDTO = crestMapper.toDto(crest);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCrestMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, crestDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(crestDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Crest in the database
        List<Crest> crestList = crestRepository.findAll();
        assertThat(crestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCrest() throws Exception {
        int databaseSizeBeforeUpdate = crestRepository.findAll().size();
        crest.setId(count.incrementAndGet());

        // Create the Crest
        CrestDTO crestDTO = crestMapper.toDto(crest);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCrestMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(crestDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Crest in the database
        List<Crest> crestList = crestRepository.findAll();
        assertThat(crestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCrest() throws Exception {
        int databaseSizeBeforeUpdate = crestRepository.findAll().size();
        crest.setId(count.incrementAndGet());

        // Create the Crest
        CrestDTO crestDTO = crestMapper.toDto(crest);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCrestMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(crestDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Crest in the database
        List<Crest> crestList = crestRepository.findAll();
        assertThat(crestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCrest() throws Exception {
        // Initialize the database
        crestRepository.saveAndFlush(crest);

        int databaseSizeBeforeDelete = crestRepository.findAll().size();

        // Delete the crest
        restCrestMockMvc
            .perform(delete(ENTITY_API_URL_ID, crest.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Crest> crestList = crestRepository.findAll();
        assertThat(crestList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
