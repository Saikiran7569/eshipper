package com.eshipper.service.dto;
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

    @Max(value = 20)
    private Integer maxSupportWeight;

    @Max(value = 20)
    private Integer length;

    @Max(value = 20)
    private Integer width;

    @Max(value = 20)
    private Integer height;

    @Max(value = 20)
    private Integer weight;


    private Long metricId;

    private Long woPackageTypeId;

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

    public Integer getMaxSupportWeight() {
        return maxSupportWeight;
    }

    public void setMaxSupportWeight(Integer maxSupportWeight) {
        this.maxSupportWeight = maxSupportWeight;
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

    public Long getMetricId() {
        return metricId;
    }

    public void setMetricId(Long metricId) {
        this.metricId = metricId;
    }

    public Long getWoPackageTypeId() {
        return woPackageTypeId;
    }

    public void setWoPackageTypeId(Long woPackageTypeId) {
        this.woPackageTypeId = woPackageTypeId;
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
            ", metric=" + getMetricId() +
            ", woPackageType=" + getWoPackageTypeId() +
            "}";
    }
}
