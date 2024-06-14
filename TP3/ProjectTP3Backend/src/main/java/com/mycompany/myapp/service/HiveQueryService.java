package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.domain.Hive;
import com.mycompany.myapp.repository.HiveRepository;
import com.mycompany.myapp.service.criteria.HiveCriteria;
import com.mycompany.myapp.service.dto.HiveDTO;
import com.mycompany.myapp.service.mapper.HiveMapper;
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
 * Service for executing complex queries for {@link Hive} entities in the database.
 * The main input is a {@link HiveCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link HiveDTO} or a {@link Page} of {@link HiveDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class HiveQueryService extends QueryService<Hive> {

    private final Logger log = LoggerFactory.getLogger(HiveQueryService.class);

    private final HiveRepository hiveRepository;

    private final HiveMapper hiveMapper;

    public HiveQueryService(HiveRepository hiveRepository, HiveMapper hiveMapper) {
        this.hiveRepository = hiveRepository;
        this.hiveMapper = hiveMapper;
    }

    /**
     * Return a {@link List} of {@link HiveDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<HiveDTO> findByCriteria(HiveCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Hive> specification = createSpecification(criteria);
        return hiveMapper.toDto(hiveRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link HiveDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<HiveDTO> findByCriteria(HiveCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Hive> specification = createSpecification(criteria);
        return hiveRepository.findAll(specification, page).map(hiveMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(HiveCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Hive> specification = createSpecification(criteria);
        return hiveRepository.count(specification);
    }

    /**
     * Function to convert {@link HiveCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Hive> createSpecification(HiveCriteria criteria) {
        Specification<Hive> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Hive_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), Hive_.code));
            }
            if (criteria.getApiaryId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getApiaryId(), root -> root.join(Hive_.apiary, JoinType.LEFT).get(Apiary_.id))
                    );
            }
            if (criteria.getFrameId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getFrameId(), root -> root.join(Hive_.frames, JoinType.LEFT).get(Frame_.id))
                    );
            }
            if (criteria.getUnfoldingCreatedHiveId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getUnfoldingCreatedHiveId(),
                            root -> root.join(Hive_.unfoldingCreatedHive, JoinType.LEFT).get(Unfolding_.id)
                        )
                    );
            }
            if (criteria.getCrestId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getCrestId(), root -> root.join(Hive_.crests, JoinType.LEFT).get(Crest_.id))
                    );
            }
            if (criteria.getUnfoldingId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getUnfoldingId(), root -> root.join(Hive_.unfoldings, JoinType.LEFT).get(Unfolding_.id))
                    );
            }
            if (criteria.getInspectionId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getInspectionId(),
                            root -> root.join(Hive_.inspections, JoinType.LEFT).get(Inspection_.id)
                        )
                    );
            }
            if (criteria.getTranshumanceRequestId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getTranshumanceRequestId(),
                            root -> root.join(Hive_.transhumanceRequests, JoinType.LEFT).get(TranshumanceRequest_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
