package com.eshipper.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.Supplies} entity.
 */
public class SuppliesDTO implements Serializable {

    private Long id;

    private String item;

    private String name;


    private Long suppliesId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSuppliesId() {
        return suppliesId;
    }

    public void setSuppliesId(Long carrierId) {
        this.suppliesId = carrierId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SuppliesDTO suppliesDTO = (SuppliesDTO) o;
        if (suppliesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), suppliesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SuppliesDTO{" +
            "id=" + getId() +
            ", item='" + getItem() + "'" +
            ", name='" + getName() + "'" +
            ", suppliesId=" + getSuppliesId() +
            "}";
    }
}
