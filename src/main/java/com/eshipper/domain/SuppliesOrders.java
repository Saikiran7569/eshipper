package com.eshipper.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A SuppliesOrders.
 */
@Entity
@Table(name = "supplies_orders")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SuppliesOrders implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @ManyToOne
    @JsonIgnoreProperties("suppliesOrders")
    private User11 createdByUser;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public SuppliesOrders itemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public SuppliesOrders quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public SuppliesOrders createdDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public User11 getCreatedByUser() {
        return createdByUser;
    }

    public SuppliesOrders createdByUser(User11 user11) {
        this.createdByUser = user11;
        return this;
    }

    public void setCreatedByUser(User11 user11) {
        this.createdByUser = user11;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SuppliesOrders)) {
            return false;
        }
        return id != null && id.equals(((SuppliesOrders) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SuppliesOrders{" +
            "id=" + getId() +
            ", itemName='" + getItemName() + "'" +
            ", quantity=" + getQuantity() +
            ", createdDate='" + getCreatedDate() + "'" +
            "}";
    }
}
