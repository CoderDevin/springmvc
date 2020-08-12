package com.devin.service;

import com.devin.domain.Role;

import java.util.List;

public interface RoleService {
    public List<Role> findRoles();

    void addRole(Role role);

    void deleteRole(Role role);
}
