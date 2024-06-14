package com.mycompany.myapp.service.criteria;

import com.mycompany.myapp.domain.enumeration.CrestState;
import java.io.Serializable;
import java.util.Objects;
import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link com.mycompany.myapp.domain.Crest} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.CrestResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /crests?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CrestCriteria implements Serializable, Criteria {

    /**
     * Class for filtering CrestState
     */
    public static class CrestStateFilter extends Filter<CrestState> {

        public CrestStateFilter() {}

        public CrestStateFilter(CrestStateFilter filter) {
            super(filter);
        }

        @Override
        public CrestStateFilter copy() {
            return new CrestStateFilter(this);
        }
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter combFrameQuantity;

    private FloatFilter waxWeight;

    private FloatFilter timeWastedCentrifuge;

    private ZonedDateTimeFilter initialDateDecantation;

    private ZonedDateTimeFilter finalDateDecantation;

    private FloatFilter producedHoneyQuantity;

    private CrestStateFilter state;

    private LongFilter hiveId;

    private Boolean distinct;

    public CrestCriteria() {}

    public CrestCriteria(CrestCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.combFrameQuantity = other.combFrameQuantity == null ? null : other.combFrameQuantity.copy();
        this.waxWeight = other.waxWeight == null ? null : other.waxWeight.copy();
        this.timeWastedCentrifuge = other.timeWastedCentrifuge == null ? null : other.timeWastedCentrifuge.copy();
        this.initialDateDecantation = other.initialDateDecantation == null ? null : other.initialDateDecantation.copy();
        this.finalDateDecantation = other.finalDateDecantation == null ? null : other.finalDateDecantation.copy();
        this.producedHoneyQuantity = other.producedHoneyQuantity == null ? null : other.producedHoneyQuantity.copy();
        this.state = other.state == null ? null : other.state.copy();
        this.hiveId = other.hiveId == null ? null : other.hiveId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public CrestCriteria copy() {
        return new CrestCriteria(this);
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

    public IntegerFilter getCombFrameQuantity() {
        return combFrameQuantity;
    }

    public IntegerFilter combFrameQuantity() {
        if (combFrameQuantity == null) {
            combFrameQuantity = new IntegerFilter();
        }
        return combFrameQuantity;
    }

    public void setCombFrameQuantity(IntegerFilter combFrameQuantity) {
        this.combFrameQuantity = combFrameQuantity;
    }

    public FloatFilter getWaxWeight() {
        return waxWeight;
    }

    public FloatFilter waxWeight() {
        if (waxWeight == null) {
            waxWeight = new FloatFilter();
        }
        return waxWeight;
    }

    public void setWaxWeight(FloatFilter waxWeight) {
        this.waxWeight = waxWeight;
    }

    public FloatFilter getTimeWastedCentrifuge() {
        return timeWastedCentrifuge;
    }

    public FloatFilter timeWastedCentrifuge() {
        if (timeWastedCentrifuge == null) {
            timeWastedCentrifuge = new FloatFilter();
        }
        return timeWastedCentrifuge;
    }

    public void setTimeWastedCentrifuge(FloatFilter timeWastedCentrifuge) {
        this.timeWastedCentrifuge = timeWastedCentrifuge;
    }

    public ZonedDateTimeFilter getInitialDateDecantation() {
        return initialDateDecantation;
    }

    public ZonedDateTimeFilter initialDateDecantation() {
        if (initialDateDecantation == null) {
            initialDateDecantation = new ZonedDateTimeFilter();
        }
        return initialDateDecantation;
    }

    public void setInitialDateDecantation(ZonedDateTimeFilter initialDateDecantation) {
        this.initialDateDecantation = initialDateDecantation;
    }

    public ZonedDateTimeFilter getFinalDateDecantation() {
        return finalDateDecantation;
    }

    public ZonedDateTimeFilter finalDateDecantation() {
        if (finalDateDecantation == null) {
            finalDateDecantation = new ZonedDateTimeFilter();
        }
        return finalDateDecantation;
    }

    public void setFinalDateDecantation(ZonedDateTimeFilter finalDateDecantation) {
        this.finalDateDecantation = finalDateDecantation;
    }

    public FloatFilter getProducedHoneyQuantity() {
        return producedHoneyQuantity;
    }

    public FloatFilter producedHoneyQuantity() {
        if (producedHoneyQuantity == null) {
            producedHoneyQuantity = new FloatFilter();
        }
        return producedHoneyQuantity;
    }

    public void setProducedHoneyQuantity(FloatFilter producedHoneyQuantity) {
        this.producedHoneyQuantity = producedHoneyQuantity;
    }

    public CrestStateFilter getState() {
        return state;
    }

    public CrestStateFilter state() {
        if (state == null) {
            state = new CrestStateFilter();
        }
        return state;
    }

    public void setState(CrestStateFilter state) {
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
        final CrestCriteria that = (CrestCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(combFrameQuantity, that.combFrameQuantity) &&
            Objects.equals(waxWeight, that.waxWeight) &&
            Objects.equals(timeWastedCentrifuge, that.timeWastedCentrifuge) &&
            Objects.equals(initialDateDecantation, that.initialDateDecantation) &&
            Objects.equals(finalDateDecantation, that.finalDateDecantation) &&
            Objects.equals(producedHoneyQuantity, that.producedHoneyQuantity) &&
            Objects.equals(state, that.state) &&
            Objects.equals(hiveId, that.hiveId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            combFrameQuantity,
            waxWeight,
            timeWastedCentrifuge,
            initialDateDecantation,
            finalDateDecantation,
            producedHoneyQuantity,
            state,
            hiveId,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CrestCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (combFrameQuantity != null ? "combFrameQuantity=" + combFrameQuantity + ", " : "") +
            (waxWeight != null ? "waxWeight=" + waxWeight + ", " : "") +
            (timeWastedCentrifuge != null ? "timeWastedCentrifuge=" + timeWastedCentrifuge + ", " : "") +
            (initialDateDecantation != null ? "initialDateDecantation=" + initialDateDecantation + ", " : "") +
            (finalDateDecantation != null ? "finalDateDecantation=" + finalDateDecantation + ", " : "") +
            (producedHoneyQuantity != null ? "producedHoneyQuantity=" + producedHoneyQuantity + ", " : "") +
            (state != null ? "state=" + state + ", " : "") +
            (hiveId != null ? "hiveId=" + hiveId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
