package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.domain.Crest;
import com.mycompany.myapp.repository.CrestRepository;
import com.mycompany.myapp.service.criteria.CrestCriteria;
import com.mycompany.myapp.service.dto.CrestDTO;
import com.mycompany.myapp.service.mapper.CrestMapper;
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
 * Service for executing complex queries for {@link Crest} entities in the database.
 * The main input is a {@link CrestCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CrestDTO} or a {@link Page} of {@link CrestDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CrestQueryService extends QueryService<Crest> {

    private final Logger log = LoggerFactory.getLogger(CrestQueryService.class);

    private final CrestRepository crestRepository;

    private final CrestMapper crestMapper;

    public CrestQueryService(CrestRepository crestRepository, CrestMapper crestMapper) {
        this.crestRepository = crestRepository;
        this.crestMapper = crestMapper;
    }

    /**
     * Return a {@link List} of {@link CrestDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CrestDTO> findByCriteria(CrestCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Crest> specification = createSpecification(criteria);
        return crestMapper.toDto(crestRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CrestDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CrestDTO> findByCriteria(CrestCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Crest> specification = createSpecification(criteria);
        return crestRepository.findAll(specification, page).map(crestMapper::toDto);
    }

    /**
     * Return a {@link Page} of {@link CrestDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CrestDTO> findByCriteriaWithUserId(Pageable page, Long userId) {
        log.debug("find by page: {}", page);
        return crestRepository.findAllByUserId(page,userId).map(crestMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CrestCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Crest> specification = createSpecification(criteria);
        return crestRepository.count(specification);
    }

    /**
     * Function to convert {@link CrestCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Crest> createSpecification(CrestCriteria criteria) {
        Specification<Crest> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Crest_.id));
            }
            if (criteria.getCombFrameQuantity() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCombFrameQuantity(), Crest_.combFrameQuantity));
            }
            if (criteria.getWaxWeight() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getWaxWeight(), Crest_.waxWeight));
            }
            if (criteria.getTimeWastedCentrifuge() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTimeWastedCentrifuge(), Crest_.timeWastedCentrifuge));
            }
            if (criteria.getInitialDateDecantation() != null) {
                specification =
                    specification.and(buildRangeSpecification(criteria.getInitialDateDecantation(), Crest_.initialDateDecantation));
            }
            if (criteria.getFinalDateDecantation() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFinalDateDecantation(), Crest_.finalDateDecantation));
            }
            if (criteria.getProducedHoneyQuantity() != null) {
                specification =
                    specification.and(buildRangeSpecification(criteria.getProducedHoneyQuantity(), Crest_.producedHoneyQuantity));
            }
            if (criteria.getState() != null) {
                specification = specification.and(buildSpecification(criteria.getState(), Crest_.state));
            }
            if (criteria.getHiveId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getHiveId(), root -> root.join(Crest_.hive, JoinType.LEFT).get(Hive_.id))
                    );
            }
        }
        return specification;
    }
}
