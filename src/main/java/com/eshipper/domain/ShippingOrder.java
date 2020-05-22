package com.eshipper.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A ShippingOrder.
 */
@Entity
@Table(name = "shipping_order")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ShippingOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties(value = "shippingOrders", allowSetters = true)
    private CommodityType commodityType;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CommodityType getCommodityType() {
        return commodityType;
    }

    public ShippingOrder commodityType(CommodityType commodityType) {
        this.commodityType = commodityType;
        return this;
    }

    public void setCommodityType(CommodityType commodityType) {
        this.commodityType = commodityType;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ShippingOrder)) {
            return false;
        }
        return id != null && id.equals(((ShippingOrder) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ShippingOrder{" +
            "id=" + getId() +
            "}";
    }
}
