package edu.mum.cs.salmans.serviceImpl;

import edu.mum.cs.salmans.models.Role;
import edu.mum.cs.salmans.repository.RoleRepository;
import edu.mum.cs.salmans.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService {
    private RoleRepository roleRepository;
    public UserServiceImplementation(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    @Override
    public boolean defaultRolesExist() {
        return roleRepository.count() > 0;
    }

    @Override
    public void saveRole(Role role) {
        roleRepository.save(role);
    }
}
