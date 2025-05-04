package com.web_project.hoodies.service;

import java.util.List;

import com.web_project.hoodies.model.Role;

public interface RoleService {
    Role findByName(String name);
    Role saveRole(Role role);
    List<Role> getAllRoles();
}
