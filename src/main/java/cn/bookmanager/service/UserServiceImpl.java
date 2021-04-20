package cn.bookmanager.service;

import cn.bookmanager.constant.CookieEnum;
import cn.bookmanager.entity.User;
import cn.bookmanager.mapper.UserMapper;
import cn.bookmanager.util.Base64Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public Boolean isLogin(User user, HttpSession session, HttpServletRequest request, HttpServletResponse response){
        final int login = userMapper.isLogin(user);
        // 登录成功 则 写入Cookie！
        if (login ==1){
            session.setAttribute(CookieEnum.SESSION_USER.value(),user);
            Cookie cookie = new Cookie(CookieEnum.COOKIE_USER.value(), Base64Util.encoder( user.getName() ) );
            cookie.setMaxAge(60 * 60 * 24 * 7);
            cookie.setPath("/");
            response.addCookie(cookie);

            return true;
        }
        return false;
    }

    @Override
    public User getUserById(String id) {
        return userMapper.getUserById(id);
    }

    @Override
    public User getUserByName(String name) {
        return userMapper.getUserByName(name);
    }

    @Override
    public void overdueCost(String id, double cost) {
    }

    @Override
    public Boolean updateUser(User user) {
        final int i = userMapper.updateUser(user);
        return i == 1;
    }

    @Override
    public void overduePay(String id) {
        userMapper.overduePay(id);
    }

    @Override
    public String registered(String name, String password) {
        // 用户名 不能重复
        // ali:A0111 用户名已存在
        final int i = userMapper.isUniqueName(name);
        if (i==1){
            return "A0111";
        }
        String id = UUID.randomUUID().toString().replace("-","");

        Date date = new Date();
        userMapper.registered(id,name,password,date);
        // ali:00000 一切OK
        return "00000";
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
