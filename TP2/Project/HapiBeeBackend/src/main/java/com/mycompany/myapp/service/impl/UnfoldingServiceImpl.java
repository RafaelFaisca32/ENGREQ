package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.Unfolding;
import com.mycompany.myapp.repository.UnfoldingRepository;
import com.mycompany.myapp.service.UnfoldingService;
import com.mycompany.myapp.service.dto.UnfoldingDTO;
import com.mycompany.myapp.service.mapper.UnfoldingMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Unfolding}.
 */
@Service
@Transactional
public class UnfoldingServiceImpl implements UnfoldingService {

    private final Logger log = LoggerFactory.getLogger(UnfoldingServiceImpl.class);

    private final UnfoldingRepository unfoldingRepository;

    private final UnfoldingMapper unfoldingMapper;

    public UnfoldingServiceImpl(UnfoldingRepository unfoldingRepository, UnfoldingMapper unfoldingMapper) {
        this.unfoldingRepository = unfoldingRepository;
        this.unfoldingMapper = unfoldingMapper;
    }

    @Override
    public UnfoldingDTO save(UnfoldingDTO unfoldingDTO) {
        log.debug("Request to save Unfolding : {}", unfoldingDTO);
        Unfolding unfolding = unfoldingMapper.toEntity(unfoldingDTO);
        unfolding = unfoldingRepository.save(unfolding);
        return unfoldingMapper.toDto(unfolding);
    }

    @Override
    public UnfoldingDTO update(UnfoldingDTO unfoldingDTO) {
        log.debug("Request to update Unfolding : {}", unfoldingDTO);
        Unfolding unfolding = unfoldingMapper.toEntity(unfoldingDTO);
        unfolding = unfoldingRepository.save(unfolding);
        return unfoldingMapper.toDto(unfolding);
    }

    @Override
    public Optional<UnfoldingDTO> partialUpdate(UnfoldingDTO unfoldingDTO) {
        log.debug("Request to partially update Unfolding : {}", unfoldingDTO);

        return unfoldingRepository
            .findById(unfoldingDTO.getId())
            .map(existingUnfolding -> {
                unfoldingMapper.partialUpdate(existingUnfolding, unfoldingDTO);

                return existingUnfolding;
            })
            .map(unfoldingRepository::save)
            .map(unfoldingMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UnfoldingDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Unfoldings");
        return unfoldingRepository.findAll(pageable).map(unfoldingMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UnfoldingDTO> findOne(Long id) {
        log.debug("Request to get Unfolding : {}", id);
        return unfoldingRepository.findById(id).map(unfoldingMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Unfolding : {}", id);
        unfoldingRepository.deleteById(id);
    }
}
