package edu.mum.cs.salmans.models;


import javax.persistence.*;
import javax.websocket.OnOpen;
import java.time.LocalDate;

@Entity
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer appointmentId;

    /***
     * refers to the date chosen
     * */
    @Column(nullable = false)
    private LocalDate appointmentDate;

    /***
     * refers to the date the user made the appointment,
     * not the date the user chose
     * */
    @Column(nullable = false)
    private LocalDate dateScheduled;
    //should be set when saving appointment

    @ManyToOne
    @JoinColumn(name = "hairstylist_fk")
    private User hairstylist;

    @ManyToOne
    @JoinColumn(name = "customer_fk")
    private User customer;

    @OneToOne
    @JoinColumn(name = "hairstyle_fk")
    private HairStyle hairStyle;


    @OneToOne
    @JoinColumn(name = "service_time_fk")
    private ServiceTime serviceTime;


    public Appointment() {
    }

    public Appointment(LocalDate appointmentDate, LocalDate dateScheduled, User hairstylist, User customer, HairStyle hairStyle, ServiceTime serviceTime) {
        this.appointmentDate = appointmentDate;
        this.dateScheduled = dateScheduled;
        this.hairstylist = hairstylist;
        this.customer = customer;
        this.hairStyle = hairStyle;
        this.serviceTime = serviceTime;
    }

    public Integer getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public LocalDate getDateScheduled() {
        return dateScheduled;
    }

    public void setDateScheduled(LocalDate dateScheduled) {
        this.dateScheduled = dateScheduled;
    }

    public User getHairstylist() {
        return hairstylist;
    }

    public void setHairstylist(User hairstylist) {
        this.hairstylist = hairstylist;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public HairStyle getHairStyle() {
        return hairStyle;
    }

    public void setHairStyle(HairStyle hairStyle) {
        this.hairStyle = hairStyle;
    }

    public ServiceTime getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(ServiceTime serviceTime) {
        this.serviceTime = serviceTime;
    }
}
