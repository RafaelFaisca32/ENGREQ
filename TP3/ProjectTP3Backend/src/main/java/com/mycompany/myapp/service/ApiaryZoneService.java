package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.ApiaryZoneDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.ApiaryZone}.
 */
public interface ApiaryZoneService {
    /**
     * Save a apiaryZone.
     *
     * @param apiaryZoneDTO the entity to save.
     * @return the persisted entity.
     */
    ApiaryZoneDTO save(ApiaryZoneDTO apiaryZoneDTO);

    /**
     * Updates a apiaryZone.
     *
     * @param apiaryZoneDTO the entity to update.
     * @return the persisted entity.
     */
    ApiaryZoneDTO update(ApiaryZoneDTO apiaryZoneDTO);

    /**
     * Partially updates a apiaryZone.
     *
     * @param apiaryZoneDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ApiaryZoneDTO> partialUpdate(ApiaryZoneDTO apiaryZoneDTO);

    /**
     * Get all the apiaryZones.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ApiaryZoneDTO> findAll(Pageable pageable);

    /**
     * Get the "id" apiaryZone.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ApiaryZoneDTO> findOne(Long id);

    /**
     * Delete the "id" apiaryZone.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
