package cn.bookmanager.controller;

import cn.bookmanager.entity.Record;
import cn.bookmanager.entity.User;
import cn.bookmanager.service.AdminService;
import cn.bookmanager.service.RecordService;
import cn.bookmanager.service.UserService;
import cn.bookmanager.util.Base64Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @author lengqie
 */

@RestController()
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RecordService recordService;

    @Autowired
    private AdminService adminService;

    @GetMapping("/")
    public User index(HttpServletRequest request,HttpServletResponse response){

        Subject subject = SecurityUtils.getSubject();
        System.out.println(subject.getPrincipal());

        for (Cookie cookie : request.getCookies()) {
            if ("cookie_user".equals(cookie.getName())) {
                String name = cookie.getValue();
                name = Base64Util.decoder(name);
                return userService.getUserByName(name);
            }
        }
        response.setStatus(HttpStatus.NOT_FOUND.value());
        return null;

    }

    @PutMapping("/user")
    public void updateUser(User user, HttpServletResponse response){
        if (!userService.updateUser(user) ) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        }
    }

    @PostMapping("/login")
    public void login(String name, String password, HttpSession session, HttpServletRequest request, HttpServletResponse response){

        final Boolean login = userService.isLogin(new User(name, password),session,request,response);

        Subject subject = SecurityUtils.getSubject();
        subject.login(new UsernamePasswordToken(name, password));

        if (!login){
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        }

    }

    @PostMapping("/logout")
    public void logout(HttpSession session, HttpServletResponse response) {

        //Shiro
        Subject subject  = SecurityUtils.getSubject();

        System.out.println(((String) subject.getPrincipal()) +  "logout 了！");

        session.removeAttribute("session_user");
        Cookie cookieUsername = new Cookie("cookie_user", "");
        cookieUsername.setMaxAge(0);
        cookieUsername.setPath("/");
        response.addCookie(cookieUsername);

        //Shiro
        subject.logout();

        // 302
        response.setStatus(HttpStatus.FOUND.value());
    }
    @PostMapping("/pay")
    public void pay(String id){
        System.out.println(id);
        userService.overduePay(id);
    }

    @PostMapping("/register")
    public void register(String name, String password, HttpServletResponse response){

        final String s = userService.registered(name, password);

        // '409' : 'Conflict', //指令冲突
        if ("exist".equals(s)){
            response.setStatus(HttpStatus.CONFLICT.value());
        }
    }

    @GetMapping("/record")
    public List<Record> getUserRecord(HttpSession session){
        final User user = (User) session.getAttribute("session_user");

        return recordService.getRecordByUserId(user.getId());
    }


    @PostMapping("/recommend")
    public void recommend(String name, String isbn,String type, HttpServletResponse response){
        Date date = new Date();
        if (!userService.recommend(name,isbn,type,date)){
            response.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
        }
    }
}
