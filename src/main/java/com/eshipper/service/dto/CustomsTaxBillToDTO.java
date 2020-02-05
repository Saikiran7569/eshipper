package com.eshipper.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.CustomsTaxBillTo} entity.
 */
public class CustomsTaxBillToDTO implements Serializable {

    private Long id;

    private String billTo;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBillTo() {
        return billTo;
    }

    public void setBillTo(String billTo) {
        this.billTo = billTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CustomsTaxBillToDTO customsTaxBillToDTO = (CustomsTaxBillToDTO) o;
        if (customsTaxBillToDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), customsTaxBillToDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CustomsTaxBillToDTO{" +
            "id=" + getId() +
            ", billTo='" + getBillTo() + "'" +
            "}";
    }
}
