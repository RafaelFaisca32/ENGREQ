package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mycompany.myapp.domain.enumeration.ApiaryState;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Apiary.
 */
@Entity
@Table(name = "apiary")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Apiary implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    private ApiaryState state;

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
    @Column(name = "number", nullable = false)
    private Integer number;

    @NotNull
    @Column(name = "intensive", nullable = false)
    private Boolean intensive;

    @NotNull
    @Column(name = "transhumance", nullable = false)
    private Boolean transhumance;

    @ManyToOne
    @JsonIgnoreProperties(value = { "user", "transhumanceRequests", "apiaries" }, allowSetters = true)
    private Beekeeper beekeeper;

    @ManyToOne
    @JsonIgnoreProperties(value = { "apiaries" }, allowSetters = true)
    private ApiaryZone apiaryZone;

    @OneToMany(mappedBy = "apiary")
    @JsonIgnoreProperties(
        value = { "apiary", "frames", "unfoldingCreatedHive", "crests", "unfoldings", "inspections", "transhumanceRequests" },
        allowSetters = true
    )
    private Set<Hive> hives = new HashSet<>();

    @OneToMany(mappedBy = "apiary")
    @JsonIgnoreProperties(value = { "beekeeper", "apiary", "hives" }, allowSetters = true)
    private Set<TranshumanceRequest> transhumanceRequests = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Apiary id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Apiary name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ApiaryState getState() {
        return this.state;
    }

    public Apiary state(ApiaryState state) {
        this.setState(state);
        return this;
    }

    public void setState(ApiaryState state) {
        this.state = state;
    }

    public String getCoordX() {
        return this.coordX;
    }

    public Apiary coordX(String coordX) {
        this.setCoordX(coordX);
        return this;
    }

    public void setCoordX(String coordX) {
        this.coordX = coordX;
    }

    public String getCoordY() {
        return this.coordY;
    }

    public Apiary coordY(String coordY) {
        this.setCoordY(coordY);
        return this;
    }

    public void setCoordY(String coordY) {
        this.coordY = coordY;
    }

    public String getCoordZ() {
        return this.coordZ;
    }

    public Apiary coordZ(String coordZ) {
        this.setCoordZ(coordZ);
        return this;
    }

    public void setCoordZ(String coordZ) {
        this.coordZ = coordZ;
    }

    public Integer getNumber() {
        return this.number;
    }

    public Apiary number(Integer number) {
        this.setNumber(number);
        return this;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Boolean getIntensive() {
        return this.intensive;
    }

    public Apiary intensive(Boolean intensive) {
        this.setIntensive(intensive);
        return this;
    }

    public void setIntensive(Boolean intensive) {
        this.intensive = intensive;
    }

    public Boolean getTranshumance() {
        return this.transhumance;
    }

    public Apiary transhumance(Boolean transhumance) {
        this.setTranshumance(transhumance);
        return this;
    }

    public void setTranshumance(Boolean transhumance) {
        this.transhumance = transhumance;
    }

    public Beekeeper getBeekeeper() {
        return this.beekeeper;
    }

    public void setBeekeeper(Beekeeper beekeeper) {
        this.beekeeper = beekeeper;
    }

    public Apiary beekeeper(Beekeeper beekeeper) {
        this.setBeekeeper(beekeeper);
        return this;
    }

    public ApiaryZone getApiaryZone() {
        return this.apiaryZone;
    }

    public void setApiaryZone(ApiaryZone apiaryZone) {
        this.apiaryZone = apiaryZone;
    }

    public Apiary apiaryZone(ApiaryZone apiaryZone) {
        this.setApiaryZone(apiaryZone);
        return this;
    }

    public Set<Hive> getHives() {
        return this.hives;
    }

    public void setHives(Set<Hive> hives) {
        if (this.hives != null) {
            this.hives.forEach(i -> i.setApiary(null));
        }
        if (hives != null) {
            hives.forEach(i -> i.setApiary(this));
        }
        this.hives = hives;
    }

    public Apiary hives(Set<Hive> hives) {
        this.setHives(hives);
        return this;
    }

    public Apiary addHive(Hive hive) {
        this.hives.add(hive);
        hive.setApiary(this);
        return this;
    }

    public Apiary removeHive(Hive hive) {
        this.hives.remove(hive);
        hive.setApiary(null);
        return this;
    }

    public Set<TranshumanceRequest> getTranshumanceRequests() {
        return this.transhumanceRequests;
    }

    public void setTranshumanceRequests(Set<TranshumanceRequest> transhumanceRequests) {
        if (this.transhumanceRequests != null) {
            this.transhumanceRequests.forEach(i -> i.setApiary(null));
        }
        if (transhumanceRequests != null) {
            transhumanceRequests.forEach(i -> i.setApiary(this));
        }
        this.transhumanceRequests = transhumanceRequests;
    }

    public Apiary transhumanceRequests(Set<TranshumanceRequest> transhumanceRequests) {
        this.setTranshumanceRequests(transhumanceRequests);
        return this;
    }

    public Apiary addTranshumanceRequest(TranshumanceRequest transhumanceRequest) {
        this.transhumanceRequests.add(transhumanceRequest);
        transhumanceRequest.setApiary(this);
        return this;
    }

    public Apiary removeTranshumanceRequest(TranshumanceRequest transhumanceRequest) {
        this.transhumanceRequests.remove(transhumanceRequest);
        transhumanceRequest.setApiary(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Apiary)) {
            return false;
        }
        return id != null && id.equals(((Apiary) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Apiary{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", state='" + getState() + "'" +
            ", coordX='" + getCoordX() + "'" +
            ", coordY='" + getCoordY() + "'" +
            ", coordZ='" + getCoordZ() + "'" +
            ", number=" + getNumber() +
            ", intensive='" + getIntensive() + "'" +
            ", transhumance='" + getTranshumance() + "'" +
            "}";
    }
}
