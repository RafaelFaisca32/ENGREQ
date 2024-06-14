package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.Frame;
import com.mycompany.myapp.domain.Hive;
import com.mycompany.myapp.repository.FrameRepository;
import com.mycompany.myapp.service.criteria.FrameCriteria;
import com.mycompany.myapp.service.dto.FrameDTO;
import com.mycompany.myapp.service.mapper.FrameMapper;
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
 * Integration tests for the {@link FrameResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class FrameResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/frames";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private FrameRepository frameRepository;

    @Autowired
    private FrameMapper frameMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFrameMockMvc;

    private Frame frame;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Frame createEntity(EntityManager em) {
        Frame frame = new Frame().code(DEFAULT_CODE);
        return frame;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Frame createUpdatedEntity(EntityManager em) {
        Frame frame = new Frame().code(UPDATED_CODE);
        return frame;
    }

    @BeforeEach
    public void initTest() {
        frame = createEntity(em);
    }

    @Test
    @Transactional
    void createFrame() throws Exception {
        int databaseSizeBeforeCreate = frameRepository.findAll().size();
        // Create the Frame
        FrameDTO frameDTO = frameMapper.toDto(frame);
        restFrameMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(frameDTO)))
            .andExpect(status().isCreated());

        // Validate the Frame in the database
        List<Frame> frameList = frameRepository.findAll();
        assertThat(frameList).hasSize(databaseSizeBeforeCreate + 1);
        Frame testFrame = frameList.get(frameList.size() - 1);
        assertThat(testFrame.getCode()).isEqualTo(DEFAULT_CODE);
    }

    @Test
    @Transactional
    void createFrameWithExistingId() throws Exception {
        // Create the Frame with an existing ID
        frame.setId(1L);
        FrameDTO frameDTO = frameMapper.toDto(frame);

        int databaseSizeBeforeCreate = frameRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restFrameMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(frameDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Frame in the database
        List<Frame> frameList = frameRepository.findAll();
        assertThat(frameList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllFrames() throws Exception {
        // Initialize the database
        frameRepository.saveAndFlush(frame);

        // Get all the frameList
        restFrameMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(frame.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)));
    }

    @Test
    @Transactional
    void getFrame() throws Exception {
        // Initialize the database
        frameRepository.saveAndFlush(frame);

        // Get the frame
        restFrameMockMvc
            .perform(get(ENTITY_API_URL_ID, frame.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(frame.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE));
    }

    @Test
    @Transactional
    void getFramesByIdFiltering() throws Exception {
        // Initialize the database
        frameRepository.saveAndFlush(frame);

        Long id = frame.getId();

        defaultFrameShouldBeFound("id.equals=" + id);
        defaultFrameShouldNotBeFound("id.notEquals=" + id);

        defaultFrameShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultFrameShouldNotBeFound("id.greaterThan=" + id);

        defaultFrameShouldBeFound("id.lessThanOrEqual=" + id);
        defaultFrameShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllFramesByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        frameRepository.saveAndFlush(frame);

        // Get all the frameList where code equals to DEFAULT_CODE
        defaultFrameShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the frameList where code equals to UPDATED_CODE
        defaultFrameShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    void getAllFramesByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        frameRepository.saveAndFlush(frame);

        // Get all the frameList where code in DEFAULT_CODE or UPDATED_CODE
        defaultFrameShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the frameList where code equals to UPDATED_CODE
        defaultFrameShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    void getAllFramesByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        frameRepository.saveAndFlush(frame);

        // Get all the frameList where code is not null
        defaultFrameShouldBeFound("code.specified=true");

        // Get all the frameList where code is null
        defaultFrameShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    void getAllFramesByCodeContainsSomething() throws Exception {
        // Initialize the database
        frameRepository.saveAndFlush(frame);

        // Get all the frameList where code contains DEFAULT_CODE
        defaultFrameShouldBeFound("code.contains=" + DEFAULT_CODE);

        // Get all the frameList where code contains UPDATED_CODE
        defaultFrameShouldNotBeFound("code.contains=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    void getAllFramesByCodeNotContainsSomething() throws Exception {
        // Initialize the database
        frameRepository.saveAndFlush(frame);

        // Get all the frameList where code does not contain DEFAULT_CODE
        defaultFrameShouldNotBeFound("code.doesNotContain=" + DEFAULT_CODE);

        // Get all the frameList where code does not contain UPDATED_CODE
        defaultFrameShouldBeFound("code.doesNotContain=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    void getAllFramesByHiveIsEqualToSomething() throws Exception {
        Hive hive;
        if (TestUtil.findAll(em, Hive.class).isEmpty()) {
            frameRepository.saveAndFlush(frame);
            hive = HiveResourceIT.createEntity(em);
        } else {
            hive = TestUtil.findAll(em, Hive.class).get(0);
        }
        em.persist(hive);
        em.flush();
        frame.setHive(hive);
        frameRepository.saveAndFlush(frame);
        Long hiveId = hive.getId();

        // Get all the frameList where hive equals to hiveId
        defaultFrameShouldBeFound("hiveId.equals=" + hiveId);

        // Get all the frameList where hive equals to (hiveId + 1)
        defaultFrameShouldNotBeFound("hiveId.equals=" + (hiveId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultFrameShouldBeFound(String filter) throws Exception {
        restFrameMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(frame.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)));

        // Check, that the count call also returns 1
        restFrameMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultFrameShouldNotBeFound(String filter) throws Exception {
        restFrameMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restFrameMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingFrame() throws Exception {
        // Get the frame
        restFrameMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingFrame() throws Exception {
        // Initialize the database
        frameRepository.saveAndFlush(frame);

        int databaseSizeBeforeUpdate = frameRepository.findAll().size();

        // Update the frame
        Frame updatedFrame = frameRepository.findById(frame.getId()).get();
        // Disconnect from session so that the updates on updatedFrame are not directly saved in db
        em.detach(updatedFrame);
        updatedFrame.code(UPDATED_CODE);
        FrameDTO frameDTO = frameMapper.toDto(updatedFrame);

        restFrameMockMvc
            .perform(
                put(ENTITY_API_URL_ID, frameDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(frameDTO))
            )
            .andExpect(status().isOk());

        // Validate the Frame in the database
        List<Frame> frameList = frameRepository.findAll();
        assertThat(frameList).hasSize(databaseSizeBeforeUpdate);
        Frame testFrame = frameList.get(frameList.size() - 1);
        assertThat(testFrame.getCode()).isEqualTo(UPDATED_CODE);
    }

    @Test
    @Transactional
    void putNonExistingFrame() throws Exception {
        int databaseSizeBeforeUpdate = frameRepository.findAll().size();
        frame.setId(count.incrementAndGet());

        // Create the Frame
        FrameDTO frameDTO = frameMapper.toDto(frame);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFrameMockMvc
            .perform(
                put(ENTITY_API_URL_ID, frameDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(frameDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Frame in the database
        List<Frame> frameList = frameRepository.findAll();
        assertThat(frameList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchFrame() throws Exception {
        int databaseSizeBeforeUpdate = frameRepository.findAll().size();
        frame.setId(count.incrementAndGet());

        // Create the Frame
        FrameDTO frameDTO = frameMapper.toDto(frame);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFrameMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(frameDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Frame in the database
        List<Frame> frameList = frameRepository.findAll();
        assertThat(frameList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamFrame() throws Exception {
        int databaseSizeBeforeUpdate = frameRepository.findAll().size();
        frame.setId(count.incrementAndGet());

        // Create the Frame
        FrameDTO frameDTO = frameMapper.toDto(frame);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFrameMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(frameDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Frame in the database
        List<Frame> frameList = frameRepository.findAll();
        assertThat(frameList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateFrameWithPatch() throws Exception {
        // Initialize the database
        frameRepository.saveAndFlush(frame);

        int databaseSizeBeforeUpdate = frameRepository.findAll().size();

        // Update the frame using partial update
        Frame partialUpdatedFrame = new Frame();
        partialUpdatedFrame.setId(frame.getId());

        restFrameMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFrame.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFrame))
            )
            .andExpect(status().isOk());

        // Validate the Frame in the database
        List<Frame> frameList = frameRepository.findAll();
        assertThat(frameList).hasSize(databaseSizeBeforeUpdate);
        Frame testFrame = frameList.get(frameList.size() - 1);
        assertThat(testFrame.getCode()).isEqualTo(DEFAULT_CODE);
    }

    @Test
    @Transactional
    void fullUpdateFrameWithPatch() throws Exception {
        // Initialize the database
        frameRepository.saveAndFlush(frame);

        int databaseSizeBeforeUpdate = frameRepository.findAll().size();

        // Update the frame using partial update
        Frame partialUpdatedFrame = new Frame();
        partialUpdatedFrame.setId(frame.getId());

        partialUpdatedFrame.code(UPDATED_CODE);

        restFrameMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFrame.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFrame))
            )
            .andExpect(status().isOk());

        // Validate the Frame in the database
        List<Frame> frameList = frameRepository.findAll();
        assertThat(frameList).hasSize(databaseSizeBeforeUpdate);
        Frame testFrame = frameList.get(frameList.size() - 1);
        assertThat(testFrame.getCode()).isEqualTo(UPDATED_CODE);
    }

    @Test
    @Transactional
    void patchNonExistingFrame() throws Exception {
        int databaseSizeBeforeUpdate = frameRepository.findAll().size();
        frame.setId(count.incrementAndGet());

        // Create the Frame
        FrameDTO frameDTO = frameMapper.toDto(frame);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFrameMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, frameDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(frameDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Frame in the database
        List<Frame> frameList = frameRepository.findAll();
        assertThat(frameList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchFrame() throws Exception {
        int databaseSizeBeforeUpdate = frameRepository.findAll().size();
        frame.setId(count.incrementAndGet());

        // Create the Frame
        FrameDTO frameDTO = frameMapper.toDto(frame);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFrameMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(frameDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Frame in the database
        List<Frame> frameList = frameRepository.findAll();
        assertThat(frameList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamFrame() throws Exception {
        int databaseSizeBeforeUpdate = frameRepository.findAll().size();
        frame.setId(count.incrementAndGet());

        // Create the Frame
        FrameDTO frameDTO = frameMapper.toDto(frame);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFrameMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(frameDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Frame in the database
        List<Frame> frameList = frameRepository.findAll();
        assertThat(frameList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteFrame() throws Exception {
        // Initialize the database
        frameRepository.saveAndFlush(frame);

        int databaseSizeBeforeDelete = frameRepository.findAll().size();

        // Delete the frame
        restFrameMockMvc
            .perform(delete(ENTITY_API_URL_ID, frame.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Frame> frameList = frameRepository.findAll();
        assertThat(frameList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
