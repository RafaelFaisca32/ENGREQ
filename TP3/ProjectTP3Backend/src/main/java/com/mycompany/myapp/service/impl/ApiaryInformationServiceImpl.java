package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.ApiaryInformation;
import com.mycompany.myapp.repository.ApiaryInformationRepository;
import com.mycompany.myapp.service.ApiaryInformationService;
import com.mycompany.myapp.service.dto.ApiaryInformationDTO;
import com.mycompany.myapp.service.mapper.ApiaryInformationMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ApiaryInformation}.
 */
@Service
@Transactional
public class ApiaryInformationServiceImpl implements ApiaryInformationService {

    private final Logger log = LoggerFactory.getLogger(ApiaryInformationServiceImpl.class);

    private final ApiaryInformationRepository apiaryInformationRepository;

    private final ApiaryInformationMapper apiaryInformationMapper;

    public ApiaryInformationServiceImpl(
        ApiaryInformationRepository apiaryInformationRepository,
        ApiaryInformationMapper apiaryInformationMapper
    ) {
        this.apiaryInformationRepository = apiaryInformationRepository;
        this.apiaryInformationMapper = apiaryInformationMapper;
    }

    @Override
    public ApiaryInformationDTO save(ApiaryInformationDTO apiaryInformationDTO) {
        log.debug("Request to save ApiaryInformation : {}", apiaryInformationDTO);
        ApiaryInformation apiaryInformation = apiaryInformationMapper.toEntity(apiaryInformationDTO);
        apiaryInformation = apiaryInformationRepository.save(apiaryInformation);
        return apiaryInformationMapper.toDto(apiaryInformation);
    }

    @Override
    public ApiaryInformationDTO update(ApiaryInformationDTO apiaryInformationDTO) {
        log.debug("Request to update ApiaryInformation : {}", apiaryInformationDTO);
        ApiaryInformation apiaryInformation = apiaryInformationMapper.toEntity(apiaryInformationDTO);
        apiaryInformation = apiaryInformationRepository.save(apiaryInformation);
        return apiaryInformationMapper.toDto(apiaryInformation);
    }

    @Override
    public Optional<ApiaryInformationDTO> partialUpdate(ApiaryInformationDTO apiaryInformationDTO) {
        log.debug("Request to partially update ApiaryInformation : {}", apiaryInformationDTO);

        return apiaryInformationRepository
            .findById(apiaryInformationDTO.getId())
            .map(existingApiaryInformation -> {
                apiaryInformationMapper.partialUpdate(existingApiaryInformation, apiaryInformationDTO);

                return existingApiaryInformation;
            })
            .map(apiaryInformationRepository::save)
            .map(apiaryInformationMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ApiaryInformationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ApiaryInformations");
        return apiaryInformationRepository.findAll(pageable).map(apiaryInformationMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ApiaryInformationDTO> findOne(Long id) {
        log.debug("Request to get ApiaryInformation : {}", id);
        return apiaryInformationRepository.findById(id).map(apiaryInformationMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ApiaryInformation : {}", id);
        apiaryInformationRepository.deleteById(id);
    }
}
