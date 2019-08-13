package edu.mum.cs.salmans.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "business_days")
public class BusinessDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dayId;

    @NotBlank(message = "Day of The Week is Required")
    private String dayOfTheWeek;

    @ManyToMany
    private List<ServiceTime> serviceTimes = new ArrayList<>();

    public BusinessDay() {
    }

    public BusinessDay(String dayOfTheWeek, List<ServiceTime> serviceTimes) {
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

    public List<ServiceTime> getServiceTimes() {
        return serviceTimes;
    }

    public void setServiceTimes(List<ServiceTime> serviceTimes) {
        this.serviceTimes = serviceTimes;
    }

    public void addServiceTime(ServiceTime serviceTime) {
        this.serviceTimes.add(serviceTime);
    }
}
