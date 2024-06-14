package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Crest;
import com.mycompany.myapp.domain.Hive;
import com.mycompany.myapp.service.dto.CrestDTO;
import com.mycompany.myapp.service.dto.HiveDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Crest} and its DTO {@link CrestDTO}.
 */
@Mapper(componentModel = "spring")
public interface CrestMapper extends EntityMapper<CrestDTO, Crest> {
    @Mapping(target = "hive", source = "hive", qualifiedByName = "hiveId")
    CrestDTO toDto(Crest s);

    @Named("hiveId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    HiveDTO toDtoHiveId(Hive hive);
}
