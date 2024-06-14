package com.mycompany.myapp.service.dto;

import com.mycompany.myapp.domain.enumeration.ApiaryZoneState;
import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.ApiaryZone} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ApiaryZoneDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Boolean controlledZone;

    @NotNull
    private ApiaryZoneState state;

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

    public Boolean getControlledZone() {
        return controlledZone;
    }

    public void setControlledZone(Boolean controlledZone) {
        this.controlledZone = controlledZone;
    }

    public ApiaryZoneState getState() {
        return state;
    }

    public void setState(ApiaryZoneState state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ApiaryZoneDTO)) {
            return false;
        }

        ApiaryZoneDTO apiaryZoneDTO = (ApiaryZoneDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, apiaryZoneDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ApiaryZoneDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", controlledZone='" + getControlledZone() + "'" +
            ", state='" + getState() + "'" +
            "}";
    }
}
