package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.domain.TranshumanceRequest;
import com.mycompany.myapp.repository.TranshumanceRequestRepository;
import com.mycompany.myapp.service.criteria.TranshumanceRequestCriteria;
import com.mycompany.myapp.service.dto.TranshumanceRequestDTO;
import com.mycompany.myapp.service.mapper.TranshumanceRequestMapper;
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
 * Service for executing complex queries for {@link TranshumanceRequest} entities in the database.
 * The main input is a {@link TranshumanceRequestCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link TranshumanceRequestDTO} or a {@link Page} of {@link TranshumanceRequestDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class TranshumanceRequestQueryService extends QueryService<TranshumanceRequest> {

    private final Logger log = LoggerFactory.getLogger(TranshumanceRequestQueryService.class);

    private final TranshumanceRequestRepository transhumanceRequestRepository;

    private final TranshumanceRequestMapper transhumanceRequestMapper;

    public TranshumanceRequestQueryService(
        TranshumanceRequestRepository transhumanceRequestRepository,
        TranshumanceRequestMapper transhumanceRequestMapper
    ) {
        this.transhumanceRequestRepository = transhumanceRequestRepository;
        this.transhumanceRequestMapper = transhumanceRequestMapper;
    }

    /**
     * Return a {@link List} of {@link TranshumanceRequestDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<TranshumanceRequestDTO> findByCriteria(TranshumanceRequestCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<TranshumanceRequest> specification = createSpecification(criteria);
        return transhumanceRequestMapper.toDto(transhumanceRequestRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link TranshumanceRequestDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<TranshumanceRequestDTO> findByCriteria(TranshumanceRequestCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<TranshumanceRequest> specification = createSpecification(criteria);
        return transhumanceRequestRepository.findAll(specification, page).map(transhumanceRequestMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(TranshumanceRequestCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<TranshumanceRequest> specification = createSpecification(criteria);
        return transhumanceRequestRepository.count(specification);
    }

    /**
     * Function to convert {@link TranshumanceRequestCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<TranshumanceRequest> createSpecification(TranshumanceRequestCriteria criteria) {
        Specification<TranshumanceRequest> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), TranshumanceRequest_.id));
            }
            if (criteria.getRequestDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRequestDate(), TranshumanceRequest_.requestDate));
            }
            if (criteria.getState() != null) {
                specification = specification.and(buildSpecification(criteria.getState(), TranshumanceRequest_.state));
            }
            if (criteria.getBeekeeperId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getBeekeeperId(),
                            root -> root.join(TranshumanceRequest_.beekeeper, JoinType.LEFT).get(Beekeeper_.id)
                        )
                    );
            }
            if (criteria.getApiaryId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getApiaryId(),
                            root -> root.join(TranshumanceRequest_.apiary, JoinType.LEFT).get(Apiary_.id)
                        )
                    );
            }
            if (criteria.getHiveId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getHiveId(), root -> root.join(TranshumanceRequest_.hives, JoinType.LEFT).get(Hive_.id))
                    );
            }
        }
        return specification;
    }
}
