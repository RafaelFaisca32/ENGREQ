package com.mycompany.myapp.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link com.mycompany.myapp.domain.Frame} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.FrameResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /frames?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FrameCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter code;

    private LongFilter hiveId;

    private LongFilter crestId;

    private LongFilter unfoldingRemovedFramesOldHiveId;

    private LongFilter unfoldingInsertedFramesOldHiveId;

    private LongFilter unfoldingInsertedFramesNewHiveId;

    private Boolean distinct;

    public FrameCriteria() {}

    public FrameCriteria(FrameCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.code = other.code == null ? null : other.code.copy();
        this.hiveId = other.hiveId == null ? null : other.hiveId.copy();
        this.crestId = other.crestId == null ? null : other.crestId.copy();
        this.unfoldingRemovedFramesOldHiveId =
            other.unfoldingRemovedFramesOldHiveId == null ? null : other.unfoldingRemovedFramesOldHiveId.copy();
        this.unfoldingInsertedFramesOldHiveId =
            other.unfoldingInsertedFramesOldHiveId == null ? null : other.unfoldingInsertedFramesOldHiveId.copy();
        this.unfoldingInsertedFramesNewHiveId =
            other.unfoldingInsertedFramesNewHiveId == null ? null : other.unfoldingInsertedFramesNewHiveId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public FrameCriteria copy() {
        return new FrameCriteria(this);
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

    public LongFilter getUnfoldingRemovedFramesOldHiveId() {
        return unfoldingRemovedFramesOldHiveId;
    }

    public LongFilter unfoldingRemovedFramesOldHiveId() {
        if (unfoldingRemovedFramesOldHiveId == null) {
            unfoldingRemovedFramesOldHiveId = new LongFilter();
        }
        return unfoldingRemovedFramesOldHiveId;
    }

    public void setUnfoldingRemovedFramesOldHiveId(LongFilter unfoldingRemovedFramesOldHiveId) {
        this.unfoldingRemovedFramesOldHiveId = unfoldingRemovedFramesOldHiveId;
    }

    public LongFilter getUnfoldingInsertedFramesOldHiveId() {
        return unfoldingInsertedFramesOldHiveId;
    }

    public LongFilter unfoldingInsertedFramesOldHiveId() {
        if (unfoldingInsertedFramesOldHiveId == null) {
            unfoldingInsertedFramesOldHiveId = new LongFilter();
        }
        return unfoldingInsertedFramesOldHiveId;
    }

    public void setUnfoldingInsertedFramesOldHiveId(LongFilter unfoldingInsertedFramesOldHiveId) {
        this.unfoldingInsertedFramesOldHiveId = unfoldingInsertedFramesOldHiveId;
    }

    public LongFilter getUnfoldingInsertedFramesNewHiveId() {
        return unfoldingInsertedFramesNewHiveId;
    }

    public LongFilter unfoldingInsertedFramesNewHiveId() {
        if (unfoldingInsertedFramesNewHiveId == null) {
            unfoldingInsertedFramesNewHiveId = new LongFilter();
        }
        return unfoldingInsertedFramesNewHiveId;
    }

    public void setUnfoldingInsertedFramesNewHiveId(LongFilter unfoldingInsertedFramesNewHiveId) {
        this.unfoldingInsertedFramesNewHiveId = unfoldingInsertedFramesNewHiveId;
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
        final FrameCriteria that = (FrameCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(code, that.code) &&
            Objects.equals(hiveId, that.hiveId) &&
            Objects.equals(crestId, that.crestId) &&
            Objects.equals(unfoldingRemovedFramesOldHiveId, that.unfoldingRemovedFramesOldHiveId) &&
            Objects.equals(unfoldingInsertedFramesOldHiveId, that.unfoldingInsertedFramesOldHiveId) &&
            Objects.equals(unfoldingInsertedFramesNewHiveId, that.unfoldingInsertedFramesNewHiveId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            code,
            hiveId,
            crestId,
            unfoldingRemovedFramesOldHiveId,
            unfoldingInsertedFramesOldHiveId,
            unfoldingInsertedFramesNewHiveId,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FrameCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (code != null ? "code=" + code + ", " : "") +
            (hiveId != null ? "hiveId=" + hiveId + ", " : "") +
            (crestId != null ? "crestId=" + crestId + ", " : "") +
            (unfoldingRemovedFramesOldHiveId != null ? "unfoldingRemovedFramesOldHiveId=" + unfoldingRemovedFramesOldHiveId + ", " : "") +
            (unfoldingInsertedFramesOldHiveId != null ? "unfoldingInsertedFramesOldHiveId=" + unfoldingInsertedFramesOldHiveId + ", " : "") +
            (unfoldingInsertedFramesNewHiveId != null ? "unfoldingInsertedFramesNewHiveId=" + unfoldingInsertedFramesNewHiveId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
