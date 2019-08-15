package edu.mum.cs.salmans.service;

import edu.mum.cs.salmans.models.Review;
import edu.mum.cs.salmans.models.Role;
import edu.mum.cs.salmans.models.User;
import org.springframework.data.domain.Page;

import javax.management.relation.RoleNotFoundException;
import java.util.List;
import java.util.Optional;

public interface UserService {

    boolean defaultRolesExist();

    void saveRole(Role role);

    Role getRole(String roleName);

    void saveUser(User user);

    void saveReview(Review review);

    public User findUserByEmail(String email);

    public List<User> getAllHairStylists();

    public  User saveCustomer(User customer) throws RoleNotFoundException;

    Page<Review> getAllReviewsPaged(int page);

    Optional<User> getUserByEmail(String email);

    Optional<User> getHairstylistWithId(Integer userId);

    Page<User> getAllHairstylistsPaged(int page);

    Page<User> getAllCustomersPaged(int page);
}
