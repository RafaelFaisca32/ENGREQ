package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mycompany.myapp.domain.enumeration.AnnualInventoyDeclarationState;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A AnnualInventoryDeclaration.
 */
@Entity
@Table(name = "annual_inventory_declaration")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AnnualInventoryDeclaration implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "total", nullable = false)
    private Integer total;

    @NotNull
    @Column(name = "beekeeper_fax_number", nullable = false)
    private Integer beekeeperFaxNumber;

    @NotNull
    @Column(name = "beekeeper_complete_name", nullable = false)
    private String beekeeperCompleteName;

    @NotNull
    @Column(name = "beekeeper_nif", nullable = false)
    private Integer beekeeperNif;

    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @NotNull
    @Column(name = "beekeeper_address", nullable = false)
    private String beekeeperAddress;

    @NotNull
    @Column(name = "beekeeper_postal_code", nullable = false)
    private String beekeeperPostalCode;

    @NotNull
    @Column(name = "beekeeper_phone_number", nullable = false)
    private Integer beekeeperPhoneNumber;

    @NotNull
    @Column(name = "beekeeper_entity_zone_residence", nullable = false)
    private String beekeeperEntityZoneResidence;

    @NotNull
    @Column(name = "beekeeper_number", nullable = false)
    private Integer beekeeperNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "annual_inventory_declaration_state")
    private AnnualInventoyDeclarationState annualInventoryDeclarationState;

    @Column(name = "revision_date")
    private LocalDate revisionDate;

    @Column(name = "revision_location")
    private String revisionLocation;

    @Column(name = "revisor_signature")
    private String revisorSignature;

    @Column(name = "revisor_name")
    private String revisorName;

    @OneToMany(mappedBy = "annualInventoryDeclaration")
    @JsonIgnoreProperties(value = { "annualInventoryDeclaration" }, allowSetters = true)
    private Set<ApiaryInformation> apiaryInformations = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public AnnualInventoryDeclaration id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTotal() {
        return this.total;
    }

    public AnnualInventoryDeclaration total(Integer total) {
        this.setTotal(total);
        return this;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getBeekeeperFaxNumber() {
        return this.beekeeperFaxNumber;
    }

    public AnnualInventoryDeclaration beekeeperFaxNumber(Integer beekeeperFaxNumber) {
        this.setBeekeeperFaxNumber(beekeeperFaxNumber);
        return this;
    }

    public void setBeekeeperFaxNumber(Integer beekeeperFaxNumber) {
        this.beekeeperFaxNumber = beekeeperFaxNumber;
    }

    public String getBeekeeperCompleteName() {
        return this.beekeeperCompleteName;
    }

    public AnnualInventoryDeclaration beekeeperCompleteName(String beekeeperCompleteName) {
        this.setBeekeeperCompleteName(beekeeperCompleteName);
        return this;
    }

    public void setBeekeeperCompleteName(String beekeeperCompleteName) {
        this.beekeeperCompleteName = beekeeperCompleteName;
    }

    public Integer getBeekeeperNif() {
        return this.beekeeperNif;
    }

    public AnnualInventoryDeclaration beekeeperNif(Integer beekeeperNif) {
        this.setBeekeeperNif(beekeeperNif);
        return this;
    }

    public void setBeekeeperNif(Integer beekeeperNif) {
        this.beekeeperNif = beekeeperNif;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public AnnualInventoryDeclaration date(LocalDate date) {
        this.setDate(date);
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getBeekeeperAddress() {
        return this.beekeeperAddress;
    }

    public AnnualInventoryDeclaration beekeeperAddress(String beekeeperAddress) {
        this.setBeekeeperAddress(beekeeperAddress);
        return this;
    }

    public void setBeekeeperAddress(String beekeeperAddress) {
        this.beekeeperAddress = beekeeperAddress;
    }

    public String getBeekeeperPostalCode() {
        return this.beekeeperPostalCode;
    }

    public AnnualInventoryDeclaration beekeeperPostalCode(String beekeeperPostalCode) {
        this.setBeekeeperPostalCode(beekeeperPostalCode);
        return this;
    }

    public void setBeekeeperPostalCode(String beekeeperPostalCode) {
        this.beekeeperPostalCode = beekeeperPostalCode;
    }

    public Integer getBeekeeperPhoneNumber() {
        return this.beekeeperPhoneNumber;
    }

    public AnnualInventoryDeclaration beekeeperPhoneNumber(Integer beekeeperPhoneNumber) {
        this.setBeekeeperPhoneNumber(beekeeperPhoneNumber);
        return this;
    }

    public void setBeekeeperPhoneNumber(Integer beekeeperPhoneNumber) {
        this.beekeeperPhoneNumber = beekeeperPhoneNumber;
    }

    public String getBeekeeperEntityZoneResidence() {
        return this.beekeeperEntityZoneResidence;
    }

    public AnnualInventoryDeclaration beekeeperEntityZoneResidence(String beekeeperEntityZoneResidence) {
        this.setBeekeeperEntityZoneResidence(beekeeperEntityZoneResidence);
        return this;
    }

    public void setBeekeeperEntityZoneResidence(String beekeeperEntityZoneResidence) {
        this.beekeeperEntityZoneResidence = beekeeperEntityZoneResidence;
    }

    public Integer getBeekeeperNumber() {
        return this.beekeeperNumber;
    }

    public AnnualInventoryDeclaration beekeeperNumber(Integer beekeeperNumber) {
        this.setBeekeeperNumber(beekeeperNumber);
        return this;
    }

    public void setBeekeeperNumber(Integer beekeeperNumber) {
        this.beekeeperNumber = beekeeperNumber;
    }

    public AnnualInventoyDeclarationState getAnnualInventoryDeclarationState() {
        return this.annualInventoryDeclarationState;
    }

    public AnnualInventoryDeclaration annualInventoryDeclarationState(AnnualInventoyDeclarationState annualInventoryDeclarationState) {
        this.setAnnualInventoryDeclarationState(annualInventoryDeclarationState);
        return this;
    }

    public void setAnnualInventoryDeclarationState(AnnualInventoyDeclarationState annualInventoryDeclarationState) {
        this.annualInventoryDeclarationState = annualInventoryDeclarationState;
    }

    public LocalDate getRevisionDate() {
        return this.revisionDate;
    }

    public AnnualInventoryDeclaration revisionDate(LocalDate revisionDate) {
        this.setRevisionDate(revisionDate);
        return this;
    }

    public void setRevisionDate(LocalDate revisionDate) {
        this.revisionDate = revisionDate;
    }

    public String getRevisionLocation() {
        return this.revisionLocation;
    }

    public AnnualInventoryDeclaration revisionLocation(String revisionLocation) {
        this.setRevisionLocation(revisionLocation);
        return this;
    }

    public void setRevisionLocation(String revisionLocation) {
        this.revisionLocation = revisionLocation;
    }

    public String getRevisorSignature() {
        return this.revisorSignature;
    }

    public AnnualInventoryDeclaration revisorSignature(String revisorSignature) {
        this.setRevisorSignature(revisorSignature);
        return this;
    }

    public void setRevisorSignature(String revisorSignature) {
        this.revisorSignature = revisorSignature;
    }

    public String getRevisorName() {
        return this.revisorName;
    }

    public AnnualInventoryDeclaration revisorName(String revisorName) {
        this.setRevisorName(revisorName);
        return this;
    }

    public void setRevisorName(String revisorName) {
        this.revisorName = revisorName;
    }

    public Set<ApiaryInformation> getApiaryInformations() {
        return this.apiaryInformations;
    }

    public void setApiaryInformations(Set<ApiaryInformation> apiaryInformations) {
        if (this.apiaryInformations != null) {
            this.apiaryInformations.forEach(i -> i.setAnnualInventoryDeclaration(null));
        }
        if (apiaryInformations != null) {
            apiaryInformations.forEach(i -> i.setAnnualInventoryDeclaration(this));
        }
        this.apiaryInformations = apiaryInformations;
    }

    public AnnualInventoryDeclaration apiaryInformations(Set<ApiaryInformation> apiaryInformations) {
        this.setApiaryInformations(apiaryInformations);
        return this;
    }

    public AnnualInventoryDeclaration addApiaryInformation(ApiaryInformation apiaryInformation) {
        this.apiaryInformations.add(apiaryInformation);
        apiaryInformation.setAnnualInventoryDeclaration(this);
        return this;
    }

    public AnnualInventoryDeclaration removeApiaryInformation(ApiaryInformation apiaryInformation) {
        this.apiaryInformations.remove(apiaryInformation);
        apiaryInformation.setAnnualInventoryDeclaration(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AnnualInventoryDeclaration)) {
            return false;
        }
        return id != null && id.equals(((AnnualInventoryDeclaration) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AnnualInventoryDeclaration{" +
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
