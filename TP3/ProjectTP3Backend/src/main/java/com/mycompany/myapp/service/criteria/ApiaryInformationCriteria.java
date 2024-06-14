package com.mycompany.myapp.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link com.mycompany.myapp.domain.ApiaryInformation} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.ApiaryInformationResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /apiary-informations?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ApiaryInformationCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter zoneNumber;

    private StringFilter zoneName;

    private IntegerFilter numberHives;

    private BooleanFilter intensive;

    private BooleanFilter transhumance;

    private StringFilter coordX;

    private StringFilter coordY;

    private StringFilter coordZ;

    private IntegerFilter numberFrames;

    private LongFilter annualInventoryDeclarationId;

    private Boolean distinct;

    public ApiaryInformationCriteria() {}

    public ApiaryInformationCriteria(ApiaryInformationCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.zoneNumber = other.zoneNumber == null ? null : other.zoneNumber.copy();
        this.zoneName = other.zoneName == null ? null : other.zoneName.copy();
        this.numberHives = other.numberHives == null ? null : other.numberHives.copy();
        this.intensive = other.intensive == null ? null : other.intensive.copy();
        this.transhumance = other.transhumance == null ? null : other.transhumance.copy();
        this.coordX = other.coordX == null ? null : other.coordX.copy();
        this.coordY = other.coordY == null ? null : other.coordY.copy();
        this.coordZ = other.coordZ == null ? null : other.coordZ.copy();
        this.numberFrames = other.numberFrames == null ? null : other.numberFrames.copy();
        this.annualInventoryDeclarationId = other.annualInventoryDeclarationId == null ? null : other.annualInventoryDeclarationId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public ApiaryInformationCriteria copy() {
        return new ApiaryInformationCriteria(this);
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

    public IntegerFilter getZoneNumber() {
        return zoneNumber;
    }

    public IntegerFilter zoneNumber() {
        if (zoneNumber == null) {
            zoneNumber = new IntegerFilter();
        }
        return zoneNumber;
    }

    public void setZoneNumber(IntegerFilter zoneNumber) {
        this.zoneNumber = zoneNumber;
    }

    public StringFilter getZoneName() {
        return zoneName;
    }

    public StringFilter zoneName() {
        if (zoneName == null) {
            zoneName = new StringFilter();
        }
        return zoneName;
    }

    public void setZoneName(StringFilter zoneName) {
        this.zoneName = zoneName;
    }

    public IntegerFilter getNumberHives() {
        return numberHives;
    }

    public IntegerFilter numberHives() {
        if (numberHives == null) {
            numberHives = new IntegerFilter();
        }
        return numberHives;
    }

    public void setNumberHives(IntegerFilter numberHives) {
        this.numberHives = numberHives;
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

    public IntegerFilter getNumberFrames() {
        return numberFrames;
    }

    public IntegerFilter numberFrames() {
        if (numberFrames == null) {
            numberFrames = new IntegerFilter();
        }
        return numberFrames;
    }

    public void setNumberFrames(IntegerFilter numberFrames) {
        this.numberFrames = numberFrames;
    }

    public LongFilter getAnnualInventoryDeclarationId() {
        return annualInventoryDeclarationId;
    }

    public LongFilter annualInventoryDeclarationId() {
        if (annualInventoryDeclarationId == null) {
            annualInventoryDeclarationId = new LongFilter();
        }
        return annualInventoryDeclarationId;
    }

    public void setAnnualInventoryDeclarationId(LongFilter annualInventoryDeclarationId) {
        this.annualInventoryDeclarationId = annualInventoryDeclarationId;
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
        final ApiaryInformationCriteria that = (ApiaryInformationCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(zoneNumber, that.zoneNumber) &&
            Objects.equals(zoneName, that.zoneName) &&
            Objects.equals(numberHives, that.numberHives) &&
            Objects.equals(intensive, that.intensive) &&
            Objects.equals(transhumance, that.transhumance) &&
            Objects.equals(coordX, that.coordX) &&
            Objects.equals(coordY, that.coordY) &&
            Objects.equals(coordZ, that.coordZ) &&
            Objects.equals(numberFrames, that.numberFrames) &&
            Objects.equals(annualInventoryDeclarationId, that.annualInventoryDeclarationId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            zoneNumber,
            zoneName,
            numberHives,
            intensive,
            transhumance,
            coordX,
            coordY,
            coordZ,
            numberFrames,
            annualInventoryDeclarationId,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ApiaryInformationCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (zoneNumber != null ? "zoneNumber=" + zoneNumber + ", " : "") +
            (zoneName != null ? "zoneName=" + zoneName + ", " : "") +
            (numberHives != null ? "numberHives=" + numberHives + ", " : "") +
            (intensive != null ? "intensive=" + intensive + ", " : "") +
            (transhumance != null ? "transhumance=" + transhumance + ", " : "") +
            (coordX != null ? "coordX=" + coordX + ", " : "") +
            (coordY != null ? "coordY=" + coordY + ", " : "") +
            (coordZ != null ? "coordZ=" + coordZ + ", " : "") +
            (numberFrames != null ? "numberFrames=" + numberFrames + ", " : "") +
            (annualInventoryDeclarationId != null ? "annualInventoryDeclarationId=" + annualInventoryDeclarationId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
