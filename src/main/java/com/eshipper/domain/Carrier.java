package com.eshipper.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Carrier.
 */
@Entity
@Table(name = "carrier")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Carrier implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "carrier")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Supply> supplies = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Supply> getSupplies() {
        return supplies;
    }

    public Carrier supplies(Set<Supply> supplies) {
        this.supplies = supplies;
        return this;
    }

    public Carrier addSupply(Supply supply) {
        this.supplies.add(supply);
        supply.setCarrier(this);
        return this;
    }

    public Carrier removeSupply(Supply supply) {
        this.supplies.remove(supply);
        supply.setCarrier(null);
        return this;
    }

    public void setSupplies(Set<Supply> supplies) {
        this.supplies = supplies;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Carrier)) {
            return false;
        }
        return id != null && id.equals(((Carrier) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Carrier{" +
            "id=" + getId() +
            "}";
    }
}
