package com.viking.service;

import com.viking.po.User;

/**
 * Created by viking on 2018/6/15.
 */
public interface UserService {
    /**
     * 验证用户是否存在
     * @param username
     * @param password
     * @return
     */
    User checkUser(String username, String password);


    User save(User user);

    User findByUsername(String username);
}
