package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.AnnualInventoryDeclaration;
import com.mycompany.myapp.service.dto.AnnualInventoryDeclarationDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AnnualInventoryDeclaration} and its DTO {@link AnnualInventoryDeclarationDTO}.
 */
@Mapper(componentModel = "spring")
public interface AnnualInventoryDeclarationMapper extends EntityMapper<AnnualInventoryDeclarationDTO, AnnualInventoryDeclaration> {}
