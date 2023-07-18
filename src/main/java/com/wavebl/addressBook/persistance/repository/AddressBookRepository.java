package com.wavebl.addressBook.persistance.repository;


import com.wavebl.addressBook.persistance.entities.BusinessCardEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressBookRepository extends CrudRepository<BusinessCardEntity, Long> {
    List<BusinessCardEntity> findByStatus(String status);
}
