package edu.mum.cs.salmans.serviceImpl;

import edu.mum.cs.salmans.models.*;
import edu.mum.cs.salmans.repository.*;
import edu.mum.cs.salmans.service.AppointmentService;
import edu.mum.cs.salmans.utility.AppValues;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class AppointmentServiceImplementation implements AppointmentService {
    private AppointmentRepository appointmentRepository;
    private BusinessDayRepository businessDayRepository;
    private SeatRepository seatRepository;
    private ServiceTimeRepository serviceTimeRepository;
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public AppointmentServiceImplementation(AppointmentRepository appointmentRepository, BusinessDayRepository businessDayRepository, SeatRepository seatRepository, ServiceTimeRepository serviceTimeRepository, RoleRepository roleRepository) {
        this.appointmentRepository = appointmentRepository;
        this.businessDayRepository = businessDayRepository;
        this.seatRepository = seatRepository;
        this.serviceTimeRepository = serviceTimeRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void saveBusinessDay(BusinessDay businessDay) {
        businessDayRepository.save(businessDay);
    }

    @Override
    public void saveServiceTime(ServiceTime serviceTime) {
        serviceTimeRepository.save(serviceTime);
    }

    @Override
    public void saveSeat(Seat seat) {
        seatRepository.save(seat);
    }

    @Override
    public void saveAppointment(Appointment appointment) {
        appointmentRepository.save(appointment);
    }

    @Override
    public boolean defaultBusinessDaysExist() {
        return businessDayRepository.count() > 0;
    }

    @Override
    public boolean defaultSeatsExist() {
        return seatRepository.count() > 0;
    }

    @Override
    public boolean defaultServiceTimesExist() {
        return serviceTimeRepository.count() > 0;
    }

    @Override
    public boolean seatIsAvailable(LocalDate appointmentDate, ServiceTime serviceTime, User hairstylist) {
        // for a date to be available,
        //the day of the date should be among the business days table and it should have this service time

        String dayOfWeek = appointmentDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.US);
        Optional<BusinessDay> businessDayWithTime = businessDayRepository.findByDayOfTheWeekEqualsAndServiceTimesContains(dayOfWeek, serviceTime);
        if (businessDayWithTime.isPresent()) {
            ///then we check if an appointment already exists for that appointment date, with the service time
            List<Appointment> existingAppointmentForDateAndTime = appointmentRepository.findByAppointmentDateEqualsAndServiceTimeEquals(appointmentDate, serviceTime);
            if (existingAppointmentForDateAndTime.size() > 0) {
                Optional<Appointment> existingAppointmentForDateAndTimeAndHairstylist = appointmentRepository.findByAppointmentDateEqualsAndServiceTimeEqualsAndHairstylistEquals(appointmentDate, serviceTime, hairstylist);
                return (!existingAppointmentForDateAndTimeAndHairstylist.isPresent());
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    @Override
    public List<Seat> getAllSeats() {
        return seatRepository.findAll();
    }

    @Override
    public List<ServiceTime> getAllServiceTimes() {
        return serviceTimeRepository.findAll();
    }

    @Override
    public Optional<ServiceTime> getServiceTimeWithId(Integer serviceTimeId) {
        return serviceTimeRepository.findById(serviceTimeId);
    }

    @Override
    public Page<Appointment> getAllAppointmentsPaged(int page) {
        return appointmentRepository.findAll(PageRequest.of(page, AppValues.ENTRIES_PER_PAGE.iVal(), Sort.by(AppValues.APPOINTMENT_SORT_BY.val())));
    }

    @Override
    public Page<Appointment> getAllAppointmentsBookedByUserPaged(User user, int page) {
        return appointmentRepository.findAllByCustomerEquals(user, PageRequest.of(page, AppValues.ENTRIES_PER_PAGE.iVal(), Sort.by(AppValues.APPOINTMENT_SORT_BY.val())));
    }


    @Override
    public void makeAppointment(Appointment appointment) {
        appointmentRepository.save(appointment);
    }


}
