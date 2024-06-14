package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
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

    @ManyToOne
    @JsonIgnoreProperties(
        value = { "apiary", "crests", "unfoldings", "frames", "inspections", "transhumanceRequests" },
        allowSetters = true
    )
    private Hive hive;

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

    public Hive getHive() {
        return this.hive;
    }

    public void setHive(Hive hive) {
        this.hive = hive;
    }

    public Frame hive(Hive hive) {
        this.setHive(hive);
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
