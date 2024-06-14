package com.mycompany.myapp.service.dto;

import com.mycompany.myapp.domain.enumeration.UnfoldingState;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Unfolding} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class UnfoldingDTO implements Serializable {

    private Long id;

    private String observations;

    @NotNull
    private ZonedDateTime date;

    @NotNull
    private UnfoldingState state;

    private HiveDTO createdHive;

    private HiveDTO hive;

    private Set<FrameDTO> removedFramesOldHives = new HashSet<>();

    private Set<FrameDTO> insertedFramesOldHives = new HashSet<>();

    private Set<FrameDTO> insertedFramesNewHives = new HashSet<>();
    private int hiveId;

    private int createdHiveId;
    public void setCreatedHiveId(int id){
        this.createdHiveId = id;
    }
    public Long getCreatedHiveId(){
        return(long) createdHiveId;
    }
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

    public UnfoldingState getState() {
        return state;
    }

    public void setState(UnfoldingState state) {
        this.state = state;
    }

    public HiveDTO getCreatedHive() {
        return createdHive;
    }

    public void setCreatedHive(HiveDTO createdHive) {
        this.createdHive = createdHive;
    }

    public HiveDTO getHive() {
        return hive;
    }

    public void setHive(HiveDTO hive) {
        this.hive = hive;
    }

    public Set<FrameDTO> getRemovedFramesOldHives() {
        return removedFramesOldHives;
    }

    public void setRemovedFramesOldHives(Set<FrameDTO> removedFramesOldHives) {
        this.removedFramesOldHives = removedFramesOldHives;
    }

    public Set<FrameDTO> getInsertedFramesOldHives() {
        return insertedFramesOldHives;
    }

    public void setInsertedFramesOldHives(Set<FrameDTO> insertedFramesOldHives) {
        this.insertedFramesOldHives = insertedFramesOldHives;
    }

    public Set<FrameDTO> getInsertedFramesNewHives() {
        return insertedFramesNewHives;
    }

    public void setInsertedFramesNewHives(Set<FrameDTO> insertedFramesNewHives) {
        this.insertedFramesNewHives = insertedFramesNewHives;
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
            ", state='" + getState() + "'" +
            ", createdHive=" + getCreatedHive() +
            ", hive=" + getHive() +
            ", removedFramesOldHives=" + getRemovedFramesOldHives() +
            ", insertedFramesOldHives=" + getInsertedFramesOldHives() +
            ", insertedFramesNewHives=" + getInsertedFramesNewHives() +
            "}";
    }
}
