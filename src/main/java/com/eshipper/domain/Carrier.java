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

    @OneToMany(mappedBy = "supplies")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Supplies> carriers = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Supplies> getCarriers() {
        return carriers;
    }

    public Carrier carriers(Set<Supplies> supplies) {
        this.carriers = supplies;
        return this;
    }

    public Carrier addCarrier(Supplies supplies) {
        this.carriers.add(supplies);
        supplies.setSupplies(this);
        return this;
    }

    public Carrier removeCarrier(Supplies supplies) {
        this.carriers.remove(supplies);
        supplies.setSupplies(null);
        return this;
    }

    public void setCarriers(Set<Supplies> supplies) {
        this.carriers = supplies;
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
