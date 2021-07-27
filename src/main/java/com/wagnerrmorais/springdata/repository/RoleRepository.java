package com.wagnerrmorais.springdata.repository;

import com.wagnerrmorais.springdata.orm.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {



}
