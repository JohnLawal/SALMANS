package edu.mum.cs.salmans.repository;

import edu.mum.cs.salmans.models.BusinessDay;
import edu.mum.cs.salmans.models.ServiceTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BusinessDayRepository extends JpaRepository<BusinessDay, Integer> {

    public Optional<BusinessDay> findByDayOfTheWeekEquals(String dayOfWeek);

    public Optional<BusinessDay> findByDayOfTheWeekEqualsAndServiceTimesContains(String dayOfWeek, ServiceTime serviceTime);
}
