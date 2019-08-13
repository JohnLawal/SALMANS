package edu.mum.cs.salmans.service;

import edu.mum.cs.salmans.models.BusinessDay;
import edu.mum.cs.salmans.models.Seat;
import edu.mum.cs.salmans.models.ServiceTime;

public interface AppointmentService {
    public void saveBusinessDay(BusinessDay businessDay);

    public void saveServiceTime(ServiceTime serviceTime);

    public void saveSeat(Seat seat);

    public boolean defaultBusinessDaysExist();

    public boolean defaultSeatsExist();

    public boolean defaultServiceTimesExist();

}
