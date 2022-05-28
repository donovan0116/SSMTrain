package com.donovan.dao;

import com.donovan.model.User;

import java.util.List;

public interface UserDao {
    List<User> findAll();
}
