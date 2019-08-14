package edu.mum.cs.salmans.service;

import edu.mum.cs.salmans.models.Role;
import edu.mum.cs.salmans.models.User;

public interface UserService {

    public boolean defaultRolesExist();

    public void saveRole(Role role);

    public Role getRole(String roleName);

    public void saveUser(User user);
}
