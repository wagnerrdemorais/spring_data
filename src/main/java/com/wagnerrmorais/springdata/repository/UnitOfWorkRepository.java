package com.wagnerrmorais.springdata.repository;

import com.wagnerrmorais.springdata.orm.UnitOfWork;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitOfWorkRepository extends CrudRepository<UnitOfWork, Integer> {
}
