package com.eshipper.repository;

import com.eshipper.domain.CommodityType;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CommodityType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CommodityTypeRepository extends JpaRepository<CommodityType, Long> {
}
