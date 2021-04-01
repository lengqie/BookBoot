package cn.bookmanager.controller;

import cn.bookmanager.entity.Admin;
import cn.bookmanager.entity.Record;
import cn.bookmanager.entity.User;
import cn.bookmanager.service.UserService;
import cn.bookmanager.utils.ReturnMapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lengqie
 */

@RestController()
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public Map login(String name, String password,HttpSession session, HttpServletRequest request, HttpServletResponse response){

        final Boolean login = userService.isLogin(new User(name, password),session,request,response);

        if (login){
            final Map map = ReturnMapUtils.getMap("200","ok");
            return map;
        }
        final Map map = ReturnMapUtils.getMap("500","username or password error!");
        return map;

    }

    @PostMapping("/logout")
    public Map logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {

        session.removeAttribute("session_user");
        Cookie cookie_username = new Cookie("cookie_user", "");
        cookie_username.setMaxAge(0);
        cookie_username.setPath("/");
        response.addCookie(cookie_username);
        final Map map = ReturnMapUtils.getMap("301","logout successful!");
        return map;
    }
    @PostMapping("/pay")
    public Map pay(String id){

        userService.overduePay(id);

        final Map map = ReturnMapUtils.getMap("200","Paid!");
        return map;

    }

    @PostMapping("/register")
    public Map register(String name, String password){

        final String s = userService.registered(name, password);

        if ("exist".equals(s)){
            final Map map = ReturnMapUtils.getMap("500","username exist!");
            return map;
        }
        final Map map = ReturnMapUtils.getMap("200","ok");

        return map;
    }
}
