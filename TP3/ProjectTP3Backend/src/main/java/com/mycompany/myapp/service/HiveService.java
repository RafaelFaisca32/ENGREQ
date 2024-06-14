package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.FrameDTO;
import com.mycompany.myapp.service.dto.HiveDTO;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.Hive}.
 */
public interface HiveService {
    /**
     * Save a hive.
     *
     * @param hiveDTO the entity to save.
     * @return the persisted entity.
     */
    HiveDTO save(HiveDTO hiveDTO);

    /**
     * Updates a hive.
     *
     * @param hiveDTO the entity to update.
     * @return the persisted entity.
     */
    HiveDTO update(HiveDTO hiveDTO);

    /**
     * Partially updates a hive.
     *
     * @param hiveDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<HiveDTO> partialUpdate(HiveDTO hiveDTO);

    /**
     * Get all the hives.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<HiveDTO> findAll(Pageable pageable);
    /**
     * Get all the HiveDTO where UnfoldingCreatedHive is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<HiveDTO> findAllWhereUnfoldingCreatedHiveIsNull();

    /**
     * Get all the hives with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<HiveDTO> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" hive.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<HiveDTO> findOne(Long id);

    /**
     * Delete the "id" hive.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Delete frames form hive.
     *
     * @param hiveDTO the hive.
     * @param frames the frames
     */
    void removeFramesFromHive(HiveDTO hiveDTO, Set<FrameDTO> frames);
    void addFramesFromHive(HiveDTO hiveDTO, Set<FrameDTO> frames);
}
