package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Inspection.
 */
@Entity
@Table(name = "inspection")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Inspection implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "note")
    private String note;

    @ManyToOne
    @JsonIgnoreProperties(
        value = { "apiary", "crests", "unfoldings", "frames", "inspections", "transhumanceRequests" },
        allowSetters = true
    )
    private Hive hive;

    @ManyToMany
    @JoinTable(
        name = "rel_inspection__disease",
        joinColumns = @JoinColumn(name = "inspection_id"),
        inverseJoinColumns = @JoinColumn(name = "disease_id")
    )
    @JsonIgnoreProperties(value = { "inspections" }, allowSetters = true)
    private Set<Disease> diseases = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Inspection id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public Inspection date(LocalDate date) {
        this.setDate(date);
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getNote() {
        return this.note;
    }

    public Inspection note(String note) {
        this.setNote(note);
        return this;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Hive getHive() {
        return this.hive;
    }

    public void setHive(Hive hive) {
        this.hive = hive;
    }

    public Inspection hive(Hive hive) {
        this.setHive(hive);
        return this;
    }

    public Set<Disease> getDiseases() {
        return this.diseases;
    }

    public void setDiseases(Set<Disease> diseases) {
        this.diseases = diseases;
    }

    public Inspection diseases(Set<Disease> diseases) {
        this.setDiseases(diseases);
        return this;
    }

    public Inspection addDisease(Disease disease) {
        this.diseases.add(disease);
        disease.getInspections().add(this);
        return this;
    }

    public Inspection removeDisease(Disease disease) {
        this.diseases.remove(disease);
        disease.getInspections().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Inspection)) {
            return false;
        }
        return id != null && id.equals(((Inspection) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Inspection{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", note='" + getNote() + "'" +
            "}";
    }
}
