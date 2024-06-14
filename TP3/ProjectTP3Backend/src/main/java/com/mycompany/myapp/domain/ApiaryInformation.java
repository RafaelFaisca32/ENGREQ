package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A ApiaryInformation.
 */
@Entity
@Table(name = "apiary_information")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ApiaryInformation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "zone_number", nullable = false)
    private Integer zoneNumber;

    @NotNull
    @Column(name = "zone_name", nullable = false)
    private String zoneName;

    @NotNull
    @Column(name = "number_hives", nullable = false)
    private Integer numberHives;

    @NotNull
    @Column(name = "intensive", nullable = false)
    private Boolean intensive;

    @NotNull
    @Column(name = "transhumance", nullable = false)
    private Boolean transhumance;

    @NotNull
    @Column(name = "coord_x", nullable = false)
    private String coordX;

    @NotNull
    @Column(name = "coord_y", nullable = false)
    private String coordY;

    @NotNull
    @Column(name = "coord_z", nullable = false)
    private String coordZ;

    @NotNull
    @Column(name = "number_frames", nullable = false)
    private Integer numberFrames;

    @ManyToOne
    @JsonIgnoreProperties(value = { "apiaryInformations" }, allowSetters = true)
    private AnnualInventoryDeclaration annualInventoryDeclaration;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ApiaryInformation id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getZoneNumber() {
        return this.zoneNumber;
    }

    public ApiaryInformation zoneNumber(Integer zoneNumber) {
        this.setZoneNumber(zoneNumber);
        return this;
    }

    public void setZoneNumber(Integer zoneNumber) {
        this.zoneNumber = zoneNumber;
    }

    public String getZoneName() {
        return this.zoneName;
    }

    public ApiaryInformation zoneName(String zoneName) {
        this.setZoneName(zoneName);
        return this;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public Integer getNumberHives() {
        return this.numberHives;
    }

    public ApiaryInformation numberHives(Integer numberHives) {
        this.setNumberHives(numberHives);
        return this;
    }

    public void setNumberHives(Integer numberHives) {
        this.numberHives = numberHives;
    }

    public Boolean getIntensive() {
        return this.intensive;
    }

    public ApiaryInformation intensive(Boolean intensive) {
        this.setIntensive(intensive);
        return this;
    }

    public void setIntensive(Boolean intensive) {
        this.intensive = intensive;
    }

    public Boolean getTranshumance() {
        return this.transhumance;
    }

    public ApiaryInformation transhumance(Boolean transhumance) {
        this.setTranshumance(transhumance);
        return this;
    }

    public void setTranshumance(Boolean transhumance) {
        this.transhumance = transhumance;
    }

    public String getCoordX() {
        return this.coordX;
    }

    public ApiaryInformation coordX(String coordX) {
        this.setCoordX(coordX);
        return this;
    }

    public void setCoordX(String coordX) {
        this.coordX = coordX;
    }

    public String getCoordY() {
        return this.coordY;
    }

    public ApiaryInformation coordY(String coordY) {
        this.setCoordY(coordY);
        return this;
    }

    public void setCoordY(String coordY) {
        this.coordY = coordY;
    }

    public String getCoordZ() {
        return this.coordZ;
    }

    public ApiaryInformation coordZ(String coordZ) {
        this.setCoordZ(coordZ);
        return this;
    }

    public void setCoordZ(String coordZ) {
        this.coordZ = coordZ;
    }

    public Integer getNumberFrames() {
        return this.numberFrames;
    }

    public ApiaryInformation numberFrames(Integer numberFrames) {
        this.setNumberFrames(numberFrames);
        return this;
    }

    public void setNumberFrames(Integer numberFrames) {
        this.numberFrames = numberFrames;
    }

    public AnnualInventoryDeclaration getAnnualInventoryDeclaration() {
        return this.annualInventoryDeclaration;
    }

    public void setAnnualInventoryDeclaration(AnnualInventoryDeclaration annualInventoryDeclaration) {
        this.annualInventoryDeclaration = annualInventoryDeclaration;
    }

    public ApiaryInformation annualInventoryDeclaration(AnnualInventoryDeclaration annualInventoryDeclaration) {
        this.setAnnualInventoryDeclaration(annualInventoryDeclaration);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ApiaryInformation)) {
            return false;
        }
        return id != null && id.equals(((ApiaryInformation) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ApiaryInformation{" +
            "id=" + getId() +
            ", zoneNumber=" + getZoneNumber() +
            ", zoneName='" + getZoneName() + "'" +
            ", numberHives=" + getNumberHives() +
            ", intensive='" + getIntensive() + "'" +
            ", transhumance='" + getTranshumance() + "'" +
            ", coordX='" + getCoordX() + "'" +
            ", coordY='" + getCoordY() + "'" +
            ", coordZ='" + getCoordZ() + "'" +
            ", numberFrames=" + getNumberFrames() +
            "}";
    }
}
