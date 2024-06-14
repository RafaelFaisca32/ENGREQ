package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.domain.Inspection;
import com.mycompany.myapp.repository.InspectionRepository;
import com.mycompany.myapp.service.criteria.InspectionCriteria;
import com.mycompany.myapp.service.dto.InspectionDTO;
import com.mycompany.myapp.service.mapper.InspectionMapper;
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
 * Service for executing complex queries for {@link Inspection} entities in the database.
 * The main input is a {@link InspectionCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link InspectionDTO} or a {@link Page} of {@link InspectionDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class InspectionQueryService extends QueryService<Inspection> {

    private final Logger log = LoggerFactory.getLogger(InspectionQueryService.class);

    private final InspectionRepository inspectionRepository;

    private final InspectionMapper inspectionMapper;

    public InspectionQueryService(InspectionRepository inspectionRepository, InspectionMapper inspectionMapper) {
        this.inspectionRepository = inspectionRepository;
        this.inspectionMapper = inspectionMapper;
    }

    /**
     * Return a {@link List} of {@link InspectionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<InspectionDTO> findByCriteria(InspectionCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Inspection> specification = createSpecification(criteria);
        return inspectionMapper.toDto(inspectionRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link InspectionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<InspectionDTO> findByCriteria(InspectionCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Inspection> specification = createSpecification(criteria);
        return inspectionRepository.findAll(specification, page).map(inspectionMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(InspectionCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Inspection> specification = createSpecification(criteria);
        return inspectionRepository.count(specification);
    }

    /**
     * Function to convert {@link InspectionCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Inspection> createSpecification(InspectionCriteria criteria) {
        Specification<Inspection> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Inspection_.id));
            }
            if (criteria.getDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDate(), Inspection_.date));
            }
            if (criteria.getNote() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNote(), Inspection_.note));
            }
            if (criteria.getHiveId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getHiveId(), root -> root.join(Inspection_.hive, JoinType.LEFT).get(Hive_.id))
                    );
            }
            if (criteria.getDiseaseId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getDiseaseId(), root -> root.join(Inspection_.diseases, JoinType.LEFT).get(Disease_.id))
                    );
            }
        }
        return specification;
    }
}
