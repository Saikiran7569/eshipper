package com.eshipper.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A PalletType.
 */
@Entity
@Table(name = "pallet_type")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PalletType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "palletType")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Box> boxes = new HashSet<>();

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

    public PalletType name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Box> getBoxes() {
        return boxes;
    }

    public PalletType boxes(Set<Box> boxes) {
        this.boxes = boxes;
        return this;
    }

    public PalletType addBox(Box box) {
        this.boxes.add(box);
        box.setPalletType(this);
        return this;
    }

    public PalletType removeBox(Box box) {
        this.boxes.remove(box);
        box.setPalletType(null);
        return this;
    }

    public void setBoxes(Set<Box> boxes) {
        this.boxes = boxes;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PalletType)) {
            return false;
        }
        return id != null && id.equals(((PalletType) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "PalletType{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
