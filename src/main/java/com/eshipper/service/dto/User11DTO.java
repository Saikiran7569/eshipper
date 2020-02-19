package com.eshipper.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.User11} entity.
 */
public class User11DTO implements Serializable {

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

        User11DTO user11DTO = (User11DTO) o;
        if (user11DTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), user11DTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "User11DTO{" +
            "id=" + getId() +
            "}";
    }
}
