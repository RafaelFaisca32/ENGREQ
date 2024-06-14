package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.DiseaseDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.Disease}.
 */
public interface DiseaseService {
    /**
     * Save a disease.
     *
     * @param diseaseDTO the entity to save.
     * @return the persisted entity.
     */
    DiseaseDTO save(DiseaseDTO diseaseDTO);

    /**
     * Updates a disease.
     *
     * @param diseaseDTO the entity to update.
     * @return the persisted entity.
     */
    DiseaseDTO update(DiseaseDTO diseaseDTO);

    /**
     * Partially updates a disease.
     *
     * @param diseaseDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DiseaseDTO> partialUpdate(DiseaseDTO diseaseDTO);

    /**
     * Get all the diseases.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DiseaseDTO> findAll(Pageable pageable);

    /**
     * Get the "id" disease.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DiseaseDTO> findOne(Long id);

    /**
     * Delete the "id" disease.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
