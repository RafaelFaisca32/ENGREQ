package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Apiary;
import com.mycompany.myapp.domain.Hive;
import com.mycompany.myapp.domain.TranshumanceRequest;
import com.mycompany.myapp.service.dto.ApiaryDTO;
import com.mycompany.myapp.service.dto.HiveDTO;
import com.mycompany.myapp.service.dto.TranshumanceRequestDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TranshumanceRequest} and its DTO {@link TranshumanceRequestDTO}.
 */
@Mapper(componentModel = "spring")
public interface TranshumanceRequestMapper extends EntityMapper<TranshumanceRequestDTO, TranshumanceRequest> {
    @Mapping(target = "apiary", source = "apiary", qualifiedByName = "apiaryId")
    @Mapping(target = "hives", source = "hives", qualifiedByName = "hiveIdSet")
    TranshumanceRequestDTO toDto(TranshumanceRequest s);

    @Mapping(target = "removeHive", ignore = true)
    TranshumanceRequest toEntity(TranshumanceRequestDTO transhumanceRequestDTO);

    @Named("apiaryId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ApiaryDTO toDtoApiaryId(Apiary apiary);

    @Named("hiveId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    HiveDTO toDtoHiveId(Hive hive);

    @Named("hiveIdSet")
    default Set<HiveDTO> toDtoHiveIdSet(Set<Hive> hive) {
        return hive.stream().map(this::toDtoHiveId).collect(Collectors.toSet());
    }
}
