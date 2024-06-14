package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Frame;
import com.mycompany.myapp.domain.Hive;
import com.mycompany.myapp.domain.Unfolding;
import com.mycompany.myapp.service.dto.FrameDTO;
import com.mycompany.myapp.service.dto.HiveDTO;
import com.mycompany.myapp.service.dto.UnfoldingDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Unfolding} and its DTO {@link UnfoldingDTO}.
 */
@Mapper(componentModel = "spring")
public interface UnfoldingMapper extends EntityMapper<UnfoldingDTO, Unfolding> {
    @Mapping(target = "createdHive", source = "createdHive", qualifiedByName = "hiveId")
    @Mapping(target = "hive", source = "hive", qualifiedByName = "hiveId")
    @Mapping(target = "removedFramesOldHives", source = "removedFramesOldHives", qualifiedByName = "frameIdSet")
    @Mapping(target = "insertedFramesOldHives", source = "insertedFramesOldHives", qualifiedByName = "frameIdSet")
    @Mapping(target = "insertedFramesNewHives", source = "insertedFramesNewHives", qualifiedByName = "frameIdSet")
    UnfoldingDTO toDto(Unfolding s);

    @Mapping(target = "removeRemovedFramesOldHive", ignore = true)
    @Mapping(target = "removeInsertedFramesOldHive", ignore = true)
    @Mapping(target = "removeInsertedFramesNewHive", ignore = true)
    Unfolding toEntity(UnfoldingDTO unfoldingDTO);

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
