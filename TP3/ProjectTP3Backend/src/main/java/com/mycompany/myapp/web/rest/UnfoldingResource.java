package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.UnfoldingRepository;
import com.mycompany.myapp.service.HiveService;
import com.mycompany.myapp.service.UnfoldingQueryService;
import com.mycompany.myapp.service.UnfoldingService;
import com.mycompany.myapp.service.criteria.UnfoldingCriteria;
import com.mycompany.myapp.service.dto.HiveDTO;
import com.mycompany.myapp.service.dto.UnfoldingDTO;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.Unfolding}.
 */
@RestController
@RequestMapping("/api")
public class UnfoldingResource {

    private final Logger log = LoggerFactory.getLogger(UnfoldingResource.class);

    private static final String ENTITY_NAME = "unfolding";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UnfoldingService unfoldingService;

    private final UnfoldingRepository unfoldingRepository;

    private final UnfoldingQueryService unfoldingQueryService;
    private final HiveService hiveService;

    public UnfoldingResource(
        UnfoldingService unfoldingService,
        UnfoldingRepository unfoldingRepository,
        UnfoldingQueryService unfoldingQueryService,
        HiveService hiveService
    ) {
        this.unfoldingService = unfoldingService;
        this.unfoldingRepository = unfoldingRepository;
        this.unfoldingQueryService = unfoldingQueryService;
        this.hiveService = hiveService;
    }

    /**
     * {@code POST  /unfoldings} : Create a new unfolding.
     *
     * @param unfoldingDTO the unfoldingDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new unfoldingDTO, or with status {@code 400 (Bad Request)} if the unfolding has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/unfoldings")
    public ResponseEntity<UnfoldingDTO> createUnfolding(@Valid @RequestBody UnfoldingDTO unfoldingDTO) throws URISyntaxException {
        log.debug("REST request to save Unfolding : {}", unfoldingDTO);
        if (unfoldingDTO.getHiveId() != 0 && unfoldingDTO.getHiveId() != null) {
            HiveDTO hiveDTO = new HiveDTO();
            hiveDTO.setId(unfoldingDTO.getHiveId());
            unfoldingDTO.setHive(hiveDTO);
        }
        if (unfoldingDTO.getCreatedHiveId() != 0 && unfoldingDTO.getCreatedHiveId() != null) {
            HiveDTO hiveDTO = new HiveDTO();
            hiveDTO.setId(unfoldingDTO.getCreatedHiveId());
            unfoldingDTO.setCreatedHive(hiveDTO);
        }
        if (unfoldingDTO.getId() != null) {
            throw new BadRequestAlertException("A new unfolding cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UnfoldingDTO result = unfoldingService.save(unfoldingDTO);
        hiveService.removeFramesFromHive(result.getHive(), result.getRemovedFramesOldHives());
        hiveService.addFramesFromHive(result.getHive(), result.getInsertedFramesOldHives());
        return ResponseEntity
            .created(new URI("/api/unfoldings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /unfoldings/:id} : Updates an existing unfolding.
     *
     * @param id the id of the unfoldingDTO to save.
     * @param unfoldingDTO the unfoldingDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated unfoldingDTO,
     * or with status {@code 400 (Bad Request)} if the unfoldingDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the unfoldingDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/unfoldings/{id}")
    public ResponseEntity<UnfoldingDTO> updateUnfolding(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody UnfoldingDTO unfoldingDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Unfolding : {}, {}", id, unfoldingDTO);
        if (unfoldingDTO.getHiveId() != 0 && unfoldingDTO.getHiveId() != null) {
            HiveDTO hiveDTO = new HiveDTO();
            hiveDTO.setId(unfoldingDTO.getHiveId());
            unfoldingDTO.setHive(hiveDTO);
        }
        if (unfoldingDTO.getCreatedHiveId() != 0 && unfoldingDTO.getCreatedHiveId() != null) {
            HiveDTO hiveDTO = new HiveDTO();
            hiveDTO.setId(unfoldingDTO.getCreatedHiveId());
            unfoldingDTO.setCreatedHive(hiveDTO);
        }
        if (unfoldingDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, unfoldingDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!unfoldingRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        UnfoldingDTO result = unfoldingService.update(unfoldingDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, unfoldingDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /unfoldings/:id} : Partial updates given fields of an existing unfolding, field will ignore if it is null
     *
     * @param id the id of the unfoldingDTO to save.
     * @param unfoldingDTO the unfoldingDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated unfoldingDTO,
     * or with status {@code 400 (Bad Request)} if the unfoldingDTO is not valid,
     * or with status {@code 404 (Not Found)} if the unfoldingDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the unfoldingDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/unfoldings/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<UnfoldingDTO> partialUpdateUnfolding(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody UnfoldingDTO unfoldingDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Unfolding partially : {}, {}", id, unfoldingDTO);
        if (unfoldingDTO.getHiveId() != 0 && unfoldingDTO.getHiveId() != null) {
            HiveDTO hiveDTO = new HiveDTO();
            hiveDTO.setId(unfoldingDTO.getHiveId());
            unfoldingDTO.setHive(hiveDTO);
        }
        if (unfoldingDTO.getCreatedHiveId() != 0 && unfoldingDTO.getCreatedHiveId() != null) {
            HiveDTO hiveDTO = new HiveDTO();
            hiveDTO.setId(unfoldingDTO.getCreatedHiveId());
            unfoldingDTO.setCreatedHive(hiveDTO);
        }
        if (unfoldingDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, unfoldingDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!unfoldingRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<UnfoldingDTO> result = unfoldingService.partialUpdate(unfoldingDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, unfoldingDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /unfoldings} : get all the unfoldings.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of unfoldings in body.
     */
    @GetMapping("/unfoldings")
    public ResponseEntity<List<UnfoldingDTO>> getAllUnfoldings(
        UnfoldingCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get Unfoldings by criteria: {}", criteria);
        Page<UnfoldingDTO> page = unfoldingQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /unfoldings/count} : count all the unfoldings.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/unfoldings/count")
    public ResponseEntity<Long> countUnfoldings(UnfoldingCriteria criteria) {
        log.debug("REST request to count Unfoldings by criteria: {}", criteria);
        return ResponseEntity.ok().body(unfoldingQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /unfoldings/:id} : get the "id" unfolding.
     *
     * @param id the id of the unfoldingDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the unfoldingDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/unfoldings/{id}")
    public ResponseEntity<UnfoldingDTO> getUnfolding(@PathVariable Long id) {
        log.debug("REST request to get Unfolding : {}", id);
        Optional<UnfoldingDTO> unfoldingDTO = unfoldingService.findOne(id);
        return ResponseUtil.wrapOrNotFound(unfoldingDTO);
    }

    /**
     * {@code DELETE  /unfoldings/:id} : delete the "id" unfolding.
     *
     * @param id the id of the unfoldingDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/unfoldings/{id}")
    public ResponseEntity<Void> deleteUnfolding(@PathVariable Long id) {
        log.debug("REST request to delete Unfolding : {}", id);
        unfoldingService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
