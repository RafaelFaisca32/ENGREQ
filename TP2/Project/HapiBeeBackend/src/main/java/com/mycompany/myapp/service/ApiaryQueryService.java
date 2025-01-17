package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.domain.Apiary;
import com.mycompany.myapp.repository.ApiaryRepository;
import com.mycompany.myapp.service.criteria.ApiaryCriteria;
import com.mycompany.myapp.service.dto.ApiaryDTO;
import com.mycompany.myapp.service.mapper.ApiaryMapper;
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
 * Service for executing complex queries for {@link Apiary} entities in the database.
 * The main input is a {@link ApiaryCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ApiaryDTO} or a {@link Page} of {@link ApiaryDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ApiaryQueryService extends QueryService<Apiary> {

    private final Logger log = LoggerFactory.getLogger(ApiaryQueryService.class);

    private final ApiaryRepository apiaryRepository;

    private final ApiaryMapper apiaryMapper;

    public ApiaryQueryService(ApiaryRepository apiaryRepository, ApiaryMapper apiaryMapper) {
        this.apiaryRepository = apiaryRepository;
        this.apiaryMapper = apiaryMapper;
    }

    /**
     * Return a {@link List} of {@link ApiaryDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ApiaryDTO> findByCriteria(ApiaryCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Apiary> specification = createSpecification(criteria);
        return apiaryMapper.toDto(apiaryRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ApiaryDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ApiaryDTO> findByCriteria(ApiaryCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Apiary> specification = createSpecification(criteria);
        return apiaryRepository.findAll(specification, page).map(apiaryMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ApiaryCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Apiary> specification = createSpecification(criteria);
        return apiaryRepository.count(specification);
    }

    /**
     * Function to convert {@link ApiaryCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Apiary> createSpecification(ApiaryCriteria criteria) {
        Specification<Apiary> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Apiary_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Apiary_.name));
            }
            if (criteria.getState() != null) {
                specification = specification.and(buildSpecification(criteria.getState(), Apiary_.state));
            }
            if (criteria.getNumber() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumber(), Apiary_.number));
            }
            if (criteria.getIntensive() != null) {
                specification = specification.and(buildSpecification(criteria.getIntensive(), Apiary_.intensive));
            }
            if (criteria.getTranshumance() != null) {
                specification = specification.and(buildSpecification(criteria.getTranshumance(), Apiary_.transhumance));
            }
            if (criteria.getZoneId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getZoneId(), root -> root.join(Apiary_.zone, JoinType.LEFT).get(Zone_.id))
                    );
            }
            if (criteria.getBeekeeperId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getBeekeeperId(),
                            root -> root.join(Apiary_.beekeeper, JoinType.LEFT).get(Beekeeper_.id)
                        )
                    );
            }
            if (criteria.getHiveId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getHiveId(), root -> root.join(Apiary_.hives, JoinType.LEFT).get(Hive_.id))
                    );
            }
            if (criteria.getTranshumanceRequestId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getTranshumanceRequestId(),
                            root -> root.join(Apiary_.transhumanceRequests, JoinType.LEFT).get(TranshumanceRequest_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
