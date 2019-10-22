package com.eshipper.service.dto;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.Box} entity.
 */
public class BoxDTO implements Serializable {

    private Long id;

    @Size(max = 255)
    private String name;

    @Size(max = 255)
    private String description;

    private Float maxSupportWeight;

    private Float length;

    private Float width;

    private Float height;

    private Float weight;

    private LocalDate createdDate;


    private Long createdByUserId;

    private Long metricId;

    private Long palletTypeId;

    private Long companyId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getMaxSupportWeight() {
        return maxSupportWeight;
    }

    public void setMaxSupportWeight(Float maxSupportWeight) {
        this.maxSupportWeight = maxSupportWeight;
    }

    public Float getLength() {
        return length;
    }

    public void setLength(Float length) {
        this.length = length;
    }

    public Float getWidth() {
        return width;
    }

    public void setWidth(Float width) {
        this.width = width;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getCreatedByUserId() {
        return createdByUserId;
    }

    public void setCreatedByUserId(Long userId) {
        this.createdByUserId = userId;
    }

    public Long getMetricId() {
        return metricId;
    }

    public void setMetricId(Long metricId) {
        this.metricId = metricId;
    }

    public Long getPalletTypeId() {
        return palletTypeId;
    }

    public void setPalletTypeId(Long palletTypeId) {
        this.palletTypeId = palletTypeId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BoxDTO boxDTO = (BoxDTO) o;
        if (boxDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), boxDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BoxDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", maxSupportWeight=" + getMaxSupportWeight() +
            ", length=" + getLength() +
            ", width=" + getWidth() +
            ", height=" + getHeight() +
            ", weight=" + getWeight() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdByUser=" + getCreatedByUserId() +
            ", metric=" + getMetricId() +
            ", palletType=" + getPalletTypeId() +
            ", company=" + getCompanyId() +
            "}";
    }
}
