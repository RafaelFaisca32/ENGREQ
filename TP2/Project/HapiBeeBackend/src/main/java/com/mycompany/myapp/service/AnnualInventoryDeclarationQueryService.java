package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.domain.AnnualInventoryDeclaration;
import com.mycompany.myapp.repository.AnnualInventoryDeclarationRepository;
import com.mycompany.myapp.service.criteria.AnnualInventoryDeclarationCriteria;
import com.mycompany.myapp.service.dto.AnnualInventoryDeclarationDTO;
import com.mycompany.myapp.service.mapper.AnnualInventoryDeclarationMapper;
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
 * Service for executing complex queries for {@link AnnualInventoryDeclaration} entities in the database.
 * The main input is a {@link AnnualInventoryDeclarationCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link AnnualInventoryDeclarationDTO} or a {@link Page} of {@link AnnualInventoryDeclarationDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class AnnualInventoryDeclarationQueryService extends QueryService<AnnualInventoryDeclaration> {

    private final Logger log = LoggerFactory.getLogger(AnnualInventoryDeclarationQueryService.class);

    private final AnnualInventoryDeclarationRepository annualInventoryDeclarationRepository;

    private final AnnualInventoryDeclarationMapper annualInventoryDeclarationMapper;

    public AnnualInventoryDeclarationQueryService(
        AnnualInventoryDeclarationRepository annualInventoryDeclarationRepository,
        AnnualInventoryDeclarationMapper annualInventoryDeclarationMapper
    ) {
        this.annualInventoryDeclarationRepository = annualInventoryDeclarationRepository;
        this.annualInventoryDeclarationMapper = annualInventoryDeclarationMapper;
    }

    /**
     * Return a {@link List} of {@link AnnualInventoryDeclarationDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<AnnualInventoryDeclarationDTO> findByCriteria(AnnualInventoryDeclarationCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<AnnualInventoryDeclaration> specification = createSpecification(criteria);
        return annualInventoryDeclarationMapper.toDto(annualInventoryDeclarationRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link AnnualInventoryDeclarationDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<AnnualInventoryDeclarationDTO> findByCriteria(AnnualInventoryDeclarationCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<AnnualInventoryDeclaration> specification = createSpecification(criteria);
        return annualInventoryDeclarationRepository.findAll(specification, page).map(annualInventoryDeclarationMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(AnnualInventoryDeclarationCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<AnnualInventoryDeclaration> specification = createSpecification(criteria);
        return annualInventoryDeclarationRepository.count(specification);
    }

    /**
     * Function to convert {@link AnnualInventoryDeclarationCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<AnnualInventoryDeclaration> createSpecification(AnnualInventoryDeclarationCriteria criteria) {
        Specification<AnnualInventoryDeclaration> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), AnnualInventoryDeclaration_.id));
            }
            if (criteria.getTotal() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTotal(), AnnualInventoryDeclaration_.total));
            }
            if (criteria.getBeekeeperFaxNumber() != null) {
                specification =
                    specification.and(
                        buildRangeSpecification(criteria.getBeekeeperFaxNumber(), AnnualInventoryDeclaration_.beekeeperFaxNumber)
                    );
            }
            if (criteria.getBeekeeperCompleteName() != null) {
                specification =
                    specification.and(
                        buildStringSpecification(criteria.getBeekeeperCompleteName(), AnnualInventoryDeclaration_.beekeeperCompleteName)
                    );
            }
            if (criteria.getBeekeeperNif() != null) {
                specification =
                    specification.and(buildRangeSpecification(criteria.getBeekeeperNif(), AnnualInventoryDeclaration_.beekeeperNif));
            }
            if (criteria.getDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDate(), AnnualInventoryDeclaration_.date));
            }
            if (criteria.getBeekeeperAddress() != null) {
                specification =
                    specification.and(
                        buildStringSpecification(criteria.getBeekeeperAddress(), AnnualInventoryDeclaration_.beekeeperAddress)
                    );
            }
            if (criteria.getBeekeeperPostalCode() != null) {
                specification =
                    specification.and(
                        buildStringSpecification(criteria.getBeekeeperPostalCode(), AnnualInventoryDeclaration_.beekeeperPostalCode)
                    );
            }
            if (criteria.getBeekeeperPhoneNumber() != null) {
                specification =
                    specification.and(
                        buildRangeSpecification(criteria.getBeekeeperPhoneNumber(), AnnualInventoryDeclaration_.beekeeperPhoneNumber)
                    );
            }
            if (criteria.getBeekeeperEntityZoneResidence() != null) {
                specification =
                    specification.and(
                        buildStringSpecification(
                            criteria.getBeekeeperEntityZoneResidence(),
                            AnnualInventoryDeclaration_.beekeeperEntityZoneResidence
                        )
                    );
            }
            if (criteria.getBeekeeperNumber() != null) {
                specification =
                    specification.and(buildRangeSpecification(criteria.getBeekeeperNumber(), AnnualInventoryDeclaration_.beekeeperNumber));
            }
            if (criteria.getAnnualInventoryDeclarationState() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getAnnualInventoryDeclarationState(),
                            AnnualInventoryDeclaration_.annualInventoryDeclarationState
                        )
                    );
            }
            if (criteria.getRevisionDate() != null) {
                specification =
                    specification.and(buildRangeSpecification(criteria.getRevisionDate(), AnnualInventoryDeclaration_.revisionDate));
            }
            if (criteria.getRevisionLocation() != null) {
                specification =
                    specification.and(
                        buildStringSpecification(criteria.getRevisionLocation(), AnnualInventoryDeclaration_.revisionLocation)
                    );
            }
            if (criteria.getRevisorSignature() != null) {
                specification =
                    specification.and(
                        buildStringSpecification(criteria.getRevisorSignature(), AnnualInventoryDeclaration_.revisorSignature)
                    );
            }
            if (criteria.getRevisorName() != null) {
                specification =
                    specification.and(buildStringSpecification(criteria.getRevisorName(), AnnualInventoryDeclaration_.revisorName));
            }
            if (criteria.getApiaryInformationId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getApiaryInformationId(),
                            root -> root.join(AnnualInventoryDeclaration_.apiaryInformations, JoinType.LEFT).get(ApiaryInformation_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
