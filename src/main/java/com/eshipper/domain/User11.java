package com.eshipper.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A User11.
 */
@Entity
@Table(name = "user_11")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class User11 implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "createdByUser")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<SuppliesOrders> suppliesOrders = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<SuppliesOrders> getSuppliesOrders() {
        return suppliesOrders;
    }

    public User11 suppliesOrders(Set<SuppliesOrders> suppliesOrders) {
        this.suppliesOrders = suppliesOrders;
        return this;
    }

    public User11 addSuppliesOrders(SuppliesOrders suppliesOrders) {
        this.suppliesOrders.add(suppliesOrders);
        suppliesOrders.setCreatedByUser(this);
        return this;
    }

    public User11 removeSuppliesOrders(SuppliesOrders suppliesOrders) {
        this.suppliesOrders.remove(suppliesOrders);
        suppliesOrders.setCreatedByUser(null);
        return this;
    }

    public void setSuppliesOrders(Set<SuppliesOrders> suppliesOrders) {
        this.suppliesOrders = suppliesOrders;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User11)) {
            return false;
        }
        return id != null && id.equals(((User11) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "User11{" +
            "id=" + getId() +
            "}";
    }
}
