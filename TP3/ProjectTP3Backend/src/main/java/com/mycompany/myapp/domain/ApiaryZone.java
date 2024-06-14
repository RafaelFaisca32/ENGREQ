package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mycompany.myapp.domain.enumeration.ApiaryZoneState;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A ApiaryZone.
 */
@Entity
@Table(name = "apiary_zone")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ApiaryZone implements Serializable {

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
    @Column(name = "controlled_zone", nullable = false)
    private Boolean controlledZone;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    private ApiaryZoneState state;

    @OneToMany(mappedBy = "apiaryZone")
    @JsonIgnoreProperties(value = { "beekeeper", "apiaryZone", "hives", "transhumanceRequests" }, allowSetters = true)
    private Set<Apiary> apiaries = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ApiaryZone id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public ApiaryZone name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getControlledZone() {
        return this.controlledZone;
    }

    public ApiaryZone controlledZone(Boolean controlledZone) {
        this.setControlledZone(controlledZone);
        return this;
    }

    public void setControlledZone(Boolean controlledZone) {
        this.controlledZone = controlledZone;
    }

    public ApiaryZoneState getState() {
        return this.state;
    }

    public ApiaryZone state(ApiaryZoneState state) {
        this.setState(state);
        return this;
    }

    public void setState(ApiaryZoneState state) {
        this.state = state;
    }

    public Set<Apiary> getApiaries() {
        return this.apiaries;
    }

    public void setApiaries(Set<Apiary> apiaries) {
        if (this.apiaries != null) {
            this.apiaries.forEach(i -> i.setApiaryZone(null));
        }
        if (apiaries != null) {
            apiaries.forEach(i -> i.setApiaryZone(this));
        }
        this.apiaries = apiaries;
    }

    public ApiaryZone apiaries(Set<Apiary> apiaries) {
        this.setApiaries(apiaries);
        return this;
    }

    public ApiaryZone addApiary(Apiary apiary) {
        this.apiaries.add(apiary);
        apiary.setApiaryZone(this);
        return this;
    }

    public ApiaryZone removeApiary(Apiary apiary) {
        this.apiaries.remove(apiary);
        apiary.setApiaryZone(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ApiaryZone)) {
            return false;
        }
        return id != null && id.equals(((ApiaryZone) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ApiaryZone{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", controlledZone='" + getControlledZone() + "'" +
            ", state='" + getState() + "'" +
            "}";
    }
}
