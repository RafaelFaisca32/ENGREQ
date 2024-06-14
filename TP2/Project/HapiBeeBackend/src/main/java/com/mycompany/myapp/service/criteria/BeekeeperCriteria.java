package com.mycompany.myapp.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link com.mycompany.myapp.domain.Beekeeper} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.BeekeeperResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /beekeepers?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class BeekeeperCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter beekeeperCompleteName;

    private IntegerFilter beekeeperNumber;

    private IntegerFilter entityZoneResidence;

    private IntegerFilter nif;

    private StringFilter address;

    private StringFilter postalCode;

    private IntegerFilter phoneNumber;

    private IntegerFilter faxNumber;

    private LongFilter userId;

    private LongFilter apiaryId;

    private Boolean distinct;

    public BeekeeperCriteria() {}

    public BeekeeperCriteria(BeekeeperCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.beekeeperCompleteName = other.beekeeperCompleteName == null ? null : other.beekeeperCompleteName.copy();
        this.beekeeperNumber = other.beekeeperNumber == null ? null : other.beekeeperNumber.copy();
        this.entityZoneResidence = other.entityZoneResidence == null ? null : other.entityZoneResidence.copy();
        this.nif = other.nif == null ? null : other.nif.copy();
        this.address = other.address == null ? null : other.address.copy();
        this.postalCode = other.postalCode == null ? null : other.postalCode.copy();
        this.phoneNumber = other.phoneNumber == null ? null : other.phoneNumber.copy();
        this.faxNumber = other.faxNumber == null ? null : other.faxNumber.copy();
        this.userId = other.userId == null ? null : other.userId.copy();
        this.apiaryId = other.apiaryId == null ? null : other.apiaryId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public BeekeeperCriteria copy() {
        return new BeekeeperCriteria(this);
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

    public IntegerFilter getEntityZoneResidence() {
        return entityZoneResidence;
    }

    public IntegerFilter entityZoneResidence() {
        if (entityZoneResidence == null) {
            entityZoneResidence = new IntegerFilter();
        }
        return entityZoneResidence;
    }

    public void setEntityZoneResidence(IntegerFilter entityZoneResidence) {
        this.entityZoneResidence = entityZoneResidence;
    }

    public IntegerFilter getNif() {
        return nif;
    }

    public IntegerFilter nif() {
        if (nif == null) {
            nif = new IntegerFilter();
        }
        return nif;
    }

    public void setNif(IntegerFilter nif) {
        this.nif = nif;
    }

    public StringFilter getAddress() {
        return address;
    }

    public StringFilter address() {
        if (address == null) {
            address = new StringFilter();
        }
        return address;
    }

    public void setAddress(StringFilter address) {
        this.address = address;
    }

    public StringFilter getPostalCode() {
        return postalCode;
    }

    public StringFilter postalCode() {
        if (postalCode == null) {
            postalCode = new StringFilter();
        }
        return postalCode;
    }

    public void setPostalCode(StringFilter postalCode) {
        this.postalCode = postalCode;
    }

    public IntegerFilter getPhoneNumber() {
        return phoneNumber;
    }

    public IntegerFilter phoneNumber() {
        if (phoneNumber == null) {
            phoneNumber = new IntegerFilter();
        }
        return phoneNumber;
    }

    public void setPhoneNumber(IntegerFilter phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public IntegerFilter getFaxNumber() {
        return faxNumber;
    }

    public IntegerFilter faxNumber() {
        if (faxNumber == null) {
            faxNumber = new IntegerFilter();
        }
        return faxNumber;
    }

    public void setFaxNumber(IntegerFilter faxNumber) {
        this.faxNumber = faxNumber;
    }

    public LongFilter getUserId() {
        return userId;
    }

    public LongFilter userId() {
        if (userId == null) {
            userId = new LongFilter();
        }
        return userId;
    }

    public void setUserId(LongFilter userId) {
        this.userId = userId;
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
        final BeekeeperCriteria that = (BeekeeperCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(beekeeperCompleteName, that.beekeeperCompleteName) &&
            Objects.equals(beekeeperNumber, that.beekeeperNumber) &&
            Objects.equals(entityZoneResidence, that.entityZoneResidence) &&
            Objects.equals(nif, that.nif) &&
            Objects.equals(address, that.address) &&
            Objects.equals(postalCode, that.postalCode) &&
            Objects.equals(phoneNumber, that.phoneNumber) &&
            Objects.equals(faxNumber, that.faxNumber) &&
            Objects.equals(userId, that.userId) &&
            Objects.equals(apiaryId, that.apiaryId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            beekeeperCompleteName,
            beekeeperNumber,
            entityZoneResidence,
            nif,
            address,
            postalCode,
            phoneNumber,
            faxNumber,
            userId,
            apiaryId,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BeekeeperCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (beekeeperCompleteName != null ? "beekeeperCompleteName=" + beekeeperCompleteName + ", " : "") +
            (beekeeperNumber != null ? "beekeeperNumber=" + beekeeperNumber + ", " : "") +
            (entityZoneResidence != null ? "entityZoneResidence=" + entityZoneResidence + ", " : "") +
            (nif != null ? "nif=" + nif + ", " : "") +
            (address != null ? "address=" + address + ", " : "") +
            (postalCode != null ? "postalCode=" + postalCode + ", " : "") +
            (phoneNumber != null ? "phoneNumber=" + phoneNumber + ", " : "") +
            (faxNumber != null ? "faxNumber=" + faxNumber + ", " : "") +
            (userId != null ? "userId=" + userId + ", " : "") +
            (apiaryId != null ? "apiaryId=" + apiaryId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
