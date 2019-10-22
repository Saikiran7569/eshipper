package com.eshipper.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Metric.
 */
@Entity
@Table(name = "metric")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Metric implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "metric")
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

    public Metric boxes(Set<Box> boxes) {
        this.boxes = boxes;
        return this;
    }

    public Metric addBox(Box box) {
        this.boxes.add(box);
        box.setMetric(this);
        return this;
    }

    public Metric removeBox(Box box) {
        this.boxes.remove(box);
        box.setMetric(null);
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
        if (!(o instanceof Metric)) {
            return false;
        }
        return id != null && id.equals(((Metric) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Metric{" +
            "id=" + getId() +
            "}";
    }
}