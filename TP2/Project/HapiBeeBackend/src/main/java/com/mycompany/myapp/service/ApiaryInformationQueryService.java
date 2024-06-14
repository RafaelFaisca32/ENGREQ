package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.domain.ApiaryInformation;
import com.mycompany.myapp.repository.ApiaryInformationRepository;
import com.mycompany.myapp.service.criteria.ApiaryInformationCriteria;
import com.mycompany.myapp.service.dto.ApiaryInformationDTO;
import com.mycompany.myapp.service.mapper.ApiaryInformationMapper;
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
 * Service for executing complex queries for {@link ApiaryInformation} entities in the database.
 * The main input is a {@link ApiaryInformationCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ApiaryInformationDTO} or a {@link Page} of {@link ApiaryInformationDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ApiaryInformationQueryService extends QueryService<ApiaryInformation> {

    private final Logger log = LoggerFactory.getLogger(ApiaryInformationQueryService.class);

    private final ApiaryInformationRepository apiaryInformationRepository;

    private final ApiaryInformationMapper apiaryInformationMapper;

    public ApiaryInformationQueryService(
        ApiaryInformationRepository apiaryInformationRepository,
        ApiaryInformationMapper apiaryInformationMapper
    ) {
        this.apiaryInformationRepository = apiaryInformationRepository;
        this.apiaryInformationMapper = apiaryInformationMapper;
    }

    /**
     * Return a {@link List} of {@link ApiaryInformationDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ApiaryInformationDTO> findByCriteria(ApiaryInformationCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ApiaryInformation> specification = createSpecification(criteria);
        return apiaryInformationMapper.toDto(apiaryInformationRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ApiaryInformationDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ApiaryInformationDTO> findByCriteria(ApiaryInformationCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ApiaryInformation> specification = createSpecification(criteria);
        return apiaryInformationRepository.findAll(specification, page).map(apiaryInformationMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ApiaryInformationCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ApiaryInformation> specification = createSpecification(criteria);
        return apiaryInformationRepository.count(specification);
    }

    /**
     * Function to convert {@link ApiaryInformationCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<ApiaryInformation> createSpecification(ApiaryInformationCriteria criteria) {
        Specification<ApiaryInformation> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), ApiaryInformation_.id));
            }
            if (criteria.getZoneNumber() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getZoneNumber(), ApiaryInformation_.zoneNumber));
            }
            if (criteria.getZoneName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getZoneName(), ApiaryInformation_.zoneName));
            }
            if (criteria.getNumberHives() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumberHives(), ApiaryInformation_.numberHives));
            }
            if (criteria.getIntensive() != null) {
                specification = specification.and(buildSpecification(criteria.getIntensive(), ApiaryInformation_.intensive));
            }
            if (criteria.getTranshumance() != null) {
                specification = specification.and(buildSpecification(criteria.getTranshumance(), ApiaryInformation_.transhumance));
            }
            if (criteria.getCoordX() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCoordX(), ApiaryInformation_.coordX));
            }
            if (criteria.getCoordY() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCoordY(), ApiaryInformation_.coordY));
            }
            if (criteria.getCoordZ() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCoordZ(), ApiaryInformation_.coordZ));
            }
            if (criteria.getNumberFrames() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumberFrames(), ApiaryInformation_.numberFrames));
            }
            if (criteria.getAnnualInventoryDeclarationId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getAnnualInventoryDeclarationId(),
                            root ->
                                root.join(ApiaryInformation_.annualInventoryDeclaration, JoinType.LEFT).get(AnnualInventoryDeclaration_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
