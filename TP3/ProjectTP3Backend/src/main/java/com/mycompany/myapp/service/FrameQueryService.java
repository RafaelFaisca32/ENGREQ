package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.domain.Frame;
import com.mycompany.myapp.repository.FrameRepository;
import com.mycompany.myapp.service.criteria.FrameCriteria;
import com.mycompany.myapp.service.dto.FrameDTO;
import com.mycompany.myapp.service.mapper.FrameMapper;
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
 * Service for executing complex queries for {@link Frame} entities in the database.
 * The main input is a {@link FrameCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link FrameDTO} or a {@link Page} of {@link FrameDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class FrameQueryService extends QueryService<Frame> {

    private final Logger log = LoggerFactory.getLogger(FrameQueryService.class);

    private final FrameRepository frameRepository;

    private final FrameMapper frameMapper;

    public FrameQueryService(FrameRepository frameRepository, FrameMapper frameMapper) {
        this.frameRepository = frameRepository;
        this.frameMapper = frameMapper;
    }

    /**
     * Return a {@link List} of {@link FrameDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<FrameDTO> findByCriteria(FrameCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Frame> specification = createSpecification(criteria);
        return frameMapper.toDto(frameRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link FrameDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<FrameDTO> findByCriteria(FrameCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Frame> specification = createSpecification(criteria);
        return frameRepository.findAll(specification, page).map(frameMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(FrameCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Frame> specification = createSpecification(criteria);
        return frameRepository.count(specification);
    }

    /**
     * Function to convert {@link FrameCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Frame> createSpecification(FrameCriteria criteria) {
        Specification<Frame> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Frame_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), Frame_.code));
            }
            if (criteria.getHiveId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getHiveId(), root -> root.join(Frame_.hives, JoinType.LEFT).get(Hive_.id))
                    );
            }
            if (criteria.getCrestId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getCrestId(), root -> root.join(Frame_.crests, JoinType.LEFT).get(Crest_.id))
                    );
            }
            if (criteria.getUnfoldingRemovedFramesOldHiveId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getUnfoldingRemovedFramesOldHiveId(),
                            root -> root.join(Frame_.unfoldingRemovedFramesOldHives, JoinType.LEFT).get(Unfolding_.id)
                        )
                    );
            }
            if (criteria.getUnfoldingInsertedFramesOldHiveId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getUnfoldingInsertedFramesOldHiveId(),
                            root -> root.join(Frame_.unfoldingInsertedFramesOldHives, JoinType.LEFT).get(Unfolding_.id)
                        )
                    );
            }
            if (criteria.getUnfoldingInsertedFramesNewHiveId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getUnfoldingInsertedFramesNewHiveId(),
                            root -> root.join(Frame_.unfoldingInsertedFramesNewHives, JoinType.LEFT).get(Unfolding_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
