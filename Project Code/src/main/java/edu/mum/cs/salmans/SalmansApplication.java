package edu.mum.cs.salmans;

import edu.mum.cs.salmans.models.*;
import edu.mum.cs.salmans.serviceImpl.AppointmentServiceImplementation;
import edu.mum.cs.salmans.serviceImpl.HairStyleServiceImplementation;
import edu.mum.cs.salmans.serviceImpl.UserServiceImplementation;
import edu.mum.cs.salmans.utility.AppHelper;
import edu.mum.cs.salmans.utility.AppValues;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class SalmansApplication implements CommandLineRunner {
    private AppointmentServiceImplementation appointmentServiceImplementation;
    private UserServiceImplementation userServiceImplementation;
    private HairStyleServiceImplementation hairStyleServiceImplementation;

    public SalmansApplication(AppointmentServiceImplementation appointmentServiceImplementation, UserServiceImplementation userServiceImplementation,HairStyleServiceImplementation hairStyleServiceImplementation) {
        this.appointmentServiceImplementation = appointmentServiceImplementation;
        this.userServiceImplementation = userServiceImplementation;
        this.hairStyleServiceImplementation = hairStyleServiceImplementation;
    }


    public static void main(String[] args) {
        SpringApplication.run(SalmansApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //initialize DB records

        //default hairstyles
        if (!hairStyleServiceImplementation.defaultStylesExist()) {
            List<HairStyle> styles = getHairstyles();
            for (HairStyle style : styles) {
                hairStyleServiceImplementation.saveStyle(style);
            }
        }


        //default seats
        if (!appointmentServiceImplementation.defaultSeatsExist()) {
            List<Seat> seats = getSeats();
            for (Seat seat : seats) {
                appointmentServiceImplementation.saveSeat(seat);
            }
        }

        //default roles
        if (!userServiceImplementation.defaultRolesExist()) {
            Role adminRole = new Role(AppValues.ROLE_ADMIN.toString());
            Role hairstylistRole = new Role(AppValues.ROLE_HAIRSTYLIST.toString());
            Role customerRole = new Role(AppValues.ROLE_CUSTOMER.toString());

            userServiceImplementation.saveRole(adminRole);
            userServiceImplementation.saveRole(hairstylistRole);
            userServiceImplementation.saveRole(customerRole);

            //default hairstylists
            adminRole = userServiceImplementation.getRole(AppValues.ROLE_ADMIN.toString());
            hairstylistRole = userServiceImplementation.getRole(AppValues.ROLE_HAIRSTYLIST.toString());
            customerRole = userServiceImplementation.getRole(AppValues.ROLE_CUSTOMER.toString());
            List<Seat> savedSeats = appointmentServiceImplementation.getAllSeats();

            User admin = new User("Mr Luns", "luns@salmans.com", "$2a$10$/X9HVgmtzI3nbCfKgsJPde1mFpY4tMU3v5NObDwIY.1FKKNyaJHYq"
                    , LocalDate.of(2019, 8, 13), adminRole);
            userServiceImplementation.saveUser(admin);


            User hairstylist1 = new User("Chinedu Ugwu", "chinedu@salmans.com", "$2a$10$/X9HVgmtzI3nbCfKgsJPde1mFpY4tMU3v5NObDwIY.1FKKNyaJHYq"
                    , LocalDate.of(2019, 8, 13), hairstylistRole, savedSeats.get(0));
            User hairstylist2 = new User("Kelvin Amiaka", "kelvin@salmans.com", "$2a$10$/X9HVgmtzI3nbCfKgsJPde1mFpY4tMU3v5NObDwIY.1FKKNyaJHYq"
                    , LocalDate.of(2019, 8, 11), hairstylistRole, savedSeats.get(1));
            User hairstylist3 = new User("Wago Kedi", "wago@salmans.com", "$2a$10$/X9HVgmtzI3nbCfKgsJPde1mFpY4tMU3v5NObDwIY.1FKKNyaJHYq"
                    , LocalDate.of(2019, 8, 12), hairstylistRole, savedSeats.get(2));

            userServiceImplementation.saveUser(hairstylist1);
            userServiceImplementation.saveUser(hairstylist2);
            userServiceImplementation.saveUser(hairstylist3);
            //
            User customer1 = new User("John Lawal", "john@salmans.com", "$2a$10$/X9HVgmtzI3nbCfKgsJPde1mFpY4tMU3v5NObDwIY.1FKKNyaJHYq"
                    , LocalDate.of(2019, 7, 10), customerRole);
            User customer2 = new User("Moses Niyonshuti", "moses@salmans.com", "$2a$10$/X9HVgmtzI3nbCfKgsJPde1mFpY4tMU3v5NObDwIY.1FKKNyaJHYq"
                    , LocalDate.of(2019, 7, 11), customerRole);

            userServiceImplementation.saveUser(customer1);
            userServiceImplementation.saveUser(customer2);
//
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
//        returnedSeats.add(new Seat(4));
//        returnedSeats.add(new Seat(5));
//        returnedSeats.add(new Seat(6));
//        returnedSeats.add(new Seat(7));
//        returnedSeats.add(new Seat(8));
//        returnedSeats.add(new Seat(9));
//        returnedSeats.add(new Seat(10));
        return returnedSeats;
    }

    private List<HairStyle> getHairstyles(){
        List<HairStyle> styles = new ArrayList<>();
        styles.add(new HairStyle("Crew Cut"));
        styles.add(new HairStyle("Buzz Cut"));
        styles.add(new HairStyle("Undercut"));
        styles.add(new HairStyle("Mohawk"));
        styles.add(new HairStyle("Ponytail"));
        styles.add(new HairStyle("Quiff"));
        styles.add(new HairStyle("Bangs"));
        styles.add(new HairStyle("Bun"));
        styles.add(new HairStyle("Beehive"));
        styles.add(new HairStyle("Cornrows"));

        return styles;
    }

    private List<BusinessDay> getBusinessDays() {
        List<BusinessDay> businessDays = new ArrayList<>();
        for (String weekDay : AppHelper.daysInAWeek) {
            businessDays.add(new BusinessDay(weekDay));
        }
        return businessDays;
    }
}
