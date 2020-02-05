package com.eshipper.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.MonthlyShipments} entity.
 */
public class MonthlyShipmentsDTO implements Serializable {

    private Long id;

    private String range;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MonthlyShipmentsDTO monthlyShipmentsDTO = (MonthlyShipmentsDTO) o;
        if (monthlyShipmentsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), monthlyShipmentsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MonthlyShipmentsDTO{" +
            "id=" + getId() +
            ", range='" + getRange() + "'" +
            "}";
    }
}
