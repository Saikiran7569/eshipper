package com.eshipper.repository;

import com.eshipper.domain.User11;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the User11 entity.
 */
@SuppressWarnings("unused")
@Repository
public interface User11Repository extends JpaRepository<User11, Long> {

}
