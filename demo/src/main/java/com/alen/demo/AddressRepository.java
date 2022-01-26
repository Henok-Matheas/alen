package com.alen.demo;

import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Integer> {
    Address findAddressById(Integer id);

    Address findAddressByPharmacy(Pharmacy pharmacy);
}
