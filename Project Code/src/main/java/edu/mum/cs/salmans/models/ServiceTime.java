package edu.mum.cs.salmans.models;

import javax.persistence.*;

import java.time.LocalTime;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "service_times")
public class ServiceTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer timeId;


    @Column(nullable = false)
    private LocalTime startTime;


    @Column(nullable = false)
    private LocalTime endTime;

    @ManyToMany
    private List<Seat> seats = new ArrayList<>();

    public ServiceTime() {
    }

    public ServiceTime(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public ServiceTime(LocalTime startTime, LocalTime endTime, List<Seat> seats) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.seats = seats;
    }

    public Integer getTimeId() {
        return timeId;
    }

    public void setTimeId(Integer timeId) {
        this.timeId = timeId;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public void addSeat(Seat seat){
        this.seats.add(seat);
    }
}
