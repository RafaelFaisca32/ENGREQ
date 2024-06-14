package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.DiseaseRepository;
import com.mycompany.myapp.service.DiseaseQueryService;
import com.mycompany.myapp.service.DiseaseService;
import com.mycompany.myapp.service.criteria.DiseaseCriteria;
import com.mycompany.myapp.service.dto.DiseaseDTO;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.Disease}.
 */
@RestController
@RequestMapping("/api")
public class DiseaseResource {

    private final Logger log = LoggerFactory.getLogger(DiseaseResource.class);

    private static final String ENTITY_NAME = "disease";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DiseaseService diseaseService;

    private final DiseaseRepository diseaseRepository;

    private final DiseaseQueryService diseaseQueryService;

    public DiseaseResource(DiseaseService diseaseService, DiseaseRepository diseaseRepository, DiseaseQueryService diseaseQueryService) {
        this.diseaseService = diseaseService;
        this.diseaseRepository = diseaseRepository;
        this.diseaseQueryService = diseaseQueryService;
    }

    /**
     * {@code POST  /diseases} : Create a new disease.
     *
     * @param diseaseDTO the diseaseDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new diseaseDTO, or with status {@code 400 (Bad Request)} if the disease has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/diseases")
    public ResponseEntity<DiseaseDTO> createDisease(@RequestBody DiseaseDTO diseaseDTO) throws URISyntaxException {
        log.debug("REST request to save Disease : {}", diseaseDTO);
        if (diseaseDTO.getId() != null) {
            throw new BadRequestAlertException("A new disease cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DiseaseDTO result = diseaseService.save(diseaseDTO);
        return ResponseEntity
            .created(new URI("/api/diseases/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /diseases/:id} : Updates an existing disease.
     *
     * @param id the id of the diseaseDTO to save.
     * @param diseaseDTO the diseaseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated diseaseDTO,
     * or with status {@code 400 (Bad Request)} if the diseaseDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the diseaseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/diseases/{id}")
    public ResponseEntity<DiseaseDTO> updateDisease(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DiseaseDTO diseaseDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Disease : {}, {}", id, diseaseDTO);
        if (diseaseDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, diseaseDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!diseaseRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        DiseaseDTO result = diseaseService.update(diseaseDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, diseaseDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /diseases/:id} : Partial updates given fields of an existing disease, field will ignore if it is null
     *
     * @param id the id of the diseaseDTO to save.
     * @param diseaseDTO the diseaseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated diseaseDTO,
     * or with status {@code 400 (Bad Request)} if the diseaseDTO is not valid,
     * or with status {@code 404 (Not Found)} if the diseaseDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the diseaseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/diseases/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DiseaseDTO> partialUpdateDisease(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DiseaseDTO diseaseDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Disease partially : {}, {}", id, diseaseDTO);
        if (diseaseDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, diseaseDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!diseaseRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DiseaseDTO> result = diseaseService.partialUpdate(diseaseDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, diseaseDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /diseases} : get all the diseases.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of diseases in body.
     */
    @GetMapping("/diseases")
    public ResponseEntity<List<DiseaseDTO>> getAllDiseases(
        DiseaseCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get Diseases by criteria: {}", criteria);
        Page<DiseaseDTO> page = diseaseQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /diseases/count} : count all the diseases.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/diseases/count")
    public ResponseEntity<Long> countDiseases(DiseaseCriteria criteria) {
        log.debug("REST request to count Diseases by criteria: {}", criteria);
        return ResponseEntity.ok().body(diseaseQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /diseases/:id} : get the "id" disease.
     *
     * @param id the id of the diseaseDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the diseaseDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/diseases/{id}")
    public ResponseEntity<DiseaseDTO> getDisease(@PathVariable Long id) {
        log.debug("REST request to get Disease : {}", id);
        Optional<DiseaseDTO> diseaseDTO = diseaseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(diseaseDTO);
    }

    /**
     * {@code DELETE  /diseases/:id} : delete the "id" disease.
     *
     * @param id the id of the diseaseDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/diseases/{id}")
    public ResponseEntity<Void> deleteDisease(@PathVariable Long id) {
        log.debug("REST request to delete Disease : {}", id);
        diseaseService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
