package edu.mum.cs.salmans.repository;

import edu.mum.cs.salmans.models.ServiceTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceTimeRepository extends JpaRepository<ServiceTime, Integer> {
}
