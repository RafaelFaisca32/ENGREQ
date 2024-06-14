package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Frame;
import com.mycompany.myapp.domain.Hive;
import com.mycompany.myapp.service.dto.FrameDTO;
import com.mycompany.myapp.service.dto.HiveDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Frame} and its DTO {@link FrameDTO}.
 */
@Mapper(componentModel = "spring")
public interface FrameMapper extends EntityMapper<FrameDTO, Frame> {
    @Mapping(target = "hive", source = "hive", qualifiedByName = "hiveId")
    FrameDTO toDto(Frame s);

    @Named("hiveId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    HiveDTO toDtoHiveId(Hive hive);
}
