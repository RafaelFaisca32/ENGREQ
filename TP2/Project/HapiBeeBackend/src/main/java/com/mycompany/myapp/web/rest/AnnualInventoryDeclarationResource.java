package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.AnnualInventoryDeclarationRepository;
import com.mycompany.myapp.service.AnnualInventoryDeclarationQueryService;
import com.mycompany.myapp.service.AnnualInventoryDeclarationService;
import com.mycompany.myapp.service.criteria.AnnualInventoryDeclarationCriteria;
import com.mycompany.myapp.service.dto.AnnualInventoryDeclarationDTO;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.AnnualInventoryDeclaration}.
 */
@RestController
@RequestMapping("/api")
public class AnnualInventoryDeclarationResource {

    private final Logger log = LoggerFactory.getLogger(AnnualInventoryDeclarationResource.class);

    private static final String ENTITY_NAME = "annualInventoryDeclaration";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AnnualInventoryDeclarationService annualInventoryDeclarationService;

    private final AnnualInventoryDeclarationRepository annualInventoryDeclarationRepository;

    private final AnnualInventoryDeclarationQueryService annualInventoryDeclarationQueryService;

    public AnnualInventoryDeclarationResource(
        AnnualInventoryDeclarationService annualInventoryDeclarationService,
        AnnualInventoryDeclarationRepository annualInventoryDeclarationRepository,
        AnnualInventoryDeclarationQueryService annualInventoryDeclarationQueryService
    ) {
        this.annualInventoryDeclarationService = annualInventoryDeclarationService;
        this.annualInventoryDeclarationRepository = annualInventoryDeclarationRepository;
        this.annualInventoryDeclarationQueryService = annualInventoryDeclarationQueryService;
    }

    /**
     * {@code POST  /annual-inventory-declarations} : Create a new annualInventoryDeclaration.
     *
     * @param annualInventoryDeclarationDTO the annualInventoryDeclarationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new annualInventoryDeclarationDTO, or with status {@code 400 (Bad Request)} if the annualInventoryDeclaration has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/annual-inventory-declarations")
    public ResponseEntity<AnnualInventoryDeclarationDTO> createAnnualInventoryDeclaration(
        @Valid @RequestBody AnnualInventoryDeclarationDTO annualInventoryDeclarationDTO
    ) throws URISyntaxException, IOException, InterruptedException {
        log.debug("REST request to save AnnualInventoryDeclaration : {}", annualInventoryDeclarationDTO);
        if (annualInventoryDeclarationDTO.getId() != null) {
            throw new BadRequestAlertException("A new annualInventoryDeclaration cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AnnualInventoryDeclarationDTO result = annualInventoryDeclarationService.save(annualInventoryDeclarationDTO);
        annualInventoryDeclarationService.sendAuthorizationRequestToPortal(result.getId());
        return ResponseEntity
            .created(new URI("/api/annual-inventory-declarations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /annual-inventory-declarations/:id} : Updates an existing annualInventoryDeclaration.
     *
     * @param id the id of the annualInventoryDeclarationDTO to save.
     * @param annualInventoryDeclarationDTO the annualInventoryDeclarationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated annualInventoryDeclarationDTO,
     * or with status {@code 400 (Bad Request)} if the annualInventoryDeclarationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the annualInventoryDeclarationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/annual-inventory-declarations/{id}")
    public ResponseEntity<AnnualInventoryDeclarationDTO> updateAnnualInventoryDeclaration(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody AnnualInventoryDeclarationDTO annualInventoryDeclarationDTO
    ) throws URISyntaxException {
        log.debug("REST request to update AnnualInventoryDeclaration : {}, {}", id, annualInventoryDeclarationDTO);
        if (annualInventoryDeclarationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, annualInventoryDeclarationDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!annualInventoryDeclarationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AnnualInventoryDeclarationDTO result = annualInventoryDeclarationService.update(annualInventoryDeclarationDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, annualInventoryDeclarationDTO.getId().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /annual-inventory-declarations/:id} : Partial updates given fields of an existing annualInventoryDeclaration, field will ignore if it is null
     *
     * @param id the id of the annualInventoryDeclarationDTO to save.
     * @param annualInventoryDeclarationDTO the annualInventoryDeclarationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated annualInventoryDeclarationDTO,
     * or with status {@code 400 (Bad Request)} if the annualInventoryDeclarationDTO is not valid,
     * or with status {@code 404 (Not Found)} if the annualInventoryDeclarationDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the annualInventoryDeclarationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/annual-inventory-declarations/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<AnnualInventoryDeclarationDTO> partialUpdateAnnualInventoryDeclaration(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody AnnualInventoryDeclarationDTO annualInventoryDeclarationDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update AnnualInventoryDeclaration partially : {}, {}", id, annualInventoryDeclarationDTO);
        if (annualInventoryDeclarationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, annualInventoryDeclarationDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!annualInventoryDeclarationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AnnualInventoryDeclarationDTO> result = annualInventoryDeclarationService.partialUpdate(annualInventoryDeclarationDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, annualInventoryDeclarationDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /annual-inventory-declarations} : get all the annualInventoryDeclarations.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of annualInventoryDeclarations in body.
     */
    @GetMapping("/annual-inventory-declarations")
    public ResponseEntity<List<AnnualInventoryDeclarationDTO>> getAllAnnualInventoryDeclarations(
        AnnualInventoryDeclarationCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get AnnualInventoryDeclarations by criteria: {}", criteria);
        Page<AnnualInventoryDeclarationDTO> page = annualInventoryDeclarationQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /annual-inventory-declarations/count} : count all the annualInventoryDeclarations.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/annual-inventory-declarations/count")
    public ResponseEntity<Long> countAnnualInventoryDeclarations(AnnualInventoryDeclarationCriteria criteria) {
        log.debug("REST request to count AnnualInventoryDeclarations by criteria: {}", criteria);
        return ResponseEntity.ok().body(annualInventoryDeclarationQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /annual-inventory-declarations/:id} : get the "id" annualInventoryDeclaration.
     *
     * @param id the id of the annualInventoryDeclarationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the annualInventoryDeclarationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/annual-inventory-declarations/{id}")
    public ResponseEntity<AnnualInventoryDeclarationDTO> getAnnualInventoryDeclaration(@PathVariable Long id) {
        log.debug("REST request to get AnnualInventoryDeclaration : {}", id);
        Optional<AnnualInventoryDeclarationDTO> annualInventoryDeclarationDTO = annualInventoryDeclarationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(annualInventoryDeclarationDTO);
    }

    /**
     * {@code DELETE  /annual-inventory-declarations/:id} : delete the "id" annualInventoryDeclaration.
     *
     * @param id the id of the annualInventoryDeclarationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/annual-inventory-declarations/{id}")
    public ResponseEntity<Void> deleteAnnualInventoryDeclaration(@PathVariable Long id) {
        log.debug("REST request to delete AnnualInventoryDeclaration : {}", id);
        annualInventoryDeclarationService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }

    @PostMapping("/receiveAnnualInventoryDeclarationApproval")
    public ResponseEntity<String> receiveTranshumanceApproval(@RequestBody String result)  {
        annualInventoryDeclarationService.approveOrRejectAnnualInventoryDeclarationApproval(result);

        return null;
    }

}
