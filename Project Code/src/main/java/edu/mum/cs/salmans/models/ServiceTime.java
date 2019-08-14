package edu.mum.cs.salmans.models;

import javax.persistence.*;
<<<<<<< HEAD
=======
import java.time.LocalTime;
<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;
=======
import java.util.HashSet;
import java.util.Set;
>>>>>>> 0c136574c4dc9d69fe61f71704f8d10fe75565bf
>>>>>>> d47b525a5b70c27a10a5172485b29c091d8c4a51

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
