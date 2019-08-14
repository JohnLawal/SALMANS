package edu.mum.cs.salmans.serviceImpl;

import edu.mum.cs.salmans.models.Role;
import edu.mum.cs.salmans.models.User;
import edu.mum.cs.salmans.repository.RoleRepository;
import edu.mum.cs.salmans.repository.UserRepository;
import edu.mum.cs.salmans.service.UserService;
import org.springframework.stereotype.Service;

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


}
