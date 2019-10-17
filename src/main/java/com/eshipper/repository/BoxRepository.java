package com.eshipper.repository;
import com.eshipper.domain.Box;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Box entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BoxRepository extends JpaRepository<Box, Long> {

    @Query("select box from Box box where box.createdByUser.login = ?#{principal.username}")
    List<Box> findByCreatedByUserIsCurrentUser();

}
