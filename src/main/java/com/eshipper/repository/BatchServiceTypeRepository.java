package com.eshipper.repository;

import com.eshipper.domain.BatchServiceType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the BatchServiceType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BatchServiceTypeRepository extends JpaRepository<BatchServiceType, Long> {

}
