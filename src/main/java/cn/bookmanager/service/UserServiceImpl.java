package cn.bookmanager.service;

import cn.bookmanager.entity.Books;
import cn.bookmanager.entity.User;
import cn.bookmanager.mapper.BooksMapper;
import cn.bookmanager.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author lengqie
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;


    @Override
    public Boolean isLogin(User user) {
        final int login = userMapper.isLogin(user);
        return login != 1;
    }

    @Override
    public void overdueCost(String id, double cost) {

    }

    @Override
    public void overduePay(String id) {
        userMapper.overduePay(id);
    }
}
