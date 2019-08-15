package edu.mum.cs.salmans.service;

import edu.mum.cs.salmans.models.Role;
import edu.mum.cs.salmans.models.User;

import javax.management.relation.RoleNotFoundException;
import java.util.List;
import java.util.Optional;

public interface UserService {

    public boolean defaultRolesExist();

    public void saveRole(Role role);

    public Role getRole(String roleName);

    public void saveUser(User user);

    public Optional<User> getUserByEmail(String email);

    public User findUserByEmail(String email);

    public Optional<User> getHairstylistWithId(Integer userId);

    public List<User> getAllHairStylists();
    public  User saveCustomer(User customer) throws RoleNotFoundException;
}
