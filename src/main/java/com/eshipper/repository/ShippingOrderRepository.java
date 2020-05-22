package com.eshipper.repository;

import com.eshipper.domain.ShippingOrder;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ShippingOrder entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ShippingOrderRepository extends JpaRepository<ShippingOrder, Long> {
}
