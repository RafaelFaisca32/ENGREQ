package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Crest;
import com.mycompany.myapp.domain.Frame;
import com.mycompany.myapp.domain.Hive;
import com.mycompany.myapp.service.dto.CrestDTO;
import com.mycompany.myapp.service.dto.FrameDTO;
import com.mycompany.myapp.service.dto.HiveDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Crest} and its DTO {@link CrestDTO}.
 */
@Mapper(componentModel = "spring")
public interface CrestMapper extends EntityMapper<CrestDTO, Crest> {
    @Mapping(target = "hive", source = "hive", qualifiedByName = "hiveId")
    @Mapping(target = "frames", source = "frames", qualifiedByName = "frameIdSet")
    CrestDTO toDto(Crest s);

    @Mapping(target = "removeFrame", ignore = true)
    Crest toEntity(CrestDTO crestDTO);

    @Named("hiveId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    HiveDTO toDtoHiveId(Hive hive);

    @Named("frameId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    FrameDTO toDtoFrameId(Frame frame);

    @Named("frameIdSet")
    default Set<FrameDTO> toDtoFrameIdSet(Set<Frame> frame) {
        return frame.stream().map(this::toDtoFrameId).collect(Collectors.toSet());
    }
}
