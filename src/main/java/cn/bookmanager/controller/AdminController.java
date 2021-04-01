package cn.bookmanager.controller;

import cn.bookmanager.entity.Admin;
import cn.bookmanager.service.AdminService;
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
import java.util.Map;

/**
 * @author lengqie
 */

@RestController()
@RequestMapping("/admin")
public class AdminController {

    // 更加推荐 使用构造方法

    @Autowired
    AdminService adminService;

    @PostMapping("/login")
    public Map login(String name, String password, HttpSession session, HttpServletRequest request, HttpServletResponse response){
        final Boolean login = adminService.isLogin(new Admin(name, password),session,request,response);

        if (login){
            final Map map = ReturnMapUtils.getMap("200","ok");
            return map;
        }
        final Map map = ReturnMapUtils.getMap("500","username or password error!");
        return map;
    }

    @GetMapping("/info")
    public Admin info(){
        return adminService.getAdmin();
    }

    @GetMapping("/logout")
    public Map logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        session.removeAttribute("session_admin");
        Cookie cookieUsername = new Cookie("cookie_admin", "");
        cookieUsername.setMaxAge(0);
        cookieUsername.setPath("/");
        response.addCookie(cookieUsername);
        final Map map = ReturnMapUtils.getMap("500","username or password error!");
        return map;    }

}
