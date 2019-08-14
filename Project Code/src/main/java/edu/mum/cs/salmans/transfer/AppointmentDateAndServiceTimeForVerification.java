package edu.mum.cs.salmans.transfer;

import edu.mum.cs.salmans.models.ServiceTime;

import java.time.LocalDate;

public class AppointmentDateAndServiceTimeForVerification {

    private LocalDate appointmentDate;
    private ServiceTime serviceTime;

    public AppointmentDateAndServiceTimeForVerification(){

    }

    public AppointmentDateAndServiceTimeForVerification(LocalDate appointmentDate, ServiceTime serviceTime) {
        this.appointmentDate = appointmentDate;
        this.serviceTime = serviceTime;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public ServiceTime getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(ServiceTime serviceTime) {
        this.serviceTime = serviceTime;
    }
}
