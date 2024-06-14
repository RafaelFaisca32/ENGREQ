package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.FrameDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.Frame}.
 */
public interface FrameService {
    /**
     * Save a frame.
     *
     * @param frameDTO the entity to save.
     * @return the persisted entity.
     */
    FrameDTO save(FrameDTO frameDTO);

    /**
     * Updates a frame.
     *
     * @param frameDTO the entity to update.
     * @return the persisted entity.
     */
    FrameDTO update(FrameDTO frameDTO);

    /**
     * Partially updates a frame.
     *
     * @param frameDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<FrameDTO> partialUpdate(FrameDTO frameDTO);

    /**
     * Get all the frames.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<FrameDTO> findAll(Pageable pageable);

    /**
     * Get the "id" frame.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FrameDTO> findOne(Long id);

    /**
     * Delete the "id" frame.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
