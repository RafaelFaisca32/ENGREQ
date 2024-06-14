package com.mycompany.myapp.service.criteria;

import com.mycompany.myapp.domain.enumeration.InspectionState;
import com.mycompany.myapp.domain.enumeration.InspectionType;
import java.io.Serializable;
import java.util.Objects;
import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link com.mycompany.myapp.domain.Inspection} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.InspectionResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /inspections?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class InspectionCriteria implements Serializable, Criteria {

    /**
     * Class for filtering InspectionType
     */
    public static class InspectionTypeFilter extends Filter<InspectionType> {

        public InspectionTypeFilter() {}

        public InspectionTypeFilter(InspectionTypeFilter filter) {
            super(filter);
        }

        @Override
        public InspectionTypeFilter copy() {
            return new InspectionTypeFilter(this);
        }
    }

    /**
     * Class for filtering InspectionState
     */
    public static class InspectionStateFilter extends Filter<InspectionState> {

        public InspectionStateFilter() {}

        public InspectionStateFilter(InspectionStateFilter filter) {
            super(filter);
        }

        @Override
        public InspectionStateFilter copy() {
            return new InspectionStateFilter(this);
        }
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LocalDateFilter date;

    private StringFilter note;

    private InspectionTypeFilter type;

    private InspectionStateFilter state;

    private LongFilter hiveId;

    private LongFilter diseaseId;

    private Boolean distinct;

    public InspectionCriteria() {}

    public InspectionCriteria(InspectionCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.date = other.date == null ? null : other.date.copy();
        this.note = other.note == null ? null : other.note.copy();
        this.type = other.type == null ? null : other.type.copy();
        this.state = other.state == null ? null : other.state.copy();
        this.hiveId = other.hiveId == null ? null : other.hiveId.copy();
        this.diseaseId = other.diseaseId == null ? null : other.diseaseId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public InspectionCriteria copy() {
        return new InspectionCriteria(this);
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

    public LocalDateFilter getDate() {
        return date;
    }

    public LocalDateFilter date() {
        if (date == null) {
            date = new LocalDateFilter();
        }
        return date;
    }

    public void setDate(LocalDateFilter date) {
        this.date = date;
    }

    public StringFilter getNote() {
        return note;
    }

    public StringFilter note() {
        if (note == null) {
            note = new StringFilter();
        }
        return note;
    }

    public void setNote(StringFilter note) {
        this.note = note;
    }

    public InspectionTypeFilter getType() {
        return type;
    }

    public InspectionTypeFilter type() {
        if (type == null) {
            type = new InspectionTypeFilter();
        }
        return type;
    }

    public void setType(InspectionTypeFilter type) {
        this.type = type;
    }

    public InspectionStateFilter getState() {
        return state;
    }

    public InspectionStateFilter state() {
        if (state == null) {
            state = new InspectionStateFilter();
        }
        return state;
    }

    public void setState(InspectionStateFilter state) {
        this.state = state;
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

    public LongFilter getDiseaseId() {
        return diseaseId;
    }

    public LongFilter diseaseId() {
        if (diseaseId == null) {
            diseaseId = new LongFilter();
        }
        return diseaseId;
    }

    public void setDiseaseId(LongFilter diseaseId) {
        this.diseaseId = diseaseId;
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
        final InspectionCriteria that = (InspectionCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(date, that.date) &&
            Objects.equals(note, that.note) &&
            Objects.equals(type, that.type) &&
            Objects.equals(state, that.state) &&
            Objects.equals(hiveId, that.hiveId) &&
            Objects.equals(diseaseId, that.diseaseId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, note, type, state, hiveId, diseaseId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InspectionCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (date != null ? "date=" + date + ", " : "") +
            (note != null ? "note=" + note + ", " : "") +
            (type != null ? "type=" + type + ", " : "") +
            (state != null ? "state=" + state + ", " : "") +
            (hiveId != null ? "hiveId=" + hiveId + ", " : "") +
            (diseaseId != null ? "diseaseId=" + diseaseId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}