package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Unfolding} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class UnfoldingDTO implements Serializable {

    private Long id;

    private String observations;

    private int hiveId;

    @NotNull
    private ZonedDateTime date;

    private HiveDTO hive;

    public void setHiveId(int id){
        this.hiveId = id;
    }
    public Long getHiveId(){ 
        return(long) hiveId; 
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public HiveDTO getHive() {
        return hive;
    }

    public void setHive(HiveDTO hive) {
        this.hive = hive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UnfoldingDTO)) {
            return false;
        }

        UnfoldingDTO unfoldingDTO = (UnfoldingDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, unfoldingDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UnfoldingDTO{" +
            "id=" + getId() +
            ", observations='" + getObservations() + "'" +
            ", date='" + getDate() + "'" +
            ", hive=" + getHive() +
            "}";
    }
}
