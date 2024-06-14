package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.Hive;
import com.mycompany.myapp.repository.HiveRepository;
import com.mycompany.myapp.service.HiveService;
import com.mycompany.myapp.service.dto.HiveDTO;
import com.mycompany.myapp.service.mapper.HiveMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Hive}.
 */
@Service
@Transactional
public class HiveServiceImpl implements HiveService {

    private final Logger log = LoggerFactory.getLogger(HiveServiceImpl.class);

    private final HiveRepository hiveRepository;

    private final HiveMapper hiveMapper;

    public HiveServiceImpl(HiveRepository hiveRepository, HiveMapper hiveMapper) {
        this.hiveRepository = hiveRepository;
        this.hiveMapper = hiveMapper;
    }

    @Override
    public HiveDTO save(HiveDTO hiveDTO) {
        log.debug("Request to save Hive : {}", hiveDTO);
        Hive hive = hiveMapper.toEntity(hiveDTO);
        hive = hiveRepository.save(hive);
        return hiveMapper.toDto(hive);
    }

    @Override
    public HiveDTO update(HiveDTO hiveDTO) {
        log.debug("Request to update Hive : {}", hiveDTO);
        Hive hive = hiveMapper.toEntity(hiveDTO);
        hive = hiveRepository.save(hive);
        return hiveMapper.toDto(hive);
    }

    @Override
    public Optional<HiveDTO> partialUpdate(HiveDTO hiveDTO) {
        log.debug("Request to partially update Hive : {}", hiveDTO);

        return hiveRepository
            .findById(hiveDTO.getId())
            .map(existingHive -> {
                hiveMapper.partialUpdate(existingHive, hiveDTO);

                return existingHive;
            })
            .map(hiveRepository::save)
            .map(hiveMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<HiveDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Hives");
        return hiveRepository.findAll(pageable).map(hiveMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<HiveDTO> findOne(Long id) {
        log.debug("Request to get Hive : {}", id);
        return hiveRepository.findById(id).map(hiveMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Hive : {}", id);
        hiveRepository.deleteById(id);
    }
}
