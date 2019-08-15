package edu.mum.cs.salmans.service;

import edu.mum.cs.salmans.models.*;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AppointmentService {
    public void saveBusinessDay(BusinessDay businessDay);

    public void saveServiceTime(ServiceTime serviceTime);

    public void saveSeat(Seat seat);

    public void saveAppointment(Appointment appointment);

    public void deleteAppointmentById(Integer appointmentId);

    public boolean defaultBusinessDaysExist();

    public boolean defaultSeatsExist();

    public boolean defaultServiceTimesExist();

    public boolean seatIsAvailable(LocalDate appointmentDate, ServiceTime serviceTime,User hairstylist);

    public List<Seat> getAllSeats();

    public List<ServiceTime> getAllServiceTimes();

    public Optional<ServiceTime> getServiceTimeWithId(Integer serviceTimeId);

    Page<Appointment> getAllAppointmentsPaged(int page);

    Page<Appointment> getAllAppointmentsBookedByUserPaged(User user, int page);

    public void makeAppointment(Appointment appointment);

}
