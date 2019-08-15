package edu.mum.cs.salmans.serviceImpl;

import edu.mum.cs.salmans.models.Role;
import edu.mum.cs.salmans.models.User;
import edu.mum.cs.salmans.repository.RoleRepository;
import edu.mum.cs.salmans.repository.UserRepository;
import edu.mum.cs.salmans.service.UserService;
import edu.mum.cs.salmans.utility.AppValues;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {
    private RoleRepository roleRepository;
    private UserRepository userRepository;

    public UserServiceImplementation(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public boolean defaultRolesExist() {
        return roleRepository.count() > 0;
    }

    @Override
    public void saveRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Role getRole(String roleName) {
        return roleRepository.findByRoleNameEquals(roleName).get();
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public Optional<User> getHairstylistWithId(Integer userId) {
        return userRepository.findById(userId);
//        return userRepository.findByRoleEqualsAndUserIdEquals(getRole(AppValues.ROLE_HAIRSTYLIST.toString()), userId);
    }

    @Override
    public List<User> getAllHairStylists() {
        return userRepository.findByRoleEquals(getRole(AppValues.ROLE_HAIRSTYLIST.toString()), Sort.by(AppValues.USERS_SORT_BY.toString()));
    }

    @Override
    public User saveCustomer(User customer) throws RoleNotFoundException {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customer.setDateRegistered(customer.getDateRegistered());
        Role role =  getRole("ROLE_CUSTOMER");
        if(role == null) {
            throw new RoleNotFoundException("Role not found");
        }
        customer.setRole(role);
        return userRepository.save(customer);
    }

}
