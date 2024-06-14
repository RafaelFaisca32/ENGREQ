package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Zone.
 */
@Entity
@Table(name = "zone")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Zone implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "number", nullable = false)
    private Integer number;

    @NotNull
    @Column(name = "coord_x", nullable = false)
    private String coordX;

    @NotNull
    @Column(name = "coord_y", nullable = false)
    private String coordY;

    @NotNull
    @Column(name = "coord_z", nullable = false)
    private String coordZ;

    @NotNull
    @Column(name = "controlled_zone", nullable = false)
    private Boolean controlledZone;

    @JsonIgnoreProperties(value = { "zone", "beekeeper", "hives", "transhumanceRequests" }, allowSetters = true)
    @OneToOne(mappedBy = "zone")
    private Apiary apiary;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Zone id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Zone name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return this.number;
    }

    public Zone number(Integer number) {
        this.setNumber(number);
        return this;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getCoordX() {
        return this.coordX;
    }

    public Zone coordX(String coordX) {
        this.setCoordX(coordX);
        return this;
    }

    public void setCoordX(String coordX) {
        this.coordX = coordX;
    }

    public String getCoordY() {
        return this.coordY;
    }

    public Zone coordY(String coordY) {
        this.setCoordY(coordY);
        return this;
    }

    public void setCoordY(String coordY) {
        this.coordY = coordY;
    }

    public String getCoordZ() {
        return this.coordZ;
    }

    public Zone coordZ(String coordZ) {
        this.setCoordZ(coordZ);
        return this;
    }

    public void setCoordZ(String coordZ) {
        this.coordZ = coordZ;
    }

    public Boolean getControlledZone() {
        return this.controlledZone;
    }

    public Zone controlledZone(Boolean controlledZone) {
        this.setControlledZone(controlledZone);
        return this;
    }

    public void setControlledZone(Boolean controlledZone) {
        this.controlledZone = controlledZone;
    }

    public Apiary getApiary() {
        return this.apiary;
    }

    public void setApiary(Apiary apiary) {
        if (this.apiary != null) {
            this.apiary.setZone(null);
        }
        if (apiary != null) {
            apiary.setZone(this);
        }
        this.apiary = apiary;
    }

    public Zone apiary(Apiary apiary) {
        this.setApiary(apiary);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Zone)) {
            return false;
        }
        return id != null && id.equals(((Zone) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Zone{" +
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
