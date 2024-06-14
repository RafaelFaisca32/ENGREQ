package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.ApiaryInformation} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ApiaryInformationDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer zoneNumber;

    @NotNull
    private String zoneName;

    @NotNull
    private Integer numberHives;

    @NotNull
    private Boolean intensive;

    @NotNull
    private Boolean transhumance;

    @NotNull
    private String coordX;

    @NotNull
    private String coordY;

    @NotNull
    private String coordZ;

    @NotNull
    private Integer numberFrames;

    private AnnualInventoryDeclarationDTO annualInventoryDeclaration;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getZoneNumber() {
        return zoneNumber;
    }

    public void setZoneNumber(Integer zoneNumber) {
        this.zoneNumber = zoneNumber;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public Integer getNumberHives() {
        return numberHives;
    }

    public void setNumberHives(Integer numberHives) {
        this.numberHives = numberHives;
    }

    public Boolean getIntensive() {
        return intensive;
    }

    public void setIntensive(Boolean intensive) {
        this.intensive = intensive;
    }

    public Boolean getTranshumance() {
        return transhumance;
    }

    public void setTranshumance(Boolean transhumance) {
        this.transhumance = transhumance;
    }

    public String getCoordX() {
        return coordX;
    }

    public void setCoordX(String coordX) {
        this.coordX = coordX;
    }

    public String getCoordY() {
        return coordY;
    }

    public void setCoordY(String coordY) {
        this.coordY = coordY;
    }

    public String getCoordZ() {
        return coordZ;
    }

    public void setCoordZ(String coordZ) {
        this.coordZ = coordZ;
    }

    public Integer getNumberFrames() {
        return numberFrames;
    }

    public void setNumberFrames(Integer numberFrames) {
        this.numberFrames = numberFrames;
    }

    public AnnualInventoryDeclarationDTO getAnnualInventoryDeclaration() {
        return annualInventoryDeclaration;
    }

    public void setAnnualInventoryDeclaration(AnnualInventoryDeclarationDTO annualInventoryDeclaration) {
        this.annualInventoryDeclaration = annualInventoryDeclaration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ApiaryInformationDTO)) {
            return false;
        }

        ApiaryInformationDTO apiaryInformationDTO = (ApiaryInformationDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, apiaryInformationDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ApiaryInformationDTO{" +
            "id=" + getId() +
            ", zoneNumber=" + getZoneNumber() +
            ", zoneName='" + getZoneName() + "'" +
            ", numberHives=" + getNumberHives() +
            ", intensive='" + getIntensive() + "'" +
            ", transhumance='" + getTranshumance() + "'" +
            ", coordX='" + getCoordX() + "'" +
            ", coordY='" + getCoordY() + "'" +
            ", coordZ='" + getCoordZ() + "'" +
            ", numberFrames=" + getNumberFrames() +
            ", annualInventoryDeclaration=" + getAnnualInventoryDeclaration() +
            "}";
    }
}
