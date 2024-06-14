package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Apiary;
import com.mycompany.myapp.domain.ApiaryZone;
import com.mycompany.myapp.domain.Beekeeper;
import com.mycompany.myapp.service.dto.ApiaryDTO;
import com.mycompany.myapp.service.dto.ApiaryZoneDTO;
import com.mycompany.myapp.service.dto.BeekeeperDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Apiary} and its DTO {@link ApiaryDTO}.
 */
@Mapper(componentModel = "spring")
public interface ApiaryMapper extends EntityMapper<ApiaryDTO, Apiary> {
    @Mapping(target = "beekeeper", source = "beekeeper", qualifiedByName = "beekeeperId")
    @Mapping(target = "apiaryZone", source = "apiaryZone", qualifiedByName = "apiaryZoneId")
    ApiaryDTO toDto(Apiary s);

    @Named("beekeeperId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    BeekeeperDTO toDtoBeekeeperId(Beekeeper beekeeper);

    @Named("apiaryZoneId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ApiaryZoneDTO toDtoApiaryZoneId(ApiaryZone apiaryZone);
}
