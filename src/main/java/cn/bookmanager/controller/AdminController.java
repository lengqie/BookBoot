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
import java.util.Date;
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
        return adminService.getAdmin();
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


    @GetMapping("/record")
    public List<Record> getAllRecord(){
        return recordService.getAllRecord();
    }

    @PutMapping("/record/{recordId}")
    public void upRecord(int success, HttpServletResponse response, @PathVariable String recordId){
        if ( !adminService.updateRecord(recordId,success) ) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            // return ReturnMapUtils.getMap("200","ok");
        }
        // return ReturnMapUtils.getMap("500","update record failed");
    }

    @GetMapping("/record/{recordId}")
    public Record getRecordByRecordId(@PathVariable String recordId,HttpServletResponse response){
        final Record record = recordService.getRecordByRecordId(recordId);

        if (record == null) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
        }
        return record;
    }


    @PutMapping("/user")
    public void upUser(User user, HttpServletResponse response){
        if (!adminService.updateUser(user) ) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        }
    }


    @PostMapping("/book")
    public void addBook(String isbn,String name ,String type, HttpServletResponse response){
        Date date = new Date();
        if (!adminService.addBook(isbn,name, type,date)) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        }
    }

    @DeleteMapping("/book/{isbn}")
    public void delBookByIsbn(@PathVariable String isbn){
        if (adminService.delBook(isbn)) {

        }
    }

    @PutMapping("/book/{isbn}")
    public void upBook(Book book,@PathVariable String isbn, HttpServletResponse response){
        if (!adminService.updateBook(book,isbn) ) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        }
    }

    @PostMapping("/book/recommend/{id}")
    public void addBookFromRecommend(@PathVariable String id){
        adminService.addBookFromRecommend(id);

    }
    @GetMapping("/recommend/all")
    public List<Recommend> getAllRecommend(){
        return adminService.getAllRecommend();
    }

    @GetMapping("/recommend/{id}")
    public Recommend getRecommendById(@PathVariable String id,HttpServletResponse response){
        final Recommend recommend = adminService.getRecommendById(id);
        if (recommend == null) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
        }
        return recommend;
    }

}
