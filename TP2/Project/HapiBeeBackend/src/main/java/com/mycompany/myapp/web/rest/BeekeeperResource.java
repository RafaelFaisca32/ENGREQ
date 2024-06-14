package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.BeekeeperRepository;
import com.mycompany.myapp.service.BeekeeperQueryService;
import com.mycompany.myapp.service.BeekeeperService;
import com.mycompany.myapp.service.criteria.BeekeeperCriteria;
import com.mycompany.myapp.service.dto.BeekeeperDTO;
import com.mycompany.myapp.service.dto.UserDTO;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.Beekeeper}.
 */
@RestController
@RequestMapping("/api")
public class BeekeeperResource {

    private final Logger log = LoggerFactory.getLogger(BeekeeperResource.class);

    private static final String ENTITY_NAME = "beekeeper";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BeekeeperService beekeeperService;

    private final BeekeeperRepository beekeeperRepository;

    private final BeekeeperQueryService beekeeperQueryService;

    public BeekeeperResource(
        BeekeeperService beekeeperService,
        BeekeeperRepository beekeeperRepository,
        BeekeeperQueryService beekeeperQueryService
    ) {
        this.beekeeperService = beekeeperService;
        this.beekeeperRepository = beekeeperRepository;
        this.beekeeperQueryService = beekeeperQueryService;
    }

    /**
     * {@code POST  /beekeepers} : Create a new beekeeper.
     *
     * @param beekeeperDTO the beekeeperDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new beekeeperDTO, or with status {@code 400 (Bad Request)} if the beekeeper has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/beekeepers")
    public ResponseEntity<BeekeeperDTO> createBeekeeper(@RequestBody BeekeeperDTO beekeeperDTO) throws URISyntaxException {
        log.debug("REST request to save Beekeeper : {}", beekeeperDTO);
        if (beekeeperDTO.getUserId() != 0 && beekeeperDTO.getUserId() != null) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(beekeeperDTO.getUserId());
            beekeeperDTO.setUser(userDTO);
        }
        if (beekeeperDTO.getId() != null) {
            throw new BadRequestAlertException("A new beekeeper cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BeekeeperDTO result = beekeeperService.save(beekeeperDTO);
        return ResponseEntity
            .created(new URI("/api/beekeepers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /beekeepers/:id} : Updates an existing beekeeper.
     *
     * @param id the id of the beekeeperDTO to save.
     * @param beekeeperDTO the beekeeperDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated beekeeperDTO,
     * or with status {@code 400 (Bad Request)} if the beekeeperDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the beekeeperDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/beekeepers/{id}")
    public ResponseEntity<BeekeeperDTO> updateBeekeeper(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody BeekeeperDTO beekeeperDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Beekeeper : {}, {}", id, beekeeperDTO);
        if (beekeeperDTO.getUserId() != 0 && beekeeperDTO.getUserId() != null) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(beekeeperDTO.getUserId());
            beekeeperDTO.setUser(userDTO);
        }
        if (beekeeperDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, beekeeperDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!beekeeperRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BeekeeperDTO result = beekeeperService.update(beekeeperDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, beekeeperDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /beekeepers/:id} : Partial updates given fields of an existing beekeeper, field will ignore if it is null
     *
     * @param id the id of the beekeeperDTO to save.
     * @param beekeeperDTO the beekeeperDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated beekeeperDTO,
     * or with status {@code 400 (Bad Request)} if the beekeeperDTO is not valid,
     * or with status {@code 404 (Not Found)} if the beekeeperDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the beekeeperDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/beekeepers/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BeekeeperDTO> partialUpdateBeekeeper(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody BeekeeperDTO beekeeperDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Beekeeper partially : {}, {}", id, beekeeperDTO);
        if (beekeeperDTO.getUserId() != 0 && beekeeperDTO.getUserId() != null) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(beekeeperDTO.getUserId());
            beekeeperDTO.setUser(userDTO);
        }
        if (beekeeperDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, beekeeperDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!beekeeperRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BeekeeperDTO> result = beekeeperService.partialUpdate(beekeeperDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, beekeeperDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /beekeepers} : get all the beekeepers.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of beekeepers in body.
     */
    @GetMapping("/beekeepers")
    public ResponseEntity<List<BeekeeperDTO>> getAllBeekeepers(
        BeekeeperCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get Beekeepers by criteria: {}", criteria);
        Page<BeekeeperDTO> page = beekeeperQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /beekeepers/count} : count all the beekeepers.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/beekeepers/count")
    public ResponseEntity<Long> countBeekeepers(BeekeeperCriteria criteria) {
        log.debug("REST request to count Beekeepers by criteria: {}", criteria);
        return ResponseEntity.ok().body(beekeeperQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /beekeepers/:id} : get the "id" beekeeper.
     *
     * @param id the id of the beekeeperDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the beekeeperDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/beekeepers/{id}")
    public ResponseEntity<BeekeeperDTO> getBeekeeper(@PathVariable Long id) {
        log.debug("REST request to get Beekeeper : {}", id);
        Optional<BeekeeperDTO> beekeeperDTO = beekeeperService.findOne(id);
        return ResponseUtil.wrapOrNotFound(beekeeperDTO);
    }

    /**
     * {@code DELETE  /beekeepers/:id} : delete the "id" beekeeper.
     *
     * @param id the id of the beekeeperDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/beekeepers/{id}")
    public ResponseEntity<Void> deleteBeekeeper(@PathVariable Long id) {
        log.debug("REST request to delete Beekeeper : {}", id);
        beekeeperService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
