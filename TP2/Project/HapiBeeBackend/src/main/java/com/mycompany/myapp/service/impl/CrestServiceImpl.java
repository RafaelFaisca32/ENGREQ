package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.Crest;
import com.mycompany.myapp.repository.CrestRepository;
import com.mycompany.myapp.service.CrestService;
import com.mycompany.myapp.service.dto.CrestDTO;
import com.mycompany.myapp.service.mapper.CrestMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Crest}.
 */
@Service
@Transactional
public class CrestServiceImpl implements CrestService {

    private final Logger log = LoggerFactory.getLogger(CrestServiceImpl.class);

    private final CrestRepository crestRepository;

    private final CrestMapper crestMapper;

    public CrestServiceImpl(CrestRepository crestRepository, CrestMapper crestMapper) {
        this.crestRepository = crestRepository;
        this.crestMapper = crestMapper;
    }

    @Override
    public CrestDTO save(CrestDTO crestDTO) {
        log.debug("Request to save Crest : {}", crestDTO);
        Crest crest = crestMapper.toEntity(crestDTO);
        crest = crestRepository.save(crest);
        return crestMapper.toDto(crest);
    }

    @Override
    public CrestDTO update(CrestDTO crestDTO) {
        log.debug("Request to update Crest : {}", crestDTO);
        Crest crest = crestMapper.toEntity(crestDTO);
        crest = crestRepository.save(crest);
        return crestMapper.toDto(crest);
    }

    @Override
    public Optional<CrestDTO> partialUpdate(CrestDTO crestDTO) {
        log.debug("Request to partially update Crest : {}", crestDTO);

        return crestRepository
            .findById(crestDTO.getId())
            .map(existingCrest -> {
                crestMapper.partialUpdate(existingCrest, crestDTO);

                return existingCrest;
            })
            .map(crestRepository::save)
            .map(crestMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CrestDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Crests");
        return crestRepository.findAll(pageable).map(crestMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CrestDTO> findOne(Long id) {
        log.debug("Request to get Crest : {}", id);
        return crestRepository.findById(id).map(crestMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Crest : {}", id);
        crestRepository.deleteById(id);
    }
}
