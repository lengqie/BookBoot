package cn.bookmanager.service;

import cn.bookmanager.entity.Admin;
import cn.bookmanager.entity.Books;
import cn.bookmanager.entity.User;
import cn.bookmanager.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author lengqie
 */
@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    AdminMapper adminMapper;


    @Override
    public Admin login(String name, String password) {
        final Admin admin = adminMapper.login(name, password);
        return admin;
    }

    @Override
    public Boolean isLogin(Admin admin, HttpSession session, HttpServletRequest request, HttpServletResponse response){
        final int login = adminMapper.isLogin(admin);
        // 登录成功 则 写入Cookie！
        if (login ==1){

            session.setAttribute("session_admin",admin);
            Cookie cookie_admin = new Cookie("cookie_admin",admin.getName());
            cookie_admin.setMaxAge(60 * 60 * 24 * 7);
            cookie_admin.setPath("/");
            response.addCookie(cookie_admin);

            return true;
        }
        return false;
    }

    @Override
    public Admin getAdmin() {
        return adminMapper.getAdminByName("root");
    }

    @Override
    public Boolean updateUser(User user) {
        final int i = adminMapper.updateUser(user);
        return i == 1;
    }

    @Override
    public Boolean updateBook(Books books) {
        return adminMapper.updateBook(books) == 1;
    }

    @Override
    public Boolean updateRecord(String recordId, int success) {
        return adminMapper.updateRecord(recordId,success) == 1;
    }
}
