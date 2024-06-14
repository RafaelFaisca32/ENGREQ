package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.ApiaryRepository;
import com.mycompany.myapp.service.ApiaryQueryService;
import com.mycompany.myapp.service.ApiaryService;
import com.mycompany.myapp.service.criteria.ApiaryCriteria;
import com.mycompany.myapp.service.dto.ApiaryDTO;
import com.mycompany.myapp.service.dto.ApiaryZoneDTO;
import com.mycompany.myapp.service.dto.BeekeeperDTO;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;

import java.io.IOException;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.Apiary}.
 */
@RestController
@RequestMapping("/api")
public class ApiaryResource {

    private final Logger log = LoggerFactory.getLogger(ApiaryResource.class);

    private static final String ENTITY_NAME = "apiary";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ApiaryService apiaryService;

    private final ApiaryRepository apiaryRepository;

    private final ApiaryQueryService apiaryQueryService;

    public ApiaryResource(ApiaryService apiaryService, ApiaryRepository apiaryRepository, ApiaryQueryService apiaryQueryService) {
        this.apiaryService = apiaryService;
        this.apiaryRepository = apiaryRepository;
        this.apiaryQueryService = apiaryQueryService;
    }

    /**
     * {@code POST  /apiaries} : Create a new apiary.
     *
     * @param apiaryDTO the apiaryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new apiaryDTO, or with status {@code 400 (Bad Request)} if the apiary has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/apiaries")
    public ResponseEntity<ApiaryDTO> createApiary(@Valid @RequestBody ApiaryDTO apiaryDTO) throws URISyntaxException, IOException, InterruptedException {
        log.debug("REST request to save Apiary : {}", apiaryDTO);

        if (apiaryDTO.getBeekeeperId() != 0 && apiaryDTO.getBeekeeperId() != null) {
            BeekeeperDTO beekeeperDTO = new BeekeeperDTO();
            beekeeperDTO.setId(apiaryDTO.getBeekeeperId());
            apiaryDTO.setBeekeeper(beekeeperDTO);
        }

        if (apiaryDTO.getApiaryZoneId() != 0 && apiaryDTO.getApiaryZoneId() != null) {
            ApiaryZoneDTO apiaryZoneDTO = new ApiaryZoneDTO();
            apiaryZoneDTO.setId(apiaryDTO.getApiaryZoneId());
            apiaryDTO.setApiaryZone(apiaryZoneDTO);
        }

        if (apiaryDTO.getId() != null) {
            throw new BadRequestAlertException("A new apiary cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ApiaryDTO result = apiaryService.save(apiaryDTO);
        apiaryService.sendAuthorizationRequestToPortal(result.getId(), result);
        return ResponseEntity
            .created(new URI("/api/apiaries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /apiaries/:id} : Updates an existing apiary.
     *
     * @param id the id of the apiaryDTO to save.
     * @param apiaryDTO the apiaryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated apiaryDTO,
     * or with status {@code 400 (Bad Request)} if the apiaryDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the apiaryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/apiaries/{id}")
    public ResponseEntity<ApiaryDTO> updateApiary(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ApiaryDTO apiaryDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Apiary : {}, {}", id, apiaryDTO);

        if (apiaryDTO.getBeekeeperId() != 0 && apiaryDTO.getBeekeeperId() != null) {
            BeekeeperDTO beekeeperDTO = new BeekeeperDTO();
            beekeeperDTO.setId(apiaryDTO.getBeekeeperId());
            apiaryDTO.setBeekeeper(beekeeperDTO);
        }

        if (apiaryDTO.getApiaryZoneId() != 0 && apiaryDTO.getApiaryZoneId() != null) {
            ApiaryZoneDTO apiaryZoneDTO = new ApiaryZoneDTO();
            apiaryZoneDTO.setId(apiaryDTO.getApiaryZoneId());
            apiaryDTO.setApiaryZone(apiaryZoneDTO);
        }

        if (apiaryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, apiaryDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!apiaryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ApiaryDTO result = apiaryService.update(apiaryDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, apiaryDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /apiaries/:id} : Partial updates given fields of an existing apiary, field will ignore if it is null
     *
     * @param id the id of the apiaryDTO to save.
     * @param apiaryDTO the apiaryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated apiaryDTO,
     * or with status {@code 400 (Bad Request)} if the apiaryDTO is not valid,
     * or with status {@code 404 (Not Found)} if the apiaryDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the apiaryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/apiaries/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ApiaryDTO> partialUpdateApiary(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ApiaryDTO apiaryDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Apiary partially : {}, {}", id, apiaryDTO);

        if (apiaryDTO.getBeekeeperId() != 0 && apiaryDTO.getBeekeeperId() != null) {
            BeekeeperDTO beekeeperDTO = new BeekeeperDTO();
            beekeeperDTO.setId(apiaryDTO.getBeekeeperId());
            apiaryDTO.setBeekeeper(beekeeperDTO);
        }

        if (apiaryDTO.getApiaryZoneId() != 0 && apiaryDTO.getApiaryZoneId() != null) {
            ApiaryZoneDTO apiaryZoneDTO = new ApiaryZoneDTO();
            apiaryZoneDTO.setId(apiaryDTO.getApiaryZoneId());
            apiaryDTO.setApiaryZone(apiaryZoneDTO);
        }

        if (apiaryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, apiaryDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!apiaryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ApiaryDTO> result = apiaryService.partialUpdate(apiaryDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, apiaryDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /apiaries} : get all the apiaries.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of apiaries in body.
     */
    @GetMapping("/apiaries")
    public ResponseEntity<List<ApiaryDTO>> getAllApiaries(
        ApiaryCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get Apiaries by criteria: {}", criteria);
        Page<ApiaryDTO> page = apiaryQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /apiaries/count} : count all the apiaries.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/apiaries/count")
    public ResponseEntity<Long> countApiaries(ApiaryCriteria criteria) {
        log.debug("REST request to count Apiaries by criteria: {}", criteria);
        return ResponseEntity.ok().body(apiaryQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /apiaries/:id} : get the "id" apiary.
     *
     * @param id the id of the apiaryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the apiaryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/apiaries/{id}")
    public ResponseEntity<ApiaryDTO> getApiary(@PathVariable Long id) {
        log.debug("REST request to get Apiary : {}", id);
        Optional<ApiaryDTO> apiaryDTO = apiaryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(apiaryDTO);
    }

    /**
     * {@code DELETE  /apiaries/:id} : delete the "id" apiary.
     *
     * @param id the id of the apiaryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/apiaries/{id}")
    public ResponseEntity<Void> deleteApiary(@PathVariable Long id) {
        log.debug("REST request to delete Apiary : {}", id);
        apiaryService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }

    @PostMapping("/receiveApiaryApproval")
    public ResponseEntity<String> receiveApiaryApproval(@RequestBody String result)  {
        apiaryService.approveOrRejectApiary(result);
        return null;
    }
}
