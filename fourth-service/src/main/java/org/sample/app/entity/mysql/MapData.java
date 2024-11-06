package org.sample.app.entity.mysql;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "mapData")
@Data
public class MapData {
    @Id
    @Column(name = "id")
	private int id;
    @Column(name = "latitude")
	private double latitude;
    @Column(name = "longitude")
	private double longitude;
    @Column(name = "name")
	private String name; 
    @Column(name = "image")
	private String image; 
    
}
