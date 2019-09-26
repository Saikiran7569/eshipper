package com.eshipper.repository;
import com.eshipper.domain.AddressBook;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AddressBook entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AddressBookRepository extends JpaRepository<AddressBook, Long> {

}
