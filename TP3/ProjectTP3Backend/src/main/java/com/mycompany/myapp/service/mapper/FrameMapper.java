package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Frame;
import com.mycompany.myapp.service.dto.FrameDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Frame} and its DTO {@link FrameDTO}.
 */
@Mapper(componentModel = "spring")
public interface FrameMapper extends EntityMapper<FrameDTO, Frame> {}
