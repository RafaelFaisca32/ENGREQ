package com.mycompany.myapp.service.dto;

import com.mycompany.myapp.domain.enumeration.TranshumanceRequestState;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.TranshumanceRequest} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TranshumanceRequestDTO implements Serializable {

    private Long id;

    private ZonedDateTime requestDate;

    private TranshumanceRequestState state;

    private ApiaryDTO apiary;

    private Set<HiveDTO> hives = new HashSet<>();

    public int apiaryId;

    public Long getId() {
        return id;
    }

    public int getApiaryId(){
        return apiaryId;
    }

    public void setApiaryId(int apiaryId) {
        this.apiaryId = apiaryId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(ZonedDateTime requestDate) {
        this.requestDate = requestDate;
    }

    public TranshumanceRequestState getState() {
        return state;
    }

    public void setState(TranshumanceRequestState state) {
        this.state = state;
    }

    public ApiaryDTO getApiary() {
        return apiary;
    }



    public void setApiary(ApiaryDTO apiary) {
        this.apiary = apiary;
    }

    public Set<HiveDTO> getHives() {
        return hives;
    }

    public void setHives(Set<HiveDTO> hives) {
        this.hives = hives;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TranshumanceRequestDTO)) {
            return false;
        }

        TranshumanceRequestDTO transhumanceRequestDTO = (TranshumanceRequestDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, transhumanceRequestDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TranshumanceRequestDTO{" +
            "id=" + getId() +
            ", requestDate='" + getRequestDate() + "'" +
            ", state='" + getState() + "'" +
            ", apiary=" + getApiary() +
            ", hives=" + getHives() +
            "}";
    }
}
