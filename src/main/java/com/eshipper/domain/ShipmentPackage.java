package com.eshipper.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A ShipmentPackage.
 */
@Entity
@Table(name = "shipment_package")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ShipmentPackage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    @Column(name = "description", length = 255)
    private String description;

    @Max(value = 11)
    @Column(name = "length")
    private Integer length;

    @Max(value = 11)
    @Column(name = "width")
    private Integer width;

    @Max(value = 11)
    @Column(name = "height")
    private Integer height;

    @Max(value = 11)
    @Column(name = "weight")
    private Integer weight;

    @Max(value = 11)
    @Column(name = "position")
    private Integer position;

    @Size(max = 255)
    @Column(name = "tracking_number", length = 255)
    private String trackingNumber;

    @Max(value = 11)
    @Column(name = "cubed_weight")
    private Integer cubedWeight;

    @Column(name = "cod_value")
    private Float codValue;

    @Column(name = "insurance_amount")
    private Float insuranceAmount;

    @Size(max = 10)
    @Column(name = "freight_class", length = 10)
    private String freightClass;

    @Size(max = 50)
    @Column(name = "nmfc_code", length = 50)
    private String nmfcCode;

    @Max(value = 11)
    @Column(name = "weight_oz")
    private Integer weightOz;

    @Column(name = "item_value")
    private Float itemValue;

    @Size(max = 100)
    @Column(name = "harmonized_code", length = 100)
    private String harmonizedCode;

    @ManyToOne
    @JsonIgnoreProperties("shipmentPackages")
    private PalletType type;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public ShipmentPackage description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getLength() {
        return length;
    }

    public ShipmentPackage length(Integer length) {
        this.length = length;
        return this;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getWidth() {
        return width;
    }

    public ShipmentPackage width(Integer width) {
        this.width = width;
        return this;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public ShipmentPackage height(Integer height) {
        this.height = height;
        return this;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public ShipmentPackage weight(Integer weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getPosition() {
        return position;
    }

    public ShipmentPackage position(Integer position) {
        this.position = position;
        return this;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public ShipmentPackage trackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
        return this;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public Integer getCubedWeight() {
        return cubedWeight;
    }

    public ShipmentPackage cubedWeight(Integer cubedWeight) {
        this.cubedWeight = cubedWeight;
        return this;
    }

    public void setCubedWeight(Integer cubedWeight) {
        this.cubedWeight = cubedWeight;
    }

    public Float getCodValue() {
        return codValue;
    }

    public ShipmentPackage codValue(Float codValue) {
        this.codValue = codValue;
        return this;
    }

    public void setCodValue(Float codValue) {
        this.codValue = codValue;
    }

    public Float getInsuranceAmount() {
        return insuranceAmount;
    }

    public ShipmentPackage insuranceAmount(Float insuranceAmount) {
        this.insuranceAmount = insuranceAmount;
        return this;
    }

    public void setInsuranceAmount(Float insuranceAmount) {
        this.insuranceAmount = insuranceAmount;
    }

    public String getFreightClass() {
        return freightClass;
    }

    public ShipmentPackage freightClass(String freightClass) {
        this.freightClass = freightClass;
        return this;
    }

    public void setFreightClass(String freightClass) {
        this.freightClass = freightClass;
    }

    public String getNmfcCode() {
        return nmfcCode;
    }

    public ShipmentPackage nmfcCode(String nmfcCode) {
        this.nmfcCode = nmfcCode;
        return this;
    }

    public void setNmfcCode(String nmfcCode) {
        this.nmfcCode = nmfcCode;
    }

    public Integer getWeightOz() {
        return weightOz;
    }

    public ShipmentPackage weightOz(Integer weightOz) {
        this.weightOz = weightOz;
        return this;
    }

    public void setWeightOz(Integer weightOz) {
        this.weightOz = weightOz;
    }

    public Float getItemValue() {
        return itemValue;
    }

    public ShipmentPackage itemValue(Float itemValue) {
        this.itemValue = itemValue;
        return this;
    }

    public void setItemValue(Float itemValue) {
        this.itemValue = itemValue;
    }

    public String getHarmonizedCode() {
        return harmonizedCode;
    }

    public ShipmentPackage harmonizedCode(String harmonizedCode) {
        this.harmonizedCode = harmonizedCode;
        return this;
    }

    public void setHarmonizedCode(String harmonizedCode) {
        this.harmonizedCode = harmonizedCode;
    }

    public PalletType getType() {
        return type;
    }

    public ShipmentPackage type(PalletType palletType) {
        this.type = palletType;
        return this;
    }

    public void setType(PalletType palletType) {
        this.type = palletType;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ShipmentPackage)) {
            return false;
        }
        return id != null && id.equals(((ShipmentPackage) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ShipmentPackage{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", length=" + getLength() +
            ", width=" + getWidth() +
            ", height=" + getHeight() +
            ", weight=" + getWeight() +
            ", position=" + getPosition() +
            ", trackingNumber='" + getTrackingNumber() + "'" +
            ", cubedWeight=" + getCubedWeight() +
            ", codValue=" + getCodValue() +
            ", insuranceAmount=" + getInsuranceAmount() +
            ", freightClass='" + getFreightClass() + "'" +
            ", nmfcCode='" + getNmfcCode() + "'" +
            ", weightOz=" + getWeightOz() +
            ", itemValue=" + getItemValue() +
            ", harmonizedCode='" + getHarmonizedCode() + "'" +
            "}";
    }
}
