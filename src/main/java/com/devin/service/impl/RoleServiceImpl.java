package com.devin.service.impl;

import com.devin.dao.RoleDao;
import com.devin.domain.Role;
import com.devin.service.RoleService;

import java.util.List;

public class RoleServiceImpl implements RoleService{

    private RoleDao roleDao;

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<Role> findRoles() {
        List<Role> roles = roleDao.findRoles();
        return roles;
    }

    @Override
    public void addRole(Role role) {
        roleDao.addRole(role);
    }

    @Override
    public void deleteRole(Role role) {
        roleDao.deleteRole(role);
    }
}
