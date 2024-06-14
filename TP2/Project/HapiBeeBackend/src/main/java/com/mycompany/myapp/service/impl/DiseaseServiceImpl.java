package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.Disease;
import com.mycompany.myapp.repository.DiseaseRepository;
import com.mycompany.myapp.service.DiseaseService;
import com.mycompany.myapp.service.dto.DiseaseDTO;
import com.mycompany.myapp.service.mapper.DiseaseMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Disease}.
 */
@Service
@Transactional
public class DiseaseServiceImpl implements DiseaseService {

    private final Logger log = LoggerFactory.getLogger(DiseaseServiceImpl.class);

    private final DiseaseRepository diseaseRepository;

    private final DiseaseMapper diseaseMapper;

    public DiseaseServiceImpl(DiseaseRepository diseaseRepository, DiseaseMapper diseaseMapper) {
        this.diseaseRepository = diseaseRepository;
        this.diseaseMapper = diseaseMapper;
    }

    @Override
    public DiseaseDTO save(DiseaseDTO diseaseDTO) {
        log.debug("Request to save Disease : {}", diseaseDTO);
        Disease disease = diseaseMapper.toEntity(diseaseDTO);
        disease = diseaseRepository.save(disease);
        return diseaseMapper.toDto(disease);
    }

    @Override
    public DiseaseDTO update(DiseaseDTO diseaseDTO) {
        log.debug("Request to update Disease : {}", diseaseDTO);
        Disease disease = diseaseMapper.toEntity(diseaseDTO);
        disease = diseaseRepository.save(disease);
        return diseaseMapper.toDto(disease);
    }

    @Override
    public Optional<DiseaseDTO> partialUpdate(DiseaseDTO diseaseDTO) {
        log.debug("Request to partially update Disease : {}", diseaseDTO);

        return diseaseRepository
            .findById(diseaseDTO.getId())
            .map(existingDisease -> {
                diseaseMapper.partialUpdate(existingDisease, diseaseDTO);

                return existingDisease;
            })
            .map(diseaseRepository::save)
            .map(diseaseMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DiseaseDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Diseases");
        return diseaseRepository.findAll(pageable).map(diseaseMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DiseaseDTO> findOne(Long id) {
        log.debug("Request to get Disease : {}", id);
        return diseaseRepository.findById(id).map(diseaseMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Disease : {}", id);
        diseaseRepository.deleteById(id);
    }
}
