package com.eshipper.repository;
import com.eshipper.domain.BoxPackageType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the BoxPackageType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BoxPackageTypeRepository extends JpaRepository<BoxPackageType, Long> {

}
