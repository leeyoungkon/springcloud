package org.sample.app.mysql;

import java.util.List;

import org.sample.app.entity.mysql.MapData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MapDataRepository extends CrudRepository<MapData, Integer> {
	MapData findById(int MapId); 
	List<MapData> findByName(String name); 
}
