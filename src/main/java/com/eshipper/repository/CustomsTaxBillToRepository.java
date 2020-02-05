package com.eshipper.repository;

import com.eshipper.domain.CustomsTaxBillTo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CustomsTaxBillTo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CustomsTaxBillToRepository extends JpaRepository<CustomsTaxBillTo, Long> {

}
