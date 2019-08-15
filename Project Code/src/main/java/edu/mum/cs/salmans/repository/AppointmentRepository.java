package edu.mum.cs.salmans.repository;

import edu.mum.cs.salmans.models.Appointment;
import edu.mum.cs.salmans.models.ServiceTime;
import edu.mum.cs.salmans.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    List<Appointment> findByAppointmentDateEqualsAndServiceTimeEquals(LocalDate appointmentDate, ServiceTime serviceTime);

    Optional<Appointment> findByAppointmentDateEqualsAndServiceTimeEqualsAndHairstylistEquals(LocalDate appointmentDate, ServiceTime serviceTime, User hairstylist);

    Page<Appointment> findAllByCustomerEquals(User customer, Pageable pageable);

    Page<Appointment> findAllByHairstylistEquals(User hairstylist, Pageable pageable);
}
