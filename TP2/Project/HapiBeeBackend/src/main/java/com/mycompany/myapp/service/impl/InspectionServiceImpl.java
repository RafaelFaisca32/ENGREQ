package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.Inspection;
import com.mycompany.myapp.repository.InspectionRepository;
import com.mycompany.myapp.service.InspectionService;
import com.mycompany.myapp.service.dto.InspectionDTO;
import com.mycompany.myapp.service.mapper.InspectionMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Inspection}.
 */
@Service
@Transactional
public class InspectionServiceImpl implements InspectionService {

    private final Logger log = LoggerFactory.getLogger(InspectionServiceImpl.class);

    private final InspectionRepository inspectionRepository;

    private final InspectionMapper inspectionMapper;

    public InspectionServiceImpl(InspectionRepository inspectionRepository, InspectionMapper inspectionMapper) {
        this.inspectionRepository = inspectionRepository;
        this.inspectionMapper = inspectionMapper;
    }

    @Override
    public InspectionDTO save(InspectionDTO inspectionDTO) {
        log.debug("Request to save Inspection : {}", inspectionDTO);
        Inspection inspection = inspectionMapper.toEntity(inspectionDTO);
        inspection = inspectionRepository.save(inspection);
        return inspectionMapper.toDto(inspection);
    }

    @Override
    public InspectionDTO update(InspectionDTO inspectionDTO) {
        log.debug("Request to update Inspection : {}", inspectionDTO);
        Inspection inspection = inspectionMapper.toEntity(inspectionDTO);
        inspection = inspectionRepository.save(inspection);
        return inspectionMapper.toDto(inspection);
    }

    @Override
    public Optional<InspectionDTO> partialUpdate(InspectionDTO inspectionDTO) {
        log.debug("Request to partially update Inspection : {}", inspectionDTO);

        return inspectionRepository
            .findById(inspectionDTO.getId())
            .map(existingInspection -> {
                inspectionMapper.partialUpdate(existingInspection, inspectionDTO);

                return existingInspection;
            })
            .map(inspectionRepository::save)
            .map(inspectionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<InspectionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Inspections");
        return inspectionRepository.findAll(pageable).map(inspectionMapper::toDto);
    }

    public Page<InspectionDTO> findAllWithEagerRelationships(Pageable pageable) {
        return inspectionRepository.findAllWithEagerRelationships(pageable).map(inspectionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<InspectionDTO> findOne(Long id) {
        log.debug("Request to get Inspection : {}", id);
        return inspectionRepository.findOneWithEagerRelationships(id).map(inspectionMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Inspection : {}", id);
        inspectionRepository.deleteById(id);
    }
}
