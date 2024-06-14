package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.Frame;
import com.mycompany.myapp.domain.Hive;
import com.mycompany.myapp.repository.HiveRepository;
import com.mycompany.myapp.service.HiveService;
import com.mycompany.myapp.service.dto.FrameDTO;
import com.mycompany.myapp.service.dto.HiveDTO;
import com.mycompany.myapp.service.mapper.FrameMapper;
import com.mycompany.myapp.service.mapper.HiveMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
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
    private final FrameMapper frameMapper;

    public HiveServiceImpl(HiveRepository hiveRepository, HiveMapper hiveMapper, FrameMapper frameMapper) {
        this.hiveRepository = hiveRepository;
        this.hiveMapper = hiveMapper;
        this.frameMapper = frameMapper;
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

    public Page<HiveDTO> findAllWithEagerRelationships(Pageable pageable) {
        return hiveRepository.findAllWithEagerRelationships(pageable).map(hiveMapper::toDto);
    }

    /**
     *  Get all the hives where UnfoldingCreatedHive is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<HiveDTO> findAllWhereUnfoldingCreatedHiveIsNull() {
        log.debug("Request to get all hives where UnfoldingCreatedHive is null");
        return StreamSupport
            .stream(hiveRepository.findAll().spliterator(), false)
            .filter(hive -> hive.getUnfoldingCreatedHive() == null)
            .map(hiveMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<HiveDTO> findOne(Long id) {
        log.debug("Request to get Hive : {}", id);
        return hiveRepository.findOneWithEagerRelationships(id).map(hiveMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Hive : {}", id);
        hiveRepository.deleteById(id);
    }

    /**
     * Delete frames form hive.
     *
     * @param hiveDTO the hive.
     * @param framesDTO the frames
     */
    @Override
    public void removeFramesFromHive(HiveDTO hiveDTO, Set<FrameDTO> framesDTO) {
        Optional<Hive> hiveOption = hiveRepository.findOneWithEagerRelationships(hiveDTO.getId());
        if(hiveOption.isPresent()){
            Hive hive = hiveOption.get();
            Set<Frame> frames = framesDTO.stream().map(frameMapper::toEntity).collect(Collectors.toSet());
            frames.forEach( frame -> {
                hive.removeFrame(frame);
            });
            hiveRepository.save(hive);
        }
    }

    @Override
    public void addFramesFromHive(HiveDTO hiveDTO, Set<FrameDTO> framesDTO) {
        Optional<Hive> hiveOption = hiveRepository.findOneWithEagerRelationships(hiveDTO.getId());
        if(hiveOption.isPresent()){
            Hive hive = hiveOption.get();
            Set<Frame> frames = framesDTO.stream().map(frameMapper::toEntity).collect(Collectors.toSet());
            frames.forEach( frame -> {
                hive.addFrame(frame);
            });
            hiveRepository.save(hive);
        }
    }
}
