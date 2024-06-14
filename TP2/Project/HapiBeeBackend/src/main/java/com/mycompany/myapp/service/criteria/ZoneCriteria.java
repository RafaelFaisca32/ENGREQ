package com.mycompany.myapp.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link com.mycompany.myapp.domain.Zone} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.ZoneResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /zones?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ZoneCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    private IntegerFilter number;

    private StringFilter coordX;

    private StringFilter coordY;

    private StringFilter coordZ;

    private BooleanFilter controlledZone;

    private LongFilter apiaryId;

    private Boolean distinct;

    public ZoneCriteria() {}

    public ZoneCriteria(ZoneCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.number = other.number == null ? null : other.number.copy();
        this.coordX = other.coordX == null ? null : other.coordX.copy();
        this.coordY = other.coordY == null ? null : other.coordY.copy();
        this.coordZ = other.coordZ == null ? null : other.coordZ.copy();
        this.controlledZone = other.controlledZone == null ? null : other.controlledZone.copy();
        this.apiaryId = other.apiaryId == null ? null : other.apiaryId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public ZoneCriteria copy() {
        return new ZoneCriteria(this);
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
        final ZoneCriteria that = (ZoneCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(number, that.number) &&
            Objects.equals(coordX, that.coordX) &&
            Objects.equals(coordY, that.coordY) &&
            Objects.equals(coordZ, that.coordZ) &&
            Objects.equals(controlledZone, that.controlledZone) &&
            Objects.equals(apiaryId, that.apiaryId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, number, coordX, coordY, coordZ, controlledZone, apiaryId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ZoneCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (name != null ? "name=" + name + ", " : "") +
            (number != null ? "number=" + number + ", " : "") +
            (coordX != null ? "coordX=" + coordX + ", " : "") +
            (coordY != null ? "coordY=" + coordY + ", " : "") +
            (coordZ != null ? "coordZ=" + coordZ + ", " : "") +
            (controlledZone != null ? "controlledZone=" + controlledZone + ", " : "") +
            (apiaryId != null ? "apiaryId=" + apiaryId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
