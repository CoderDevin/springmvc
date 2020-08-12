package com.devin.dao;

import com.devin.domain.User;

import java.util.List;

public interface  UserDao {
    List<User> findUsers();

    Long saveUser(User user);

    void saveUserIdAndRoleId(Long[] roleId, Long userId);

    void deleteUser(Long id);
}
