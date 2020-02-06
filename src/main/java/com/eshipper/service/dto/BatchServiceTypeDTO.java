package com.eshipper.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.BatchServiceType} entity.
 */
public class BatchServiceTypeDTO implements Serializable {

    private Long id;

    private String serviceName;

    private String value;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BatchServiceTypeDTO batchServiceTypeDTO = (BatchServiceTypeDTO) o;
        if (batchServiceTypeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), batchServiceTypeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BatchServiceTypeDTO{" +
            "id=" + getId() +
            ", serviceName='" + getServiceName() + "'" +
            ", value='" + getValue() + "'" +
            "}";
    }
}
