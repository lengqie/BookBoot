package cn.bookmanager.controller;

import cn.bookmanager.constant.CookieEnum;
import cn.bookmanager.entity.Admin;
import cn.bookmanager.service.AdminService;
import cn.bookmanager.util.Base64Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author lengqie
 */

@RestController
@RequestMapping("/admin")
public class AdminController {

    // 更加推荐 使用构造方法

    @Autowired
    private AdminService adminService;


    /**
     * anon 管理员登录
     * @param name     Admin.Name
     * @param password Admin.Password
     * @param session  Session
     * @param request  equest
     * @param response response
     */
    @PostMapping("/login")
    public void login(String name, String password, HttpSession session, HttpServletRequest request, HttpServletResponse response){

        // Service层登录 设置了Cookie
        final Boolean login = adminService.isLogin(new Admin(name, password),session,request,response);

        if (!login){
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        }
        // Shiro登录 授权和认证... 设置了ShiroSession
        Subject subject = SecurityUtils.getSubject();
        subject.login(new UsernamePasswordToken(name, password));

        response.setStatus(200);
    }

    /**
     * roles[admin] 管理员注销
     * @param session  session
     * @param response response
     */
    @RequiresUser
    @GetMapping("/logout")
    public void logout(HttpSession session, HttpServletResponse response) {
        session.removeAttribute(CookieEnum.SESSION_ADMIN.value());
        Cookie cookieUsername = new Cookie(CookieEnum.COOKIE_ADMIN.value(), "");
        cookieUsername.setMaxAge(0);
        cookieUsername.setPath("/");
        response.addCookie(cookieUsername);


        //Shiro
        Subject subject  = SecurityUtils.getSubject();
        subject.logout();

        // 302
        response.setStatus(HttpStatus.FOUND.value());
        // return ReturnMapUtils.getMap("500","username or password error!");
    }

    /**
     * roles[admin] 获取管理员信息
     * @return
     */
    @RequiresRoles({"admin"})
    @GetMapping("/")
    public Admin info(HttpServletRequest request, HttpServletResponse response){

        for (Cookie cookie : request.getCookies()) {
            if ( CookieEnum.COOKIE_ADMIN.value().equals( cookie.getName() ) ) {
                String name = cookie.getValue();
                name = Base64Util.decoder(name);
                final Admin admin = adminService.getAdmin(name);
                if (admin == null) {
                    response.setStatus(HttpStatus.NOT_FOUND.value());
                }
                return admin;
            }
        }
        response.setStatus(HttpStatus.NOT_FOUND.value());
        return null;
    }
}
