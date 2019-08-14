package edu.mum.cs.salmans.repository;

import edu.mum.cs.salmans.models.Role;
import edu.mum.cs.salmans.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    List<User> findByRoleEquals(Role role);
}
