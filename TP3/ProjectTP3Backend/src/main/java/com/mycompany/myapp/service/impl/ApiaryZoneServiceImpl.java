package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.ApiaryZone;
import com.mycompany.myapp.repository.ApiaryZoneRepository;
import com.mycompany.myapp.service.ApiaryZoneService;
import com.mycompany.myapp.service.dto.ApiaryZoneDTO;
import com.mycompany.myapp.service.mapper.ApiaryZoneMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ApiaryZone}.
 */
@Service
@Transactional
public class ApiaryZoneServiceImpl implements ApiaryZoneService {

    private final Logger log = LoggerFactory.getLogger(ApiaryZoneServiceImpl.class);

    private final ApiaryZoneRepository apiaryZoneRepository;

    private final ApiaryZoneMapper apiaryZoneMapper;

    public ApiaryZoneServiceImpl(ApiaryZoneRepository apiaryZoneRepository, ApiaryZoneMapper apiaryZoneMapper) {
        this.apiaryZoneRepository = apiaryZoneRepository;
        this.apiaryZoneMapper = apiaryZoneMapper;
    }

    @Override
    public ApiaryZoneDTO save(ApiaryZoneDTO apiaryZoneDTO) {
        log.debug("Request to save ApiaryZone : {}", apiaryZoneDTO);
        ApiaryZone apiaryZone = apiaryZoneMapper.toEntity(apiaryZoneDTO);
        apiaryZone = apiaryZoneRepository.save(apiaryZone);
        return apiaryZoneMapper.toDto(apiaryZone);
    }

    @Override
    public ApiaryZoneDTO update(ApiaryZoneDTO apiaryZoneDTO) {
        log.debug("Request to update ApiaryZone : {}", apiaryZoneDTO);
        ApiaryZone apiaryZone = apiaryZoneMapper.toEntity(apiaryZoneDTO);
        apiaryZone = apiaryZoneRepository.save(apiaryZone);
        return apiaryZoneMapper.toDto(apiaryZone);
    }

    @Override
    public Optional<ApiaryZoneDTO> partialUpdate(ApiaryZoneDTO apiaryZoneDTO) {
        log.debug("Request to partially update ApiaryZone : {}", apiaryZoneDTO);

        return apiaryZoneRepository
            .findById(apiaryZoneDTO.getId())
            .map(existingApiaryZone -> {
                apiaryZoneMapper.partialUpdate(existingApiaryZone, apiaryZoneDTO);

                return existingApiaryZone;
            })
            .map(apiaryZoneRepository::save)
            .map(apiaryZoneMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ApiaryZoneDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ApiaryZones");
        return apiaryZoneRepository.findAll(pageable).map(apiaryZoneMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ApiaryZoneDTO> findOne(Long id) {
        log.debug("Request to get ApiaryZone : {}", id);
        return apiaryZoneRepository.findById(id).map(apiaryZoneMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ApiaryZone : {}", id);
        apiaryZoneRepository.deleteById(id);
    }
}
