package com.eshipper.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A AddressBook.
 */
@Entity
@Table(name = "address_book")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AddressBook implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    @Column(name = "company_name", length = 255)
    private String companyName;

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
    @Column(name = "contact_name", length = 255)
    private String contactName;

    @Size(max = 255)
    @Column(name = "phone_no", length = 255)
    private String phoneNo;

    @Size(max = 255)
    @Column(name = "contact_email", length = 255)
    private String contactEmail;

    @Size(max = 255)
    @Column(name = "province", length = 255)
    private String province;

    @Column(name = "notify")
    private Boolean notify;

    @Column(name = "residential")
    private Boolean residential;

    @Size(max = 255)
    @Column(name = "created_by_user", length = 255)
    private String createdByUser;

    @Size(max = 255)
    @Column(name = "instruction", length = 255)
    private String instruction;

    @Column(name = "date_created")
    private LocalDate dateCreated;

    @Column(name = "date_updated")
    private LocalDate dateUpdated;

    @ManyToOne
    @JsonIgnoreProperties("addressBooks")
    private Country country;

    @ManyToOne
    @JsonIgnoreProperties("addressBooks")
    private Province province;

    @ManyToOne
    @JsonIgnoreProperties("addressBooks")
    private City city;

    @ManyToOne
    @JsonIgnoreProperties("addressBooks")
    private Customer customer;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public AddressBook companyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress1() {
        return address1;
    }

    public AddressBook address1(String address1) {
        this.address1 = address1;
        return this;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public AddressBook address2(String address2) {
        this.address2 = address2;
        return this;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public AddressBook postalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getContactName() {
        return contactName;
    }

    public AddressBook contactName(String contactName) {
        this.contactName = contactName;
        return this;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public AddressBook phoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
        return this;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public AddressBook contactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
        return this;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getProvince() {
        return province;
    }

    public AddressBook province(String province) {
        this.province = province;
        return this;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Boolean isNotify() {
        return notify;
    }

    public AddressBook notify(Boolean notify) {
        this.notify = notify;
        return this;
    }

    public void setNotify(Boolean notify) {
        this.notify = notify;
    }

    public Boolean isResidential() {
        return residential;
    }

    public AddressBook residential(Boolean residential) {
        this.residential = residential;
        return this;
    }

    public void setResidential(Boolean residential) {
        this.residential = residential;
    }

    public String getCreatedByUser() {
        return createdByUser;
    }

    public AddressBook createdByUser(String createdByUser) {
        this.createdByUser = createdByUser;
        return this;
    }

    public void setCreatedByUser(String createdByUser) {
        this.createdByUser = createdByUser;
    }

    public String getInstruction() {
        return instruction;
    }

    public AddressBook instruction(String instruction) {
        this.instruction = instruction;
        return this;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public AddressBook dateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDate getDateUpdated() {
        return dateUpdated;
    }

    public AddressBook dateUpdated(LocalDate dateUpdated) {
        this.dateUpdated = dateUpdated;
        return this;
    }

    public void setDateUpdated(LocalDate dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public Country getCountry() {
        return country;
    }

    public AddressBook country(Country country) {
        this.country = country;
        return this;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Province getProvince() {
        return province;
    }

    public AddressBook province(Province province) {
        this.province = province;
        return this;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public City getCity() {
        return city;
    }

    public AddressBook city(City city) {
        this.city = city;
        return this;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Customer getCustomer() {
        return customer;
    }

    public AddressBook customer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AddressBook)) {
            return false;
        }
        return id != null && id.equals(((AddressBook) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "AddressBook{" +
            "id=" + getId() +
            ", companyName='" + getCompanyName() + "'" +
            ", address1='" + getAddress1() + "'" +
            ", address2='" + getAddress2() + "'" +
            ", postalCode='" + getPostalCode() + "'" +
            ", contactName='" + getContactName() + "'" +
            ", phoneNo='" + getPhoneNo() + "'" +
            ", contactEmail='" + getContactEmail() + "'" +
            ", province='" + getProvince() + "'" +
            ", notify='" + isNotify() + "'" +
            ", residential='" + isResidential() + "'" +
            ", createdByUser='" + getCreatedByUser() + "'" +
            ", instruction='" + getInstruction() + "'" +
            ", dateCreated='" + getDateCreated() + "'" +
            ", dateUpdated='" + getDateUpdated() + "'" +
            "}";
    }
}
