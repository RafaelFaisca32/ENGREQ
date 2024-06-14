package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Disease;
import com.mycompany.myapp.domain.Hive;
import com.mycompany.myapp.domain.Inspection;
import com.mycompany.myapp.service.dto.DiseaseDTO;
import com.mycompany.myapp.service.dto.HiveDTO;
import com.mycompany.myapp.service.dto.InspectionDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Inspection} and its DTO {@link InspectionDTO}.
 */
@Mapper(componentModel = "spring")
public interface InspectionMapper extends EntityMapper<InspectionDTO, Inspection> {
    @Mapping(target = "hive", source = "hive", qualifiedByName = "hiveId")
    @Mapping(target = "diseases", source = "diseases", qualifiedByName = "diseaseIdSet")
    InspectionDTO toDto(Inspection s);

    @Mapping(target = "removeDisease", ignore = true)
    Inspection toEntity(InspectionDTO inspectionDTO);

    @Named("hiveId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    HiveDTO toDtoHiveId(Hive hive);

    @Named("diseaseId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DiseaseDTO toDtoDiseaseId(Disease disease);

    @Named("diseaseIdSet")
    default Set<DiseaseDTO> toDtoDiseaseIdSet(Set<Disease> disease) {
        return disease.stream().map(this::toDtoDiseaseId).collect(Collectors.toSet());
    }
}
