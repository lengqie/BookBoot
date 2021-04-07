package cn.bookmanager.service;

import cn.bookmanager.entity.User;
import cn.bookmanager.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.UUID;


/**
 * @author lengqie
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public Boolean isLogin(User user, HttpSession session, HttpServletRequest request, HttpServletResponse response){
        final int login = userMapper.isLogin(user);
        // 登录成功 则 写入Cookie！
        if (login ==1){
            session.setAttribute("session_user",user);
            Cookie cookie_admin = new Cookie("cookie_user",user.getName());
            cookie_admin.setMaxAge(60 * 60 * 24 * 7);
            cookie_admin.setPath("/");
            response.addCookie(cookie_admin);

            return true;
        }
        return false;
    }

    @Override
    public User getUserById(String id) {
        return userMapper.getUserById(id);
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
        // 用户名 不能重复
        final int i = userMapper.isUniqueName(name);
        if (i==1){
            return "exist";
        }
        String id = UUID.randomUUID().toString().replace("-","");

        Date date = new Date();
        userMapper.registered(id,name,password,date);
        return "Ok";
    }

    @Override
    public boolean reserve(String isbn, String userId, Date date) {
        final int i = userMapper.reserve(isbn, userId, date);
        return i == 1;
    }

    @Override
    public boolean recommend(String name, String isbn, String type, Date date) {
        final int i = userMapper.recommend(name, isbn ,type, date);
        return i == 1;
    }


}
