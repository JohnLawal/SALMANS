package edu.mum.cs.salmans;

import edu.mum.cs.salmans.models.BusinessDay;
import edu.mum.cs.salmans.models.Role;
import edu.mum.cs.salmans.models.Seat;
import edu.mum.cs.salmans.models.ServiceTime;
import edu.mum.cs.salmans.serviceImpl.AppointmentServiceImplementation;
import edu.mum.cs.salmans.serviceImpl.UserServiceImplementation;
import edu.mum.cs.salmans.utility.AppHelper;
import edu.mum.cs.salmans.utility.AppValues;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SalmansApplication implements CommandLineRunner {
    private AppointmentServiceImplementation appointmentServiceImplementation;
    private UserServiceImplementation userServiceImplementation;

    public SalmansApplication(AppointmentServiceImplementation appointmentServiceImplementation, UserServiceImplementation userServiceImplementation) {
        this.appointmentServiceImplementation = appointmentServiceImplementation;
        this.userServiceImplementation = userServiceImplementation;
    }


    public static void main(String[] args) {
        SpringApplication.run(SalmansApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //initialize DB records

        //default roles
        if(!userServiceImplementation.defaultRolesExist()){
            Role adminRole = new Role(AppValues.ROLE_ADMIN.toString());
            Role hairstylistRole = new Role(AppValues.ROLE_HAIRSTYLIST.toString());
            Role customerRole = new Role(AppValues.ROLE_CUSTOMER.toString());

            userServiceImplementation.saveRole(adminRole);
            userServiceImplementation.saveRole(hairstylistRole);
            userServiceImplementation.saveRole(customerRole);
        }

        //default seats
        if (!appointmentServiceImplementation.defaultSeatsExist()) {
            List<Seat> seats = getSeats();
            for (Seat seat : seats) {
                appointmentServiceImplementation.saveSeat(seat);
            }
        }

        //default service times
        if (!appointmentServiceImplementation.defaultServiceTimesExist()) {
            List<Seat> savedSeats = appointmentServiceImplementation.getAllSeats();

            List<ServiceTime> serviceTimes = getServiceTimes(savedSeats);

            for (ServiceTime serviceTime : serviceTimes) {
                appointmentServiceImplementation.saveServiceTime(serviceTime);
            }
        }

        //default business days
        if (!appointmentServiceImplementation.defaultBusinessDaysExist()) {
            List<ServiceTime> saveServiceTimes = appointmentServiceImplementation.getAllServiceTimes();

            List<BusinessDay> businessDays = getBusinessDays();

            for (BusinessDay businessDay : businessDays) {
                for (ServiceTime serviceTime : saveServiceTimes) {
                    businessDay.addServiceTime(serviceTime);
                }
                appointmentServiceImplementation.saveBusinessDay(businessDay);
            }
        }


    }

    private List<ServiceTime> getServiceTimes(List<Seat> savedSeats) {
        LocalTime[] openTimes = new LocalTime[]{
                LocalTime.of(7, 30),//8:30 am
                LocalTime.of(8, 30),//9:30 am
                LocalTime.of(9, 30),//10:30 am
                LocalTime.of(10, 30),//11:30 am
                LocalTime.of(11, 30),//12:30 pm
                LocalTime.of(12, 30),//1:30 pm
                LocalTime.of(13, 30),//2:30 pm
                LocalTime.of(14, 30),//3:30 pm
                LocalTime.of(15, 30),//4:30 pm
                LocalTime.of(16, 30),//5:30 pm
                LocalTime.of(17, 30),//6:30 pm
                LocalTime.of(18, 30),//7:30 pm
                LocalTime.of(19, 30)//8:30 pm
        };
        List<ServiceTime> serviceTimes = new ArrayList<>();

        for (int i = 0; i < openTimes.length - 1; i++) {
            serviceTimes.add(new ServiceTime(openTimes[i], openTimes[i + 1], savedSeats));
        }
        return serviceTimes;
    }

    private List<Seat> getSeats() {
        List<Seat> returnedSeats = new ArrayList<>();
        returnedSeats.add(new Seat(1));
        returnedSeats.add(new Seat(2));
        returnedSeats.add(new Seat(3));
        returnedSeats.add(new Seat(4));
        returnedSeats.add(new Seat(5));
        returnedSeats.add(new Seat(6));
        returnedSeats.add(new Seat(7));
        returnedSeats.add(new Seat(8));
        returnedSeats.add(new Seat(9));
        returnedSeats.add(new Seat(10));
        return returnedSeats;
    }

    private List<BusinessDay> getBusinessDays() {
        List<BusinessDay> businessDays = new ArrayList<>();
        for (String weekDay : AppHelper.daysInAWeek) {
            businessDays.add(new BusinessDay(weekDay));
        }
        return businessDays;
    }
}
