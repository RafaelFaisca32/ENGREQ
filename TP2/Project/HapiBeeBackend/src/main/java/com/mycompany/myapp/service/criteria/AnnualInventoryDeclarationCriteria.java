package com.mycompany.myapp.service.criteria;

import com.mycompany.myapp.domain.enumeration.AnnualInventoyDeclarationState;
import java.io.Serializable;
import java.util.Objects;
import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link com.mycompany.myapp.domain.AnnualInventoryDeclaration} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.AnnualInventoryDeclarationResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /annual-inventory-declarations?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AnnualInventoryDeclarationCriteria implements Serializable, Criteria {

    /**
     * Class for filtering AnnualInventoyDeclarationState
     */
    public static class AnnualInventoyDeclarationStateFilter extends Filter<AnnualInventoyDeclarationState> {

        public AnnualInventoyDeclarationStateFilter() {}

        public AnnualInventoyDeclarationStateFilter(AnnualInventoyDeclarationStateFilter filter) {
            super(filter);
        }

        @Override
        public AnnualInventoyDeclarationStateFilter copy() {
            return new AnnualInventoyDeclarationStateFilter(this);
        }
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter total;

    private IntegerFilter beekeeperFaxNumber;

    private StringFilter beekeeperCompleteName;

    private IntegerFilter beekeeperNif;

    private LocalDateFilter date;

    private StringFilter beekeeperAddress;

    private StringFilter beekeeperPostalCode;

    private IntegerFilter beekeeperPhoneNumber;

    private StringFilter beekeeperEntityZoneResidence;

    private IntegerFilter beekeeperNumber;

    private AnnualInventoyDeclarationStateFilter annualInventoryDeclarationState;

    private LocalDateFilter revisionDate;

    private StringFilter revisionLocation;

    private StringFilter revisorSignature;

    private StringFilter revisorName;

    private LongFilter apiaryInformationId;

    private Boolean distinct;

    public AnnualInventoryDeclarationCriteria() {}

    public AnnualInventoryDeclarationCriteria(AnnualInventoryDeclarationCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.total = other.total == null ? null : other.total.copy();
        this.beekeeperFaxNumber = other.beekeeperFaxNumber == null ? null : other.beekeeperFaxNumber.copy();
        this.beekeeperCompleteName = other.beekeeperCompleteName == null ? null : other.beekeeperCompleteName.copy();
        this.beekeeperNif = other.beekeeperNif == null ? null : other.beekeeperNif.copy();
        this.date = other.date == null ? null : other.date.copy();
        this.beekeeperAddress = other.beekeeperAddress == null ? null : other.beekeeperAddress.copy();
        this.beekeeperPostalCode = other.beekeeperPostalCode == null ? null : other.beekeeperPostalCode.copy();
        this.beekeeperPhoneNumber = other.beekeeperPhoneNumber == null ? null : other.beekeeperPhoneNumber.copy();
        this.beekeeperEntityZoneResidence = other.beekeeperEntityZoneResidence == null ? null : other.beekeeperEntityZoneResidence.copy();
        this.beekeeperNumber = other.beekeeperNumber == null ? null : other.beekeeperNumber.copy();
        this.annualInventoryDeclarationState =
            other.annualInventoryDeclarationState == null ? null : other.annualInventoryDeclarationState.copy();
        this.revisionDate = other.revisionDate == null ? null : other.revisionDate.copy();
        this.revisionLocation = other.revisionLocation == null ? null : other.revisionLocation.copy();
        this.revisorSignature = other.revisorSignature == null ? null : other.revisorSignature.copy();
        this.revisorName = other.revisorName == null ? null : other.revisorName.copy();
        this.apiaryInformationId = other.apiaryInformationId == null ? null : other.apiaryInformationId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public AnnualInventoryDeclarationCriteria copy() {
        return new AnnualInventoryDeclarationCriteria(this);
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

    public IntegerFilter getTotal() {
        return total;
    }

    public IntegerFilter total() {
        if (total == null) {
            total = new IntegerFilter();
        }
        return total;
    }

    public void setTotal(IntegerFilter total) {
        this.total = total;
    }

    public IntegerFilter getBeekeeperFaxNumber() {
        return beekeeperFaxNumber;
    }

    public IntegerFilter beekeeperFaxNumber() {
        if (beekeeperFaxNumber == null) {
            beekeeperFaxNumber = new IntegerFilter();
        }
        return beekeeperFaxNumber;
    }

    public void setBeekeeperFaxNumber(IntegerFilter beekeeperFaxNumber) {
        this.beekeeperFaxNumber = beekeeperFaxNumber;
    }

    public StringFilter getBeekeeperCompleteName() {
        return beekeeperCompleteName;
    }

    public StringFilter beekeeperCompleteName() {
        if (beekeeperCompleteName == null) {
            beekeeperCompleteName = new StringFilter();
        }
        return beekeeperCompleteName;
    }

    public void setBeekeeperCompleteName(StringFilter beekeeperCompleteName) {
        this.beekeeperCompleteName = beekeeperCompleteName;
    }

    public IntegerFilter getBeekeeperNif() {
        return beekeeperNif;
    }

    public IntegerFilter beekeeperNif() {
        if (beekeeperNif == null) {
            beekeeperNif = new IntegerFilter();
        }
        return beekeeperNif;
    }

    public void setBeekeeperNif(IntegerFilter beekeeperNif) {
        this.beekeeperNif = beekeeperNif;
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

    public StringFilter getBeekeeperAddress() {
        return beekeeperAddress;
    }

    public StringFilter beekeeperAddress() {
        if (beekeeperAddress == null) {
            beekeeperAddress = new StringFilter();
        }
        return beekeeperAddress;
    }

    public void setBeekeeperAddress(StringFilter beekeeperAddress) {
        this.beekeeperAddress = beekeeperAddress;
    }

    public StringFilter getBeekeeperPostalCode() {
        return beekeeperPostalCode;
    }

    public StringFilter beekeeperPostalCode() {
        if (beekeeperPostalCode == null) {
            beekeeperPostalCode = new StringFilter();
        }
        return beekeeperPostalCode;
    }

    public void setBeekeeperPostalCode(StringFilter beekeeperPostalCode) {
        this.beekeeperPostalCode = beekeeperPostalCode;
    }

    public IntegerFilter getBeekeeperPhoneNumber() {
        return beekeeperPhoneNumber;
    }

    public IntegerFilter beekeeperPhoneNumber() {
        if (beekeeperPhoneNumber == null) {
            beekeeperPhoneNumber = new IntegerFilter();
        }
        return beekeeperPhoneNumber;
    }

    public void setBeekeeperPhoneNumber(IntegerFilter beekeeperPhoneNumber) {
        this.beekeeperPhoneNumber = beekeeperPhoneNumber;
    }

    public StringFilter getBeekeeperEntityZoneResidence() {
        return beekeeperEntityZoneResidence;
    }

    public StringFilter beekeeperEntityZoneResidence() {
        if (beekeeperEntityZoneResidence == null) {
            beekeeperEntityZoneResidence = new StringFilter();
        }
        return beekeeperEntityZoneResidence;
    }

    public void setBeekeeperEntityZoneResidence(StringFilter beekeeperEntityZoneResidence) {
        this.beekeeperEntityZoneResidence = beekeeperEntityZoneResidence;
    }

    public IntegerFilter getBeekeeperNumber() {
        return beekeeperNumber;
    }

    public IntegerFilter beekeeperNumber() {
        if (beekeeperNumber == null) {
            beekeeperNumber = new IntegerFilter();
        }
        return beekeeperNumber;
    }

    public void setBeekeeperNumber(IntegerFilter beekeeperNumber) {
        this.beekeeperNumber = beekeeperNumber;
    }

    public AnnualInventoyDeclarationStateFilter getAnnualInventoryDeclarationState() {
        return annualInventoryDeclarationState;
    }

    public AnnualInventoyDeclarationStateFilter annualInventoryDeclarationState() {
        if (annualInventoryDeclarationState == null) {
            annualInventoryDeclarationState = new AnnualInventoyDeclarationStateFilter();
        }
        return annualInventoryDeclarationState;
    }

    public void setAnnualInventoryDeclarationState(AnnualInventoyDeclarationStateFilter annualInventoryDeclarationState) {
        this.annualInventoryDeclarationState = annualInventoryDeclarationState;
    }

    public LocalDateFilter getRevisionDate() {
        return revisionDate;
    }

    public LocalDateFilter revisionDate() {
        if (revisionDate == null) {
            revisionDate = new LocalDateFilter();
        }
        return revisionDate;
    }

    public void setRevisionDate(LocalDateFilter revisionDate) {
        this.revisionDate = revisionDate;
    }

    public StringFilter getRevisionLocation() {
        return revisionLocation;
    }

    public StringFilter revisionLocation() {
        if (revisionLocation == null) {
            revisionLocation = new StringFilter();
        }
        return revisionLocation;
    }

    public void setRevisionLocation(StringFilter revisionLocation) {
        this.revisionLocation = revisionLocation;
    }

    public StringFilter getRevisorSignature() {
        return revisorSignature;
    }

    public StringFilter revisorSignature() {
        if (revisorSignature == null) {
            revisorSignature = new StringFilter();
        }
        return revisorSignature;
    }

    public void setRevisorSignature(StringFilter revisorSignature) {
        this.revisorSignature = revisorSignature;
    }

    public StringFilter getRevisorName() {
        return revisorName;
    }

    public StringFilter revisorName() {
        if (revisorName == null) {
            revisorName = new StringFilter();
        }
        return revisorName;
    }

    public void setRevisorName(StringFilter revisorName) {
        this.revisorName = revisorName;
    }

    public LongFilter getApiaryInformationId() {
        return apiaryInformationId;
    }

    public LongFilter apiaryInformationId() {
        if (apiaryInformationId == null) {
            apiaryInformationId = new LongFilter();
        }
        return apiaryInformationId;
    }

    public void setApiaryInformationId(LongFilter apiaryInformationId) {
        this.apiaryInformationId = apiaryInformationId;
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
        final AnnualInventoryDeclarationCriteria that = (AnnualInventoryDeclarationCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(total, that.total) &&
            Objects.equals(beekeeperFaxNumber, that.beekeeperFaxNumber) &&
            Objects.equals(beekeeperCompleteName, that.beekeeperCompleteName) &&
            Objects.equals(beekeeperNif, that.beekeeperNif) &&
            Objects.equals(date, that.date) &&
            Objects.equals(beekeeperAddress, that.beekeeperAddress) &&
            Objects.equals(beekeeperPostalCode, that.beekeeperPostalCode) &&
            Objects.equals(beekeeperPhoneNumber, that.beekeeperPhoneNumber) &&
            Objects.equals(beekeeperEntityZoneResidence, that.beekeeperEntityZoneResidence) &&
            Objects.equals(beekeeperNumber, that.beekeeperNumber) &&
            Objects.equals(annualInventoryDeclarationState, that.annualInventoryDeclarationState) &&
            Objects.equals(revisionDate, that.revisionDate) &&
            Objects.equals(revisionLocation, that.revisionLocation) &&
            Objects.equals(revisorSignature, that.revisorSignature) &&
            Objects.equals(revisorName, that.revisorName) &&
            Objects.equals(apiaryInformationId, that.apiaryInformationId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            total,
            beekeeperFaxNumber,
            beekeeperCompleteName,
            beekeeperNif,
            date,
            beekeeperAddress,
            beekeeperPostalCode,
            beekeeperPhoneNumber,
            beekeeperEntityZoneResidence,
            beekeeperNumber,
            annualInventoryDeclarationState,
            revisionDate,
            revisionLocation,
            revisorSignature,
            revisorName,
            apiaryInformationId,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AnnualInventoryDeclarationCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (total != null ? "total=" + total + ", " : "") +
            (beekeeperFaxNumber != null ? "beekeeperFaxNumber=" + beekeeperFaxNumber + ", " : "") +
            (beekeeperCompleteName != null ? "beekeeperCompleteName=" + beekeeperCompleteName + ", " : "") +
            (beekeeperNif != null ? "beekeeperNif=" + beekeeperNif + ", " : "") +
            (date != null ? "date=" + date + ", " : "") +
            (beekeeperAddress != null ? "beekeeperAddress=" + beekeeperAddress + ", " : "") +
            (beekeeperPostalCode != null ? "beekeeperPostalCode=" + beekeeperPostalCode + ", " : "") +
            (beekeeperPhoneNumber != null ? "beekeeperPhoneNumber=" + beekeeperPhoneNumber + ", " : "") +
            (beekeeperEntityZoneResidence != null ? "beekeeperEntityZoneResidence=" + beekeeperEntityZoneResidence + ", " : "") +
            (beekeeperNumber != null ? "beekeeperNumber=" + beekeeperNumber + ", " : "") +
            (annualInventoryDeclarationState != null ? "annualInventoryDeclarationState=" + annualInventoryDeclarationState + ", " : "") +
            (revisionDate != null ? "revisionDate=" + revisionDate + ", " : "") +
            (revisionLocation != null ? "revisionLocation=" + revisionLocation + ", " : "") +
            (revisorSignature != null ? "revisorSignature=" + revisorSignature + ", " : "") +
            (revisorName != null ? "revisorName=" + revisorName + ", " : "") +
            (apiaryInformationId != null ? "apiaryInformationId=" + apiaryInformationId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
