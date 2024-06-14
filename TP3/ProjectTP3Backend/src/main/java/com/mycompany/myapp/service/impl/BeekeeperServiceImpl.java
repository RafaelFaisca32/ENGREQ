package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.Beekeeper;
import com.mycompany.myapp.repository.BeekeeperRepository;
import com.mycompany.myapp.service.BeekeeperService;
import com.mycompany.myapp.service.dto.BeekeeperDTO;
import com.mycompany.myapp.service.mapper.BeekeeperMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Beekeeper}.
 */
@Service
@Transactional
public class BeekeeperServiceImpl implements BeekeeperService {

    private final Logger log = LoggerFactory.getLogger(BeekeeperServiceImpl.class);

    private final BeekeeperRepository beekeeperRepository;

    private final BeekeeperMapper beekeeperMapper;

    public BeekeeperServiceImpl(BeekeeperRepository beekeeperRepository, BeekeeperMapper beekeeperMapper) {
        this.beekeeperRepository = beekeeperRepository;
        this.beekeeperMapper = beekeeperMapper;
    }

    @Override
    public BeekeeperDTO save(BeekeeperDTO beekeeperDTO) {
        log.debug("Request to save Beekeeper : {}", beekeeperDTO);
        Beekeeper beekeeper = beekeeperMapper.toEntity(beekeeperDTO);
        beekeeper = beekeeperRepository.save(beekeeper);
        return beekeeperMapper.toDto(beekeeper);
    }

    @Override
    public BeekeeperDTO update(BeekeeperDTO beekeeperDTO) {
        log.debug("Request to update Beekeeper : {}", beekeeperDTO);
        Beekeeper beekeeper = beekeeperMapper.toEntity(beekeeperDTO);
        beekeeper = beekeeperRepository.save(beekeeper);
        return beekeeperMapper.toDto(beekeeper);
    }

    @Override
    public Optional<BeekeeperDTO> partialUpdate(BeekeeperDTO beekeeperDTO) {
        log.debug("Request to partially update Beekeeper : {}", beekeeperDTO);

        return beekeeperRepository
            .findById(beekeeperDTO.getId())
            .map(existingBeekeeper -> {
                beekeeperMapper.partialUpdate(existingBeekeeper, beekeeperDTO);

                return existingBeekeeper;
            })
            .map(beekeeperRepository::save)
            .map(beekeeperMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BeekeeperDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Beekeepers");
        return beekeeperRepository.findAll(pageable).map(beekeeperMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BeekeeperDTO> findOne(Long id) {
        log.debug("Request to get Beekeeper : {}", id);
        return beekeeperRepository.findById(id).map(beekeeperMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Beekeeper : {}", id);
        beekeeperRepository.deleteById(id);
    }
}
