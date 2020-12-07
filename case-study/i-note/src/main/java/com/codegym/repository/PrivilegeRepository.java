package com.codegym.repository;

import com.codegym.model.Privilege;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends CrudRepository<Privilege, Integer> {
    Privilege findByName(String name);
}
