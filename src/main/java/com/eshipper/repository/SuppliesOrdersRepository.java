package com.eshipper.repository;

import com.eshipper.domain.SuppliesOrders;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SuppliesOrders entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SuppliesOrdersRepository extends JpaRepository<SuppliesOrders, Long> {

}
