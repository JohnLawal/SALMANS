package edu.mum.cs.salmans.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;


@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @NotBlank(message = "FullName is Required")
    @Column(nullable = false)
    private String fullName;

    @NotBlank(message = "Email is Required")
    @Column(name = "username", unique = true, nullable = false)
    @Email(message = "{errors.invalid_email}")
    private String email;

    @NotBlank(message = "Password is Required")
    @Column(nullable = false)
    @Size(min = 8)
    private String password;

    @Column(nullable = false)
    private LocalDate dateRegistered;
    //should be set by code when saving to DB for first time

    @OneToOne
    private Role role;

    @OneToOne
    @JoinColumn(name = "seat_fk", nullable = true)
    private Seat seat;

    public User() {
    }

    public User(String fullName, String email, String password, LocalDate dateRegistered, Role role, Seat seat) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.dateRegistered = dateRegistered;
        this.role = role;
        this.seat = seat;
    }

    public User(String fullName, String email, String password, LocalDate dateRegistered, Role role) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.dateRegistered = dateRegistered;
        this.role = role;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(LocalDate dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }
}
