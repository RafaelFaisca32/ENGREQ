package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Hive;
import com.mycompany.myapp.domain.Unfolding;
import com.mycompany.myapp.service.dto.HiveDTO;
import com.mycompany.myapp.service.dto.UnfoldingDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Unfolding} and its DTO {@link UnfoldingDTO}.
 */
@Mapper(componentModel = "spring")
public interface UnfoldingMapper extends EntityMapper<UnfoldingDTO, Unfolding> {
    @Mapping(target = "hive", source = "hive", qualifiedByName = "hiveId")
    UnfoldingDTO toDto(Unfolding s);

    @Named("hiveId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    HiveDTO toDtoHiveId(Hive hive);
}
