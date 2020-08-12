package com.devin.service;

import com.devin.domain.User;

import java.util.List;

public interface UserService {
    List<User> findUsers();

    void saveUser(User user,Long[] roleId);

    void deleteUser(Long id);
}
