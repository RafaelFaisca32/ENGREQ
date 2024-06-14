package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Beekeeper} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class BeekeeperDTO implements Serializable {

    private Long id;

    private String beekeeperCompleteName;

    private Integer beekeeperNumber;

    private Integer entityZoneResidence;

    private Integer nif;

    private String address;

    private String postalCode;

    private Integer phoneNumber;

    private Integer faxNumber;

    private UserDTO user;

    private int userId;

    public Long getUserId() {
        return (long) userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBeekeeperCompleteName() {
        return beekeeperCompleteName;
    }

    public void setBeekeeperCompleteName(String beekeeperCompleteName) {
        this.beekeeperCompleteName = beekeeperCompleteName;
    }

    public Integer getBeekeeperNumber() {
        return beekeeperNumber;
    }

    public void setBeekeeperNumber(Integer beekeeperNumber) {
        this.beekeeperNumber = beekeeperNumber;
    }

    public Integer getEntityZoneResidence() {
        return entityZoneResidence;
    }

    public void setEntityZoneResidence(Integer entityZoneResidence) {
        this.entityZoneResidence = entityZoneResidence;
    }

    public Integer getNif() {
        return nif;
    }

    public void setNif(Integer nif) {
        this.nif = nif;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(Integer faxNumber) {
        this.faxNumber = faxNumber;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BeekeeperDTO)) {
            return false;
        }

        BeekeeperDTO beekeeperDTO = (BeekeeperDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, beekeeperDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BeekeeperDTO{" +
            "id=" + getId() +
            ", beekeeperCompleteName='" + getBeekeeperCompleteName() + "'" +
            ", beekeeperNumber=" + getBeekeeperNumber() +
            ", entityZoneResidence=" + getEntityZoneResidence() +
            ", nif=" + getNif() +
            ", address='" + getAddress() + "'" +
            ", postalCode='" + getPostalCode() + "'" +
            ", phoneNumber=" + getPhoneNumber() +
            ", faxNumber=" + getFaxNumber() +
            ", user=" + getUser() +
            "}";
    }
}
