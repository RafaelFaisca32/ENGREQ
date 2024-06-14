package com.mycompany.myapp.service.dto;

import com.mycompany.myapp.domain.enumeration.InspectionState;
import com.mycompany.myapp.domain.enumeration.InspectionType;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Inspection} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class InspectionDTO implements Serializable {

    private Long id;

    @NotNull
    private LocalDate date;

    private String note;

    private InspectionType type;

    @NotNull
    private InspectionState state;

    private HiveDTO hive;

    private Set<DiseaseDTO> diseases = new HashSet<>();
    private int hiveId;
    public Long getHiveId() {
        return (long) hiveId;
    }

    public void setHiveId(int hiveId) {
        this.hiveId = hiveId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public InspectionType getType() {
        return type;
    }

    public void setType(InspectionType type) {
        this.type = type;
    }

    public InspectionState getState() {
        return state;
    }

    public void setState(InspectionState state) {
        this.state = state;
    }

    public HiveDTO getHive() {
        return hive;
    }

    public void setHive(HiveDTO hive) {
        this.hive = hive;
    }

    public Set<DiseaseDTO> getDiseases() {
        return diseases;
    }

    public void setDiseases(Set<DiseaseDTO> diseases) {
        this.diseases = diseases;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InspectionDTO)) {
            return false;
        }

        InspectionDTO inspectionDTO = (InspectionDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, inspectionDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InspectionDTO{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", note='" + getNote() + "'" +
            ", type='" + getType() + "'" +
            ", state='" + getState() + "'" +
            ", hive=" + getHive() +
            ", diseases=" + getDiseases() +
            "}";
    }
}
