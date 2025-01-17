package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.Zone;
import com.mycompany.myapp.repository.ZoneRepository;
import com.mycompany.myapp.service.ZoneService;
import com.mycompany.myapp.service.dto.ZoneDTO;
import com.mycompany.myapp.service.mapper.ZoneMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Zone}.
 */
@Service
@Transactional
public class ZoneServiceImpl implements ZoneService {

    private final Logger log = LoggerFactory.getLogger(ZoneServiceImpl.class);

    private final ZoneRepository zoneRepository;

    private final ZoneMapper zoneMapper;

    public ZoneServiceImpl(ZoneRepository zoneRepository, ZoneMapper zoneMapper) {
        this.zoneRepository = zoneRepository;
        this.zoneMapper = zoneMapper;
    }

    @Override
    public ZoneDTO save(ZoneDTO zoneDTO) {
        log.debug("Request to save Zone : {}", zoneDTO);
        Zone zone = zoneMapper.toEntity(zoneDTO);
        zone = zoneRepository.save(zone);
        return zoneMapper.toDto(zone);
    }

    @Override
    public ZoneDTO update(ZoneDTO zoneDTO) {
        log.debug("Request to update Zone : {}", zoneDTO);
        Zone zone = zoneMapper.toEntity(zoneDTO);
        zone = zoneRepository.save(zone);
        return zoneMapper.toDto(zone);
    }

    @Override
    public Optional<ZoneDTO> partialUpdate(ZoneDTO zoneDTO) {
        log.debug("Request to partially update Zone : {}", zoneDTO);

        return zoneRepository
            .findById(zoneDTO.getId())
            .map(existingZone -> {
                zoneMapper.partialUpdate(existingZone, zoneDTO);

                return existingZone;
            })
            .map(zoneRepository::save)
            .map(zoneMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ZoneDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Zones");
        return zoneRepository.findAll(pageable).map(zoneMapper::toDto);
    }

    /**
     *  Get all the zones where Apiary is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ZoneDTO> findAllWhereApiaryIsNull() {
        log.debug("Request to get all zones where Apiary is null");
        return StreamSupport
            .stream(zoneRepository.findAll().spliterator(), false)
            .filter(zone -> zone.getApiary() == null)
            .map(zoneMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ZoneDTO> findOne(Long id) {
        log.debug("Request to get Zone : {}", id);
        return zoneRepository.findById(id).map(zoneMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Zone : {}", id);
        zoneRepository.deleteById(id);
    }
}
