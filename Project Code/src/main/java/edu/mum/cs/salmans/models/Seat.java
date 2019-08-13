package edu.mum.cs.salmans.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "seats")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer seatId;

    @Column(nullable = false,unique = true)
    @NotNull(message = "Seat Number is Required")
    private Integer seatNumber;

    @OneToOne(mappedBy = "seat")
    private User hairstylist;

    public Seat() {
    }

    public Seat(Integer seatNumber, User hairstylist) {
        this.seatNumber = seatNumber;
        this.hairstylist = hairstylist;
    }

    public Integer getSeatId() {
        return seatId;
    }

    public void setSeatId(Integer seatId) {
        this.seatId = seatId;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }

    public User getHairstylist() {
        return hairstylist;
    }

    public void setHairstylist(User hairstylist) {
        this.hairstylist = hairstylist;
    }
}
