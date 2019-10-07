package com.eshipper.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A User1.
 */
@Entity
@Table(name = "user_1")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class User1 implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "user1")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<AddressBook> createdByUserIds = new HashSet<>();

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

    public User1 name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<AddressBook> getCreatedByUserIds() {
        return createdByUserIds;
    }

    public User1 createdByUserIds(Set<AddressBook> addressBooks) {
        this.createdByUserIds = addressBooks;
        return this;
    }

    public User1 addCreatedByUserId(AddressBook addressBook) {
        this.createdByUserIds.add(addressBook);
        addressBook.setUser1(this);
        return this;
    }

    public User1 removeCreatedByUserId(AddressBook addressBook) {
        this.createdByUserIds.remove(addressBook);
        addressBook.setUser1(null);
        return this;
    }

    public void setCreatedByUserIds(Set<AddressBook> addressBooks) {
        this.createdByUserIds = addressBooks;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User1)) {
            return false;
        }
        return id != null && id.equals(((User1) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "User1{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}