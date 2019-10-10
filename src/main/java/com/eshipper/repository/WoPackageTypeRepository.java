package com.eshipper.repository;
import com.eshipper.domain.WoPackageType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the WoPackageType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WoPackageTypeRepository extends JpaRepository<WoPackageType, Long> {

}
