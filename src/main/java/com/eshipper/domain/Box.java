package com.eshipper.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Box.
 */
@Entity
@Table(name = "box")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Box implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    @Column(name = "name", length = 255)
    private String name;

    @Size(max = 255)
    @Column(name = "description", length = 255)
    private String description;

    @Max(value = 20)
    @Column(name = "max_support_weight")
    private Integer maxSupportWeight;

    @Max(value = 20)
    @Column(name = "length")
    private Integer length;

    @Max(value = 20)
    @Column(name = "width")
    private Integer width;

    @Max(value = 20)
    @Column(name = "height")
    private Integer height;

    @Max(value = 20)
    @Column(name = "weight")
    private Integer weight;

    @ManyToOne
    @JsonIgnoreProperties("boxes")
    private Metric metric;

    @ManyToOne
    @JsonIgnoreProperties("boxes")
    private WoPackageType woPackageType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Box name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Box description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMaxSupportWeight() {
        return maxSupportWeight;
    }

    public Box maxSupportWeight(Integer maxSupportWeight) {
        this.maxSupportWeight = maxSupportWeight;
        return this;
    }

    public void setMaxSupportWeight(Integer maxSupportWeight) {
        this.maxSupportWeight = maxSupportWeight;
    }

    public Integer getLength() {
        return length;
    }

    public Box length(Integer length) {
        this.length = length;
        return this;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getWidth() {
        return width;
    }

    public Box width(Integer width) {
        this.width = width;
        return this;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public Box height(Integer height) {
        this.height = height;
        return this;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public Box weight(Integer weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Metric getMetric() {
        return metric;
    }

    public Box metric(Metric metric) {
        this.metric = metric;
        return this;
    }

    public void setMetric(Metric metric) {
        this.metric = metric;
    }

    public WoPackageType getWoPackageType() {
        return woPackageType;
    }

    public Box woPackageType(WoPackageType woPackageType) {
        this.woPackageType = woPackageType;
        return this;
    }

    public void setWoPackageType(WoPackageType woPackageType) {
        this.woPackageType = woPackageType;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Box)) {
            return false;
        }
        return id != null && id.equals(((Box) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Box{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", maxSupportWeight=" + getMaxSupportWeight() +
            ", length=" + getLength() +
            ", width=" + getWidth() +
            ", height=" + getHeight() +
            ", weight=" + getWeight() +
            "}";
    }
}
