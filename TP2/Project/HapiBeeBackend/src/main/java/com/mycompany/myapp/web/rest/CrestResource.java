package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.User;
import com.mycompany.myapp.repository.CrestRepository;
import com.mycompany.myapp.security.SecurityUtils;
import com.mycompany.myapp.service.CrestQueryService;
import com.mycompany.myapp.service.CrestService;
import com.mycompany.myapp.service.UserService;
import com.mycompany.myapp.service.criteria.CrestCriteria;
import com.mycompany.myapp.service.dto.CrestDTO;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.Crest}.
 */
@RestController
@RequestMapping("/api")
public class CrestResource {

    private final Logger log = LoggerFactory.getLogger(CrestResource.class);

    private static final String ENTITY_NAME = "crest";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CrestService crestService;

    private final CrestRepository crestRepository;

    private final CrestQueryService crestQueryService;
    private final UserService userService;

    public CrestResource(CrestService crestService, CrestRepository crestRepository, CrestQueryService crestQueryService, UserService userService) {
        this.crestService = crestService;
        this.crestRepository = crestRepository;
        this.crestQueryService = crestQueryService;
        this.userService = userService;
    }

    /**
     * {@code POST  /crests} : Create a new crest.
     *
     * @param crestDTO the crestDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new crestDTO, or with status {@code 400 (Bad Request)} if the crest has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/crests")
    public ResponseEntity<CrestDTO> createCrest(@Valid @RequestBody CrestDTO crestDTO) throws URISyntaxException {
        log.debug("REST request to save Crest : {}", crestDTO);
        if (crestDTO.getHiveId() != 0 && crestDTO.getHiveId() != null) {
            HiveDTO hiveDTO = new HiveDTO();
            hiveDTO.setId(crestDTO.getHiveId());
            crestDTO.setHive(hiveDTO);
        }
        if (crestDTO.getId() != null) {
            throw new BadRequestAlertException("A new crest cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CrestDTO result = crestService.save(crestDTO);
        return ResponseEntity
            .created(new URI("/api/crests/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /crests/:id} : Updates an existing crest.
     *
     * @param id the id of the crestDTO to save.
     * @param crestDTO the crestDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated crestDTO,
     * or with status {@code 400 (Bad Request)} if the crestDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the crestDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/crests/{id}")
    public ResponseEntity<CrestDTO> updateCrest(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody CrestDTO crestDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Crest : {}, {}", id, crestDTO);

        if (crestDTO.getHiveId() != 0 && crestDTO.getHiveId() != null) {
            HiveDTO hiveDTO = new HiveDTO();
            hiveDTO.setId(crestDTO.getHiveId());
            crestDTO.setHive(hiveDTO);
        }

        if (crestDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, crestDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!crestRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        CrestDTO result = crestService.update(crestDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, crestDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /crests/:id} : Partial updates given fields of an existing crest, field will ignore if it is null
     *
     * @param id the id of the crestDTO to save.
     * @param crestDTO the crestDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated crestDTO,
     * or with status {@code 400 (Bad Request)} if the crestDTO is not valid,
     * or with status {@code 404 (Not Found)} if the crestDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the crestDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/crests/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CrestDTO> partialUpdateCrest(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody CrestDTO crestDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Crest partially : {}, {}", id, crestDTO);

        if (crestDTO.getHiveId() != 0 && crestDTO.getHiveId() != null) {
            HiveDTO hiveDTO = new HiveDTO();
            hiveDTO.setId(crestDTO.getHiveId());
            crestDTO.setHive(hiveDTO);
        }


        if (crestDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, crestDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!crestRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CrestDTO> result = crestService.partialUpdate(crestDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, crestDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /crests} : get all the crests.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of crests in body.
     */
    @GetMapping("/crests")
    public ResponseEntity<List<CrestDTO>> getAllCrests(
        CrestCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {

        log.debug("REST request to get Crests by criteria: {}", criteria);
        Page<CrestDTO> page = crestQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /crests} : get all the crests.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of crests in body.
     */
    @GetMapping("/crests/byUser")
    public ResponseEntity<List<CrestDTO>> getAllCrestsByUser(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        final Optional<User> isUser = userService.getUserWithAuthorities();
        if(isUser.isEmpty()) {
            log.error("User is not logged in");
            throw new RuntimeException("User is not logged in");
        }

        final Long userId = isUser.get().getId();

        log.debug("REST request to get Crests");
        Page<CrestDTO> page = crestQueryService.findByCriteriaWithUserId(pageable,userId);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /crests/count} : count all the crests.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/crests/count")
    public ResponseEntity<Long> countCrests(CrestCriteria criteria) {
        log.debug("REST request to count Crests by criteria: {}", criteria);
        return ResponseEntity.ok().body(crestQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /crests/:id} : get the "id" crest.
     *
     * @param id the id of the crestDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the crestDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/crests/{id}")
    public ResponseEntity<CrestDTO> getCrest(@PathVariable Long id) {
        log.debug("REST request to get Crest : {}", id);
        Optional<CrestDTO> crestDTO = crestService.findOne(id);
        return ResponseUtil.wrapOrNotFound(crestDTO);
    }

    /**
     * {@code DELETE  /crests/:id} : delete the "id" crest.
     *
     * @param id the id of the crestDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/crests/{id}")
    public ResponseEntity<Void> deleteCrest(@PathVariable Long id) {
        log.debug("REST request to delete Crest : {}", id);
        crestService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
