package com.mycompany.myapp.service.criteria;

import com.mycompany.myapp.domain.enumeration.ApiaryZoneState;
import java.io.Serializable;
import java.util.Objects;
import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link com.mycompany.myapp.domain.ApiaryZone} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.ApiaryZoneResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /apiary-zones?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ApiaryZoneCriteria implements Serializable, Criteria {

    /**
     * Class for filtering ApiaryZoneState
     */
    public static class ApiaryZoneStateFilter extends Filter<ApiaryZoneState> {

        public ApiaryZoneStateFilter() {}

        public ApiaryZoneStateFilter(ApiaryZoneStateFilter filter) {
            super(filter);
        }

        @Override
        public ApiaryZoneStateFilter copy() {
            return new ApiaryZoneStateFilter(this);
        }
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    private BooleanFilter controlledZone;

    private ApiaryZoneStateFilter state;

    private LongFilter apiaryId;

    private Boolean distinct;

    public ApiaryZoneCriteria() {}

    public ApiaryZoneCriteria(ApiaryZoneCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.controlledZone = other.controlledZone == null ? null : other.controlledZone.copy();
        this.state = other.state == null ? null : other.state.copy();
        this.apiaryId = other.apiaryId == null ? null : other.apiaryId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public ApiaryZoneCriteria copy() {
        return new ApiaryZoneCriteria(this);
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

    public StringFilter getName() {
        return name;
    }

    public StringFilter name() {
        if (name == null) {
            name = new StringFilter();
        }
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public BooleanFilter getControlledZone() {
        return controlledZone;
    }

    public BooleanFilter controlledZone() {
        if (controlledZone == null) {
            controlledZone = new BooleanFilter();
        }
        return controlledZone;
    }

    public void setControlledZone(BooleanFilter controlledZone) {
        this.controlledZone = controlledZone;
    }

    public ApiaryZoneStateFilter getState() {
        return state;
    }

    public ApiaryZoneStateFilter state() {
        if (state == null) {
            state = new ApiaryZoneStateFilter();
        }
        return state;
    }

    public void setState(ApiaryZoneStateFilter state) {
        this.state = state;
    }

    public LongFilter getApiaryId() {
        return apiaryId;
    }

    public LongFilter apiaryId() {
        if (apiaryId == null) {
            apiaryId = new LongFilter();
        }
        return apiaryId;
    }

    public void setApiaryId(LongFilter apiaryId) {
        this.apiaryId = apiaryId;
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
        final ApiaryZoneCriteria that = (ApiaryZoneCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(controlledZone, that.controlledZone) &&
            Objects.equals(state, that.state) &&
            Objects.equals(apiaryId, that.apiaryId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, controlledZone, state, apiaryId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ApiaryZoneCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (name != null ? "name=" + name + ", " : "") +
            (controlledZone != null ? "controlledZone=" + controlledZone + ", " : "") +
            (state != null ? "state=" + state + ", " : "") +
            (apiaryId != null ? "apiaryId=" + apiaryId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
