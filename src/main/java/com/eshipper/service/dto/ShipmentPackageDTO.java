package com.eshipper.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.ShipmentPackage} entity.
 */
public class ShipmentPackageDTO implements Serializable {

    private Long id;

    @Size(max = 255)
    private String description;

    @Max(value = 11)
    private Integer length;

    @Max(value = 11)
    private Integer width;

    @Max(value = 11)
    private Integer height;

    @Max(value = 11)
    private Integer weight;

    @Max(value = 11)
    private Integer position;

    @Size(max = 255)
    private String trackingNumber;

    @Max(value = 11)
    private Integer cubedWeight;

    private Float codValue;

    private Float insuranceAmount;

    @Size(max = 10)
    private String freightClass;

    @Size(max = 50)
    private String nmfcCode;

    @Max(value = 11)
    private Integer weightOz;

    private Float itemValue;

    @Size(max = 100)
    private String harmonizedCode;


    private Long typeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public Integer getCubedWeight() {
        return cubedWeight;
    }

    public void setCubedWeight(Integer cubedWeight) {
        this.cubedWeight = cubedWeight;
    }

    public Float getCodValue() {
        return codValue;
    }

    public void setCodValue(Float codValue) {
        this.codValue = codValue;
    }

    public Float getInsuranceAmount() {
        return insuranceAmount;
    }

    public void setInsuranceAmount(Float insuranceAmount) {
        this.insuranceAmount = insuranceAmount;
    }

    public String getFreightClass() {
        return freightClass;
    }

    public void setFreightClass(String freightClass) {
        this.freightClass = freightClass;
    }

    public String getNmfcCode() {
        return nmfcCode;
    }

    public void setNmfcCode(String nmfcCode) {
        this.nmfcCode = nmfcCode;
    }

    public Integer getWeightOz() {
        return weightOz;
    }

    public void setWeightOz(Integer weightOz) {
        this.weightOz = weightOz;
    }

    public Float getItemValue() {
        return itemValue;
    }

    public void setItemValue(Float itemValue) {
        this.itemValue = itemValue;
    }

    public String getHarmonizedCode() {
        return harmonizedCode;
    }

    public void setHarmonizedCode(String harmonizedCode) {
        this.harmonizedCode = harmonizedCode;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long palletTypeId) {
        this.typeId = palletTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ShipmentPackageDTO shipmentPackageDTO = (ShipmentPackageDTO) o;
        if (shipmentPackageDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), shipmentPackageDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ShipmentPackageDTO{" +
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
            ", type=" + getTypeId() +
            "}";
    }
}
