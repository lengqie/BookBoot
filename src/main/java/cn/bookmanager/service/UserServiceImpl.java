package cn.bookmanager.service;

import cn.bookmanager.entity.User;
import cn.bookmanager.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public Boolean isLogin(User user) {
        final int login = userMapper.isLogin(user);
        return login != 1;
    }
}
