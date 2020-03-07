package com.viking.service;

import com.viking.NotFoundException;
import com.viking.dao.UserRepository;
import com.viking.po.User;
import com.viking.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by limi on 2017/10/15.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }

    @Transactional
    @Override
    public User save(User user) {
            user.setAvatar("https://unsplash.it/100/100?image=1005");
            user.setNickname("user000");
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            //MD5加密密码
            user.setPassword(MD5Utils.code(user.getPassword()));
            return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


}
