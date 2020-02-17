package com.eshipper.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.Supply} entity.
 */
public class SupplyDTO implements Serializable {

    private Long id;

    private String itemName;

    private String logoPath;


    private Long carrierId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public Long getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(Long carrierId) {
        this.carrierId = carrierId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SupplyDTO supplyDTO = (SupplyDTO) o;
        if (supplyDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), supplyDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SupplyDTO{" +
            "id=" + getId() +
            ", itemName='" + getItemName() + "'" +
            ", logoPath='" + getLogoPath() + "'" +
            ", carrierId=" + getCarrierId() +
            "}";
    }
}
