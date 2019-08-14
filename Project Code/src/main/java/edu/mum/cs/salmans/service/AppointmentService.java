package edu.mum.cs.salmans.service;

import edu.mum.cs.salmans.models.*;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentService {
    public void saveBusinessDay(BusinessDay businessDay);

    public void saveServiceTime(ServiceTime serviceTime);

    public void saveSeat(Seat seat);

    public boolean defaultBusinessDaysExist();

    public boolean defaultSeatsExist();

    public boolean defaultServiceTimesExist();

    public boolean seatIsAvailable(LocalDate appointmentDate, ServiceTime serviceTime);

    public List<Seat> getAllSeats();

    public List<ServiceTime> getAllServiceTimes();

    Page<Appointment> getAllAppointmentsPaged(int page);

    Page<Appointment> getAllAppointmentsBookedByUserPaged(User user, int page);

    public void makeAppointment(Appointment appointment);

}
