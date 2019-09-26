package com.eshipper.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Country.
 */
@Entity
@Table(name = "country")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Country implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    @Column(name = "name", length = 255)
    private String name;

    @Size(max = 255)
    @Column(name = "full_name", length = 255)
    private String fullName;

    @Column(name = "is_restricted")
    private Boolean isRestricted;

    @OneToMany(mappedBy = "country")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<AddressBook> addressBooks = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Country name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public Country fullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Boolean isIsRestricted() {
        return isRestricted;
    }

    public Country isRestricted(Boolean isRestricted) {
        this.isRestricted = isRestricted;
        return this;
    }

    public void setIsRestricted(Boolean isRestricted) {
        this.isRestricted = isRestricted;
    }

    public Set<AddressBook> getAddressBooks() {
        return addressBooks;
    }

    public Country addressBooks(Set<AddressBook> addressBooks) {
        this.addressBooks = addressBooks;
        return this;
    }

    public Country addAddressBook(AddressBook addressBook) {
        this.addressBooks.add(addressBook);
        addressBook.setCountry(this);
        return this;
    }

    public Country removeAddressBook(AddressBook addressBook) {
        this.addressBooks.remove(addressBook);
        addressBook.setCountry(null);
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
        if (!(o instanceof Country)) {
            return false;
        }
        return id != null && id.equals(((Country) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Country{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", fullName='" + getFullName() + "'" +
            ", isRestricted='" + isIsRestricted() + "'" +
            "}";
    }
}
