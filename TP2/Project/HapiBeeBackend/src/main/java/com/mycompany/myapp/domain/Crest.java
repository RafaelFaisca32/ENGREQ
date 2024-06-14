package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mycompany.myapp.domain.enumeration.CrestState;
import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Crest.
 */
@Entity
@Table(name = "crest")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Crest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "comb_frame_quantity", nullable = false)
    private Integer combFrameQuantity;

    @NotNull
    @Column(name = "wax_weight", nullable = false)
    private Float waxWeight;

    @NotNull
    @Column(name = "time_wasted_centrifuge", nullable = false)
    private Float timeWastedCentrifuge;

    @NotNull
    @Column(name = "initial_date_decantation", nullable = false)
    private ZonedDateTime initialDateDecantation;

    @Column(name = "final_date_decantation")
    private ZonedDateTime finalDateDecantation;

    @Column(name = "produced_honey_quantity")
    private Float producedHoneyQuantity;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    private CrestState state;

    @ManyToOne
    @JoinColumn(name = "hive_id")
    @JsonIgnoreProperties(
        value = { "apiary", "crests", "unfoldings", "frames", "inspections", "transhumanceRequests" },
        allowSetters = true
    )
    private Hive hive;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Crest id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCombFrameQuantity() {
        return this.combFrameQuantity;
    }

    public Crest combFrameQuantity(Integer combFrameQuantity) {
        this.setCombFrameQuantity(combFrameQuantity);
        return this;
    }

    public void setCombFrameQuantity(Integer combFrameQuantity) {
        this.combFrameQuantity = combFrameQuantity;
    }

    public Float getWaxWeight() {
        return this.waxWeight;
    }

    public Crest waxWeight(Float waxWeight) {
        this.setWaxWeight(waxWeight);
        return this;
    }

    public void setWaxWeight(Float waxWeight) {
        this.waxWeight = waxWeight;
    }

    public Float getTimeWastedCentrifuge() {
        return this.timeWastedCentrifuge;
    }

    public Crest timeWastedCentrifuge(Float timeWastedCentrifuge) {
        this.setTimeWastedCentrifuge(timeWastedCentrifuge);
        return this;
    }

    public void setTimeWastedCentrifuge(Float timeWastedCentrifuge) {
        this.timeWastedCentrifuge = timeWastedCentrifuge;
    }

    public ZonedDateTime getInitialDateDecantation() {
        return this.initialDateDecantation;
    }

    public Crest initialDateDecantation(ZonedDateTime initialDateDecantation) {
        this.setInitialDateDecantation(initialDateDecantation);
        return this;
    }

    public void setInitialDateDecantation(ZonedDateTime initialDateDecantation) {
        this.initialDateDecantation = initialDateDecantation;
    }

    public ZonedDateTime getFinalDateDecantation() {
        return this.finalDateDecantation;
    }

    public Crest finalDateDecantation(ZonedDateTime finalDateDecantation) {
        this.setFinalDateDecantation(finalDateDecantation);
        return this;
    }

    public void setFinalDateDecantation(ZonedDateTime finalDateDecantation) {
        this.finalDateDecantation = finalDateDecantation;
    }

    public Float getProducedHoneyQuantity() {
        return this.producedHoneyQuantity;
    }

    public Crest producedHoneyQuantity(Float producedHoneyQuantity) {
        this.setProducedHoneyQuantity(producedHoneyQuantity);
        return this;
    }

    public void setProducedHoneyQuantity(Float producedHoneyQuantity) {
        this.producedHoneyQuantity = producedHoneyQuantity;
    }

    public CrestState getState() {
        return this.state;
    }

    public Crest state(CrestState state) {
        this.setState(state);
        return this;
    }

    public void setState(CrestState state) {
        this.state = state;
    }

    public Hive getHive() {
        return this.hive;
    }

    public void setHive(Hive hive) {
        this.hive = hive;
    }

    public Crest hive(Hive hive) {
        this.setHive(hive);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Crest)) {
            return false;
        }
        return id != null && id.equals(((Crest) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Crest{" +
            "id=" + getId() +
            ", combFrameQuantity=" + getCombFrameQuantity() +
            ", waxWeight=" + getWaxWeight() +
            ", timeWastedCentrifuge=" + getTimeWastedCentrifuge() +
            ", initialDateDecantation='" + getInitialDateDecantation() + "'" +
            ", finalDateDecantation='" + getFinalDateDecantation() + "'" +
            ", producedHoneyQuantity=" + getProducedHoneyQuantity() +
            ", state='" + getState() + "'" +
            "}";
    }
}
