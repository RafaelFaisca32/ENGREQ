package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * A Beekeeper.
 */
@Entity
@Table(name = "beekeeper")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Beekeeper implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "beekeeper_complete_name")
    private String beekeeperCompleteName;

    @Column(name = "beekeeper_number")
    private Integer beekeeperNumber;

    @Column(name = "entity_zone_residence")
    private Integer entityZoneResidence;

    @Column(name = "nif")
    private Integer nif;

    @Column(name = "address")
    private String address;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "phone_number")
    private Integer phoneNumber;

    @Column(name = "fax_number")
    private Integer faxNumber;

    @OneToOne
    @JoinColumn(unique = true)
    private User user;

    @OneToMany(mappedBy = "beekeeper")
    @JsonIgnoreProperties(value = { "zone", "beekeeper", "hives", "transhumanceRequests" }, allowSetters = true)
    private Set<Apiary> apiaries = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Beekeeper id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBeekeeperCompleteName() {
        return this.beekeeperCompleteName;
    }

    public Beekeeper beekeeperCompleteName(String beekeeperCompleteName) {
        this.setBeekeeperCompleteName(beekeeperCompleteName);
        return this;
    }

    public void setBeekeeperCompleteName(String beekeeperCompleteName) {
        this.beekeeperCompleteName = beekeeperCompleteName;
    }

    public Integer getBeekeeperNumber() {
        return this.beekeeperNumber;
    }

    public Beekeeper beekeeperNumber(Integer beekeeperNumber) {
        this.setBeekeeperNumber(beekeeperNumber);
        return this;
    }

    public void setBeekeeperNumber(Integer beekeeperNumber) {
        this.beekeeperNumber = beekeeperNumber;
    }

    public Integer getEntityZoneResidence() {
        return this.entityZoneResidence;
    }

    public Beekeeper entityZoneResidence(Integer entityZoneResidence) {
        this.setEntityZoneResidence(entityZoneResidence);
        return this;
    }

    public void setEntityZoneResidence(Integer entityZoneResidence) {
        this.entityZoneResidence = entityZoneResidence;
    }

    public Integer getNif() {
        return this.nif;
    }

    public Beekeeper nif(Integer nif) {
        this.setNif(nif);
        return this;
    }

    public void setNif(Integer nif) {
        this.nif = nif;
    }

    public String getAddress() {
        return this.address;
    }

    public Beekeeper address(String address) {
        this.setAddress(address);
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public Beekeeper postalCode(String postalCode) {
        this.setPostalCode(postalCode);
        return this;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Integer getPhoneNumber() {
        return this.phoneNumber;
    }

    public Beekeeper phoneNumber(Integer phoneNumber) {
        this.setPhoneNumber(phoneNumber);
        return this;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getFaxNumber() {
        return this.faxNumber;
    }

    public Beekeeper faxNumber(Integer faxNumber) {
        this.setFaxNumber(faxNumber);
        return this;
    }

    public void setFaxNumber(Integer faxNumber) {
        this.faxNumber = faxNumber;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Beekeeper user(User user) {
        this.setUser(user);
        return this;
    }

    public Set<Apiary> getApiaries() {
        return this.apiaries;
    }

    public void setApiaries(Set<Apiary> apiaries) {
        if (this.apiaries != null) {
            this.apiaries.forEach(i -> i.setBeekeeper(null));
        }
        if (apiaries != null) {
            apiaries.forEach(i -> i.setBeekeeper(this));
        }
        this.apiaries = apiaries;
    }

    public Beekeeper apiaries(Set<Apiary> apiaries) {
        this.setApiaries(apiaries);
        return this;
    }

    public Beekeeper addApiary(Apiary apiary) {
        this.apiaries.add(apiary);
        apiary.setBeekeeper(this);
        return this;
    }

    public Beekeeper removeApiary(Apiary apiary) {
        this.apiaries.remove(apiary);
        apiary.setBeekeeper(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Beekeeper)) {
            return false;
        }
        return id != null && id.equals(((Beekeeper) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Beekeeper{" +
            "id=" + getId() +
            ", beekeeperCompleteName='" + getBeekeeperCompleteName() + "'" +
            ", beekeeperNumber=" + getBeekeeperNumber() +
            ", entityZoneResidence=" + getEntityZoneResidence() +
            ", nif=" + getNif() +
            ", address='" + getAddress() + "'" +
            ", postalCode='" + getPostalCode() + "'" +
            ", phoneNumber=" + getPhoneNumber() +
            ", faxNumber=" + getFaxNumber() +
            "}";
    }
}
