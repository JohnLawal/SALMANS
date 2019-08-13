package edu.mum.cs.salmans.serviceImpl;

import edu.mum.cs.salmans.models.BusinessDay;
import edu.mum.cs.salmans.models.Seat;
import edu.mum.cs.salmans.models.ServiceTime;
import edu.mum.cs.salmans.repository.BusinessDayRepository;
import edu.mum.cs.salmans.repository.SeatRepository;
import edu.mum.cs.salmans.repository.ServiceTimeRepository;
import edu.mum.cs.salmans.service.AppointmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImplementation implements AppointmentService {
    private BusinessDayRepository businessDayRepository;
    private SeatRepository seatRepository;
    private ServiceTimeRepository serviceTimeRepository;

    public AppointmentServiceImplementation(BusinessDayRepository businessDayRepository, SeatRepository seatRepository, ServiceTimeRepository serviceTimeRepository) {
        this.businessDayRepository = businessDayRepository;
        this.seatRepository = seatRepository;
        this.serviceTimeRepository = serviceTimeRepository;
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
    public List<Seat> getAllSeats() {
        return seatRepository.findAll();
    }

    @Override
    public List<ServiceTime> getAllServiceTimes() {
        return serviceTimeRepository.findAll();
    }

}
