package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.Apiary;
import com.mycompany.myapp.domain.Zone;
import com.mycompany.myapp.repository.ZoneRepository;
import com.mycompany.myapp.service.criteria.ZoneCriteria;
import com.mycompany.myapp.service.dto.ZoneDTO;
import com.mycompany.myapp.service.mapper.ZoneMapper;
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
 * Integration tests for the {@link ZoneResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ZoneResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUMBER = 1;
    private static final Integer UPDATED_NUMBER = 2;
    private static final Integer SMALLER_NUMBER = 1 - 1;

    private static final String DEFAULT_COORD_X = "AAAAAAAAAA";
    private static final String UPDATED_COORD_X = "BBBBBBBBBB";

    private static final String DEFAULT_COORD_Y = "AAAAAAAAAA";
    private static final String UPDATED_COORD_Y = "BBBBBBBBBB";

    private static final String DEFAULT_COORD_Z = "AAAAAAAAAA";
    private static final String UPDATED_COORD_Z = "BBBBBBBBBB";

    private static final Boolean DEFAULT_CONTROLLED_ZONE = false;
    private static final Boolean UPDATED_CONTROLLED_ZONE = true;

    private static final String ENTITY_API_URL = "/api/zones";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ZoneRepository zoneRepository;

    @Autowired
    private ZoneMapper zoneMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restZoneMockMvc;

    private Zone zone;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Zone createEntity(EntityManager em) {
        Zone zone = new Zone()
            .name(DEFAULT_NAME)
            .number(DEFAULT_NUMBER)
            .coordX(DEFAULT_COORD_X)
            .coordY(DEFAULT_COORD_Y)
            .coordZ(DEFAULT_COORD_Z)
            .controlledZone(DEFAULT_CONTROLLED_ZONE);
        return zone;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Zone createUpdatedEntity(EntityManager em) {
        Zone zone = new Zone()
            .name(UPDATED_NAME)
            .number(UPDATED_NUMBER)
            .coordX(UPDATED_COORD_X)
            .coordY(UPDATED_COORD_Y)
            .coordZ(UPDATED_COORD_Z)
            .controlledZone(UPDATED_CONTROLLED_ZONE);
        return zone;
    }

    @BeforeEach
    public void initTest() {
        zone = createEntity(em);
    }

    @Test
    @Transactional
    void createZone() throws Exception {
        int databaseSizeBeforeCreate = zoneRepository.findAll().size();
        // Create the Zone
        ZoneDTO zoneDTO = zoneMapper.toDto(zone);
        restZoneMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(zoneDTO)))
            .andExpect(status().isCreated());

        // Validate the Zone in the database
        List<Zone> zoneList = zoneRepository.findAll();
        assertThat(zoneList).hasSize(databaseSizeBeforeCreate + 1);
        Zone testZone = zoneList.get(zoneList.size() - 1);
        assertThat(testZone.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testZone.getNumber()).isEqualTo(DEFAULT_NUMBER);
        assertThat(testZone.getCoordX()).isEqualTo(DEFAULT_COORD_X);
        assertThat(testZone.getCoordY()).isEqualTo(DEFAULT_COORD_Y);
        assertThat(testZone.getCoordZ()).isEqualTo(DEFAULT_COORD_Z);
        assertThat(testZone.getControlledZone()).isEqualTo(DEFAULT_CONTROLLED_ZONE);
    }

    @Test
    @Transactional
    void createZoneWithExistingId() throws Exception {
        // Create the Zone with an existing ID
        zone.setId(1L);
        ZoneDTO zoneDTO = zoneMapper.toDto(zone);

        int databaseSizeBeforeCreate = zoneRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restZoneMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(zoneDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Zone in the database
        List<Zone> zoneList = zoneRepository.findAll();
        assertThat(zoneList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = zoneRepository.findAll().size();
        // set the field null
        zone.setName(null);

        // Create the Zone, which fails.
        ZoneDTO zoneDTO = zoneMapper.toDto(zone);

        restZoneMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(zoneDTO)))
            .andExpect(status().isBadRequest());

        List<Zone> zoneList = zoneRepository.findAll();
        assertThat(zoneList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = zoneRepository.findAll().size();
        // set the field null
        zone.setNumber(null);

        // Create the Zone, which fails.
        ZoneDTO zoneDTO = zoneMapper.toDto(zone);

        restZoneMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(zoneDTO)))
            .andExpect(status().isBadRequest());

        List<Zone> zoneList = zoneRepository.findAll();
        assertThat(zoneList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCoordXIsRequired() throws Exception {
        int databaseSizeBeforeTest = zoneRepository.findAll().size();
        // set the field null
        zone.setCoordX(null);

        // Create the Zone, which fails.
        ZoneDTO zoneDTO = zoneMapper.toDto(zone);

        restZoneMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(zoneDTO)))
            .andExpect(status().isBadRequest());

        List<Zone> zoneList = zoneRepository.findAll();
        assertThat(zoneList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCoordYIsRequired() throws Exception {
        int databaseSizeBeforeTest = zoneRepository.findAll().size();
        // set the field null
        zone.setCoordY(null);

        // Create the Zone, which fails.
        ZoneDTO zoneDTO = zoneMapper.toDto(zone);

        restZoneMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(zoneDTO)))
            .andExpect(status().isBadRequest());

        List<Zone> zoneList = zoneRepository.findAll();
        assertThat(zoneList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCoordZIsRequired() throws Exception {
        int databaseSizeBeforeTest = zoneRepository.findAll().size();
        // set the field null
        zone.setCoordZ(null);

        // Create the Zone, which fails.
        ZoneDTO zoneDTO = zoneMapper.toDto(zone);

        restZoneMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(zoneDTO)))
            .andExpect(status().isBadRequest());

        List<Zone> zoneList = zoneRepository.findAll();
        assertThat(zoneList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkControlledZoneIsRequired() throws Exception {
        int databaseSizeBeforeTest = zoneRepository.findAll().size();
        // set the field null
        zone.setControlledZone(null);

        // Create the Zone, which fails.
        ZoneDTO zoneDTO = zoneMapper.toDto(zone);

        restZoneMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(zoneDTO)))
            .andExpect(status().isBadRequest());

        List<Zone> zoneList = zoneRepository.findAll();
        assertThat(zoneList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllZones() throws Exception {
        // Initialize the database
        zoneRepository.saveAndFlush(zone);

        // Get all the zoneList
        restZoneMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(zone.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].number").value(hasItem(DEFAULT_NUMBER)))
            .andExpect(jsonPath("$.[*].coordX").value(hasItem(DEFAULT_COORD_X)))
            .andExpect(jsonPath("$.[*].coordY").value(hasItem(DEFAULT_COORD_Y)))
            .andExpect(jsonPath("$.[*].coordZ").value(hasItem(DEFAULT_COORD_Z)))
            .andExpect(jsonPath("$.[*].controlledZone").value(hasItem(DEFAULT_CONTROLLED_ZONE.booleanValue())));
    }

    @Test
    @Transactional
    void getZone() throws Exception {
        // Initialize the database
        zoneRepository.saveAndFlush(zone);

        // Get the zone
        restZoneMockMvc
            .perform(get(ENTITY_API_URL_ID, zone.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(zone.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.number").value(DEFAULT_NUMBER))
            .andExpect(jsonPath("$.coordX").value(DEFAULT_COORD_X))
            .andExpect(jsonPath("$.coordY").value(DEFAULT_COORD_Y))
            .andExpect(jsonPath("$.coordZ").value(DEFAULT_COORD_Z))
            .andExpect(jsonPath("$.controlledZone").value(DEFAULT_CONTROLLED_ZONE.booleanValue()));
    }

    @Test
    @Transactional
    void getZonesByIdFiltering() throws Exception {
        // Initialize the database
        zoneRepository.saveAndFlush(zone);

        Long id = zone.getId();

        defaultZoneShouldBeFound("id.equals=" + id);
        defaultZoneShouldNotBeFound("id.notEquals=" + id);

        defaultZoneShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultZoneShouldNotBeFound("id.greaterThan=" + id);

        defaultZoneShouldBeFound("id.lessThanOrEqual=" + id);
        defaultZoneShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllZonesByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        zoneRepository.saveAndFlush(zone);

        // Get all the zoneList where name equals to DEFAULT_NAME
        defaultZoneShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the zoneList where name equals to UPDATED_NAME
        defaultZoneShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllZonesByNameIsInShouldWork() throws Exception {
        // Initialize the database
        zoneRepository.saveAndFlush(zone);

        // Get all the zoneList where name in DEFAULT_NAME or UPDATED_NAME
        defaultZoneShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the zoneList where name equals to UPDATED_NAME
        defaultZoneShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllZonesByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        zoneRepository.saveAndFlush(zone);

        // Get all the zoneList where name is not null
        defaultZoneShouldBeFound("name.specified=true");

        // Get all the zoneList where name is null
        defaultZoneShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    void getAllZonesByNameContainsSomething() throws Exception {
        // Initialize the database
        zoneRepository.saveAndFlush(zone);

        // Get all the zoneList where name contains DEFAULT_NAME
        defaultZoneShouldBeFound("name.contains=" + DEFAULT_NAME);

        // Get all the zoneList where name contains UPDATED_NAME
        defaultZoneShouldNotBeFound("name.contains=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllZonesByNameNotContainsSomething() throws Exception {
        // Initialize the database
        zoneRepository.saveAndFlush(zone);

        // Get all the zoneList where name does not contain DEFAULT_NAME
        defaultZoneShouldNotBeFound("name.doesNotContain=" + DEFAULT_NAME);

        // Get all the zoneList where name does not contain UPDATED_NAME
        defaultZoneShouldBeFound("name.doesNotContain=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllZonesByNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        zoneRepository.saveAndFlush(zone);

        // Get all the zoneList where number equals to DEFAULT_NUMBER
        defaultZoneShouldBeFound("number.equals=" + DEFAULT_NUMBER);

        // Get all the zoneList where number equals to UPDATED_NUMBER
        defaultZoneShouldNotBeFound("number.equals=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    void getAllZonesByNumberIsInShouldWork() throws Exception {
        // Initialize the database
        zoneRepository.saveAndFlush(zone);

        // Get all the zoneList where number in DEFAULT_NUMBER or UPDATED_NUMBER
        defaultZoneShouldBeFound("number.in=" + DEFAULT_NUMBER + "," + UPDATED_NUMBER);

        // Get all the zoneList where number equals to UPDATED_NUMBER
        defaultZoneShouldNotBeFound("number.in=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    void getAllZonesByNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        zoneRepository.saveAndFlush(zone);

        // Get all the zoneList where number is not null
        defaultZoneShouldBeFound("number.specified=true");

        // Get all the zoneList where number is null
        defaultZoneShouldNotBeFound("number.specified=false");
    }

    @Test
    @Transactional
    void getAllZonesByNumberIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        zoneRepository.saveAndFlush(zone);

        // Get all the zoneList where number is greater than or equal to DEFAULT_NUMBER
        defaultZoneShouldBeFound("number.greaterThanOrEqual=" + DEFAULT_NUMBER);

        // Get all the zoneList where number is greater than or equal to UPDATED_NUMBER
        defaultZoneShouldNotBeFound("number.greaterThanOrEqual=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    void getAllZonesByNumberIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        zoneRepository.saveAndFlush(zone);

        // Get all the zoneList where number is less than or equal to DEFAULT_NUMBER
        defaultZoneShouldBeFound("number.lessThanOrEqual=" + DEFAULT_NUMBER);

        // Get all the zoneList where number is less than or equal to SMALLER_NUMBER
        defaultZoneShouldNotBeFound("number.lessThanOrEqual=" + SMALLER_NUMBER);
    }

    @Test
    @Transactional
    void getAllZonesByNumberIsLessThanSomething() throws Exception {
        // Initialize the database
        zoneRepository.saveAndFlush(zone);

        // Get all the zoneList where number is less than DEFAULT_NUMBER
        defaultZoneShouldNotBeFound("number.lessThan=" + DEFAULT_NUMBER);

        // Get all the zoneList where number is less than UPDATED_NUMBER
        defaultZoneShouldBeFound("number.lessThan=" + UPDATED_NUMBER);
    }

    @Test
    @Transactional
    void getAllZonesByNumberIsGreaterThanSomething() throws Exception {
        // Initialize the database
        zoneRepository.saveAndFlush(zone);

        // Get all the zoneList where number is greater than DEFAULT_NUMBER
        defaultZoneShouldNotBeFound("number.greaterThan=" + DEFAULT_NUMBER);

        // Get all the zoneList where number is greater than SMALLER_NUMBER
        defaultZoneShouldBeFound("number.greaterThan=" + SMALLER_NUMBER);
    }

    @Test
    @Transactional
    void getAllZonesByCoordXIsEqualToSomething() throws Exception {
        // Initialize the database
        zoneRepository.saveAndFlush(zone);

        // Get all the zoneList where coordX equals to DEFAULT_COORD_X
        defaultZoneShouldBeFound("coordX.equals=" + DEFAULT_COORD_X);

        // Get all the zoneList where coordX equals to UPDATED_COORD_X
        defaultZoneShouldNotBeFound("coordX.equals=" + UPDATED_COORD_X);
    }

    @Test
    @Transactional
    void getAllZonesByCoordXIsInShouldWork() throws Exception {
        // Initialize the database
        zoneRepository.saveAndFlush(zone);

        // Get all the zoneList where coordX in DEFAULT_COORD_X or UPDATED_COORD_X
        defaultZoneShouldBeFound("coordX.in=" + DEFAULT_COORD_X + "," + UPDATED_COORD_X);

        // Get all the zoneList where coordX equals to UPDATED_COORD_X
        defaultZoneShouldNotBeFound("coordX.in=" + UPDATED_COORD_X);
    }

    @Test
    @Transactional
    void getAllZonesByCoordXIsNullOrNotNull() throws Exception {
        // Initialize the database
        zoneRepository.saveAndFlush(zone);

        // Get all the zoneList where coordX is not null
        defaultZoneShouldBeFound("coordX.specified=true");

        // Get all the zoneList where coordX is null
        defaultZoneShouldNotBeFound("coordX.specified=false");
    }

    @Test
    @Transactional
    void getAllZonesByCoordXContainsSomething() throws Exception {
        // Initialize the database
        zoneRepository.saveAndFlush(zone);

        // Get all the zoneList where coordX contains DEFAULT_COORD_X
        defaultZoneShouldBeFound("coordX.contains=" + DEFAULT_COORD_X);

        // Get all the zoneList where coordX contains UPDATED_COORD_X
        defaultZoneShouldNotBeFound("coordX.contains=" + UPDATED_COORD_X);
    }

    @Test
    @Transactional
    void getAllZonesByCoordXNotContainsSomething() throws Exception {
        // Initialize the database
        zoneRepository.saveAndFlush(zone);

        // Get all the zoneList where coordX does not contain DEFAULT_COORD_X
        defaultZoneShouldNotBeFound("coordX.doesNotContain=" + DEFAULT_COORD_X);

        // Get all the zoneList where coordX does not contain UPDATED_COORD_X
        defaultZoneShouldBeFound("coordX.doesNotContain=" + UPDATED_COORD_X);
    }

    @Test
    @Transactional
    void getAllZonesByCoordYIsEqualToSomething() throws Exception {
        // Initialize the database
        zoneRepository.saveAndFlush(zone);

        // Get all the zoneList where coordY equals to DEFAULT_COORD_Y
        defaultZoneShouldBeFound("coordY.equals=" + DEFAULT_COORD_Y);

        // Get all the zoneList where coordY equals to UPDATED_COORD_Y
        defaultZoneShouldNotBeFound("coordY.equals=" + UPDATED_COORD_Y);
    }

    @Test
    @Transactional
    void getAllZonesByCoordYIsInShouldWork() throws Exception {
        // Initialize the database
        zoneRepository.saveAndFlush(zone);

        // Get all the zoneList where coordY in DEFAULT_COORD_Y or UPDATED_COORD_Y
        defaultZoneShouldBeFound("coordY.in=" + DEFAULT_COORD_Y + "," + UPDATED_COORD_Y);

        // Get all the zoneList where coordY equals to UPDATED_COORD_Y
        defaultZoneShouldNotBeFound("coordY.in=" + UPDATED_COORD_Y);
    }

    @Test
    @Transactional
    void getAllZonesByCoordYIsNullOrNotNull() throws Exception {
        // Initialize the database
        zoneRepository.saveAndFlush(zone);

        // Get all the zoneList where coordY is not null
        defaultZoneShouldBeFound("coordY.specified=true");

        // Get all the zoneList where coordY is null
        defaultZoneShouldNotBeFound("coordY.specified=false");
    }

    @Test
    @Transactional
    void getAllZonesByCoordYContainsSomething() throws Exception {
        // Initialize the database
        zoneRepository.saveAndFlush(zone);

        // Get all the zoneList where coordY contains DEFAULT_COORD_Y
        defaultZoneShouldBeFound("coordY.contains=" + DEFAULT_COORD_Y);

        // Get all the zoneList where coordY contains UPDATED_COORD_Y
        defaultZoneShouldNotBeFound("coordY.contains=" + UPDATED_COORD_Y);
    }

    @Test
    @Transactional
    void getAllZonesByCoordYNotContainsSomething() throws Exception {
        // Initialize the database
        zoneRepository.saveAndFlush(zone);

        // Get all the zoneList where coordY does not contain DEFAULT_COORD_Y
        defaultZoneShouldNotBeFound("coordY.doesNotContain=" + DEFAULT_COORD_Y);

        // Get all the zoneList where coordY does not contain UPDATED_COORD_Y
        defaultZoneShouldBeFound("coordY.doesNotContain=" + UPDATED_COORD_Y);
    }

    @Test
    @Transactional
    void getAllZonesByCoordZIsEqualToSomething() throws Exception {
        // Initialize the database
        zoneRepository.saveAndFlush(zone);

        // Get all the zoneList where coordZ equals to DEFAULT_COORD_Z
        defaultZoneShouldBeFound("coordZ.equals=" + DEFAULT_COORD_Z);

        // Get all the zoneList where coordZ equals to UPDATED_COORD_Z
        defaultZoneShouldNotBeFound("coordZ.equals=" + UPDATED_COORD_Z);
    }

    @Test
    @Transactional
    void getAllZonesByCoordZIsInShouldWork() throws Exception {
        // Initialize the database
        zoneRepository.saveAndFlush(zone);

        // Get all the zoneList where coordZ in DEFAULT_COORD_Z or UPDATED_COORD_Z
        defaultZoneShouldBeFound("coordZ.in=" + DEFAULT_COORD_Z + "," + UPDATED_COORD_Z);

        // Get all the zoneList where coordZ equals to UPDATED_COORD_Z
        defaultZoneShouldNotBeFound("coordZ.in=" + UPDATED_COORD_Z);
    }

    @Test
    @Transactional
    void getAllZonesByCoordZIsNullOrNotNull() throws Exception {
        // Initialize the database
        zoneRepository.saveAndFlush(zone);

        // Get all the zoneList where coordZ is not null
        defaultZoneShouldBeFound("coordZ.specified=true");

        // Get all the zoneList where coordZ is null
        defaultZoneShouldNotBeFound("coordZ.specified=false");
    }

    @Test
    @Transactional
    void getAllZonesByCoordZContainsSomething() throws Exception {
        // Initialize the database
        zoneRepository.saveAndFlush(zone);

        // Get all the zoneList where coordZ contains DEFAULT_COORD_Z
        defaultZoneShouldBeFound("coordZ.contains=" + DEFAULT_COORD_Z);

        // Get all the zoneList where coordZ contains UPDATED_COORD_Z
        defaultZoneShouldNotBeFound("coordZ.contains=" + UPDATED_COORD_Z);
    }

    @Test
    @Transactional
    void getAllZonesByCoordZNotContainsSomething() throws Exception {
        // Initialize the database
        zoneRepository.saveAndFlush(zone);

        // Get all the zoneList where coordZ does not contain DEFAULT_COORD_Z
        defaultZoneShouldNotBeFound("coordZ.doesNotContain=" + DEFAULT_COORD_Z);

        // Get all the zoneList where coordZ does not contain UPDATED_COORD_Z
        defaultZoneShouldBeFound("coordZ.doesNotContain=" + UPDATED_COORD_Z);
    }

    @Test
    @Transactional
    void getAllZonesByControlledZoneIsEqualToSomething() throws Exception {
        // Initialize the database
        zoneRepository.saveAndFlush(zone);

        // Get all the zoneList where controlledZone equals to DEFAULT_CONTROLLED_ZONE
        defaultZoneShouldBeFound("controlledZone.equals=" + DEFAULT_CONTROLLED_ZONE);

        // Get all the zoneList where controlledZone equals to UPDATED_CONTROLLED_ZONE
        defaultZoneShouldNotBeFound("controlledZone.equals=" + UPDATED_CONTROLLED_ZONE);
    }

    @Test
    @Transactional
    void getAllZonesByControlledZoneIsInShouldWork() throws Exception {
        // Initialize the database
        zoneRepository.saveAndFlush(zone);

        // Get all the zoneList where controlledZone in DEFAULT_CONTROLLED_ZONE or UPDATED_CONTROLLED_ZONE
        defaultZoneShouldBeFound("controlledZone.in=" + DEFAULT_CONTROLLED_ZONE + "," + UPDATED_CONTROLLED_ZONE);

        // Get all the zoneList where controlledZone equals to UPDATED_CONTROLLED_ZONE
        defaultZoneShouldNotBeFound("controlledZone.in=" + UPDATED_CONTROLLED_ZONE);
    }

    @Test
    @Transactional
    void getAllZonesByControlledZoneIsNullOrNotNull() throws Exception {
        // Initialize the database
        zoneRepository.saveAndFlush(zone);

        // Get all the zoneList where controlledZone is not null
        defaultZoneShouldBeFound("controlledZone.specified=true");

        // Get all the zoneList where controlledZone is null
        defaultZoneShouldNotBeFound("controlledZone.specified=false");
    }

    @Test
    @Transactional
    void getAllZonesByApiaryIsEqualToSomething() throws Exception {
        Apiary apiary;
        if (TestUtil.findAll(em, Apiary.class).isEmpty()) {
            zoneRepository.saveAndFlush(zone);
            apiary = ApiaryResourceIT.createEntity(em);
        } else {
            apiary = TestUtil.findAll(em, Apiary.class).get(0);
        }
        em.persist(apiary);
        em.flush();
        zone.setApiary(apiary);
        apiary.setZone(zone);
        zoneRepository.saveAndFlush(zone);
        Long apiaryId = apiary.getId();

        // Get all the zoneList where apiary equals to apiaryId
        defaultZoneShouldBeFound("apiaryId.equals=" + apiaryId);

        // Get all the zoneList where apiary equals to (apiaryId + 1)
        defaultZoneShouldNotBeFound("apiaryId.equals=" + (apiaryId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultZoneShouldBeFound(String filter) throws Exception {
        restZoneMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(zone.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].number").value(hasItem(DEFAULT_NUMBER)))
            .andExpect(jsonPath("$.[*].coordX").value(hasItem(DEFAULT_COORD_X)))
            .andExpect(jsonPath("$.[*].coordY").value(hasItem(DEFAULT_COORD_Y)))
            .andExpect(jsonPath("$.[*].coordZ").value(hasItem(DEFAULT_COORD_Z)))
            .andExpect(jsonPath("$.[*].controlledZone").value(hasItem(DEFAULT_CONTROLLED_ZONE.booleanValue())));

        // Check, that the count call also returns 1
        restZoneMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultZoneShouldNotBeFound(String filter) throws Exception {
        restZoneMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restZoneMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingZone() throws Exception {
        // Get the zone
        restZoneMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingZone() throws Exception {
        // Initialize the database
        zoneRepository.saveAndFlush(zone);

        int databaseSizeBeforeUpdate = zoneRepository.findAll().size();

        // Update the zone
        Zone updatedZone = zoneRepository.findById(zone.getId()).get();
        // Disconnect from session so that the updates on updatedZone are not directly saved in db
        em.detach(updatedZone);
        updatedZone
            .name(UPDATED_NAME)
            .number(UPDATED_NUMBER)
            .coordX(UPDATED_COORD_X)
            .coordY(UPDATED_COORD_Y)
            .coordZ(UPDATED_COORD_Z)
            .controlledZone(UPDATED_CONTROLLED_ZONE);
        ZoneDTO zoneDTO = zoneMapper.toDto(updatedZone);

        restZoneMockMvc
            .perform(
                put(ENTITY_API_URL_ID, zoneDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(zoneDTO))
            )
            .andExpect(status().isOk());

        // Validate the Zone in the database
        List<Zone> zoneList = zoneRepository.findAll();
        assertThat(zoneList).hasSize(databaseSizeBeforeUpdate);
        Zone testZone = zoneList.get(zoneList.size() - 1);
        assertThat(testZone.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testZone.getNumber()).isEqualTo(UPDATED_NUMBER);
        assertThat(testZone.getCoordX()).isEqualTo(UPDATED_COORD_X);
        assertThat(testZone.getCoordY()).isEqualTo(UPDATED_COORD_Y);
        assertThat(testZone.getCoordZ()).isEqualTo(UPDATED_COORD_Z);
        assertThat(testZone.getControlledZone()).isEqualTo(UPDATED_CONTROLLED_ZONE);
    }

    @Test
    @Transactional
    void putNonExistingZone() throws Exception {
        int databaseSizeBeforeUpdate = zoneRepository.findAll().size();
        zone.setId(count.incrementAndGet());

        // Create the Zone
        ZoneDTO zoneDTO = zoneMapper.toDto(zone);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restZoneMockMvc
            .perform(
                put(ENTITY_API_URL_ID, zoneDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(zoneDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Zone in the database
        List<Zone> zoneList = zoneRepository.findAll();
        assertThat(zoneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchZone() throws Exception {
        int databaseSizeBeforeUpdate = zoneRepository.findAll().size();
        zone.setId(count.incrementAndGet());

        // Create the Zone
        ZoneDTO zoneDTO = zoneMapper.toDto(zone);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restZoneMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(zoneDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Zone in the database
        List<Zone> zoneList = zoneRepository.findAll();
        assertThat(zoneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamZone() throws Exception {
        int databaseSizeBeforeUpdate = zoneRepository.findAll().size();
        zone.setId(count.incrementAndGet());

        // Create the Zone
        ZoneDTO zoneDTO = zoneMapper.toDto(zone);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restZoneMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(zoneDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Zone in the database
        List<Zone> zoneList = zoneRepository.findAll();
        assertThat(zoneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateZoneWithPatch() throws Exception {
        // Initialize the database
        zoneRepository.saveAndFlush(zone);

        int databaseSizeBeforeUpdate = zoneRepository.findAll().size();

        // Update the zone using partial update
        Zone partialUpdatedZone = new Zone();
        partialUpdatedZone.setId(zone.getId());

        partialUpdatedZone.coordY(UPDATED_COORD_Y).controlledZone(UPDATED_CONTROLLED_ZONE);

        restZoneMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedZone.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedZone))
            )
            .andExpect(status().isOk());

        // Validate the Zone in the database
        List<Zone> zoneList = zoneRepository.findAll();
        assertThat(zoneList).hasSize(databaseSizeBeforeUpdate);
        Zone testZone = zoneList.get(zoneList.size() - 1);
        assertThat(testZone.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testZone.getNumber()).isEqualTo(DEFAULT_NUMBER);
        assertThat(testZone.getCoordX()).isEqualTo(DEFAULT_COORD_X);
        assertThat(testZone.getCoordY()).isEqualTo(UPDATED_COORD_Y);
        assertThat(testZone.getCoordZ()).isEqualTo(DEFAULT_COORD_Z);
        assertThat(testZone.getControlledZone()).isEqualTo(UPDATED_CONTROLLED_ZONE);
    }

    @Test
    @Transactional
    void fullUpdateZoneWithPatch() throws Exception {
        // Initialize the database
        zoneRepository.saveAndFlush(zone);

        int databaseSizeBeforeUpdate = zoneRepository.findAll().size();

        // Update the zone using partial update
        Zone partialUpdatedZone = new Zone();
        partialUpdatedZone.setId(zone.getId());

        partialUpdatedZone
            .name(UPDATED_NAME)
            .number(UPDATED_NUMBER)
            .coordX(UPDATED_COORD_X)
            .coordY(UPDATED_COORD_Y)
            .coordZ(UPDATED_COORD_Z)
            .controlledZone(UPDATED_CONTROLLED_ZONE);

        restZoneMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedZone.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedZone))
            )
            .andExpect(status().isOk());

        // Validate the Zone in the database
        List<Zone> zoneList = zoneRepository.findAll();
        assertThat(zoneList).hasSize(databaseSizeBeforeUpdate);
        Zone testZone = zoneList.get(zoneList.size() - 1);
        assertThat(testZone.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testZone.getNumber()).isEqualTo(UPDATED_NUMBER);
        assertThat(testZone.getCoordX()).isEqualTo(UPDATED_COORD_X);
        assertThat(testZone.getCoordY()).isEqualTo(UPDATED_COORD_Y);
        assertThat(testZone.getCoordZ()).isEqualTo(UPDATED_COORD_Z);
        assertThat(testZone.getControlledZone()).isEqualTo(UPDATED_CONTROLLED_ZONE);
    }

    @Test
    @Transactional
    void patchNonExistingZone() throws Exception {
        int databaseSizeBeforeUpdate = zoneRepository.findAll().size();
        zone.setId(count.incrementAndGet());

        // Create the Zone
        ZoneDTO zoneDTO = zoneMapper.toDto(zone);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restZoneMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, zoneDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(zoneDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Zone in the database
        List<Zone> zoneList = zoneRepository.findAll();
        assertThat(zoneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchZone() throws Exception {
        int databaseSizeBeforeUpdate = zoneRepository.findAll().size();
        zone.setId(count.incrementAndGet());

        // Create the Zone
        ZoneDTO zoneDTO = zoneMapper.toDto(zone);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restZoneMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(zoneDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Zone in the database
        List<Zone> zoneList = zoneRepository.findAll();
        assertThat(zoneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamZone() throws Exception {
        int databaseSizeBeforeUpdate = zoneRepository.findAll().size();
        zone.setId(count.incrementAndGet());

        // Create the Zone
        ZoneDTO zoneDTO = zoneMapper.toDto(zone);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restZoneMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(zoneDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Zone in the database
        List<Zone> zoneList = zoneRepository.findAll();
        assertThat(zoneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteZone() throws Exception {
        // Initialize the database
        zoneRepository.saveAndFlush(zone);

        int databaseSizeBeforeDelete = zoneRepository.findAll().size();

        // Delete the zone
        restZoneMockMvc
            .perform(delete(ENTITY_API_URL_ID, zone.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Zone> zoneList = zoneRepository.findAll();
        assertThat(zoneList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
