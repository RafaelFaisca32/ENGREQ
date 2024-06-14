package com.mycompany.myapp.service.criteria;

import com.mycompany.myapp.domain.enumeration.ApiaryState;
import java.io.Serializable;
import java.util.Objects;
import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link com.mycompany.myapp.domain.Apiary} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.ApiaryResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /apiaries?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ApiaryCriteria implements Serializable, Criteria {

    /**
     * Class for filtering ApiaryState
     */
    public static class ApiaryStateFilter extends Filter<ApiaryState> {

        public ApiaryStateFilter() {}

        public ApiaryStateFilter(ApiaryStateFilter filter) {
            super(filter);
        }

        @Override
        public ApiaryStateFilter copy() {
            return new ApiaryStateFilter(this);
        }
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    private ApiaryStateFilter state;

    private StringFilter coordX;

    private StringFilter coordY;

    private StringFilter coordZ;

    private IntegerFilter number;

    private BooleanFilter intensive;

    private BooleanFilter transhumance;

    private LongFilter beekeeperId;

    private LongFilter apiaryZoneId;

    private LongFilter hiveId;

    private LongFilter transhumanceRequestId;

    private Boolean distinct;

    public ApiaryCriteria() {}

    public ApiaryCriteria(ApiaryCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.state = other.state == null ? null : other.state.copy();
        this.coordX = other.coordX == null ? null : other.coordX.copy();
        this.coordY = other.coordY == null ? null : other.coordY.copy();
        this.coordZ = other.coordZ == null ? null : other.coordZ.copy();
        this.number = other.number == null ? null : other.number.copy();
        this.intensive = other.intensive == null ? null : other.intensive.copy();
        this.transhumance = other.transhumance == null ? null : other.transhumance.copy();
        this.beekeeperId = other.beekeeperId == null ? null : other.beekeeperId.copy();
        this.apiaryZoneId = other.apiaryZoneId == null ? null : other.apiaryZoneId.copy();
        this.hiveId = other.hiveId == null ? null : other.hiveId.copy();
        this.transhumanceRequestId = other.transhumanceRequestId == null ? null : other.transhumanceRequestId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public ApiaryCriteria copy() {
        return new ApiaryCriteria(this);
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

    public ApiaryStateFilter getState() {
        return state;
    }

    public ApiaryStateFilter state() {
        if (state == null) {
            state = new ApiaryStateFilter();
        }
        return state;
    }

    public void setState(ApiaryStateFilter state) {
        this.state = state;
    }

    public StringFilter getCoordX() {
        return coordX;
    }

    public StringFilter coordX() {
        if (coordX == null) {
            coordX = new StringFilter();
        }
        return coordX;
    }

    public void setCoordX(StringFilter coordX) {
        this.coordX = coordX;
    }

    public StringFilter getCoordY() {
        return coordY;
    }

    public StringFilter coordY() {
        if (coordY == null) {
            coordY = new StringFilter();
        }
        return coordY;
    }

    public void setCoordY(StringFilter coordY) {
        this.coordY = coordY;
    }

    public StringFilter getCoordZ() {
        return coordZ;
    }

    public StringFilter coordZ() {
        if (coordZ == null) {
            coordZ = new StringFilter();
        }
        return coordZ;
    }

    public void setCoordZ(StringFilter coordZ) {
        this.coordZ = coordZ;
    }

    public IntegerFilter getNumber() {
        return number;
    }

    public IntegerFilter number() {
        if (number == null) {
            number = new IntegerFilter();
        }
        return number;
    }

    public void setNumber(IntegerFilter number) {
        this.number = number;
    }

    public BooleanFilter getIntensive() {
        return intensive;
    }

    public BooleanFilter intensive() {
        if (intensive == null) {
            intensive = new BooleanFilter();
        }
        return intensive;
    }

    public void setIntensive(BooleanFilter intensive) {
        this.intensive = intensive;
    }

    public BooleanFilter getTranshumance() {
        return transhumance;
    }

    public BooleanFilter transhumance() {
        if (transhumance == null) {
            transhumance = new BooleanFilter();
        }
        return transhumance;
    }

    public void setTranshumance(BooleanFilter transhumance) {
        this.transhumance = transhumance;
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

    public LongFilter getApiaryZoneId() {
        return apiaryZoneId;
    }

    public LongFilter apiaryZoneId() {
        if (apiaryZoneId == null) {
            apiaryZoneId = new LongFilter();
        }
        return apiaryZoneId;
    }

    public void setApiaryZoneId(LongFilter apiaryZoneId) {
        this.apiaryZoneId = apiaryZoneId;
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

    public LongFilter getTranshumanceRequestId() {
        return transhumanceRequestId;
    }

    public LongFilter transhumanceRequestId() {
        if (transhumanceRequestId == null) {
            transhumanceRequestId = new LongFilter();
        }
        return transhumanceRequestId;
    }

    public void setTranshumanceRequestId(LongFilter transhumanceRequestId) {
        this.transhumanceRequestId = transhumanceRequestId;
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
        final ApiaryCriteria that = (ApiaryCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(state, that.state) &&
            Objects.equals(coordX, that.coordX) &&
            Objects.equals(coordY, that.coordY) &&
            Objects.equals(coordZ, that.coordZ) &&
            Objects.equals(number, that.number) &&
            Objects.equals(intensive, that.intensive) &&
            Objects.equals(transhumance, that.transhumance) &&
            Objects.equals(beekeeperId, that.beekeeperId) &&
            Objects.equals(apiaryZoneId, that.apiaryZoneId) &&
            Objects.equals(hiveId, that.hiveId) &&
            Objects.equals(transhumanceRequestId, that.transhumanceRequestId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            name,
            state,
            coordX,
            coordY,
            coordZ,
            number,
            intensive,
            transhumance,
            beekeeperId,
            apiaryZoneId,
            hiveId,
            transhumanceRequestId,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ApiaryCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (name != null ? "name=" + name + ", " : "") +
            (state != null ? "state=" + state + ", " : "") +
            (coordX != null ? "coordX=" + coordX + ", " : "") +
            (coordY != null ? "coordY=" + coordY + ", " : "") +
            (coordZ != null ? "coordZ=" + coordZ + ", " : "") +
            (number != null ? "number=" + number + ", " : "") +
            (intensive != null ? "intensive=" + intensive + ", " : "") +
            (transhumance != null ? "transhumance=" + transhumance + ", " : "") +
            (beekeeperId != null ? "beekeeperId=" + beekeeperId + ", " : "") +
            (apiaryZoneId != null ? "apiaryZoneId=" + apiaryZoneId + ", " : "") +
            (hiveId != null ? "hiveId=" + hiveId + ", " : "") +
            (transhumanceRequestId != null ? "transhumanceRequestId=" + transhumanceRequestId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
