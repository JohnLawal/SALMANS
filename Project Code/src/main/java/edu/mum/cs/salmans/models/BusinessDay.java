package edu.mum.cs.salmans.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "business_days")
public class BusinessDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dayId;

    @NotBlank(message = "Day of The Week is Required")
    private String dayOfTheWeek;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<ServiceTime> serviceTimes = new HashSet<>();

    public BusinessDay() {
    }

    public BusinessDay(String dayOfTheWeek, Set<ServiceTime> serviceTimes) {
        this.dayOfTheWeek = dayOfTheWeek;
        this.serviceTimes = serviceTimes;
    }

    public BusinessDay(String dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public Integer getDayId() {
        return dayId;
    }

    public void setDayId(Integer dayId) {
        this.dayId = dayId;
    }

    public String getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public void setDayOfTheWeek(String dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public Set<ServiceTime> getServiceTimes() {
        return serviceTimes;
    }

    public void setServiceTimes(Set<ServiceTime> serviceTimes) {
        this.serviceTimes = serviceTimes;
    }

    public void addServiceTime(ServiceTime serviceTime) {
        this.serviceTimes.add(serviceTime);
    }
}
