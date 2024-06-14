package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.domain.Disease;
import com.mycompany.myapp.repository.DiseaseRepository;
import com.mycompany.myapp.service.criteria.DiseaseCriteria;
import com.mycompany.myapp.service.dto.DiseaseDTO;
import com.mycompany.myapp.service.mapper.DiseaseMapper;
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
 * Service for executing complex queries for {@link Disease} entities in the database.
 * The main input is a {@link DiseaseCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DiseaseDTO} or a {@link Page} of {@link DiseaseDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DiseaseQueryService extends QueryService<Disease> {

    private final Logger log = LoggerFactory.getLogger(DiseaseQueryService.class);

    private final DiseaseRepository diseaseRepository;

    private final DiseaseMapper diseaseMapper;

    public DiseaseQueryService(DiseaseRepository diseaseRepository, DiseaseMapper diseaseMapper) {
        this.diseaseRepository = diseaseRepository;
        this.diseaseMapper = diseaseMapper;
    }

    /**
     * Return a {@link List} of {@link DiseaseDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DiseaseDTO> findByCriteria(DiseaseCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Disease> specification = createSpecification(criteria);
        return diseaseMapper.toDto(diseaseRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DiseaseDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DiseaseDTO> findByCriteria(DiseaseCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Disease> specification = createSpecification(criteria);
        return diseaseRepository.findAll(specification, page).map(diseaseMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DiseaseCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Disease> specification = createSpecification(criteria);
        return diseaseRepository.count(specification);
    }

    /**
     * Function to convert {@link DiseaseCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Disease> createSpecification(DiseaseCriteria criteria) {
        Specification<Disease> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Disease_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Disease_.name));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), Disease_.description));
            }
            if (criteria.getInspectionId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getInspectionId(),
                            root -> root.join(Disease_.inspections, JoinType.LEFT).get(Inspection_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
