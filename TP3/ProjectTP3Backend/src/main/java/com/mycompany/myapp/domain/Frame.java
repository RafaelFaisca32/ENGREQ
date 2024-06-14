package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Frame.
 */
@Entity
@Table(name = "frame")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Frame implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "code", unique = true)
    private String code;

    @ManyToMany(mappedBy = "frames")
    @JsonIgnoreProperties(
        value = { "apiary", "frames", "unfoldingCreatedHive", "crests", "unfoldings", "inspections", "transhumanceRequests" },
        allowSetters = true
    )
    private Set<Hive> hives = new HashSet<>();

    @ManyToMany(mappedBy = "frames")
    @JsonIgnoreProperties(value = { "hive", "frames" }, allowSetters = true)
    private Set<Crest> crests = new HashSet<>();

    @ManyToMany(mappedBy = "removedFramesOldHives")
    @JsonIgnoreProperties(
        value = { "createdHive", "hive", "removedFramesOldHives", "insertedFramesOldHives", "insertedFramesNewHives" },
        allowSetters = true
    )
    private Set<Unfolding> unfoldingRemovedFramesOldHives = new HashSet<>();

    @ManyToMany(mappedBy = "insertedFramesOldHives")
    @JsonIgnoreProperties(
        value = { "createdHive", "hive", "removedFramesOldHives", "insertedFramesOldHives", "insertedFramesNewHives" },
        allowSetters = true
    )
    private Set<Unfolding> unfoldingInsertedFramesOldHives = new HashSet<>();

    @ManyToMany(mappedBy = "insertedFramesNewHives")
    @JsonIgnoreProperties(
        value = { "createdHive", "hive", "removedFramesOldHives", "insertedFramesOldHives", "insertedFramesNewHives" },
        allowSetters = true
    )
    private Set<Unfolding> unfoldingInsertedFramesNewHives = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Frame id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public Frame code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Set<Hive> getHives() {
        return this.hives;
    }

    public void setHives(Set<Hive> hives) {
        if (this.hives != null) {
            this.hives.forEach(i -> i.removeFrame(this));
        }
        if (hives != null) {
            hives.forEach(i -> i.addFrame(this));
        }
        this.hives = hives;
    }

    public Frame hives(Set<Hive> hives) {
        this.setHives(hives);
        return this;
    }

    public Frame addHive(Hive hive) {
        this.hives.add(hive);
        hive.getFrames().add(this);
        return this;
    }

    public Frame removeHive(Hive hive) {
        this.hives.remove(hive);
        hive.getFrames().remove(this);
        return this;
    }

    public Set<Crest> getCrests() {
        return this.crests;
    }

    public void setCrests(Set<Crest> crests) {
        if (this.crests != null) {
            this.crests.forEach(i -> i.removeFrame(this));
        }
        if (crests != null) {
            crests.forEach(i -> i.addFrame(this));
        }
        this.crests = crests;
    }

    public Frame crests(Set<Crest> crests) {
        this.setCrests(crests);
        return this;
    }

    public Frame addCrest(Crest crest) {
        this.crests.add(crest);
        crest.getFrames().add(this);
        return this;
    }

    public Frame removeCrest(Crest crest) {
        this.crests.remove(crest);
        crest.getFrames().remove(this);
        return this;
    }

    public Set<Unfolding> getUnfoldingRemovedFramesOldHives() {
        return this.unfoldingRemovedFramesOldHives;
    }

    public void setUnfoldingRemovedFramesOldHives(Set<Unfolding> unfoldings) {
        if (this.unfoldingRemovedFramesOldHives != null) {
            this.unfoldingRemovedFramesOldHives.forEach(i -> i.removeRemovedFramesOldHive(this));
        }
        if (unfoldings != null) {
            unfoldings.forEach(i -> i.addRemovedFramesOldHive(this));
        }
        this.unfoldingRemovedFramesOldHives = unfoldings;
    }

    public Frame unfoldingRemovedFramesOldHives(Set<Unfolding> unfoldings) {
        this.setUnfoldingRemovedFramesOldHives(unfoldings);
        return this;
    }

    public Frame addUnfoldingRemovedFramesOldHive(Unfolding unfolding) {
        this.unfoldingRemovedFramesOldHives.add(unfolding);
        unfolding.getRemovedFramesOldHives().add(this);
        return this;
    }

    public Frame removeUnfoldingRemovedFramesOldHive(Unfolding unfolding) {
        this.unfoldingRemovedFramesOldHives.remove(unfolding);
        unfolding.getRemovedFramesOldHives().remove(this);
        return this;
    }

    public Set<Unfolding> getUnfoldingInsertedFramesOldHives() {
        return this.unfoldingInsertedFramesOldHives;
    }

    public void setUnfoldingInsertedFramesOldHives(Set<Unfolding> unfoldings) {
        if (this.unfoldingInsertedFramesOldHives != null) {
            this.unfoldingInsertedFramesOldHives.forEach(i -> i.removeInsertedFramesOldHive(this));
        }
        if (unfoldings != null) {
            unfoldings.forEach(i -> i.addInsertedFramesOldHive(this));
        }
        this.unfoldingInsertedFramesOldHives = unfoldings;
    }

    public Frame unfoldingInsertedFramesOldHives(Set<Unfolding> unfoldings) {
        this.setUnfoldingInsertedFramesOldHives(unfoldings);
        return this;
    }

    public Frame addUnfoldingInsertedFramesOldHive(Unfolding unfolding) {
        this.unfoldingInsertedFramesOldHives.add(unfolding);
        unfolding.getInsertedFramesOldHives().add(this);
        return this;
    }

    public Frame removeUnfoldingInsertedFramesOldHive(Unfolding unfolding) {
        this.unfoldingInsertedFramesOldHives.remove(unfolding);
        unfolding.getInsertedFramesOldHives().remove(this);
        return this;
    }

    public Set<Unfolding> getUnfoldingInsertedFramesNewHives() {
        return this.unfoldingInsertedFramesNewHives;
    }

    public void setUnfoldingInsertedFramesNewHives(Set<Unfolding> unfoldings) {
        if (this.unfoldingInsertedFramesNewHives != null) {
            this.unfoldingInsertedFramesNewHives.forEach(i -> i.removeInsertedFramesNewHive(this));
        }
        if (unfoldings != null) {
            unfoldings.forEach(i -> i.addInsertedFramesNewHive(this));
        }
        this.unfoldingInsertedFramesNewHives = unfoldings;
    }

    public Frame unfoldingInsertedFramesNewHives(Set<Unfolding> unfoldings) {
        this.setUnfoldingInsertedFramesNewHives(unfoldings);
        return this;
    }

    public Frame addUnfoldingInsertedFramesNewHive(Unfolding unfolding) {
        this.unfoldingInsertedFramesNewHives.add(unfolding);
        unfolding.getInsertedFramesNewHives().add(this);
        return this;
    }

    public Frame removeUnfoldingInsertedFramesNewHive(Unfolding unfolding) {
        this.unfoldingInsertedFramesNewHives.remove(unfolding);
        unfolding.getInsertedFramesNewHives().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Frame)) {
            return false;
        }
        return id != null && id.equals(((Frame) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Frame{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            "}";
    }
}
