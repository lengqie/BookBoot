package cn.bookmanager.controller;

import cn.bookmanager.entity.*;
import cn.bookmanager.service.AdminService;
import cn.bookmanager.service.RecordService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author lengqie
 */

@RestController()
@RequestMapping("/admin")
public class AdminController {

    // 更加推荐 使用构造方法

    @Autowired
    private AdminService adminService;

    @Autowired
    private RecordService recordService;


    @PostMapping("/login")
    public void login(String name, String password, HttpSession session, HttpServletRequest request, HttpServletResponse response){

        final Boolean login = adminService.isLogin(new Admin(name, password),session,request,response);

        Subject subject = SecurityUtils.getSubject();
        subject.login(new UsernamePasswordToken(name, password));

        if (!login){
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        }
        response.setStatus(HttpStatus.ACCEPTED.value());
    }

    @GetMapping("/info")
    public Admin info(){
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        System.out.println();
        return adminService.getAdmin("root");
    }

    @GetMapping("/logout")
    public void logout(HttpSession session, HttpServletResponse response) {
        session.removeAttribute("session_admin");
        Cookie cookieUsername = new Cookie("cookie_admin", "");
        cookieUsername.setMaxAge(0);
        cookieUsername.setPath("/");
        response.addCookie(cookieUsername);

        // 302
        response.setStatus(HttpStatus.FOUND.value());
        // return ReturnMapUtils.getMap("500","username or password error!");
    }
}
