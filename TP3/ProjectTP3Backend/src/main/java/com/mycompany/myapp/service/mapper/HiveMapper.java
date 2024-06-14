package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Apiary;
import com.mycompany.myapp.domain.Frame;
import com.mycompany.myapp.domain.Hive;
import com.mycompany.myapp.service.dto.ApiaryDTO;
import com.mycompany.myapp.service.dto.FrameDTO;
import com.mycompany.myapp.service.dto.HiveDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Hive} and its DTO {@link HiveDTO}.
 */
@Mapper(componentModel = "spring")
public interface HiveMapper extends EntityMapper<HiveDTO, Hive> {
    @Mapping(target = "apiary", source = "apiary", qualifiedByName = "apiaryId")
    @Mapping(target = "frames", source = "frames", qualifiedByName = "frameIdSet")
    HiveDTO toDto(Hive s);

    @Mapping(target = "removeFrame", ignore = true)
    Hive toEntity(HiveDTO hiveDTO);

    @Named("apiaryId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ApiaryDTO toDtoApiaryId(Apiary apiary);

    @Named("frameId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    FrameDTO toDtoFrameId(Frame frame);

    @Named("frameIdSet")
    default Set<FrameDTO> toDtoFrameIdSet(Set<Frame> frame) {
        return frame.stream().map(this::toDtoFrameId).collect(Collectors.toSet());
    }
}
