package org.sample.app.controller;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.List;

import org.sample.app.entity.mysql.Depart;
import org.sample.app.entity.mysql.MysqlSample;
import org.sample.app.mysql.BookRepository;
import org.sample.app.mysql.DepartRepository;
import org.sample.app.mysql.MapDataRepository;
import org.sample.app.mysql.MysqlSampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

@Controller
public class SampleController {
	
	@Autowired
    MysqlSampleRepository mysqlRepo;
	
	@Autowired
    BookRepository bookRepo;
	
	@Autowired
    DepartRepository departRepo;
	
	@Autowired
	Gson gson;
	
	@RequestMapping("/")
    public String home() {
		return "index";
    }
	
	@RequestMapping("/book")
    public String home2() {
		return "book";
    }
	
	@RequestMapping("/depart")
    public String home3() {
		return "depart";
    }
	
	@RequestMapping(value="/view/{name}", method=RequestMethod.GET, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String getName(@PathVariable("name") String name) {
		List<Depart> departs = departRepo.findByName(name); 
		String temp = "";
		for (Depart depart:departs) temp += (depart.getDepartId()+":"+depart.getName()+":"+depart.getPrice());
		return temp;
	}

	@RequestMapping(value="/bookList", method=RequestMethod.GET, produces="text/plain;charset=UTF-8")
	@ResponseBody
    public String bookList() {
		//System.out.println("여기로 오는 것 체크! 마이에수 " + mysqlRepo.count());
		if(bookRepo.count() != 0) {
			return gson.toJson(bookRepo.findAll());
		}
		return gson.toJson(bookRepo.findAll());
	}
	
	@RequestMapping(value="/departList", method=RequestMethod.GET, produces="text/plain;charset=UTF-8")
	@ResponseBody
    public String departList() {
		//System.out.println("여기로 오는 것 체크! 마이에수 " + mysqlRepo.count());
		if(departRepo.count() != 0) {
			return gson.toJson(departRepo.findAll());
		}
		return gson.toJson(departRepo.findAll());
	}
	

	
	@RequestMapping(value="/mysql", method=RequestMethod.GET, produces="text/plain;charset=UTF-8")
	@ResponseBody
    public String mysqlList() {
		if(mysqlRepo.count() != 0) {
			return gson.toJson(mysqlRepo.findAll());
		}
		
		try {
			List<MysqlSample> mysqlList = (List<MysqlSample>) sampleSetter("mysql", MysqlSample.class);
			mysqlList.forEach(mysqlSample -> mysqlRepo.save(mysqlSample)); //mysqlRepo.save(mysqlSample)
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return gson.toJson(mysqlRepo.findAll());
    }
	
	private List<?> sampleSetter(String DBName, Object type) throws Exception {
		List<Object> resultList = new ArrayList<>();
		Object resultObject = null;
		Class<?> aClass = (Class<?>) type;
      
		Method methodSetId = aClass.getMethod("setId", Long.TYPE);
        Method methodSetName = aClass.getMethod("setName", String.class);
        Method methodSetEmail = aClass.getMethod("setEmail", String.class);

        
		for(int index=1; index<6; index++) {
			resultObject = ((Class) type).newInstance();
			methodSetId.invoke(resultObject, index);
			methodSetName.invoke(resultObject, DBName + "-sample" + index);
			methodSetEmail.invoke(resultObject, "sample" + index + "@sample.com");
			resultList.add(resultObject);
		}
		return resultList;
	}
	
	@Autowired
	MapDataRepository mapDataRepository; 


}
