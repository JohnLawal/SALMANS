package edu.mum.cs.salmans;

import edu.mum.cs.salmans.models.BusinessDay;
import edu.mum.cs.salmans.models.ServiceTime;
import edu.mum.cs.salmans.service.AppointmentService;
import edu.mum.cs.salmans.serviceImpl.AppointmentServiceImplementation;
import edu.mum.cs.salmans.utility.AppHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SalmansApplication implements CommandLineRunner {

    @Autowired
    AppointmentServiceImplementation appointmentServiceImplementation;

    public static void main(String[] args) {
        SpringApplication.run(SalmansApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //initialize DB records

        //default business days
        if (!appointmentServiceImplementation.defaultBusinessDaysExist()) {
            List<BusinessDay> businessDays = new ArrayList<>();
            for (String weekDay : AppHelper.daysInAWeek) {
                businessDays.add(new BusinessDay(weekDay));
            }

            for (BusinessDay businessDay : businessDays) {
                //appointmentServiceImplementation.saveBusinessDay(businessDay);
            }
        }

        //default seats
        if(!appointmentServiceImplementation.defaultSeatsExist()){

        }

        //default business days and service times
        if (!appointmentServiceImplementation.defaultServiceTimesExist()) {
            LocalTime[] openTimes = getTimes();
            List<ServiceTime> serviceTimes = new ArrayList<>();
            for (int i = 0; i < openTimes.length - 1; i++) {
                serviceTimes.add(new ServiceTime(openTimes[i], openTimes[i + 1]));
            }

            for(ServiceTime serviceTime: serviceTimes){
                appointmentServiceImplementation.saveServiceTime(serviceTime);
            }
        }

    }

    private LocalTime[] getTimes() {
        return new LocalTime[]{
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
                LocalTime.of(17, 30)//8:30 pm
        };
    }
}
