package edu.mum.cs.salmans.models;

import javax.persistence.*;
<<<<<<< HEAD
=======
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
>>>>>>> 0c136574c4dc9d69fe61f71704f8d10fe75565bf

@Entity
@Table(name = "service_times")
public class ServiceTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer timeId;

<<<<<<< HEAD
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer hoursId;

=======
    @Column(nullable = false)
    private LocalTime startTime;
>>>>>>> 0c136574c4dc9d69fe61f71704f8d10fe75565bf

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
