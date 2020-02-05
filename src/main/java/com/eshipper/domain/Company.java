package com.eshipper.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

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

    @ManyToOne
    @JsonIgnoreProperties("companies")
    private Industry industry;

    @ManyToOne
    @JsonIgnoreProperties("companies")
    private MonthlyShipments monthlyshipments;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Industry getIndustry() {
        return industry;
    }

    public Company industry(Industry industry) {
        this.industry = industry;
        return this;
    }

    public void setIndustry(Industry industry) {
        this.industry = industry;
    }

    public MonthlyShipments getMonthlyshipments() {
        return monthlyshipments;
    }

    public Company monthlyshipments(MonthlyShipments monthlyShipments) {
        this.monthlyshipments = monthlyShipments;
        return this;
    }

    public void setMonthlyshipments(MonthlyShipments monthlyShipments) {
        this.monthlyshipments = monthlyShipments;
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
            "}";
    }
}
