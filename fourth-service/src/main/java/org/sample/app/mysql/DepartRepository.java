package org.sample.app.mysql;

import java.util.List;

import org.sample.app.entity.mysql.Depart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartRepository extends CrudRepository<Depart, Integer> {
	List<Depart> findByName(String name); 
}
