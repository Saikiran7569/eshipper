package com.eshipper.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A WoPackageType.
 */
@Entity
@Table(name = "wo_package_type")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class WoPackageType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "woPackageType")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Box> boxes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Box> getBoxes() {
        return boxes;
    }

    public WoPackageType boxes(Set<Box> boxes) {
        this.boxes = boxes;
        return this;
    }

    public WoPackageType addBox(Box box) {
        this.boxes.add(box);
        box.setWoPackageType(this);
        return this;
    }

    public WoPackageType removeBox(Box box) {
        this.boxes.remove(box);
        box.setWoPackageType(null);
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
        if (!(o instanceof WoPackageType)) {
            return false;
        }
        return id != null && id.equals(((WoPackageType) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "WoPackageType{" +
            "id=" + getId() +
            "}";
    }
}
