package com.devin.service.impl;

import com.devin.dao.RoleDao;
import com.devin.dao.UserDao;
import com.devin.domain.Role;
import com.devin.domain.User;
import com.devin.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private RoleDao roleDao;

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> findUsers() {
        List<User> users = userDao.findUsers();
        for(User user:users){
            List<Role> roles = roleDao.findRoleById(user.getId());
            user.setRoles(roles);
        }
        return users;
    }

    @Override
    public void saveUser(User user, Long[] roleId) {
        Long userId = userDao.saveUser(user);
        userDao.saveUserIdAndRoleId(roleId,userId);
    }

    @Override
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }
}
