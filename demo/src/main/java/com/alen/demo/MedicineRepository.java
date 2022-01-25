package com.alen.demo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MedicineRepository extends CrudRepository<Medicine, Integer> {

    @Query("SELECT med FROM Medicine med WHERE med.pharmacy =:pharmacy ")
    List<Medicine> findMedicineByPharmacy(@Param("pharmacy") Pharmacy pharmacy);

    Medicine findMedicineById(Integer id);

    Medicine findByIdAndPharmacy(Integer id, Pharmacy pharmacy);

    @Query("SELECT m FROM Medicine m WHERE m.name LIKE %:key%")
    public List<Medicine> SearchBykeyword(@Param("key") String key);

}
