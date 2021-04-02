package cn.bookmanager.controller;

import cn.bookmanager.entity.Admin;
import cn.bookmanager.entity.Books;
import cn.bookmanager.entity.Record;
import cn.bookmanager.entity.User;
import cn.bookmanager.service.AdminService;
import cn.bookmanager.service.RecordService;
import cn.bookmanager.utils.ReturnMapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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
@RequestMapping("/admin")
public class AdminController {

    // 更加推荐 使用构造方法

    @Autowired
    AdminService adminService;

    @Autowired
    RecordService recordService;


    @PostMapping("/login")
    public Map<String, String> login(String name, String password, HttpSession session, HttpServletRequest request, HttpServletResponse response){
        final Boolean login = adminService.isLogin(new Admin(name, password),session,request,response);

        if (login){
            return ReturnMapUtils.getMap("200","ok");
        }
        return ReturnMapUtils.getMap("500","username or password error!");
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
        return ReturnMapUtils.getMap("500","username or password error!");
    }

    @GetMapping("/record")
    public Map getAllRecord(){
        final List<Record> record = recordService.getAllRecord();
        return ReturnMapUtils.getMap("200","ok",record);
    }

    @GetMapping("/record/{recordId}")
    public Map getRecordByRecordId(@PathVariable String recordId){
        final Record record = recordService.getRecordByRecordId(recordId);

        if (record == null) {
            return ReturnMapUtils.getMap("404","not found");
        }
        return ReturnMapUtils.getMap("200","ok",record);
    }

    @PostMapping("/updateUser")
    public Map upUser(User user){
        if ( adminService.updateUser(user) ) {
            return ReturnMapUtils.getMap("200","ok");
        }
        return ReturnMapUtils.getMap("500","update book failed");
    }


    @PostMapping("/updateBook")
    public Map upBook(Books books){
        if ( adminService.updateBook(books) ) {
            return ReturnMapUtils.getMap("200","ok");
        }
        return ReturnMapUtils.getMap("500","update book failed");
    }

    @PostMapping("/updateRecord")
    public Map upRecord(String recordId, int success){
        if ( adminService.updateRecord(recordId,success) ) {
            return ReturnMapUtils.getMap("200","ok");
        }
        return ReturnMapUtils.getMap("500","update record failed");
    }

}
