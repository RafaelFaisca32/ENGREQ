package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.ApiaryZoneRepository;
import com.mycompany.myapp.service.ApiaryZoneQueryService;
import com.mycompany.myapp.service.ApiaryZoneService;
import com.mycompany.myapp.service.criteria.ApiaryZoneCriteria;
import com.mycompany.myapp.service.dto.ApiaryZoneDTO;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.ApiaryZone}.
 */
@RestController
@RequestMapping("/api")
public class ApiaryZoneResource {

    private final Logger log = LoggerFactory.getLogger(ApiaryZoneResource.class);

    private static final String ENTITY_NAME = "apiaryZone";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ApiaryZoneService apiaryZoneService;

    private final ApiaryZoneRepository apiaryZoneRepository;

    private final ApiaryZoneQueryService apiaryZoneQueryService;

    public ApiaryZoneResource(
        ApiaryZoneService apiaryZoneService,
        ApiaryZoneRepository apiaryZoneRepository,
        ApiaryZoneQueryService apiaryZoneQueryService
    ) {
        this.apiaryZoneService = apiaryZoneService;
        this.apiaryZoneRepository = apiaryZoneRepository;
        this.apiaryZoneQueryService = apiaryZoneQueryService;
    }

    /**
     * {@code POST  /apiary-zones} : Create a new apiaryZone.
     *
     * @param apiaryZoneDTO the apiaryZoneDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new apiaryZoneDTO, or with status {@code 400 (Bad Request)} if the apiaryZone has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/apiary-zones")
    public ResponseEntity<ApiaryZoneDTO> createApiaryZone(@Valid @RequestBody ApiaryZoneDTO apiaryZoneDTO) throws URISyntaxException {
        log.debug("REST request to save ApiaryZone : {}", apiaryZoneDTO);
        if (apiaryZoneDTO.getId() != null) {
            throw new BadRequestAlertException("A new apiaryZone cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ApiaryZoneDTO result = apiaryZoneService.save(apiaryZoneDTO);
        return ResponseEntity
            .created(new URI("/api/apiary-zones/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /apiary-zones/:id} : Updates an existing apiaryZone.
     *
     * @param id the id of the apiaryZoneDTO to save.
     * @param apiaryZoneDTO the apiaryZoneDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated apiaryZoneDTO,
     * or with status {@code 400 (Bad Request)} if the apiaryZoneDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the apiaryZoneDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/apiary-zones/{id}")
    public ResponseEntity<ApiaryZoneDTO> updateApiaryZone(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ApiaryZoneDTO apiaryZoneDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ApiaryZone : {}, {}", id, apiaryZoneDTO);
        if (apiaryZoneDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, apiaryZoneDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!apiaryZoneRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ApiaryZoneDTO result = apiaryZoneService.update(apiaryZoneDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, apiaryZoneDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /apiary-zones/:id} : Partial updates given fields of an existing apiaryZone, field will ignore if it is null
     *
     * @param id the id of the apiaryZoneDTO to save.
     * @param apiaryZoneDTO the apiaryZoneDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated apiaryZoneDTO,
     * or with status {@code 400 (Bad Request)} if the apiaryZoneDTO is not valid,
     * or with status {@code 404 (Not Found)} if the apiaryZoneDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the apiaryZoneDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/apiary-zones/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ApiaryZoneDTO> partialUpdateApiaryZone(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ApiaryZoneDTO apiaryZoneDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ApiaryZone partially : {}, {}", id, apiaryZoneDTO);
        if (apiaryZoneDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, apiaryZoneDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!apiaryZoneRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ApiaryZoneDTO> result = apiaryZoneService.partialUpdate(apiaryZoneDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, apiaryZoneDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /apiary-zones} : get all the apiaryZones.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of apiaryZones in body.
     */
    @GetMapping("/apiary-zones")
    public ResponseEntity<List<ApiaryZoneDTO>> getAllApiaryZones(
        ApiaryZoneCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get ApiaryZones by criteria: {}", criteria);
        Page<ApiaryZoneDTO> page = apiaryZoneQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /apiary-zones/count} : count all the apiaryZones.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/apiary-zones/count")
    public ResponseEntity<Long> countApiaryZones(ApiaryZoneCriteria criteria) {
        log.debug("REST request to count ApiaryZones by criteria: {}", criteria);
        return ResponseEntity.ok().body(apiaryZoneQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /apiary-zones/:id} : get the "id" apiaryZone.
     *
     * @param id the id of the apiaryZoneDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the apiaryZoneDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/apiary-zones/{id}")
    public ResponseEntity<ApiaryZoneDTO> getApiaryZone(@PathVariable Long id) {
        log.debug("REST request to get ApiaryZone : {}", id);
        Optional<ApiaryZoneDTO> apiaryZoneDTO = apiaryZoneService.findOne(id);
        return ResponseUtil.wrapOrNotFound(apiaryZoneDTO);
    }

    /**
     * {@code DELETE  /apiary-zones/:id} : delete the "id" apiaryZone.
     *
     * @param id the id of the apiaryZoneDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/apiary-zones/{id}")
    public ResponseEntity<Void> deleteApiaryZone(@PathVariable Long id) {
        log.debug("REST request to delete ApiaryZone : {}", id);
        apiaryZoneService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
