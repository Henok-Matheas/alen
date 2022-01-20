package com.alen.demo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface MedicineRepository extends CrudRepository<Medicine, Long> {
    List<Medicine> findAllByPharmacy(Pharmacy pharmacy);

}
