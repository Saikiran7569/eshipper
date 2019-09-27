package com.eshipper.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * A Company.
 */
@Entity
@Table(name = "company")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    @Column(name = "account_number", length = 255)
    private String accountNumber;

    @Size(max = 255)
    @Column(name = "name", length = 255)
    private String name;

    @Size(max = 255)
    @Column(name = "address_1", length = 255)
    private String address1;

    @Size(max = 255)
    @Column(name = "address_2", length = 255)
    private String address2;

    @Size(max = 255)
    @Column(name = "postal_code", length = 255)
    private String postalCode;

    @Size(max = 255)
    @Column(name = "phone", length = 255)
    private String phone;

    @Size(max = 255)
    @Column(name = "email", length = 255)
    private String email;

    @Size(max = 255)
    @Column(name = "time_zone", length = 255)
    private String timeZone;

    @Max(value = 4)
    @Column(name = "cost_account")
    private Integer costAccount;

    @Column(name = "date_created")
    private ZonedDateTime dateCreated;

    @Max(value = 20)
    @Column(name = "creator")
    private Integer creator;

    @Size(max = 255)
    @Column(name = "contact", length = 255)
    private String contact;

    @Column(name = "is_shopify_enable")
    private Boolean isShopifyEnable;

    @Max(value = 10)
    @Column(name = "default_signature_option")
    private Integer defaultSignatureOption;

    @OneToMany(mappedBy = "company")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<AddressBook> addressBooks = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Company accountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getName() {
        return name;
    }

    public Company name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress1() {
        return address1;
    }

    public Company address1(String address1) {
        this.address1 = address1;
        return this;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public Company address2(String address2) {
        this.address2 = address2;
        return this;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public Company postalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public Company phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public Company email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public Company timeZone(String timeZone) {
        this.timeZone = timeZone;
        return this;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public Integer getCostAccount() {
        return costAccount;
    }

    public Company costAccount(Integer costAccount) {
        this.costAccount = costAccount;
        return this;
    }

    public void setCostAccount(Integer costAccount) {
        this.costAccount = costAccount;
    }

    public ZonedDateTime getDateCreated() {
        return dateCreated;
    }

    public Company dateCreated(ZonedDateTime dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public void setDateCreated(ZonedDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Integer getCreator() {
        return creator;
    }

    public Company creator(Integer creator) {
        this.creator = creator;
        return this;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public String getContact() {
        return contact;
    }

    public Company contact(String contact) {
        this.contact = contact;
        return this;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Boolean isIsShopifyEnable() {
        return isShopifyEnable;
    }

    public Company isShopifyEnable(Boolean isShopifyEnable) {
        this.isShopifyEnable = isShopifyEnable;
        return this;
    }

    public void setIsShopifyEnable(Boolean isShopifyEnable) {
        this.isShopifyEnable = isShopifyEnable;
    }

    public Integer getDefaultSignatureOption() {
        return defaultSignatureOption;
    }

    public Company defaultSignatureOption(Integer defaultSignatureOption) {
        this.defaultSignatureOption = defaultSignatureOption;
        return this;
    }

    public void setDefaultSignatureOption(Integer defaultSignatureOption) {
        this.defaultSignatureOption = defaultSignatureOption;
    }

    public Set<AddressBook> getAddressBooks() {
        return addressBooks;
    }

    public Company addressBooks(Set<AddressBook> addressBooks) {
        this.addressBooks = addressBooks;
        return this;
    }

    public Company addAddressBook(AddressBook addressBook) {
        this.addressBooks.add(addressBook);
        addressBook.setCompany(this);
        return this;
    }

    public Company removeAddressBook(AddressBook addressBook) {
        this.addressBooks.remove(addressBook);
        addressBook.setCompany(null);
        return this;
    }

    public void setAddressBooks(Set<AddressBook> addressBooks) {
        this.addressBooks = addressBooks;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Company)) {
            return false;
        }
        return id != null && id.equals(((Company) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Company{" +
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
