package com.eshipper.repository;

import com.eshipper.domain.Supply;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Supply entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SupplyRepository extends JpaRepository<Supply, Long> {

}
