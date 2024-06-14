package com.mycompany.myapp.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link com.mycompany.myapp.domain.Unfolding} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.UnfoldingResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /unfoldings?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class UnfoldingCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter observations;

    private ZonedDateTimeFilter date;

    private LongFilter hiveId;

    private Boolean distinct;

    public UnfoldingCriteria() {}

    public UnfoldingCriteria(UnfoldingCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.observations = other.observations == null ? null : other.observations.copy();
        this.date = other.date == null ? null : other.date.copy();
        this.hiveId = other.hiveId == null ? null : other.hiveId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public UnfoldingCriteria copy() {
        return new UnfoldingCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public LongFilter id() {
        if (id == null) {
            id = new LongFilter();
        }
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getObservations() {
        return observations;
    }

    public StringFilter observations() {
        if (observations == null) {
            observations = new StringFilter();
        }
        return observations;
    }

    public void setObservations(StringFilter observations) {
        this.observations = observations;
    }

    public ZonedDateTimeFilter getDate() {
        return date;
    }

    public ZonedDateTimeFilter date() {
        if (date == null) {
            date = new ZonedDateTimeFilter();
        }
        return date;
    }

    public void setDate(ZonedDateTimeFilter date) {
        this.date = date;
    }

    public LongFilter getHiveId() {
        return hiveId;
    }

    public LongFilter hiveId() {
        if (hiveId == null) {
            hiveId = new LongFilter();
        }
        return hiveId;
    }

    public void setHiveId(LongFilter hiveId) {
        this.hiveId = hiveId;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final UnfoldingCriteria that = (UnfoldingCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(observations, that.observations) &&
            Objects.equals(date, that.date) &&
            Objects.equals(hiveId, that.hiveId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, observations, date, hiveId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UnfoldingCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (observations != null ? "observations=" + observations + ", " : "") +
            (date != null ? "date=" + date + ", " : "") +
            (hiveId != null ? "hiveId=" + hiveId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
