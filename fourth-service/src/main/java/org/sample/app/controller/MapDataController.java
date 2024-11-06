package org.sample.app.controller;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.sample.app.entity.mysql.MapData;
import org.sample.app.mysql.MapDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

@Controller
public class MapDataController {
	
	@Autowired
	MapDataRepository mapDataRepository; 
	
	@Autowired
	Gson gson; 
	
	@RequestMapping(value="/map", method=RequestMethod.GET, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String getMapList() {
		if (mapDataRepository.count()!= 0) {
			return gson.toJson(mapDataRepository.findAll()); 
		} else {
			return "empty";
		}
	}
	
	@RequestMapping(value="/map", method=RequestMethod.POST, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public void storeMap(@RequestBody MapData mapData) {
		mapDataRepository.save(mapData); 
	}
	
	@RequestMapping(value="/map/{mapId}", method=RequestMethod.GET, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String getMapId(@PathVariable("mapId") int mapId) {
		return gson.toJson(mapDataRepository.findOne(mapId));
	}
	
	@RequestMapping(value="/name/{name}", method=RequestMethod.GET, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String getMapName(@PathVariable("name") String name)   {
			return gson.toJson(mapDataRepository.findByName(name));
	}
	
	@RequestMapping(value="/map/{mapId}", method=RequestMethod.PUT, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public void mpdifyMap(@PathVariable("mapId") int mapId, @RequestBody MapData mapData) {
		if (mapDataRepository.exists(mapId)) {
			mapData.setId(mapId);
			mapDataRepository.delete(mapData);
			mapDataRepository.save(mapData); 
		}
	}

	@DeleteMapping("/map/{mapId}")
	public void deleteMapId(@PathVariable("mapId") int mapId) {
		mapDataRepository.delete(mapId);
	}


}
