package com.mycompany.myapp.service.dto;

import com.mycompany.myapp.domain.enumeration.ApiaryState;
import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Apiary} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ApiaryDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private ApiaryState state;

    @NotNull
    private String coordX;

    @NotNull
    private String coordY;

    @NotNull
    private String coordZ;

    @NotNull
    private Integer number;

    @NotNull
    private Boolean intensive;

    @NotNull
    private Boolean transhumance;

    private BeekeeperDTO beekeeper;

    private ApiaryZoneDTO apiaryZone;
    private int apiaryZoneId;

    private int beekeeperId;


    public Long getApiaryZoneId() {
        return (long) apiaryZoneId;
    }

    public Long getBeekeeperId() {
        return (long) beekeeperId;
    }

    public void setApiaryZoneId(int apiaryZoneId) {
        this.apiaryZoneId = apiaryZoneId;
    }

    public void setBeekeeperId(int beekeeperId) {
        this.beekeeperId = beekeeperId;
    }

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

    public ApiaryState getState() {
        return state;
    }

    public void setState(ApiaryState state) {
        this.state = state;
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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
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

    public BeekeeperDTO getBeekeeper() {
        return beekeeper;
    }

    public void setBeekeeper(BeekeeperDTO beekeeper) {
        this.beekeeper = beekeeper;
    }

    public ApiaryZoneDTO getApiaryZone() {
        return apiaryZone;
    }

    public void setApiaryZone(ApiaryZoneDTO apiaryZone) {
        this.apiaryZone = apiaryZone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ApiaryDTO)) {
            return false;
        }

        ApiaryDTO apiaryDTO = (ApiaryDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, apiaryDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ApiaryDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", state='" + getState() + "'" +
            ", coordX='" + getCoordX() + "'" +
            ", coordY='" + getCoordY() + "'" +
            ", coordZ='" + getCoordZ() + "'" +
            ", number=" + getNumber() +
            ", intensive='" + getIntensive() + "'" +
            ", transhumance='" + getTranshumance() + "'" +
            ", beekeeper=" + getBeekeeper() +
            ", apiaryZone=" + getApiaryZone() +
            "}";
    }
}
