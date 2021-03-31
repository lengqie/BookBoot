package cn.bookmanager.service;

import cn.bookmanager.entity.Books;
import cn.bookmanager.entity.Record;
import cn.bookmanager.entity.User;
import cn.bookmanager.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;


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
        return login == 1;
    }

    @Override
    public void overdueCost(String id, double cost) {

    }

    @Override
    public void overduePay(String id) {
        userMapper.overduePay(id);
    }

    @Override
    public String registered(String name, String password) {
        String id = UUID.randomUUID().toString().replace("-","");
        userMapper.registered(id,name,password);

        return "Ok";
    }

}
