package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Apiary;
import com.mycompany.myapp.domain.Beekeeper;
import com.mycompany.myapp.domain.Zone;
import com.mycompany.myapp.service.dto.ApiaryDTO;
import com.mycompany.myapp.service.dto.BeekeeperDTO;
import com.mycompany.myapp.service.dto.ZoneDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Apiary} and its DTO {@link ApiaryDTO}.
 */
@Mapper(componentModel = "spring")
public interface ApiaryMapper extends EntityMapper<ApiaryDTO, Apiary> {
    @Mapping(target = "zone", source = "zone", qualifiedByName = "zoneId")
    @Mapping(target = "beekeeper", source = "beekeeper", qualifiedByName = "beekeeperId")
    ApiaryDTO toDto(Apiary s);

    @Named("zoneId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ZoneDTO toDtoZoneId(Zone zone);

    @Named("beekeeperId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    BeekeeperDTO toDtoBeekeeperId(Beekeeper beekeeper);
}
