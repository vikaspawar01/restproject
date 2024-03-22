package com.example.restApi.repo;

import org.springframework.data.repository.CrudRepository;

import com.example.restApi.models.Address;

public interface AddressRepo extends CrudRepository<Address, Integer>{

}
