package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Hive.
 */
@Entity
@Table(name = "hive")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Hive implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @ManyToOne
    @JsonIgnoreProperties(value = { "beekeeper", "apiaryZone", "hives", "transhumanceRequests" }, allowSetters = true)
    private Apiary apiary;

    @ManyToMany
    @JoinTable(name = "rel_hive__frame", joinColumns = @JoinColumn(name = "hive_id"), inverseJoinColumns = @JoinColumn(name = "frame_id"))
    @JsonIgnoreProperties(
        value = {
            "hives", "crests", "unfoldingRemovedFramesOldHives", "unfoldingInsertedFramesOldHives", "unfoldingInsertedFramesNewHives",
        },
        allowSetters = true
    )
    private Set<Frame> frames = new HashSet<>();

    @JsonIgnoreProperties(
        value = { "createdHive", "hive", "removedFramesOldHives", "insertedFramesOldHives", "insertedFramesNewHives" },
        allowSetters = true
    )
    @OneToOne(mappedBy = "createdHive")
    private Unfolding unfoldingCreatedHive;

    @OneToMany(mappedBy = "hive")
    @JsonIgnoreProperties(value = { "hive", "frames" }, allowSetters = true)
    private Set<Crest> crests = new HashSet<>();

    @OneToMany(mappedBy = "hive")
    @JsonIgnoreProperties(
        value = { "createdHive", "hive", "removedFramesOldHives", "insertedFramesOldHives", "insertedFramesNewHives" },
        allowSetters = true
    )
    private Set<Unfolding> unfoldings = new HashSet<>();

    @OneToMany(mappedBy = "hive")
    @JsonIgnoreProperties(value = { "hive", "diseases" }, allowSetters = true)
    private Set<Inspection> inspections = new HashSet<>();

    @ManyToMany(mappedBy = "hives")
    @JsonIgnoreProperties(value = { "beekeeper", "apiary", "hives" }, allowSetters = true)
    private Set<TranshumanceRequest> transhumanceRequests = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Hive id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public Hive code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Apiary getApiary() {
        return this.apiary;
    }

    public void setApiary(Apiary apiary) {
        this.apiary = apiary;
    }

    public Hive apiary(Apiary apiary) {
        this.setApiary(apiary);
        return this;
    }

    public Set<Frame> getFrames() {
        return this.frames;
    }

    public void setFrames(Set<Frame> frames) {
        this.frames = frames;
    }

    public Hive frames(Set<Frame> frames) {
        this.setFrames(frames);
        return this;
    }

    public Hive addFrame(Frame frame) {
        this.frames.add(frame);
        frame.getHives().add(this);
        return this;
    }

    public Hive removeFrame(Frame frame) {
        this.frames.remove(frame);
        frame.getHives().remove(this);
        return this;
    }

    public Unfolding getUnfoldingCreatedHive() {
        return this.unfoldingCreatedHive;
    }

    public void setUnfoldingCreatedHive(Unfolding unfolding) {
        if (this.unfoldingCreatedHive != null) {
            this.unfoldingCreatedHive.setCreatedHive(null);
        }
        if (unfolding != null) {
            unfolding.setCreatedHive(this);
        }
        this.unfoldingCreatedHive = unfolding;
    }

    public Hive unfoldingCreatedHive(Unfolding unfolding) {
        this.setUnfoldingCreatedHive(unfolding);
        return this;
    }

    public Set<Crest> getCrests() {
        return this.crests;
    }

    public void setCrests(Set<Crest> crests) {
        if (this.crests != null) {
            this.crests.forEach(i -> i.setHive(null));
        }
        if (crests != null) {
            crests.forEach(i -> i.setHive(this));
        }
        this.crests = crests;
    }

    public Hive crests(Set<Crest> crests) {
        this.setCrests(crests);
        return this;
    }

    public Hive addCrest(Crest crest) {
        this.crests.add(crest);
        crest.setHive(this);
        return this;
    }

    public Hive removeCrest(Crest crest) {
        this.crests.remove(crest);
        crest.setHive(null);
        return this;
    }

    public Set<Unfolding> getUnfoldings() {
        return this.unfoldings;
    }

    public void setUnfoldings(Set<Unfolding> unfoldings) {
        if (this.unfoldings != null) {
            this.unfoldings.forEach(i -> i.setHive(null));
        }
        if (unfoldings != null) {
            unfoldings.forEach(i -> i.setHive(this));
        }
        this.unfoldings = unfoldings;
    }

    public Hive unfoldings(Set<Unfolding> unfoldings) {
        this.setUnfoldings(unfoldings);
        return this;
    }

    public Hive addUnfolding(Unfolding unfolding) {
        this.unfoldings.add(unfolding);
        unfolding.setHive(this);
        return this;
    }

    public Hive removeUnfolding(Unfolding unfolding) {
        this.unfoldings.remove(unfolding);
        unfolding.setHive(null);
        return this;
    }

    public Set<Inspection> getInspections() {
        return this.inspections;
    }

    public void setInspections(Set<Inspection> inspections) {
        if (this.inspections != null) {
            this.inspections.forEach(i -> i.setHive(null));
        }
        if (inspections != null) {
            inspections.forEach(i -> i.setHive(this));
        }
        this.inspections = inspections;
    }

    public Hive inspections(Set<Inspection> inspections) {
        this.setInspections(inspections);
        return this;
    }

    public Hive addInspection(Inspection inspection) {
        this.inspections.add(inspection);
        inspection.setHive(this);
        return this;
    }

    public Hive removeInspection(Inspection inspection) {
        this.inspections.remove(inspection);
        inspection.setHive(null);
        return this;
    }

    public Set<TranshumanceRequest> getTranshumanceRequests() {
        return this.transhumanceRequests;
    }

    public void setTranshumanceRequests(Set<TranshumanceRequest> transhumanceRequests) {
        if (this.transhumanceRequests != null) {
            this.transhumanceRequests.forEach(i -> i.removeHive(this));
        }
        if (transhumanceRequests != null) {
            transhumanceRequests.forEach(i -> i.addHive(this));
        }
        this.transhumanceRequests = transhumanceRequests;
    }

    public Hive transhumanceRequests(Set<TranshumanceRequest> transhumanceRequests) {
        this.setTranshumanceRequests(transhumanceRequests);
        return this;
    }

    public Hive addTranshumanceRequest(TranshumanceRequest transhumanceRequest) {
        this.transhumanceRequests.add(transhumanceRequest);
        transhumanceRequest.getHives().add(this);
        return this;
    }

    public Hive removeTranshumanceRequest(TranshumanceRequest transhumanceRequest) {
        this.transhumanceRequests.remove(transhumanceRequest);
        transhumanceRequest.getHives().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Hive)) {
            return false;
        }
        return id != null && id.equals(((Hive) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Hive{" + getId() +
            ", code='" + getCode() + "'" +
            "}";
    }
}
