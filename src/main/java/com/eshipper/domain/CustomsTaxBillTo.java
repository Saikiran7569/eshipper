package com.eshipper.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A CustomsTaxBillTo.
 */
@Entity
@Table(name = "customs_tax_bill_to")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CustomsTaxBillTo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bill_to")
    private String billTo;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBillTo() {
        return billTo;
    }

    public CustomsTaxBillTo billTo(String billTo) {
        this.billTo = billTo;
        return this;
    }

    public void setBillTo(String billTo) {
        this.billTo = billTo;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CustomsTaxBillTo)) {
            return false;
        }
        return id != null && id.equals(((CustomsTaxBillTo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CustomsTaxBillTo{" +
            "id=" + getId() +
            ", billTo='" + getBillTo() + "'" +
            "}";
    }
}
