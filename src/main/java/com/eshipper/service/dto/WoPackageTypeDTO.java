package com.eshipper.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.WoPackageType} entity.
 */
public class WoPackageTypeDTO implements Serializable {

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

        WoPackageTypeDTO woPackageTypeDTO = (WoPackageTypeDTO) o;
        if (woPackageTypeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), woPackageTypeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WoPackageTypeDTO{" +
            "id=" + getId() +
            "}";
    }
}
