package com.eshipper.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.SuppliesOrders} entity.
 */
public class SuppliesOrdersDTO implements Serializable {

    private Long id;

    private String itemName;

    private Integer quantity;

    private LocalDate createdDate;


    private Long createdByUserId;

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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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

    public void setCreatedByUserId(Long user11Id) {
        this.createdByUserId = user11Id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SuppliesOrdersDTO suppliesOrdersDTO = (SuppliesOrdersDTO) o;
        if (suppliesOrdersDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), suppliesOrdersDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SuppliesOrdersDTO{" +
            "id=" + getId() +
            ", itemName='" + getItemName() + "'" +
            ", quantity=" + getQuantity() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdByUserId=" + getCreatedByUserId() +
            "}";
    }
}
