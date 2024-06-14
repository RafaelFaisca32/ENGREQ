package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.Apiary;
import com.mycompany.myapp.domain.ApiaryZone;
import com.mycompany.myapp.domain.Beekeeper;
import com.mycompany.myapp.domain.Hive;
import com.mycompany.myapp.domain.TranshumanceRequest;
import com.mycompany.myapp.domain.enumeration.ApiaryState;
import com.mycompany.myapp.repository.ApiaryRepository;
import com.mycompany.myapp.service.criteria.ApiaryCriteria;
import com.mycompany.myapp.service.dto.ApiaryDTO;
import com.mycompany.myapp.service.mapper.ApiaryMapper;
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
 * Integration tests for the {@link ApiaryResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ApiaryResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final ApiaryState DEFAULT_STATE = ApiaryState.NOT_APPROVED;
    private static final ApiaryState UPDATED_STATE = ApiaryState.APPROVED;

    private static final String DEFAULT_COORD_X = "AAAAAAAAAA";
    private static final String UPDATED_COORD_X = "BBBBBBBBBB";

    private static final String DEFAULT_COORD_Y = "AAAAAAAAAA";
    private static final String UPDATED_COORD_Y = "BBBBBBBBBB";

    private static final String DEFAULT_COORD_Z = "AAAAAAAAAA";
    private static final String UPDATED_COORD_Z = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUMBER = 1;
    private static final Integer UPDATED_NUMBER = 2;
    private static final Integer SMALLER_NUMBER = 1 - 1;

    private static final Boolean DEFAULT_INTENSIVE = false;
    private static final Boolean UPDATED_INTENSIVE = true;

    private static final Boolean DEFAULT_TRANSHUMANCE = false;
    private static final Boolean UPDATED_TRANSHUMANCE = true;

    private static final String ENTITY_API_URL = "/api/apiaries";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ApiaryRepository apiaryRepository;

    @Autowired
    private ApiaryMapper apiaryMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restApiaryMockMvc;

    private Apiary apiary;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Apiary createEntity(EntityManager em) {
        Apiary apiary = new Apiary()
            .name(DEFAULT_NAME)
            .state(DEFAULT_STATE)
            .coordX(DEFAULT_COORD_X)
            .coordY(DEFAULT_COORD_Y)
            .coordZ(DEFAULT_COORD_Z)
            .number(DEFAULT_NUMBER)
            .intensive(DEFAULT_INTENSIVE)
            .transhumance(DEFAULT_TRANSHUMANCE);
        return apiary;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Apiary createUpdatedEntity(EntityManager em) {
        Apiary apiary = new Apiary()
            .name(UPDATED_NAME)
            .state(UPDATED_STATE)
            .coordX(UPDATED_COORD_X)
            .coordY(UPDATED_COORD_Y)
            .coordZ(UPDATED_COORD_Z)
            .number(UPDATED_NUMBER)
            .intensive(UPDATED_INTENSIVE)
            .transhumance(UPDATED_TRANSHUMANCE);
        return apiary;
    }

    @BeforeEach
    public void initTest() {
        apiary = createEntity(em);
    }

    @Test
    @Transactional
    void createApiaryWithExistingId() throws Exception {
        // Create the Apiary with an existing ID
        apiary.setId(1L);
        ApiaryDTO apiaryDTO = apiaryMapper.toDto(apiary);

        int databaseSizeBeforeCreate = apiaryRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restApiaryMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(apiaryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Apiary in the database
        List<Apiary> apiaryList = apiaryRepository.findAll();
        assertThat(apiaryList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = apiaryRepository.findAll().size();
        // set the field null
        apiary.setName(null);

        // Create the Apiary, which fails.
        ApiaryDTO apiaryDTO = apiaryMapper.toDto(apiary);

        restApiaryMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(apiaryDTO)))
            .andExpect(status().isBadRequest());

        List<Apiary> apiaryList = apiaryRepository.findAll();
        assertThat(apiaryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkStateIsRequired() throws Exception {
        int databaseSizeBeforeTest = apiaryRepository.findAll().size();
        // set the field null
        apiary.setState(null);

        // Create the Apiary, which fails.
        ApiaryDTO apiaryDTO = apiaryMapper.toDto(apiary);

        restApiaryMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(apiaryDTO)))
            .andExpect(status().isBadRequest());

        List<Apiary> apiaryList = apiaryRepository.findAll();
        assertThat(apiaryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCoordXIsRequired() throws Exception {
        int databaseSizeBeforeTest = apiaryRepository.findAll().size();
        // set the field null
        apiary.setCoordX(null);

        // Create the Apiary, which fails.
        ApiaryDTO apiaryDTO = apiaryMapper.toDto(apiary);

        restApiaryMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(apiaryDTO)))
            .andExpect(status().isBadRequest());

        List<Apiary> apiaryList = apiaryRepository.findAll();
        assertThat(apiaryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCoordYIsRequired() throws Exception {
        int databaseSizeBeforeTest = apiaryRepository.findAll().size();
        // set the field null
        apiary.setCoordY(null);

        // Create the Apiary, which fails.
        ApiaryDTO apiaryDTO = apiaryMapper.toDto(apiary);

        restApiaryMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(apiaryDTO)))
            .andExpect(status().isBadRequest());

        List<Apiary> apiaryList = apiaryRepository.findAll();
        assertThat(apiaryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCoordZIsRequired() throws Exception {
        int databaseSizeBeforeTest = apiaryRepository.findAll().size();
        // set the field null
        apiary.setCoordZ(null);

        // Create the Apiary, which fails.
        ApiaryDTO apiaryDTO = apiaryMapper.toDto(apiary);

        restApiaryMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(apiaryDTO)))
            .andExpect(status().isBadRequest());

        List<Apiary> apiaryList = apiaryRepository.findAll();
        assertThat(apiaryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = apiaryRepository.findAll().size();
        // set the field null
        apiary.setNumber(null);

        // Create the Apiary, which fails.
        ApiaryDTO apiaryDTO = apiaryMapper.toDto(apiary);

        restApiaryMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(apiaryDTO)))
            .andExpect(status().isBadRequest());

        List<Apiary> apiaryList = apiaryRepository.findAll();
        assertThat(apiaryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkIntensiveIsRequired() throws Exception {
        int databaseSizeBeforeTest = apiaryRepository.findAll().size();
        // set the field null
        apiary.setIntensive(null);

        // Create the Apiary, which fails.
        ApiaryDTO apiaryDTO = apiaryMapper.toDto(apiary);

        restApiaryMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(apiaryDTO)))
            .andExpect(status().isBadRequest());

        List<Apiary> apiaryList = apiaryRepository.findAll();
        assertThat(apiaryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTranshumanceIsRequired() throws Exception {
        int databaseSizeBeforeTest = apiaryRepository.findAll().size();
        // set the field null
        apiary.setTranshumance(null);

        // Create the Apiary, which fails.
        ApiaryDTO apiaryDTO = apiaryMapper.toDto(apiary);

        restApiaryMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(apiaryDTO)))
            .andExpect(status().isBadRequest());

        List<Apiary> apiaryList = apiaryRepository.findAll();
        assertThat(apiaryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllApiaries() throws Exception {
        // Initialize the database
        apiaryRepository.saveAndFlush(apiary);

        // Get all the apiaryList
        restApiaryMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(apiary.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE.toString())))
            .andExpect(jsonPath("$.[*].coordX").value(hasItem(DEFAULT_COORD_X)))
            .andExpect(jsonPath("$.[*].coordY").value(hasItem(DEFAULT_COORD_Y)))
            .andExpect(jsonPath("$.[*].coordZ").value(hasItem(DEFAULT_COORD_Z)))
            .andExpect(jsonPath("$.[*].number").value(hasItem(DEFAULT_NUMBER)))
            .andExpect(jsonPath("$.[*].intensive").value(hasItem(DEFAULT_INTENSIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].transhumance").value(hasItem(DEFAULT_TRANSHUMANCE.booleanValue())));
    }

    @Test
    @Transactional
    void getApiary() throws Exception {
        // Initialize the database
        apiaryRepository.saveAndFlush(apiary);

        // Get the apiary
        restApiaryMockMvc
            .perform(get(ENTITY_API_URL_ID, apiary.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(apiary.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE.toString()))
            .andExpect(jsonPath("$.coordX").value(DEFAULT_COORD_X))
            .andExpect(jsonPath("$.coordY").value(DEFAULT_COORD_Y))
            .andExpect(jsonPath("$.coordZ").value(DEFAULT_COORD_Z))
            .andExpect(jsonPath("$.number").value(DEFAULT_NUMBER))
            .andExpect(jsonPath("$.intensive").value(DEFAULT_INTENSIVE.booleanValue()))
            .andExpect(jsonPath("$.transhumance").value(DEFAULT_TRANSHUMANCE.booleanValue()));
    }

    @Test
    @Transactional
    void getApiariesByIdFiltering() throws Exception {
        // Initialize the database
        apiaryRepository.saveAndFlush(apiary);

        Long id = apiary.getId();

        defaultApiaryShouldBeFound("id.equals=" + id);
        defaultApiaryShouldNotBeFound("id.notEquals=" + id);

        defaultApiaryShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultApiaryShouldNotBeFound("id.greaterThan=" + id);

        defaultApiaryShouldBeFound("id.lessThanOrEqual=" + id);
        defaultApiaryShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllApiariesByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        apiaryRepository.saveAndFlush(apiary);

        // Get all the apiaryList where name equals to DEFAULT_NAME
        defaultApiaryShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the apiaryList where name equals to UPDATED_NAME
        defaultApiaryShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllApiariesByNameIsInShouldWork() throws Exception {
        // Initialize the database
        apiaryRepository.saveAndFlush(apiary);

        // Get all the apiaryList where name in DEFAULT_NAME or UPDATED_NAME
        defaultApiaryShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the apiaryList where name equals to UPDATED_NAME
        defaultApiaryShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllApiariesByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        apiaryRepository.saveAndFlush(apiary);

        // Get all the apiaryList where name is not null
        defaultApiaryShouldBeFound("name.specified=true");

        // Get all the apiaryList where name is null
        defaultApiaryShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    void getAllApiariesByNameContainsSomething() throws Exception {
        // Initialize the database
        apiaryRepository.saveAndFlush(apiary);

        // Get all the apiaryList where name contains DEFAULT_NAME
        defaultApiaryShouldBeFound("name.contains=" + DEFAULT_NAME);

        // Get all the apiaryList where name contains UPDATED_NAME
        defaultApiaryShouldNotBeFound("name.contains=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllApiariesByNameNotContainsSomething() throws Exception {
        // Initialize the database
        apiaryRepository.saveAndFlush(apiary);

        // Get all the apiaryList where name does not contain DEFAULT_NAME
        defaultApiaryShouldNotBeFound("name.doesNotContain=" + DEFAULT_NAME);

        // Get all the apiaryList where name does not contain UPDATED_NAME
        defaultApiaryShouldBeFound("name.doesNotContain=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllApiariesByStateIsEqualToSomething() throws Exception {
        // Initialize the database
        apiaryRepository.saveAndFlush(apiary);

        // Get all the apiaryList where state equals to DEFAULT_STATE
        defaultApiaryShouldBeFound("state.equals=" + DEFAULT_STATE);

        // Get all the apiaryList where state equals to UPDATED_STATE
        defaultApiaryShouldNotBeFound("state.equals=" + UPDATED_STATE);
    }

    @Test
    @Transactional
    void getAllApiariesByStateIsInShouldWork() throws Exception {
        // Initialize the database
        apiaryRepository.saveAndFlush(apiary);

        // Get all the apiaryList where state in DEFAULT_STATE or UPDATED_STATE
        defaultApiaryShouldBeFound("state.in=" + DEFAULT_STATE + "," + UPDATED_STATE);

        // Get all the apiaryList where state equals to UPDATED_STATE
        defaultApiaryShouldNotBeFound("state.in=" + UPDATED_STATE);
    }

    @Test
    @Transactional
    void getAllApiariesByStateIsNullOrNotNull() throws Exception {
        // Initialize the database
        apiaryRepository.saveAndFlush(apiary);

        // Get all the apiaryList where state is not null
        defaultApiaryShouldBeFound("state.specified=true");

        // Get all the apiaryList where state is null
        defaultApiaryShouldNotBeFound("state.specified=false");
    }

    @Test
    @Transactional
    void getAllApiariesByCoordXIsEqualToSomething() throws Exception {
        // Initialize the database
        apiaryRepository.saveAndFlush(apiary);

        // Get all the apiaryList where coordX equals to DEFAULT_COORD_X
        defaultApiaryShouldBeFound("coordX.equals=" + DEFAULT_COORD_X);

        // Get all the apiaryList where coordX equals to UPDATED_COORD_X
        defaultApiaryShouldNotBeFound("coordX.equals=" + UPDATED_COORD_X);
    }

    @Test
    @Transactional
    void getAllApiariesByCoordXIsInShouldWork() throws Exception {
        // Initialize the database
        apiaryRepository.saveAndFlush(apiary);

        // Get all the apiaryList where coordX in DEFAULT_COORD_X or UPDATED_COORD_X
        defaultApiaryShouldBeFound("coordX.in=" + DEFAULT_COORD_X + "," + UPDATED_COORD_X);

        // Get all the apiaryList where coordX equals to UPDATED_COORD_X
        defaultApiaryShouldNotBeFound("coordX.in=" + UPDATED_COORD_X);
    }

    @Test
    @Transactional
    void getAllApiariesByCoordXIsNullOrNotNull() throws Exception {
        // Initialize the database
        apiaryRepository.saveAndFlush(apiary);

        // Get all the apiaryList where coordX is not null
        defaultApiaryShouldBeFound("coordX.specified=true");

        // Get all the apiaryList where coordX is null
        defaultApiaryShouldNotBeFound("coordX.specified=false");
    }

    @Test
    @Transactional
    void getAllApiariesByCoordXContainsSomething() throws Exception {
        // Initialize the database
        apiaryRepository.saveAndFlush(apiary);

        // Get all the apiaryList where coordX contains DEFAULT_COORD_X
        defaultApiaryShouldBeFound("coordX.contains=" + DEFAULT_COORD_X);

        // Get all the apiaryList where coordX contains UPDATED_COORD_X
        defaultApiaryShouldNotBeFound("coordX.contains=" + UPDATED_COORD_X);
    }

    @Test
    @Transactional
    void getAllApiariesByCoordXNotContainsSomething() throws Exception {
        // Initialize the database
        apiaryRepository.saveAndFlush(apiary);

        // Get all the apiaryList where coordX does not contain DEFAULT_COORD_X
        defaultApiaryShouldNotBeFound("coordX.doesNotContain=" + DEFAULT_COORD_X);

        // Get all the apiaryList where coordX does not contain UPDATED_COORD_X
        defaultApiaryShouldBeFound("coordX.doesNotContain=" + UPDATED_COORD_X);
    }

    @Test
    @Transactional
    void getAllApiariesByCoordYIsEqualToSomething() throws Exception {
        // Initialize the database
        apiaryRepository.saveAndFlush(apiary);

        // Get all the apiaryList where coordY equals to DEFAULT_COORD_Y
        defaultApiaryShouldBeFound("coordY.equals=" + DEFAULT_COORD_Y);

        // Get all the apiaryList where coordY equals to UPDATED_COORD_Y
        defaultApiaryShouldNotBeFound("coordY.equals=" + UPDATED_COORD_Y);
    }

    @Test
    @Transactional
    void getAllApiariesByCoordYIsInShouldWork() throws Exception {
        // Initialize the database
        apiaryRepository.saveAndFlush(apiary);

        // Get all the apiaryList where coordY in DEFAULT_COORD_Y or UPDATED_COORD_Y
        defaultApiaryShouldBeFound("coordY.in=" + DEFAULT_COORD_Y + "," + UPDATED_COORD_Y);

        // Get all the apiaryList where coordY equals to UPDATED_COORD_Y
        defaultApiaryShouldNotBeFound("coordY.in=" + UPDATED_COORD_Y);
    }

    @Test
    @Transactional
    void getAllApiariesByCoordYIsNullOrNotNull() throws Exception {
        // Initialize the database
        apiaryRepository.saveAndFlush(apiary);

        // Get all the apiaryList where coordY is not null
        defaultApiaryShouldBeFound("coordY.specified=true");

        // Get all the apiaryList where coordY is null
        defaultApiaryShouldNotBeFound("coordY.specified=false");
    }

    @Test
    @Transactional
    void getAllApiariesByCoordYContainsSomething() throws Exception {
        // Initialize the database
        apiaryRepository.saveAndFlush(apiary);

        // Get all the apiaryList where coordY contains DEFAULT_COORD_Y
        defaultApiaryShouldBeFound("coordY.contains=" + DEFAULT_COORD_Y);

        // Get all the apiaryList where coordY contains UPDATED_COORD_Y
        defaultApiaryShouldNotBeFound("coordY.contains=" + UPDATED_COORD_Y);
    }

    @Test
    @Transactional
    void getAllApiariesByCoordYNotContainsSomething() throws Exception {
        // Initialize the database
        apiaryRepository.saveAndFlush(apiary);

        // Get all the apiaryList where coordY does not contain DEFAULT_COORD_Y
        defaultApiaryShouldNotBeFound("coordY.doesNotContain=" + DEFAULT_COORD_Y);

        // Get all the apiaryList where coordY does not contain UPDATED_COORD_Y
        defaultApiaryShouldBeFound("coordY.doesNotContain=" + UPDATED_COORD_Y);
    }

    @Test
    @Transactional
    void getAllApiariesByCoordZIsEqualToSomething() throws Exception {
        // Initialize the database
        apiaryRepository.saveAndFlush(apiary);

        // Get all the apiaryList where coordZ equals to DEFAULT_COORD_Z
        defaultApiaryShouldBeFound("coordZ.equals=" + DEFAULT_COORD_Z);

        // Get all the apiaryList where coordZ equals to UPDATED_COORD_Z
        defaultApiaryShouldNotBeFound("coordZ.equals=" + UPDATED_COORD_Z);
    }

    @Test
    @Transactional
    void getAllApiariesByCoordZIsInShouldWork() throws Exception {
        // Initialize the database
        apiaryRepository.saveAndFlush(apiary);

        // Get all the apiaryList where coordZ in DEFAULT_COORD_Z or UPDATED_COORD_Z
        defaultApiaryShouldBeFound("coordZ.in=" + DEFAULT_COORD_Z + "," + UPDATED_COORD_Z);

        // Get all the apiaryList where coordZ equals to UPDATED_COORD_Z
        defaultApiaryShouldNotBeFound("coordZ.in=" + UPDATED_COORD_Z);
    }

    @Test
    @Transactional
    void getAllApiariesByCoordZIsNullOrNotNull() throws Exception {
        // Initialize the database
        apiaryRepository.saveAndFlush(apiary);

        // Get all the apiaryList where coordZ is not null
        defaultApiaryShouldBeFound("coordZ.specified=true");

        // Get all the apiaryList where coordZ is null
        defaultApiaryShouldNotBeFound("coordZ.specified=false");
    }

    @Test
    @Transactional
    void getAllApiariesByCoordZContainsSomething() throws Exception {
        // Initialize the database
        apiaryRepository.saveAndFlush(apiary);

        // Get all the apiaryList where coordZ contains DEFAULT_COORD_Z
        defaultApiaryShouldBeFound("coordZ.contains=" + DEFAULT_COORD_Z);

        // Get all the apiaryList where coordZ contains UPDATED_COORD_Z
        defaultApiaryShouldNotBeFound("coordZ.contains=" + UPDATED_COORD_Z);
    }

    @Test
    @Transactional
    void getAllApiariesByCoordZNotContainsSomething() throws Exception {
        // Initialize the database
        apiaryRepository.saveAndFlush(apiary);

        // Get all the apiaryList where coordZ does not contain DEFAULT_COORD_Z
        defaultApiaryShouldNotBeFound("coordZ.doesNotContain=" + DEFAULT_COORD_Z);

        // Get all the apiaryList where coordZ does not contain UPDATED_COORD_Z
        defaultApiaryShouldBeFound("coordZ.doesNotContain=" + UPDATED_COORD_Z);
    }

    @Test
    @Transactional
    void getAllApiariesByNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        apiaryRepository.saveAndFlush(apiary);

        // Get all the apiaryList where number equals to DEFAULT_NUMBER
        defaultApiaryShouldBeFound("number.equals=" + DEFAULT_NUMBER);

        // Get all the apiaryList where number equals to UPDATED_NUMBER
        defaultApiaryShouldNotBeFound("number.equals=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    void getAllApiariesByNumberIsInShouldWork() throws Exception {
        // Initialize the database
        apiaryRepository.saveAndFlush(apiary);

        // Get all the apiaryList where number in DEFAULT_NUMBER or UPDATED_NUMBER
        defaultApiaryShouldBeFound("number.in=" + DEFAULT_NUMBER + "," + UPDATED_NUMBER);

        // Get all the apiaryList where number equals to UPDATED_NUMBER
        defaultApiaryShouldNotBeFound("number.in=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    void getAllApiariesByNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        apiaryRepository.saveAndFlush(apiary);

        // Get all the apiaryList where number is not null
        defaultApiaryShouldBeFound("number.specified=true");

        // Get all the apiaryList where number is null
        defaultApiaryShouldNotBeFound("number.specified=false");
    }

    @Test
    @Transactional
    void getAllApiariesByNumberIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        apiaryRepository.saveAndFlush(apiary);

        // Get all the apiaryList where number is greater than or equal to DEFAULT_NUMBER
        defaultApiaryShouldBeFound("number.greaterThanOrEqual=" + DEFAULT_NUMBER);

        // Get all the apiaryList where number is greater than or equal to UPDATED_NUMBER
        defaultApiaryShouldNotBeFound("number.greaterThanOrEqual=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    void getAllApiariesByNumberIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        apiaryRepository.saveAndFlush(apiary);

        // Get all the apiaryList where number is less than or equal to DEFAULT_NUMBER
        defaultApiaryShouldBeFound("number.lessThanOrEqual=" + DEFAULT_NUMBER);

        // Get all the apiaryList where number is less than or equal to SMALLER_NUMBER
        defaultApiaryShouldNotBeFound("number.lessThanOrEqual=" + SMALLER_NUMBER);
    }

    @Test
    @Transactional
    void getAllApiariesByNumberIsLessThanSomething() throws Exception {
        // Initialize the database
        apiaryRepository.saveAndFlush(apiary);

        // Get all the apiaryList where number is less than DEFAULT_NUMBER
        defaultApiaryShouldNotBeFound("number.lessThan=" + DEFAULT_NUMBER);

        // Get all the apiaryList where number is less than UPDATED_NUMBER
        defaultApiaryShouldBeFound("number.lessThan=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    void getAllApiariesByNumberIsGreaterThanSomething() throws Exception {
        // Initialize the database
        apiaryRepository.saveAndFlush(apiary);

        // Get all the apiaryList where number is greater than DEFAULT_NUMBER
        defaultApiaryShouldNotBeFound("number.greaterThan=" + DEFAULT_NUMBER);

        // Get all the apiaryList where number is greater than SMALLER_NUMBER
        defaultApiaryShouldBeFound("number.greaterThan=" + SMALLER_NUMBER);
    }

    @Test
    @Transactional
    void getAllApiariesByIntensiveIsEqualToSomething() throws Exception {
        // Initialize the database
        apiaryRepository.saveAndFlush(apiary);

        // Get all the apiaryList where intensive equals to DEFAULT_INTENSIVE
        defaultApiaryShouldBeFound("intensive.equals=" + DEFAULT_INTENSIVE);

        // Get all the apiaryList where intensive equals to UPDATED_INTENSIVE
        defaultApiaryShouldNotBeFound("intensive.equals=" + UPDATED_INTENSIVE);
    }

    @Test
    @Transactional
    void getAllApiariesByIntensiveIsInShouldWork() throws Exception {
        // Initialize the database
        apiaryRepository.saveAndFlush(apiary);

        // Get all the apiaryList where intensive in DEFAULT_INTENSIVE or UPDATED_INTENSIVE
        defaultApiaryShouldBeFound("intensive.in=" + DEFAULT_INTENSIVE + "," + UPDATED_INTENSIVE);

        // Get all the apiaryList where intensive equals to UPDATED_INTENSIVE
        defaultApiaryShouldNotBeFound("intensive.in=" + UPDATED_INTENSIVE);
    }

    @Test
    @Transactional
    void getAllApiariesByIntensiveIsNullOrNotNull() throws Exception {
        // Initialize the database
        apiaryRepository.saveAndFlush(apiary);

        // Get all the apiaryList where intensive is not null
        defaultApiaryShouldBeFound("intensive.specified=true");

        // Get all the apiaryList where intensive is null
        defaultApiaryShouldNotBeFound("intensive.specified=false");
    }

    @Test
    @Transactional
    void getAllApiariesByTranshumanceIsEqualToSomething() throws Exception {
        // Initialize the database
        apiaryRepository.saveAndFlush(apiary);

        // Get all the apiaryList where transhumance equals to DEFAULT_TRANSHUMANCE
        defaultApiaryShouldBeFound("transhumance.equals=" + DEFAULT_TRANSHUMANCE);

        // Get all the apiaryList where transhumance equals to UPDATED_TRANSHUMANCE
        defaultApiaryShouldNotBeFound("transhumance.equals=" + UPDATED_TRANSHUMANCE);
    }

    @Test
    @Transactional
    void getAllApiariesByTranshumanceIsInShouldWork() throws Exception {
        // Initialize the database
        apiaryRepository.saveAndFlush(apiary);

        // Get all the apiaryList where transhumance in DEFAULT_TRANSHUMANCE or UPDATED_TRANSHUMANCE
        defaultApiaryShouldBeFound("transhumance.in=" + DEFAULT_TRANSHUMANCE + "," + UPDATED_TRANSHUMANCE);

        // Get all the apiaryList where transhumance equals to UPDATED_TRANSHUMANCE
        defaultApiaryShouldNotBeFound("transhumance.in=" + UPDATED_TRANSHUMANCE);
    }

    @Test
    @Transactional
    void getAllApiariesByTranshumanceIsNullOrNotNull() throws Exception {
        // Initialize the database
        apiaryRepository.saveAndFlush(apiary);

        // Get all the apiaryList where transhumance is not null
        defaultApiaryShouldBeFound("transhumance.specified=true");

        // Get all the apiaryList where transhumance is null
        defaultApiaryShouldNotBeFound("transhumance.specified=false");
    }

    @Test
    @Transactional
    void getAllApiariesByBeekeeperIsEqualToSomething() throws Exception {
        Beekeeper beekeeper;
        if (TestUtil.findAll(em, Beekeeper.class).isEmpty()) {
            apiaryRepository.saveAndFlush(apiary);
            beekeeper = BeekeeperResourceIT.createEntity(em);
        } else {
            beekeeper = TestUtil.findAll(em, Beekeeper.class).get(0);
        }
        em.persist(beekeeper);
        em.flush();
        apiary.setBeekeeper(beekeeper);
        apiaryRepository.saveAndFlush(apiary);
        Long beekeeperId = beekeeper.getId();

        // Get all the apiaryList where beekeeper equals to beekeeperId
        defaultApiaryShouldBeFound("beekeeperId.equals=" + beekeeperId);

        // Get all the apiaryList where beekeeper equals to (beekeeperId + 1)
        defaultApiaryShouldNotBeFound("beekeeperId.equals=" + (beekeeperId + 1));
    }

    @Test
    @Transactional
    void getAllApiariesByApiaryZoneIsEqualToSomething() throws Exception {
        ApiaryZone apiaryZone;
        if (TestUtil.findAll(em, ApiaryZone.class).isEmpty()) {
            apiaryRepository.saveAndFlush(apiary);
            apiaryZone = ApiaryZoneResourceIT.createEntity(em);
        } else {
            apiaryZone = TestUtil.findAll(em, ApiaryZone.class).get(0);
        }
        em.persist(apiaryZone);
        em.flush();
        apiary.setApiaryZone(apiaryZone);
        apiaryRepository.saveAndFlush(apiary);
        Long apiaryZoneId = apiaryZone.getId();

        // Get all the apiaryList where apiaryZone equals to apiaryZoneId
        defaultApiaryShouldBeFound("apiaryZoneId.equals=" + apiaryZoneId);

        // Get all the apiaryList where apiaryZone equals to (apiaryZoneId + 1)
        defaultApiaryShouldNotBeFound("apiaryZoneId.equals=" + (apiaryZoneId + 1));
    }

    @Test
    @Transactional
    void getAllApiariesByHiveIsEqualToSomething() throws Exception {
        Hive hive;
        if (TestUtil.findAll(em, Hive.class).isEmpty()) {
            apiaryRepository.saveAndFlush(apiary);
            hive = HiveResourceIT.createEntity(em);
        } else {
            hive = TestUtil.findAll(em, Hive.class).get(0);
        }
        em.persist(hive);
        em.flush();
        apiary.addHive(hive);
        apiaryRepository.saveAndFlush(apiary);
        Long hiveId = hive.getId();

        // Get all the apiaryList where hive equals to hiveId
        defaultApiaryShouldBeFound("hiveId.equals=" + hiveId);

        // Get all the apiaryList where hive equals to (hiveId + 1)
        defaultApiaryShouldNotBeFound("hiveId.equals=" + (hiveId + 1));
    }

    @Test
    @Transactional
    void getAllApiariesByTranshumanceRequestIsEqualToSomething() throws Exception {
        TranshumanceRequest transhumanceRequest;
        if (TestUtil.findAll(em, TranshumanceRequest.class).isEmpty()) {
            apiaryRepository.saveAndFlush(apiary);
            transhumanceRequest = TranshumanceRequestResourceIT.createEntity(em);
        } else {
            transhumanceRequest = TestUtil.findAll(em, TranshumanceRequest.class).get(0);
        }
        em.persist(transhumanceRequest);
        em.flush();
        apiary.addTranshumanceRequest(transhumanceRequest);
        apiaryRepository.saveAndFlush(apiary);
        Long transhumanceRequestId = transhumanceRequest.getId();

        // Get all the apiaryList where transhumanceRequest equals to transhumanceRequestId
        defaultApiaryShouldBeFound("transhumanceRequestId.equals=" + transhumanceRequestId);

        // Get all the apiaryList where transhumanceRequest equals to (transhumanceRequestId + 1)
        defaultApiaryShouldNotBeFound("transhumanceRequestId.equals=" + (transhumanceRequestId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultApiaryShouldBeFound(String filter) throws Exception {
        restApiaryMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(apiary.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE.toString())))
            .andExpect(jsonPath("$.[*].coordX").value(hasItem(DEFAULT_COORD_X)))
            .andExpect(jsonPath("$.[*].coordY").value(hasItem(DEFAULT_COORD_Y)))
            .andExpect(jsonPath("$.[*].coordZ").value(hasItem(DEFAULT_COORD_Z)))
            .andExpect(jsonPath("$.[*].number").value(hasItem(DEFAULT_NUMBER)))
            .andExpect(jsonPath("$.[*].intensive").value(hasItem(DEFAULT_INTENSIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].transhumance").value(hasItem(DEFAULT_TRANSHUMANCE.booleanValue())));

        // Check, that the count call also returns 1
        restApiaryMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultApiaryShouldNotBeFound(String filter) throws Exception {
        restApiaryMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restApiaryMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingApiary() throws Exception {
        // Get the apiary
        restApiaryMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingApiary() throws Exception {
        // Initialize the database
        apiaryRepository.saveAndFlush(apiary);

        int databaseSizeBeforeUpdate = apiaryRepository.findAll().size();

        // Update the apiary
        Apiary updatedApiary = apiaryRepository.findById(apiary.getId()).get();
        // Disconnect from session so that the updates on updatedApiary are not directly saved in db
        em.detach(updatedApiary);
        updatedApiary
            .name(UPDATED_NAME)
            .state(UPDATED_STATE)
            .coordX(UPDATED_COORD_X)
            .coordY(UPDATED_COORD_Y)
            .coordZ(UPDATED_COORD_Z)
            .number(UPDATED_NUMBER)
            .intensive(UPDATED_INTENSIVE)
            .transhumance(UPDATED_TRANSHUMANCE);
        ApiaryDTO apiaryDTO = apiaryMapper.toDto(updatedApiary);

        restApiaryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, apiaryDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(apiaryDTO))
            )
            .andExpect(status().isOk());

        // Validate the Apiary in the database
        List<Apiary> apiaryList = apiaryRepository.findAll();
        assertThat(apiaryList).hasSize(databaseSizeBeforeUpdate);
        Apiary testApiary = apiaryList.get(apiaryList.size() - 1);
        assertThat(testApiary.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testApiary.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testApiary.getCoordX()).isEqualTo(UPDATED_COORD_X);
        assertThat(testApiary.getCoordY()).isEqualTo(UPDATED_COORD_Y);
        assertThat(testApiary.getCoordZ()).isEqualTo(UPDATED_COORD_Z);
        assertThat(testApiary.getNumber()).isEqualTo(UPDATED_NUMBER);
        assertThat(testApiary.getIntensive()).isEqualTo(UPDATED_INTENSIVE);
        assertThat(testApiary.getTranshumance()).isEqualTo(UPDATED_TRANSHUMANCE);
    }

    @Test
    @Transactional
    void putNonExistingApiary() throws Exception {
        int databaseSizeBeforeUpdate = apiaryRepository.findAll().size();
        apiary.setId(count.incrementAndGet());

        // Create the Apiary
        ApiaryDTO apiaryDTO = apiaryMapper.toDto(apiary);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restApiaryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, apiaryDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(apiaryDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Apiary in the database
        List<Apiary> apiaryList = apiaryRepository.findAll();
        assertThat(apiaryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchApiary() throws Exception {
        int databaseSizeBeforeUpdate = apiaryRepository.findAll().size();
        apiary.setId(count.incrementAndGet());

        // Create the Apiary
        ApiaryDTO apiaryDTO = apiaryMapper.toDto(apiary);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restApiaryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(apiaryDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Apiary in the database
        List<Apiary> apiaryList = apiaryRepository.findAll();
        assertThat(apiaryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamApiary() throws Exception {
        int databaseSizeBeforeUpdate = apiaryRepository.findAll().size();
        apiary.setId(count.incrementAndGet());

        // Create the Apiary
        ApiaryDTO apiaryDTO = apiaryMapper.toDto(apiary);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restApiaryMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(apiaryDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Apiary in the database
        List<Apiary> apiaryList = apiaryRepository.findAll();
        assertThat(apiaryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateApiaryWithPatch() throws Exception {
        // Initialize the database
        apiaryRepository.saveAndFlush(apiary);

        int databaseSizeBeforeUpdate = apiaryRepository.findAll().size();

        // Update the apiary using partial update
        Apiary partialUpdatedApiary = new Apiary();
        partialUpdatedApiary.setId(apiary.getId());

        partialUpdatedApiary.coordX(UPDATED_COORD_X).coordZ(UPDATED_COORD_Z).transhumance(UPDATED_TRANSHUMANCE);

        restApiaryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedApiary.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedApiary))
            )
            .andExpect(status().isOk());

        // Validate the Apiary in the database
        List<Apiary> apiaryList = apiaryRepository.findAll();
        assertThat(apiaryList).hasSize(databaseSizeBeforeUpdate);
        Apiary testApiary = apiaryList.get(apiaryList.size() - 1);
        assertThat(testApiary.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testApiary.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testApiary.getCoordX()).isEqualTo(UPDATED_COORD_X);
        assertThat(testApiary.getCoordY()).isEqualTo(DEFAULT_COORD_Y);
        assertThat(testApiary.getCoordZ()).isEqualTo(UPDATED_COORD_Z);
        assertThat(testApiary.getNumber()).isEqualTo(DEFAULT_NUMBER);
        assertThat(testApiary.getIntensive()).isEqualTo(DEFAULT_INTENSIVE);
        assertThat(testApiary.getTranshumance()).isEqualTo(UPDATED_TRANSHUMANCE);
    }

    @Test
    @Transactional
    void fullUpdateApiaryWithPatch() throws Exception {
        // Initialize the database
        apiaryRepository.saveAndFlush(apiary);

        int databaseSizeBeforeUpdate = apiaryRepository.findAll().size();

        // Update the apiary using partial update
        Apiary partialUpdatedApiary = new Apiary();
        partialUpdatedApiary.setId(apiary.getId());

        partialUpdatedApiary
            .name(UPDATED_NAME)
            .state(UPDATED_STATE)
            .coordX(UPDATED_COORD_X)
            .coordY(UPDATED_COORD_Y)
            .coordZ(UPDATED_COORD_Z)
            .number(UPDATED_NUMBER)
            .intensive(UPDATED_INTENSIVE)
            .transhumance(UPDATED_TRANSHUMANCE);

        restApiaryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedApiary.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedApiary))
            )
            .andExpect(status().isOk());

        // Validate the Apiary in the database
        List<Apiary> apiaryList = apiaryRepository.findAll();
        assertThat(apiaryList).hasSize(databaseSizeBeforeUpdate);
        Apiary testApiary = apiaryList.get(apiaryList.size() - 1);
        assertThat(testApiary.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testApiary.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testApiary.getCoordX()).isEqualTo(UPDATED_COORD_X);
        assertThat(testApiary.getCoordY()).isEqualTo(UPDATED_COORD_Y);
        assertThat(testApiary.getCoordZ()).isEqualTo(UPDATED_COORD_Z);
        assertThat(testApiary.getNumber()).isEqualTo(UPDATED_NUMBER);
        assertThat(testApiary.getIntensive()).isEqualTo(UPDATED_INTENSIVE);
        assertThat(testApiary.getTranshumance()).isEqualTo(UPDATED_TRANSHUMANCE);
    }

    @Test
    @Transactional
    void patchNonExistingApiary() throws Exception {
        int databaseSizeBeforeUpdate = apiaryRepository.findAll().size();
        apiary.setId(count.incrementAndGet());

        // Create the Apiary
        ApiaryDTO apiaryDTO = apiaryMapper.toDto(apiary);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restApiaryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, apiaryDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(apiaryDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Apiary in the database
        List<Apiary> apiaryList = apiaryRepository.findAll();
        assertThat(apiaryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchApiary() throws Exception {
        int databaseSizeBeforeUpdate = apiaryRepository.findAll().size();
        apiary.setId(count.incrementAndGet());

        // Create the Apiary
        ApiaryDTO apiaryDTO = apiaryMapper.toDto(apiary);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restApiaryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(apiaryDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Apiary in the database
        List<Apiary> apiaryList = apiaryRepository.findAll();
        assertThat(apiaryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamApiary() throws Exception {
        int databaseSizeBeforeUpdate = apiaryRepository.findAll().size();
        apiary.setId(count.incrementAndGet());

        // Create the Apiary
        ApiaryDTO apiaryDTO = apiaryMapper.toDto(apiary);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restApiaryMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(apiaryDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Apiary in the database
        List<Apiary> apiaryList = apiaryRepository.findAll();
        assertThat(apiaryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteApiary() throws Exception {
        // Initialize the database
        apiaryRepository.saveAndFlush(apiary);

        int databaseSizeBeforeDelete = apiaryRepository.findAll().size();

        // Delete the apiary
        restApiaryMockMvc
            .perform(delete(ENTITY_API_URL_ID, apiary.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Apiary> apiaryList = apiaryRepository.findAll();
        assertThat(apiaryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
