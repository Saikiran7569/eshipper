package com.eshipper.repository;

import com.eshipper.domain.MonthlyShipments;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MonthlyShipments entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MonthlyShipmentsRepository extends JpaRepository<MonthlyShipments, Long> {

}
