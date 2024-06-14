package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mycompany.myapp.domain.enumeration.TranshumanceRequestState;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A TranshumanceRequest.
 */
@Entity
@Table(name = "transhumance_request")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TranshumanceRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "request_date", nullable = false)
    private ZonedDateTime requestDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    private TranshumanceRequestState state;

    @ManyToOne
    @JsonIgnoreProperties(value = { "zone", "beekeeper", "hives", "transhumanceRequests" }, allowSetters = true)
    private Apiary apiary;

    @ManyToMany
    @JoinTable(
        name = "rel_transhumance_request__hive",
        joinColumns = @JoinColumn(name = "transhumance_request_id"),
        inverseJoinColumns = @JoinColumn(name = "hive_id")
    )
    @JsonIgnoreProperties(
        value = { "apiary", "crests", "unfoldings", "frames", "inspections", "transhumanceRequests" },
        allowSetters = true
    )
    private Set<Hive> hives = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public TranshumanceRequest id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getRequestDate() {
        return this.requestDate;
    }

    public TranshumanceRequest requestDate(ZonedDateTime requestDate) {
        this.setRequestDate(requestDate);
        return this;
    }

    public void setRequestDate(ZonedDateTime requestDate) {
        this.requestDate = requestDate;
    }

    public TranshumanceRequestState getState() {
        return this.state;
    }

    public TranshumanceRequest state(TranshumanceRequestState state) {
        this.setState(state);
        return this;
    }

    public void setState(TranshumanceRequestState state) {
        this.state = state;
    }

    public Apiary getApiary() {
        return this.apiary;
    }

    public void setApiary(Apiary apiary) {
        this.apiary = apiary;
    }

    public TranshumanceRequest apiary(Apiary apiary) {
        this.setApiary(apiary);
        return this;
    }

    public Set<Hive> getHives() {
        return this.hives;
    }

    public void setHives(Set<Hive> hives) {
        this.hives = hives;
    }

    public TranshumanceRequest hives(Set<Hive> hives) {
        this.setHives(hives);
        return this;
    }

    public TranshumanceRequest addHive(Hive hive) {
        this.hives.add(hive);
        hive.getTranshumanceRequests().add(this);
        return this;
    }

    public TranshumanceRequest removeHive(Hive hive) {
        this.hives.remove(hive);
        hive.getTranshumanceRequests().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TranshumanceRequest)) {
            return false;
        }
        return id != null && id.equals(((TranshumanceRequest) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TranshumanceRequest{" +
            "id=" + getId() +
            ", requestDate='" + getRequestDate() + "'" +
            ", state='" + getState() + "'" +
            "}";
    }
}
