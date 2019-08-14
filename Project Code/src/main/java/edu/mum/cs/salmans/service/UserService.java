package edu.mum.cs.salmans.service;

import edu.mum.cs.salmans.models.Role;

public interface UserService {

    public boolean defaultRolesExist();

    public void saveRole(Role role);
}
