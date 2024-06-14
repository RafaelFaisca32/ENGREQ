package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Apiary;
import com.mycompany.myapp.domain.Hive;
import com.mycompany.myapp.service.dto.ApiaryDTO;
import com.mycompany.myapp.service.dto.HiveDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Hive} and its DTO {@link HiveDTO}.
 */
@Mapper(componentModel = "spring")
public interface HiveMapper extends EntityMapper<HiveDTO, Hive> {
    @Mapping(target = "apiary", source = "apiary", qualifiedByName = "apiaryId")
    HiveDTO toDto(Hive s);

    @Named("apiaryId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ApiaryDTO toDtoApiaryId(Apiary apiary);
}
