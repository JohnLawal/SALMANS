package edu.mum.cs.salmans.models;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

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
    @JoinTable(name = "join_service_time_seats")
    private Set<Seat> seats = new HashSet<>();

    public ServiceTime() {
    }

    public ServiceTime(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public ServiceTime(LocalTime startTime, LocalTime endTime, Set<Seat> seats) {
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

    public Set<Seat> getSeats() {
        return seats;
    }

    public void setSeats(Set<Seat> seats) {
        this.seats = seats;
    }

    public void addSeat(Seat seat){
        this.seats.add(seat);
    }
}
