package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.HiveRepository;
import com.mycompany.myapp.service.HiveQueryService;
import com.mycompany.myapp.service.HiveService;
import com.mycompany.myapp.service.criteria.HiveCriteria;
import com.mycompany.myapp.service.dto.ApiaryDTO;
import com.mycompany.myapp.service.dto.HiveDTO;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.Hive}.
 */
@RestController
@RequestMapping("/api")
public class HiveResource {

    private final Logger log = LoggerFactory.getLogger(HiveResource.class);

    private static final String ENTITY_NAME = "hive";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final HiveService hiveService;

    private final HiveRepository hiveRepository;

    private final HiveQueryService hiveQueryService;

    public HiveResource(HiveService hiveService, HiveRepository hiveRepository, HiveQueryService hiveQueryService) {
        this.hiveService = hiveService;
        this.hiveRepository = hiveRepository;
        this.hiveQueryService = hiveQueryService;
    }

    /**
     * {@code POST  /hives} : Create a new hive.
     *
     * @param hiveDTO the hiveDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new hiveDTO, or with status {@code 400 (Bad Request)} if the hive has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/hives")
    public ResponseEntity<HiveDTO> createHive(@Valid @RequestBody HiveDTO hiveDTO) throws URISyntaxException {
        log.debug("REST request to save Hive : {}", hiveDTO);
        if (hiveDTO.getApiaryId() != 0 && hiveDTO.getApiaryId() != null) {
            ApiaryDTO apiaryDTO = new ApiaryDTO();
            apiaryDTO.setId(hiveDTO.getApiaryId());
            hiveDTO.setApiary(apiaryDTO);
        }
        if (hiveDTO.getId() != null) {
            throw new BadRequestAlertException("A new hive cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HiveDTO result = hiveService.save(hiveDTO);
        return ResponseEntity
            .created(new URI("/api/hives/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /hives/:id} : Updates an existing hive.
     *
     * @param id the id of the hiveDTO to save.
     * @param hiveDTO the hiveDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated hiveDTO,
     * or with status {@code 400 (Bad Request)} if the hiveDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the hiveDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/hives/{id}")
    public ResponseEntity<HiveDTO> updateHive(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody HiveDTO hiveDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Hive : {}, {}", id, hiveDTO);
        if (hiveDTO.getApiaryId() != 0 && hiveDTO.getApiaryId() != null) {
            ApiaryDTO apiaryDTO = new ApiaryDTO();
            apiaryDTO.setId(hiveDTO.getApiaryId());
            hiveDTO.setApiary(apiaryDTO);
        }
        if (hiveDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, hiveDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!hiveRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        HiveDTO result = hiveService.update(hiveDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, hiveDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /hives/:id} : Partial updates given fields of an existing hive, field will ignore if it is null
     *
     * @param id the id of the hiveDTO to save.
     * @param hiveDTO the hiveDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated hiveDTO,
     * or with status {@code 400 (Bad Request)} if the hiveDTO is not valid,
     * or with status {@code 404 (Not Found)} if the hiveDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the hiveDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/hives/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<HiveDTO> partialUpdateHive(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody HiveDTO hiveDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Hive partially : {}, {}", id, hiveDTO);
        if (hiveDTO.getApiaryId() != 0 && hiveDTO.getApiaryId() != null) {
            ApiaryDTO apiaryDTO = new ApiaryDTO();
            apiaryDTO.setId(hiveDTO.getApiaryId());
            hiveDTO.setApiary(apiaryDTO);
        }
        if (hiveDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, hiveDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!hiveRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<HiveDTO> result = hiveService.partialUpdate(hiveDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, hiveDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /hives} : get all the hives.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of hives in body.
     */
    @GetMapping("/hives")
    public ResponseEntity<List<HiveDTO>> getAllHives(
        HiveCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get Hives by criteria: {}", criteria);
        Page<HiveDTO> page = hiveQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /hives/count} : count all the hives.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/hives/count")
    public ResponseEntity<Long> countHives(HiveCriteria criteria) {
        log.debug("REST request to count Hives by criteria: {}", criteria);
        return ResponseEntity.ok().body(hiveQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /hives/:id} : get the "id" hive.
     *
     * @param id the id of the hiveDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the hiveDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/hives/{id}")
    public ResponseEntity<HiveDTO> getHive(@PathVariable Long id) {
        log.debug("REST request to get Hive : {}", id);
        Optional<HiveDTO> hiveDTO = hiveService.findOne(id);
        return ResponseUtil.wrapOrNotFound(hiveDTO);
    }

    /**
     * {@code DELETE  /hives/:id} : delete the "id" hive.
     *
     * @param id the id of the hiveDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/hives/{id}")
    public ResponseEntity<Void> deleteHive(@PathVariable Long id) {
        log.debug("REST request to delete Hive : {}", id);
        hiveService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
