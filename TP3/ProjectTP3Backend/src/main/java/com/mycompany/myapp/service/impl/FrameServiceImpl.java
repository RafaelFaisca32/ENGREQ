package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.Frame;
import com.mycompany.myapp.repository.FrameRepository;
import com.mycompany.myapp.service.FrameService;
import com.mycompany.myapp.service.dto.FrameDTO;
import com.mycompany.myapp.service.mapper.FrameMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Frame}.
 */
@Service
@Transactional
public class FrameServiceImpl implements FrameService {

    private final Logger log = LoggerFactory.getLogger(FrameServiceImpl.class);

    private final FrameRepository frameRepository;

    private final FrameMapper frameMapper;

    public FrameServiceImpl(FrameRepository frameRepository, FrameMapper frameMapper) {
        this.frameRepository = frameRepository;
        this.frameMapper = frameMapper;
    }

    @Override
    public FrameDTO save(FrameDTO frameDTO) {
        log.debug("Request to save Frame : {}", frameDTO);
        Frame frame = frameMapper.toEntity(frameDTO);
        frame = frameRepository.save(frame);
        return frameMapper.toDto(frame);
    }

    @Override
    public FrameDTO update(FrameDTO frameDTO) {
        log.debug("Request to update Frame : {}", frameDTO);
        Frame frame = frameMapper.toEntity(frameDTO);
        frame = frameRepository.save(frame);
        return frameMapper.toDto(frame);
    }

    @Override
    public Optional<FrameDTO> partialUpdate(FrameDTO frameDTO) {
        log.debug("Request to partially update Frame : {}", frameDTO);

        return frameRepository
            .findById(frameDTO.getId())
            .map(existingFrame -> {
                frameMapper.partialUpdate(existingFrame, frameDTO);

                return existingFrame;
            })
            .map(frameRepository::save)
            .map(frameMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<FrameDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Frames");
        return frameRepository.findAll(pageable).map(frameMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<FrameDTO> findOne(Long id) {
        log.debug("Request to get Frame : {}", id);
        return frameRepository.findById(id).map(frameMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Frame : {}", id);
        frameRepository.deleteById(id);
    }
}
