package com.eshipper.repository;
import com.eshipper.domain.PalletType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PalletType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PalletTypeRepository extends JpaRepository<PalletType, Long> {

}
