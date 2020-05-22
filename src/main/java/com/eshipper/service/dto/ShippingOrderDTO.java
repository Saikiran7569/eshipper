package com.eshipper.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.eshipper.domain.ShippingOrder} entity.
 */
public class ShippingOrderDTO implements Serializable {
    
    private Long id;


    private Long commodityTypeId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCommodityTypeId() {
        return commodityTypeId;
    }

    public void setCommodityTypeId(Long commodityTypeId) {
        this.commodityTypeId = commodityTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ShippingOrderDTO)) {
            return false;
        }

        return id != null && id.equals(((ShippingOrderDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ShippingOrderDTO{" +
            "id=" + getId() +
            ", commodityTypeId=" + getCommodityTypeId() +
            "}";
    }
}
