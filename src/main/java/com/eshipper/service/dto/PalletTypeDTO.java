package com.eshipper.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.PalletType} entity.
 */
public class PalletTypeDTO implements Serializable {

    private Long id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PalletTypeDTO palletTypeDTO = (PalletTypeDTO) o;
        if (palletTypeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), palletTypeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PalletTypeDTO{" +
            "id=" + getId() +
            "}";
    }
}
