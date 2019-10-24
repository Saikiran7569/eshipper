package com.eshipper.repository;
import com.eshipper.domain.ShipmentPackage;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ShipmentPackage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ShipmentPackageRepository extends JpaRepository<ShipmentPackage, Long> {

}
