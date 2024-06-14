package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.CrestDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.Crest}.
 */
public interface CrestService {
    /**
     * Save a crest.
     *
     * @param crestDTO the entity to save.
     * @return the persisted entity.
     */
    CrestDTO save(CrestDTO crestDTO);

    /**
     * Updates a crest.
     *
     * @param crestDTO the entity to update.
     * @return the persisted entity.
     */
    CrestDTO update(CrestDTO crestDTO);

    /**
     * Partially updates a crest.
     *
     * @param crestDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<CrestDTO> partialUpdate(CrestDTO crestDTO);

    /**
     * Get all the crests.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CrestDTO> findAll(Pageable pageable);

    /**
     * Get all the crests with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CrestDTO> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" crest.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CrestDTO> findOne(Long id);

    /**
     * Delete the "id" crest.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
