package com.eshipper.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.Company} entity.
 */
public class CompanyDTO implements Serializable {

    private Long id;


    private Long industryId;

    private Long monthlyshipmentsId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Long industryId) {
        this.industryId = industryId;
    }

    public Long getMonthlyshipmentsId() {
        return monthlyshipmentsId;
    }

    public void setMonthlyshipmentsId(Long monthlyShipmentsId) {
        this.monthlyshipmentsId = monthlyShipmentsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CompanyDTO companyDTO = (CompanyDTO) o;
        if (companyDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), companyDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CompanyDTO{" +
            "id=" + getId() +
            ", industryId=" + getIndustryId() +
            ", monthlyshipmentsId=" + getMonthlyshipmentsId() +
            "}";
    }
}
