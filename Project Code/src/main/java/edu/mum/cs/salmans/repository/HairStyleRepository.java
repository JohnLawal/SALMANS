package edu.mum.cs.salmans.repository;

import edu.mum.cs.salmans.models.HairStyle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HairStyleRepository extends JpaRepository<HairStyle, Long> {
}
