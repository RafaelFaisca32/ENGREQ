package com.mycompany.myapp.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link com.mycompany.myapp.domain.Hive} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.HiveResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /hives?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class HiveCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter code;

    private LongFilter apiaryId;

    private LongFilter frameId;

    private LongFilter unfoldingCreatedHiveId;

    private LongFilter crestId;

    private LongFilter unfoldingId;

    private LongFilter inspectionId;

    private LongFilter transhumanceRequestId;

    private Boolean distinct;

    public HiveCriteria() {}

    public HiveCriteria(HiveCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.code = other.code == null ? null : other.code.copy();
        this.apiaryId = other.apiaryId == null ? null : other.apiaryId.copy();
        this.frameId = other.frameId == null ? null : other.frameId.copy();
        this.unfoldingCreatedHiveId = other.unfoldingCreatedHiveId == null ? null : other.unfoldingCreatedHiveId.copy();
        this.crestId = other.crestId == null ? null : other.crestId.copy();
        this.unfoldingId = other.unfoldingId == null ? null : other.unfoldingId.copy();
        this.inspectionId = other.inspectionId == null ? null : other.inspectionId.copy();
        this.transhumanceRequestId = other.transhumanceRequestId == null ? null : other.transhumanceRequestId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public HiveCriteria copy() {
        return new HiveCriteria(this);
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

    public StringFilter getCode() {
        return code;
    }

    public StringFilter code() {
        if (code == null) {
            code = new StringFilter();
        }
        return code;
    }

    public void setCode(StringFilter code) {
        this.code = code;
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

    public LongFilter getFrameId() {
        return frameId;
    }

    public LongFilter frameId() {
        if (frameId == null) {
            frameId = new LongFilter();
        }
        return frameId;
    }

    public void setFrameId(LongFilter frameId) {
        this.frameId = frameId;
    }

    public LongFilter getUnfoldingCreatedHiveId() {
        return unfoldingCreatedHiveId;
    }

    public LongFilter unfoldingCreatedHiveId() {
        if (unfoldingCreatedHiveId == null) {
            unfoldingCreatedHiveId = new LongFilter();
        }
        return unfoldingCreatedHiveId;
    }

    public void setUnfoldingCreatedHiveId(LongFilter unfoldingCreatedHiveId) {
        this.unfoldingCreatedHiveId = unfoldingCreatedHiveId;
    }

    public LongFilter getCrestId() {
        return crestId;
    }

    public LongFilter crestId() {
        if (crestId == null) {
            crestId = new LongFilter();
        }
        return crestId;
    }

    public void setCrestId(LongFilter crestId) {
        this.crestId = crestId;
    }

    public LongFilter getUnfoldingId() {
        return unfoldingId;
    }

    public LongFilter unfoldingId() {
        if (unfoldingId == null) {
            unfoldingId = new LongFilter();
        }
        return unfoldingId;
    }

    public void setUnfoldingId(LongFilter unfoldingId) {
        this.unfoldingId = unfoldingId;
    }

    public LongFilter getInspectionId() {
        return inspectionId;
    }

    public LongFilter inspectionId() {
        if (inspectionId == null) {
            inspectionId = new LongFilter();
        }
        return inspectionId;
    }

    public void setInspectionId(LongFilter inspectionId) {
        this.inspectionId = inspectionId;
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
        final HiveCriteria that = (HiveCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(code, that.code) &&
            Objects.equals(apiaryId, that.apiaryId) &&
            Objects.equals(frameId, that.frameId) &&
            Objects.equals(unfoldingCreatedHiveId, that.unfoldingCreatedHiveId) &&
            Objects.equals(crestId, that.crestId) &&
            Objects.equals(unfoldingId, that.unfoldingId) &&
            Objects.equals(inspectionId, that.inspectionId) &&
            Objects.equals(transhumanceRequestId, that.transhumanceRequestId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            code,
            apiaryId,
            frameId,
            unfoldingCreatedHiveId,
            crestId,
            unfoldingId,
            inspectionId,
            transhumanceRequestId,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "HiveCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (code != null ? "code=" + code + ", " : "") +
            (apiaryId != null ? "apiaryId=" + apiaryId + ", " : "") +
            (frameId != null ? "frameId=" + frameId + ", " : "") +
            (unfoldingCreatedHiveId != null ? "unfoldingCreatedHiveId=" + unfoldingCreatedHiveId + ", " : "") +
            (crestId != null ? "crestId=" + crestId + ", " : "") +
            (unfoldingId != null ? "unfoldingId=" + unfoldingId + ", " : "") +
            (inspectionId != null ? "inspectionId=" + inspectionId + ", " : "") +
            (transhumanceRequestId != null ? "transhumanceRequestId=" + transhumanceRequestId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
