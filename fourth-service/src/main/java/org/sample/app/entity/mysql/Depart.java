package org.sample.app.entity.mysql;

import lombok.Data;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "depart")
@Data
public class Depart {

    @Id
    @Column(name = "departId")
    private long departId;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private int price;

    @CreationTimestamp
    @Column(name = "saleDate", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date saleDate;

}
