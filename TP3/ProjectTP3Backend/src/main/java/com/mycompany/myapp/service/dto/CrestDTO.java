package com.mycompany.myapp.service.dto;

import com.mycompany.myapp.domain.enumeration.CrestState;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Crest} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CrestDTO implements Serializable {

    private Long id;
    private int hiveId;
    @NotNull
    private Float waxWeight;

    @NotNull
    private Float timeWastedCentrifuge;

    @NotNull
    private ZonedDateTime initialDateDecantation;

    private ZonedDateTime finalDateDecantation;

    private Float producedHoneyQuantity;

    @NotNull
    private CrestState state;

    private HiveDTO hive;

    private Set<FrameDTO> frames = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setHiveId(int id){
        this.hiveId = id;
    }
    public Long getHiveId(){
        return(long) hiveId;
    }

    public Float getWaxWeight() {
        return waxWeight;
    }

    public void setWaxWeight(Float waxWeight) {
        this.waxWeight = waxWeight;
    }

    public Float getTimeWastedCentrifuge() {
        return timeWastedCentrifuge;
    }

    public void setTimeWastedCentrifuge(Float timeWastedCentrifuge) {
        this.timeWastedCentrifuge = timeWastedCentrifuge;
    }

    public ZonedDateTime getInitialDateDecantation() {
        return initialDateDecantation;
    }

    public void setInitialDateDecantation(ZonedDateTime initialDateDecantation) {
        this.initialDateDecantation = initialDateDecantation;
    }

    public ZonedDateTime getFinalDateDecantation() {
        return finalDateDecantation;
    }

    public void setFinalDateDecantation(ZonedDateTime finalDateDecantation) {
        this.finalDateDecantation = finalDateDecantation;
    }

    public Float getProducedHoneyQuantity() {
        return producedHoneyQuantity;
    }

    public void setProducedHoneyQuantity(Float producedHoneyQuantity) {
        this.producedHoneyQuantity = producedHoneyQuantity;
    }

    public CrestState getState() {
        return state;
    }

    public void setState(CrestState state) {
        this.state = state;
    }

    public HiveDTO getHive() {
        return hive;
    }

    public void setHive(HiveDTO hive) {
        this.hive = hive;
    }

    public Set<FrameDTO> getFrames() {
        return frames;
    }

    public void setFrames(Set<FrameDTO> frames) {
        this.frames = frames;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CrestDTO)) {
            return false;
        }

        CrestDTO crestDTO = (CrestDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, crestDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CrestDTO{" +
            "id=" + getId() +
            ", waxWeight=" + getWaxWeight() +
            ", timeWastedCentrifuge=" + getTimeWastedCentrifuge() +
            ", initialDateDecantation='" + getInitialDateDecantation() + "'" +
            ", finalDateDecantation='" + getFinalDateDecantation() + "'" +
            ", producedHoneyQuantity=" + getProducedHoneyQuantity() +
            ", state='" + getState() + "'" +
            ", hive=" + getHive() +
            ", frames=" + getFrames() +
            "}";
    }
}
