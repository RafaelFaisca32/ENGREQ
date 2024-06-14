package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.InspectionDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.Inspection}.
 */
public interface InspectionService {
    /**
     * Save a inspection.
     *
     * @param inspectionDTO the entity to save.
     * @return the persisted entity.
     */
    InspectionDTO save(InspectionDTO inspectionDTO);

    /**
     * Updates a inspection.
     *
     * @param inspectionDTO the entity to update.
     * @return the persisted entity.
     */
    InspectionDTO update(InspectionDTO inspectionDTO);

    /**
     * Partially updates a inspection.
     *
     * @param inspectionDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<InspectionDTO> partialUpdate(InspectionDTO inspectionDTO);

    /**
     * Get all the inspections.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<InspectionDTO> findAll(Pageable pageable);

    /**
     * Get all the inspections with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<InspectionDTO> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" inspection.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<InspectionDTO> findOne(Long id);

    /**
     * Delete the "id" inspection.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
