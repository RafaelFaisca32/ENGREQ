package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.TranshumanceRequestRepository;
import com.mycompany.myapp.service.TranshumanceRequestQueryService;
import com.mycompany.myapp.service.TranshumanceRequestService;
import com.mycompany.myapp.service.criteria.TranshumanceRequestCriteria;
import com.mycompany.myapp.service.dto.TranshumanceRequestDTO;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.TranshumanceRequest}.
 */
@RestController
@RequestMapping("/api")
public class TranshumanceRequestResource {

    private final Logger log = LoggerFactory.getLogger(TranshumanceRequestResource.class);

    private static final String ENTITY_NAME = "transhumanceRequest";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TranshumanceRequestService transhumanceRequestService;

    private final TranshumanceRequestRepository transhumanceRequestRepository;

    private final TranshumanceRequestQueryService transhumanceRequestQueryService;

    public TranshumanceRequestResource(
        TranshumanceRequestService transhumanceRequestService,
        TranshumanceRequestRepository transhumanceRequestRepository,
        TranshumanceRequestQueryService transhumanceRequestQueryService
    ) {
        this.transhumanceRequestService = transhumanceRequestService;
        this.transhumanceRequestRepository = transhumanceRequestRepository;
        this.transhumanceRequestQueryService = transhumanceRequestQueryService;
    }

    /**
     * {@code POST  /transhumance-requests} : Create a new transhumanceRequest.
     *
     * @param transhumanceRequestDTO the transhumanceRequestDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new transhumanceRequestDTO, or with status {@code 400 (Bad Request)} if the transhumanceRequest has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/transhumance-requests")
    public ResponseEntity<TranshumanceRequestDTO> createTranshumanceRequest(
        @Valid @RequestBody TranshumanceRequestDTO transhumanceRequestDTO
    ) throws URISyntaxException, IOException, InterruptedException {
        log.debug("REST request to save TranshumanceRequest : {}", transhumanceRequestDTO);
        if (transhumanceRequestDTO.getId() != null) {
            throw new BadRequestAlertException("A new transhumanceRequest cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TranshumanceRequestDTO result = transhumanceRequestService.save(transhumanceRequestDTO);
        transhumanceRequestService.sendAuthorizationRequestToPortal(result.getId(),result);
        return ResponseEntity
            .created(new URI("/api/transhumance-requests/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
    @PostMapping("/receiveTranshumanceApproval")
    public ResponseEntity<String> receiveTranshumanceApproval(@RequestBody String result)  {
        transhumanceRequestService.approveOrRejectTranshumance(result);

        return null;
    }




    /**
     * {@code PUT  /transhumance-requests/:id} : Updates an existing transhumanceRequest.
     *
     * @param id the id of the transhumanceRequestDTO to save.
     * @param transhumanceRequestDTO the transhumanceRequestDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated transhumanceRequestDTO,
     * or with status {@code 400 (Bad Request)} if the transhumanceRequestDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the transhumanceRequestDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/transhumance-requests/{id}")
    public ResponseEntity<TranshumanceRequestDTO> updateTranshumanceRequest(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody TranshumanceRequestDTO transhumanceRequestDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TranshumanceRequest : {}, {}", id, transhumanceRequestDTO);
        if (transhumanceRequestDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, transhumanceRequestDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!transhumanceRequestRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TranshumanceRequestDTO result = transhumanceRequestService.update(transhumanceRequestDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, transhumanceRequestDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /transhumance-requests/:id} : Partial updates given fields of an existing transhumanceRequest, field will ignore if it is null
     *
     * @param id the id of the transhumanceRequestDTO to save.
     * @param transhumanceRequestDTO the transhumanceRequestDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated transhumanceRequestDTO,
     * or with status {@code 400 (Bad Request)} if the transhumanceRequestDTO is not valid,
     * or with status {@code 404 (Not Found)} if the transhumanceRequestDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the transhumanceRequestDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/transhumance-requests/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TranshumanceRequestDTO> partialUpdateTranshumanceRequest(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody TranshumanceRequestDTO transhumanceRequestDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TranshumanceRequest partially : {}, {}", id, transhumanceRequestDTO);
        if (transhumanceRequestDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, transhumanceRequestDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!transhumanceRequestRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TranshumanceRequestDTO> result = transhumanceRequestService.partialUpdate(transhumanceRequestDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, transhumanceRequestDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /transhumance-requests} : get all the transhumanceRequests.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of transhumanceRequests in body.
     */
    @GetMapping("/transhumance-requests")
    public ResponseEntity<List<TranshumanceRequestDTO>> getAllTranshumanceRequests(
        TranshumanceRequestCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get TranshumanceRequests by criteria: {}", criteria);
        Page<TranshumanceRequestDTO> page = transhumanceRequestQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /transhumance-requests/count} : count all the transhumanceRequests.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/transhumance-requests/count")
    public ResponseEntity<Long> countTranshumanceRequests(TranshumanceRequestCriteria criteria) {
        log.debug("REST request to count TranshumanceRequests by criteria: {}", criteria);
        return ResponseEntity.ok().body(transhumanceRequestQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /transhumance-requests/:id} : get the "id" transhumanceRequest.
     *
     * @param id the id of the transhumanceRequestDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the transhumanceRequestDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/transhumance-requests/{id}")
    public ResponseEntity<TranshumanceRequestDTO> getTranshumanceRequest(@PathVariable Long id) {
        log.debug("REST request to get TranshumanceRequest : {}", id);
        Optional<TranshumanceRequestDTO> transhumanceRequestDTO = transhumanceRequestService.findOne(id);
        return ResponseUtil.wrapOrNotFound(transhumanceRequestDTO);
    }

    /**
     * {@code DELETE  /transhumance-requests/:id} : delete the "id" transhumanceRequest.
     *
     * @param id the id of the transhumanceRequestDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/transhumance-requests/{id}")
    public ResponseEntity<Void> deleteTranshumanceRequest(@PathVariable Long id) {
        log.debug("REST request to delete TranshumanceRequest : {}", id);
        transhumanceRequestService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
