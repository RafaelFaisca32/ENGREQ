package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Zone} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ZoneDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Integer number;

    @NotNull
    private String coordX;

    @NotNull
    private String coordY;

    @NotNull
    private String coordZ;

    @NotNull
    private Boolean controlledZone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
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

    public Boolean getControlledZone() {
        return controlledZone;
    }

    public void setControlledZone(Boolean controlledZone) {
        this.controlledZone = controlledZone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ZoneDTO)) {
            return false;
        }

        ZoneDTO zoneDTO = (ZoneDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, zoneDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ZoneDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", number=" + getNumber() +
            ", coordX='" + getCoordX() + "'" +
            ", coordY='" + getCoordY() + "'" +
            ", coordZ='" + getCoordZ() + "'" +
            ", controlledZone='" + getControlledZone() + "'" +
            "}";
    }
}
