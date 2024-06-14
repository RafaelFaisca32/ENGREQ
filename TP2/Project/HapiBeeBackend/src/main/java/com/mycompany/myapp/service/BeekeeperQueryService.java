package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.domain.Beekeeper;
import com.mycompany.myapp.repository.BeekeeperRepository;
import com.mycompany.myapp.service.criteria.BeekeeperCriteria;
import com.mycompany.myapp.service.dto.BeekeeperDTO;
import com.mycompany.myapp.service.mapper.BeekeeperMapper;
import java.util.List;
import javax.persistence.criteria.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Beekeeper} entities in the database.
 * The main input is a {@link BeekeeperCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link BeekeeperDTO} or a {@link Page} of {@link BeekeeperDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class BeekeeperQueryService extends QueryService<Beekeeper> {

    private final Logger log = LoggerFactory.getLogger(BeekeeperQueryService.class);

    private final BeekeeperRepository beekeeperRepository;

    private final BeekeeperMapper beekeeperMapper;

    public BeekeeperQueryService(BeekeeperRepository beekeeperRepository, BeekeeperMapper beekeeperMapper) {
        this.beekeeperRepository = beekeeperRepository;
        this.beekeeperMapper = beekeeperMapper;
    }

    /**
     * Return a {@link List} of {@link BeekeeperDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<BeekeeperDTO> findByCriteria(BeekeeperCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Beekeeper> specification = createSpecification(criteria);
        return beekeeperMapper.toDto(beekeeperRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link BeekeeperDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<BeekeeperDTO> findByCriteria(BeekeeperCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Beekeeper> specification = createSpecification(criteria);
        return beekeeperRepository.findAll(specification, page).map(beekeeperMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(BeekeeperCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Beekeeper> specification = createSpecification(criteria);
        return beekeeperRepository.count(specification);
    }

    /**
     * Function to convert {@link BeekeeperCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Beekeeper> createSpecification(BeekeeperCriteria criteria) {
        Specification<Beekeeper> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Beekeeper_.id));
            }
            if (criteria.getBeekeeperCompleteName() != null) {
                specification =
                    specification.and(buildStringSpecification(criteria.getBeekeeperCompleteName(), Beekeeper_.beekeeperCompleteName));
            }
            if (criteria.getBeekeeperNumber() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBeekeeperNumber(), Beekeeper_.beekeeperNumber));
            }
            if (criteria.getEntityZoneResidence() != null) {
                specification =
                    specification.and(buildRangeSpecification(criteria.getEntityZoneResidence(), Beekeeper_.entityZoneResidence));
            }
            if (criteria.getNif() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNif(), Beekeeper_.nif));
            }
            if (criteria.getAddress() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAddress(), Beekeeper_.address));
            }
            if (criteria.getPostalCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPostalCode(), Beekeeper_.postalCode));
            }
            if (criteria.getPhoneNumber() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPhoneNumber(), Beekeeper_.phoneNumber));
            }
            if (criteria.getFaxNumber() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFaxNumber(), Beekeeper_.faxNumber));
            }
            if (criteria.getUserId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getUserId(), root -> root.join(Beekeeper_.user, JoinType.LEFT).get(User_.id))
                    );
            }
            if (criteria.getApiaryId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getApiaryId(), root -> root.join(Beekeeper_.apiaries, JoinType.LEFT).get(Apiary_.id))
                    );
            }
        }
        return specification;
    }
}
