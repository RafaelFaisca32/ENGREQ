package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.domain.Unfolding;
import com.mycompany.myapp.repository.UnfoldingRepository;
import com.mycompany.myapp.service.criteria.UnfoldingCriteria;
import com.mycompany.myapp.service.dto.UnfoldingDTO;
import com.mycompany.myapp.service.mapper.UnfoldingMapper;
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
 * Service for executing complex queries for {@link Unfolding} entities in the database.
 * The main input is a {@link UnfoldingCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link UnfoldingDTO} or a {@link Page} of {@link UnfoldingDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class UnfoldingQueryService extends QueryService<Unfolding> {

    private final Logger log = LoggerFactory.getLogger(UnfoldingQueryService.class);

    private final UnfoldingRepository unfoldingRepository;

    private final UnfoldingMapper unfoldingMapper;

    public UnfoldingQueryService(UnfoldingRepository unfoldingRepository, UnfoldingMapper unfoldingMapper) {
        this.unfoldingRepository = unfoldingRepository;
        this.unfoldingMapper = unfoldingMapper;
    }

    /**
     * Return a {@link List} of {@link UnfoldingDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<UnfoldingDTO> findByCriteria(UnfoldingCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Unfolding> specification = createSpecification(criteria);
        return unfoldingMapper.toDto(unfoldingRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link UnfoldingDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<UnfoldingDTO> findByCriteria(UnfoldingCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Unfolding> specification = createSpecification(criteria);
        return unfoldingRepository.findAll(specification, page).map(unfoldingMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(UnfoldingCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Unfolding> specification = createSpecification(criteria);
        return unfoldingRepository.count(specification);
    }

    /**
     * Function to convert {@link UnfoldingCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Unfolding> createSpecification(UnfoldingCriteria criteria) {
        Specification<Unfolding> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Unfolding_.id));
            }
            if (criteria.getObservations() != null) {
                specification = specification.and(buildStringSpecification(criteria.getObservations(), Unfolding_.observations));
            }
            if (criteria.getDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDate(), Unfolding_.date));
            }
            if (criteria.getHiveId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getHiveId(), root -> root.join(Unfolding_.hive, JoinType.LEFT).get(Hive_.id))
                    );
            }
        }
        return specification;
    }
}
