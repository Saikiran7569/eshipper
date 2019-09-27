package com.eshipper.service.dto;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.eshipper.domain.Company} entity.
 */
public class CompanyDTO implements Serializable {

    private Long id;

    @Size(max = 255)
    private String accountNumber;

    @Size(max = 255)
    private String name;

    @Size(max = 255)
    private String address1;

    @Size(max = 255)
    private String address2;

    @Size(max = 255)
    private String postalCode;

    @Size(max = 255)
    private String phone;

    @Size(max = 255)
    private String email;

    @Size(max = 255)
    private String timeZone;

    @Max(value = 4)
    private Integer costAccount;

    private ZonedDateTime dateCreated;

    @Max(value = 20)
    private Integer creator;

    @Size(max = 255)
    private String contact;

    private Boolean isShopifyEnable;

    @Max(value = 10)
    private Integer defaultSignatureOption;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public Integer getCostAccount() {
        return costAccount;
    }

    public void setCostAccount(Integer costAccount) {
        this.costAccount = costAccount;
    }

    public ZonedDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(ZonedDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Boolean isIsShopifyEnable() {
        return isShopifyEnable;
    }

    public void setIsShopifyEnable(Boolean isShopifyEnable) {
        this.isShopifyEnable = isShopifyEnable;
    }

    public Integer getDefaultSignatureOption() {
        return defaultSignatureOption;
    }

    public void setDefaultSignatureOption(Integer defaultSignatureOption) {
        this.defaultSignatureOption = defaultSignatureOption;
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
            ", accountNumber='" + getAccountNumber() + "'" +
            ", name='" + getName() + "'" +
            ", address1='" + getAddress1() + "'" +
            ", address2='" + getAddress2() + "'" +
            ", postalCode='" + getPostalCode() + "'" +
            ", phone='" + getPhone() + "'" +
            ", email='" + getEmail() + "'" +
            ", timeZone='" + getTimeZone() + "'" +
            ", costAccount=" + getCostAccount() +
            ", dateCreated='" + getDateCreated() + "'" +
            ", creator=" + getCreator() +
            ", contact='" + getContact() + "'" +
            ", isShopifyEnable='" + isIsShopifyEnable() + "'" +
            ", defaultSignatureOption=" + getDefaultSignatureOption() +
            "}";
    }
}
