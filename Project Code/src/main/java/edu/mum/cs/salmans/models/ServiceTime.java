package edu.mum.cs.salmans.models;

import javax.persistence.*;

@Entity
@Table(name = "service_times")
public class ServiceTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer hoursId;



}
