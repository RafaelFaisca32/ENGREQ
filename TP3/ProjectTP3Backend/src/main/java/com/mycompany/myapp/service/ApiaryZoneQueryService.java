package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.domain.ApiaryZone;
import com.mycompany.myapp.repository.ApiaryZoneRepository;
import com.mycompany.myapp.service.criteria.ApiaryZoneCriteria;
import com.mycompany.myapp.service.dto.ApiaryZoneDTO;
import com.mycompany.myapp.service.mapper.ApiaryZoneMapper;
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
 * Service for executing complex queries for {@link ApiaryZone} entities in the database.
 * The main input is a {@link ApiaryZoneCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ApiaryZoneDTO} or a {@link Page} of {@link ApiaryZoneDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ApiaryZoneQueryService extends QueryService<ApiaryZone> {

    private final Logger log = LoggerFactory.getLogger(ApiaryZoneQueryService.class);

    private final ApiaryZoneRepository apiaryZoneRepository;

    private final ApiaryZoneMapper apiaryZoneMapper;

    public ApiaryZoneQueryService(ApiaryZoneRepository apiaryZoneRepository, ApiaryZoneMapper apiaryZoneMapper) {
        this.apiaryZoneRepository = apiaryZoneRepository;
        this.apiaryZoneMapper = apiaryZoneMapper;
    }

    /**
     * Return a {@link List} of {@link ApiaryZoneDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ApiaryZoneDTO> findByCriteria(ApiaryZoneCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ApiaryZone> specification = createSpecification(criteria);
        return apiaryZoneMapper.toDto(apiaryZoneRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ApiaryZoneDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ApiaryZoneDTO> findByCriteria(ApiaryZoneCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ApiaryZone> specification = createSpecification(criteria);
        return apiaryZoneRepository.findAll(specification, page).map(apiaryZoneMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ApiaryZoneCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ApiaryZone> specification = createSpecification(criteria);
        return apiaryZoneRepository.count(specification);
    }

    /**
     * Function to convert {@link ApiaryZoneCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<ApiaryZone> createSpecification(ApiaryZoneCriteria criteria) {
        Specification<ApiaryZone> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), ApiaryZone_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ApiaryZone_.name));
            }
            if (criteria.getControlledZone() != null) {
                specification = specification.and(buildSpecification(criteria.getControlledZone(), ApiaryZone_.controlledZone));
            }
            if (criteria.getState() != null) {
                specification = specification.and(buildSpecification(criteria.getState(), ApiaryZone_.state));
            }
            if (criteria.getApiaryId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getApiaryId(), root -> root.join(ApiaryZone_.apiaries, JoinType.LEFT).get(Apiary_.id))
                    );
            }
        }
        return specification;
    }
}
