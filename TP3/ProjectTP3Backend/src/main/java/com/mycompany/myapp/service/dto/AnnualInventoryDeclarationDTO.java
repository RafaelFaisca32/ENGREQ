package com.mycompany.myapp.service.dto;

import com.mycompany.myapp.domain.enumeration.AnnualInventoryDeclarationState;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.AnnualInventoryDeclaration} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AnnualInventoryDeclarationDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer total;

    @NotNull
    private Integer beekeeperFaxNumber;

    @NotNull
    private String beekeeperCompleteName;

    @NotNull
    private Integer beekeeperNif;

    @NotNull
    private LocalDate date;

    @NotNull
    private String beekeeperAddress;

    @NotNull
    private String beekeeperPostalCode;

    @NotNull
    private Integer beekeeperPhoneNumber;

    @NotNull
    private String beekeeperEntityZoneResidence;

    @NotNull
    private Integer beekeeperNumber;

    private AnnualInventoryDeclarationState annualInventoryDeclarationState;

    private LocalDate revisionDate;

    private String revisionLocation;

    private String revisorSignature;

    private String revisorName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getBeekeeperFaxNumber() {
        return beekeeperFaxNumber;
    }

    public void setBeekeeperFaxNumber(Integer beekeeperFaxNumber) {
        this.beekeeperFaxNumber = beekeeperFaxNumber;
    }

    public String getBeekeeperCompleteName() {
        return beekeeperCompleteName;
    }

    public void setBeekeeperCompleteName(String beekeeperCompleteName) {
        this.beekeeperCompleteName = beekeeperCompleteName;
    }

    public Integer getBeekeeperNif() {
        return beekeeperNif;
    }

    public void setBeekeeperNif(Integer beekeeperNif) {
        this.beekeeperNif = beekeeperNif;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getBeekeeperAddress() {
        return beekeeperAddress;
    }

    public void setBeekeeperAddress(String beekeeperAddress) {
        this.beekeeperAddress = beekeeperAddress;
    }

    public String getBeekeeperPostalCode() {
        return beekeeperPostalCode;
    }

    public void setBeekeeperPostalCode(String beekeeperPostalCode) {
        this.beekeeperPostalCode = beekeeperPostalCode;
    }

    public Integer getBeekeeperPhoneNumber() {
        return beekeeperPhoneNumber;
    }

    public void setBeekeeperPhoneNumber(Integer beekeeperPhoneNumber) {
        this.beekeeperPhoneNumber = beekeeperPhoneNumber;
    }

    public String getBeekeeperEntityZoneResidence() {
        return beekeeperEntityZoneResidence;
    }

    public void setBeekeeperEntityZoneResidence(String beekeeperEntityZoneResidence) {
        this.beekeeperEntityZoneResidence = beekeeperEntityZoneResidence;
    }

    public Integer getBeekeeperNumber() {
        return beekeeperNumber;
    }

    public void setBeekeeperNumber(Integer beekeeperNumber) {
        this.beekeeperNumber = beekeeperNumber;
    }

    public AnnualInventoryDeclarationState getAnnualInventoryDeclarationState() {
        return annualInventoryDeclarationState;
    }

    public void setAnnualInventoryDeclarationState(AnnualInventoryDeclarationState annualInventoryDeclarationState) {
        this.annualInventoryDeclarationState = annualInventoryDeclarationState;
    }

    public LocalDate getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(LocalDate revisionDate) {
        this.revisionDate = revisionDate;
    }

    public String getRevisionLocation() {
        return revisionLocation;
    }

    public void setRevisionLocation(String revisionLocation) {
        this.revisionLocation = revisionLocation;
    }

    public String getRevisorSignature() {
        return revisorSignature;
    }

    public void setRevisorSignature(String revisorSignature) {
        this.revisorSignature = revisorSignature;
    }

    public String getRevisorName() {
        return revisorName;
    }

    public void setRevisorName(String revisorName) {
        this.revisorName = revisorName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AnnualInventoryDeclarationDTO)) {
            return false;
        }

        AnnualInventoryDeclarationDTO annualInventoryDeclarationDTO = (AnnualInventoryDeclarationDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, annualInventoryDeclarationDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AnnualInventoryDeclarationDTO{" +
            "id=" + getId() +
            ", total=" + getTotal() +
            ", beekeeperFaxNumber=" + getBeekeeperFaxNumber() +
            ", beekeeperCompleteName='" + getBeekeeperCompleteName() + "'" +
            ", beekeeperNif=" + getBeekeeperNif() +
            ", date='" + getDate() + "'" +
            ", beekeeperAddress='" + getBeekeeperAddress() + "'" +
            ", beekeeperPostalCode='" + getBeekeeperPostalCode() + "'" +
            ", beekeeperPhoneNumber=" + getBeekeeperPhoneNumber() +
            ", beekeeperEntityZoneResidence='" + getBeekeeperEntityZoneResidence() + "'" +
            ", beekeeperNumber=" + getBeekeeperNumber() +
            ", annualInventoryDeclarationState='" + getAnnualInventoryDeclarationState() + "'" +
            ", revisionDate='" + getRevisionDate() + "'" +
            ", revisionLocation='" + getRevisionLocation() + "'" +
            ", revisorSignature='" + getRevisorSignature() + "'" +
            ", revisorName='" + getRevisorName() + "'" +
            "}";
    }
}
