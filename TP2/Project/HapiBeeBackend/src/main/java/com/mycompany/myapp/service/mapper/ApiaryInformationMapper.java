package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.AnnualInventoryDeclaration;
import com.mycompany.myapp.domain.ApiaryInformation;
import com.mycompany.myapp.service.dto.AnnualInventoryDeclarationDTO;
import com.mycompany.myapp.service.dto.ApiaryInformationDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ApiaryInformation} and its DTO {@link ApiaryInformationDTO}.
 */
@Mapper(componentModel = "spring")
public interface ApiaryInformationMapper extends EntityMapper<ApiaryInformationDTO, ApiaryInformation> {
    @Mapping(target = "annualInventoryDeclaration", source = "annualInventoryDeclaration", qualifiedByName = "annualInventoryDeclarationId")
    ApiaryInformationDTO toDto(ApiaryInformation s);

    @Named("annualInventoryDeclarationId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    AnnualInventoryDeclarationDTO toDtoAnnualInventoryDeclarationId(AnnualInventoryDeclaration annualInventoryDeclaration);
}
