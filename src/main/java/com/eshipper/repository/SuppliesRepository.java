package com.eshipper.repository;

import com.eshipper.domain.Supplies;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Supplies entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SuppliesRepository extends JpaRepository<Supplies, Long> {

}
