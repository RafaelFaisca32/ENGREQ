package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.FrameRepository;
import com.mycompany.myapp.service.FrameQueryService;
import com.mycompany.myapp.service.FrameService;
import com.mycompany.myapp.service.criteria.FrameCriteria;
import com.mycompany.myapp.service.dto.FrameDTO;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.Frame}.
 */
@RestController
@RequestMapping("/api")
public class FrameResource {

    private final Logger log = LoggerFactory.getLogger(FrameResource.class);

    private static final String ENTITY_NAME = "frame";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FrameService frameService;

    private final FrameRepository frameRepository;

    private final FrameQueryService frameQueryService;

    public FrameResource(FrameService frameService, FrameRepository frameRepository, FrameQueryService frameQueryService) {
        this.frameService = frameService;
        this.frameRepository = frameRepository;
        this.frameQueryService = frameQueryService;
    }

    /**
     * {@code POST  /frames} : Create a new frame.
     *
     * @param frameDTO the frameDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new frameDTO, or with status {@code 400 (Bad Request)} if the frame has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/frames")
    public ResponseEntity<FrameDTO> createFrame(@Valid @RequestBody FrameDTO frameDTO) throws URISyntaxException {
        log.debug("REST request to save Frame : {}", frameDTO);
        if (frameDTO.getId() != null) {
            throw new BadRequestAlertException("A new frame cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FrameDTO result = frameService.save(frameDTO);
        return ResponseEntity
            .created(new URI("/api/frames/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /frames/:id} : Updates an existing frame.
     *
     * @param id the id of the frameDTO to save.
     * @param frameDTO the frameDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated frameDTO,
     * or with status {@code 400 (Bad Request)} if the frameDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the frameDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/frames/{id}")
    public ResponseEntity<FrameDTO> updateFrame(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody FrameDTO frameDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Frame : {}, {}", id, frameDTO);
        if (frameDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, frameDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!frameRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        FrameDTO result = frameService.update(frameDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, frameDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /frames/:id} : Partial updates given fields of an existing frame, field will ignore if it is null
     *
     * @param id the id of the frameDTO to save.
     * @param frameDTO the frameDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated frameDTO,
     * or with status {@code 400 (Bad Request)} if the frameDTO is not valid,
     * or with status {@code 404 (Not Found)} if the frameDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the frameDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/frames/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<FrameDTO> partialUpdateFrame(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody FrameDTO frameDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Frame partially : {}, {}", id, frameDTO);
        if (frameDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, frameDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!frameRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<FrameDTO> result = frameService.partialUpdate(frameDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, frameDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /frames} : get all the frames.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of frames in body.
     */
    @GetMapping("/frames")
    public ResponseEntity<List<FrameDTO>> getAllFrames(
        FrameCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get Frames by criteria: {}", criteria);
        Page<FrameDTO> page = frameQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /frames/count} : count all the frames.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/frames/count")
    public ResponseEntity<Long> countFrames(FrameCriteria criteria) {
        log.debug("REST request to count Frames by criteria: {}", criteria);
        return ResponseEntity.ok().body(frameQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /frames/:id} : get the "id" frame.
     *
     * @param id the id of the frameDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the frameDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/frames/{id}")
    public ResponseEntity<FrameDTO> getFrame(@PathVariable Long id) {
        log.debug("REST request to get Frame : {}", id);
        Optional<FrameDTO> frameDTO = frameService.findOne(id);
        return ResponseUtil.wrapOrNotFound(frameDTO);
    }

    /**
     * {@code DELETE  /frames/:id} : delete the "id" frame.
     *
     * @param id the id of the frameDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/frames/{id}")
    public ResponseEntity<Void> deleteFrame(@PathVariable Long id) {
        log.debug("REST request to delete Frame : {}", id);
        frameService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
