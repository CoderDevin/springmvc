package com.devin.dao;

import com.devin.domain.Role;

import java.util.List;

public interface RoleDao {
    public List<Role> findRoles();

    void addRole(Role role);

    void deleteRole(Role role);

    List<Role> findRoleById(Long id);
}
