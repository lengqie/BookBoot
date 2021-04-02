package cn.bookmanager.controller;

import cn.bookmanager.entity.Record;
import cn.bookmanager.entity.User;
import cn.bookmanager.service.RecordService;
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
import java.util.List;
import java.util.Map;

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

    @PostMapping("/")
    public User index(String id){
        return userService.getUserById(id);
    }

    @PostMapping("/login")
    public Map login(String name, String password, HttpSession session, HttpServletRequest request, HttpServletResponse response){

        final Boolean login = userService.isLogin(new User(name, password),session,request,response);

        if (login){
            return ReturnMapUtils.getMap("200","ok");
        }
        return ReturnMapUtils.getMap("500","username or password error!");

    }

    @PostMapping("/logout")
    public Map logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {

        session.removeAttribute("session_user");
        Cookie cookie_username = new Cookie("cookie_user", "");
        cookie_username.setMaxAge(0);
        cookie_username.setPath("/");
        response.addCookie(cookie_username);
        return ReturnMapUtils.getMap("301","logout successful!");
    }
    @PostMapping("/pay")
    public Map pay(String id){

        userService.overduePay(id);

        return ReturnMapUtils.getMap("200","Paid!");

    }

    @PostMapping("/register")
    public Map register(String name, String password){

        final String s = userService.registered(name, password);

        if ("exist".equals(s)){
            return ReturnMapUtils.getMap("500","username exist!");
        }

        return ReturnMapUtils.getMap("200","ok");
    }

    @GetMapping("/record")
    public List<Record> getAllRecord(HttpSession session){
        final User user = (User) session.getAttribute("session_user");

        return recordService.getRecordByUserId(user.getId());
    }
}
