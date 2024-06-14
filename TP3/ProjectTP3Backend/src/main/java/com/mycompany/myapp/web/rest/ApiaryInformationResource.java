package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.ApiaryInformationRepository;
import com.mycompany.myapp.service.ApiaryInformationQueryService;
import com.mycompany.myapp.service.ApiaryInformationService;
import com.mycompany.myapp.service.criteria.ApiaryInformationCriteria;
import com.mycompany.myapp.service.dto.ApiaryInformationDTO;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.ApiaryInformation}.
 */
@RestController
@RequestMapping("/api")
public class ApiaryInformationResource {

    private final Logger log = LoggerFactory.getLogger(ApiaryInformationResource.class);

    private static final String ENTITY_NAME = "apiaryInformation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ApiaryInformationService apiaryInformationService;

    private final ApiaryInformationRepository apiaryInformationRepository;

    private final ApiaryInformationQueryService apiaryInformationQueryService;

    public ApiaryInformationResource(
        ApiaryInformationService apiaryInformationService,
        ApiaryInformationRepository apiaryInformationRepository,
        ApiaryInformationQueryService apiaryInformationQueryService
    ) {
        this.apiaryInformationService = apiaryInformationService;
        this.apiaryInformationRepository = apiaryInformationRepository;
        this.apiaryInformationQueryService = apiaryInformationQueryService;
    }

    /**
     * {@code POST  /apiary-informations} : Create a new apiaryInformation.
     *
     * @param apiaryInformationDTO the apiaryInformationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new apiaryInformationDTO, or with status {@code 400 (Bad Request)} if the apiaryInformation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/apiary-informations")
    public ResponseEntity<ApiaryInformationDTO> createApiaryInformation(@Valid @RequestBody ApiaryInformationDTO apiaryInformationDTO)
        throws URISyntaxException {
        log.debug("REST request to save ApiaryInformation : {}", apiaryInformationDTO);
        if (apiaryInformationDTO.getId() != null) {
            throw new BadRequestAlertException("A new apiaryInformation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ApiaryInformationDTO result = apiaryInformationService.save(apiaryInformationDTO);
        return ResponseEntity
            .created(new URI("/api/apiary-informations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /apiary-informations/:id} : Updates an existing apiaryInformation.
     *
     * @param id the id of the apiaryInformationDTO to save.
     * @param apiaryInformationDTO the apiaryInformationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated apiaryInformationDTO,
     * or with status {@code 400 (Bad Request)} if the apiaryInformationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the apiaryInformationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/apiary-informations/{id}")
    public ResponseEntity<ApiaryInformationDTO> updateApiaryInformation(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ApiaryInformationDTO apiaryInformationDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ApiaryInformation : {}, {}", id, apiaryInformationDTO);
        if (apiaryInformationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, apiaryInformationDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!apiaryInformationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ApiaryInformationDTO result = apiaryInformationService.update(apiaryInformationDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, apiaryInformationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /apiary-informations/:id} : Partial updates given fields of an existing apiaryInformation, field will ignore if it is null
     *
     * @param id the id of the apiaryInformationDTO to save.
     * @param apiaryInformationDTO the apiaryInformationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated apiaryInformationDTO,
     * or with status {@code 400 (Bad Request)} if the apiaryInformationDTO is not valid,
     * or with status {@code 404 (Not Found)} if the apiaryInformationDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the apiaryInformationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/apiary-informations/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ApiaryInformationDTO> partialUpdateApiaryInformation(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ApiaryInformationDTO apiaryInformationDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ApiaryInformation partially : {}, {}", id, apiaryInformationDTO);
        if (apiaryInformationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, apiaryInformationDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!apiaryInformationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ApiaryInformationDTO> result = apiaryInformationService.partialUpdate(apiaryInformationDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, apiaryInformationDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /apiary-informations} : get all the apiaryInformations.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of apiaryInformations in body.
     */
    @GetMapping("/apiary-informations")
    public ResponseEntity<List<ApiaryInformationDTO>> getAllApiaryInformations(
        ApiaryInformationCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get ApiaryInformations by criteria: {}", criteria);
        Page<ApiaryInformationDTO> page = apiaryInformationQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /apiary-informations/count} : count all the apiaryInformations.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/apiary-informations/count")
    public ResponseEntity<Long> countApiaryInformations(ApiaryInformationCriteria criteria) {
        log.debug("REST request to count ApiaryInformations by criteria: {}", criteria);
        return ResponseEntity.ok().body(apiaryInformationQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /apiary-informations/:id} : get the "id" apiaryInformation.
     *
     * @param id the id of the apiaryInformationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the apiaryInformationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/apiary-informations/{id}")
    public ResponseEntity<ApiaryInformationDTO> getApiaryInformation(@PathVariable Long id) {
        log.debug("REST request to get ApiaryInformation : {}", id);
        Optional<ApiaryInformationDTO> apiaryInformationDTO = apiaryInformationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(apiaryInformationDTO);
    }

    /**
     * {@code DELETE  /apiary-informations/:id} : delete the "id" apiaryInformation.
     *
     * @param id the id of the apiaryInformationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/apiary-informations/{id}")
    public ResponseEntity<Void> deleteApiaryInformation(@PathVariable Long id) {
        log.debug("REST request to delete ApiaryInformation : {}", id);
        apiaryInformationService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
