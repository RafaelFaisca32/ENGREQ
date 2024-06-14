package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mycompany.myapp.domain.enumeration.UnfoldingState;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Unfolding.
 */
@Entity
@Table(name = "unfolding")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Unfolding implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "observations")
    private String observations;

    @NotNull
    @Column(name = "date", nullable = false)
    private ZonedDateTime date;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    private UnfoldingState state;

    @JsonIgnoreProperties(
        value = { "apiary", "frames", "unfoldingCreatedHive", "crests", "unfoldings", "inspections", "transhumanceRequests" },
        allowSetters = true
    )
    @OneToOne
    @JoinColumn(unique = true)
    private Hive createdHive;

    @ManyToOne
    @JsonIgnoreProperties(
        value = { "apiary", "frames", "unfoldingCreatedHive", "crests", "unfoldings", "inspections", "transhumanceRequests" },
        allowSetters = true
    )
    private Hive hive;

    @ManyToMany
    @JoinTable(
        name = "rel_unfolding__removed_frames_old_hive",
        joinColumns = @JoinColumn(name = "unfolding_id"),
        inverseJoinColumns = @JoinColumn(name = "removed_frames_old_hive_id")
    )
    @JsonIgnoreProperties(
        value = {
            "hives", "crests", "unfoldingRemovedFramesOldHives", "unfoldingInsertedFramesOldHives", "unfoldingInsertedFramesNewHives",
        },
        allowSetters = true
    )
    private Set<Frame> removedFramesOldHives = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "rel_unfolding__inserted_frames_old_hive",
        joinColumns = @JoinColumn(name = "unfolding_id"),
        inverseJoinColumns = @JoinColumn(name = "inserted_frames_old_hive_id")
    )
    @JsonIgnoreProperties(
        value = {
            "hives", "crests", "unfoldingRemovedFramesOldHives", "unfoldingInsertedFramesOldHives", "unfoldingInsertedFramesNewHives",
        },
        allowSetters = true
    )
    private Set<Frame> insertedFramesOldHives = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "rel_unfolding__inserted_frames_new_hive",
        joinColumns = @JoinColumn(name = "unfolding_id"),
        inverseJoinColumns = @JoinColumn(name = "inserted_frames_new_hive_id")
    )
    @JsonIgnoreProperties(
        value = {
            "hives", "crests", "unfoldingRemovedFramesOldHives", "unfoldingInsertedFramesOldHives", "unfoldingInsertedFramesNewHives",
        },
        allowSetters = true
    )
    private Set<Frame> insertedFramesNewHives = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Unfolding id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObservations() {
        return this.observations;
    }

    public Unfolding observations(String observations) {
        this.setObservations(observations);
        return this;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public ZonedDateTime getDate() {
        return this.date;
    }

    public Unfolding date(ZonedDateTime date) {
        this.setDate(date);
        return this;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public UnfoldingState getState() {
        return this.state;
    }

    public Unfolding state(UnfoldingState state) {
        this.setState(state);
        return this;
    }

    public void setState(UnfoldingState state) {
        this.state = state;
    }

    public Hive getCreatedHive() {
        return this.createdHive;
    }

    public void setCreatedHive(Hive hive) {
        this.createdHive = hive;
    }

    public Unfolding createdHive(Hive hive) {
        this.setCreatedHive(hive);
        return this;
    }

    public Hive getHive() {
        return this.hive;
    }

    public void setHive(Hive hive) {
        this.hive = hive;
    }

    public Unfolding hive(Hive hive) {
        this.setHive(hive);
        return this;
    }

    public Set<Frame> getRemovedFramesOldHives() {
        return this.removedFramesOldHives;
    }

    public void setRemovedFramesOldHives(Set<Frame> frames) {
        this.removedFramesOldHives = frames;
    }

    public Unfolding removedFramesOldHives(Set<Frame> frames) {
        this.setRemovedFramesOldHives(frames);
        return this;
    }

    public Unfolding addRemovedFramesOldHive(Frame frame) {
        this.removedFramesOldHives.add(frame);
        frame.getUnfoldingRemovedFramesOldHives().add(this);
        return this;
    }

    public Unfolding removeRemovedFramesOldHive(Frame frame) {
        this.removedFramesOldHives.remove(frame);
        frame.getUnfoldingRemovedFramesOldHives().remove(this);
        return this;
    }

    public Set<Frame> getInsertedFramesOldHives() {
        return this.insertedFramesOldHives;
    }

    public void setInsertedFramesOldHives(Set<Frame> frames) {
        this.insertedFramesOldHives = frames;
    }

    public Unfolding insertedFramesOldHives(Set<Frame> frames) {
        this.setInsertedFramesOldHives(frames);
        return this;
    }

    public Unfolding addInsertedFramesOldHive(Frame frame) {
        this.insertedFramesOldHives.add(frame);
        frame.getUnfoldingInsertedFramesOldHives().add(this);
        return this;
    }

    public Unfolding removeInsertedFramesOldHive(Frame frame) {
        this.insertedFramesOldHives.remove(frame);
        frame.getUnfoldingInsertedFramesOldHives().remove(this);
        return this;
    }

    public Set<Frame> getInsertedFramesNewHives() {
        return this.insertedFramesNewHives;
    }

    public void setInsertedFramesNewHives(Set<Frame> frames) {
        this.insertedFramesNewHives = frames;
    }

    public Unfolding insertedFramesNewHives(Set<Frame> frames) {
        this.setInsertedFramesNewHives(frames);
        return this;
    }

    public Unfolding addInsertedFramesNewHive(Frame frame) {
        this.insertedFramesNewHives.add(frame);
        frame.getUnfoldingInsertedFramesNewHives().add(this);
        return this;
    }

    public Unfolding removeInsertedFramesNewHive(Frame frame) {
        this.insertedFramesNewHives.remove(frame);
        frame.getUnfoldingInsertedFramesNewHives().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Unfolding)) {
            return false;
        }
        return id != null && id.equals(((Unfolding) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Unfolding{" +
            "id=" + getId() +
            ", observations='" + getObservations() + "'" +
            ", date='" + getDate() + "'" +
            ", state='" + getState() + "'" +
            "}";
    }
}
