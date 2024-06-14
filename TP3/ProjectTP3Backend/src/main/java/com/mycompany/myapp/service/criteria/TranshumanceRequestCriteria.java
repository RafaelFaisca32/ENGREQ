package com.mycompany.myapp.service.criteria;

import com.mycompany.myapp.domain.enumeration.TranshumanceRequestState;
import java.io.Serializable;
import java.util.Objects;
import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link com.mycompany.myapp.domain.TranshumanceRequest} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.TranshumanceRequestResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /transhumance-requests?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TranshumanceRequestCriteria implements Serializable, Criteria {

    /**
     * Class for filtering TranshumanceRequestState
     */
    public static class TranshumanceRequestStateFilter extends Filter<TranshumanceRequestState> {

        public TranshumanceRequestStateFilter() {}

        public TranshumanceRequestStateFilter(TranshumanceRequestStateFilter filter) {
            super(filter);
        }

        @Override
        public TranshumanceRequestStateFilter copy() {
            return new TranshumanceRequestStateFilter(this);
        }
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private ZonedDateTimeFilter requestDate;

    private TranshumanceRequestStateFilter state;

    private LongFilter beekeeperId;

    private LongFilter apiaryId;

    private LongFilter hiveId;

    private Boolean distinct;

    public TranshumanceRequestCriteria() {}

    public TranshumanceRequestCriteria(TranshumanceRequestCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.requestDate = other.requestDate == null ? null : other.requestDate.copy();
        this.state = other.state == null ? null : other.state.copy();
        this.beekeeperId = other.beekeeperId == null ? null : other.beekeeperId.copy();
        this.apiaryId = other.apiaryId == null ? null : other.apiaryId.copy();
        this.hiveId = other.hiveId == null ? null : other.hiveId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public TranshumanceRequestCriteria copy() {
        return new TranshumanceRequestCriteria(this);
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

    public ZonedDateTimeFilter getRequestDate() {
        return requestDate;
    }

    public ZonedDateTimeFilter requestDate() {
        if (requestDate == null) {
            requestDate = new ZonedDateTimeFilter();
        }
        return requestDate;
    }

    public void setRequestDate(ZonedDateTimeFilter requestDate) {
        this.requestDate = requestDate;
    }

    public TranshumanceRequestStateFilter getState() {
        return state;
    }

    public TranshumanceRequestStateFilter state() {
        if (state == null) {
            state = new TranshumanceRequestStateFilter();
        }
        return state;
    }

    public void setState(TranshumanceRequestStateFilter state) {
        this.state = state;
    }

    public LongFilter getBeekeeperId() {
        return beekeeperId;
    }

    public LongFilter beekeeperId() {
        if (beekeeperId == null) {
            beekeeperId = new LongFilter();
        }
        return beekeeperId;
    }

    public void setBeekeeperId(LongFilter beekeeperId) {
        this.beekeeperId = beekeeperId;
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
        final TranshumanceRequestCriteria that = (TranshumanceRequestCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(requestDate, that.requestDate) &&
            Objects.equals(state, that.state) &&
            Objects.equals(beekeeperId, that.beekeeperId) &&
            Objects.equals(apiaryId, that.apiaryId) &&
            Objects.equals(hiveId, that.hiveId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, requestDate, state, beekeeperId, apiaryId, hiveId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TranshumanceRequestCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (requestDate != null ? "requestDate=" + requestDate + ", " : "") +
            (state != null ? "state=" + state + ", " : "") +
            (beekeeperId != null ? "beekeeperId=" + beekeeperId + ", " : "") +
            (apiaryId != null ? "apiaryId=" + apiaryId + ", " : "") +
            (hiveId != null ? "hiveId=" + hiveId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
