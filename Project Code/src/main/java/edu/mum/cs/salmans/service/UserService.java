package edu.mum.cs.salmans.service;

import edu.mum.cs.salmans.models.Review;
import edu.mum.cs.salmans.models.Role;
import edu.mum.cs.salmans.models.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface UserService {

    boolean defaultRolesExist();

    void saveRole(Role role);

    Role getRole(String roleName);

    void saveUser(User user);

    void saveReview(Review review);

    Page<Review> getAllReviewsPaged(int page);

    Optional<User> getUserByEmail(String email);

    Optional<User> getHairstylistWithId(Integer userId);

    List<User> getAllHairStylists();

    Page<User> getAllHairstylistsPaged(int page);

    Page<User> getAllCustomersPaged(int page);
}
