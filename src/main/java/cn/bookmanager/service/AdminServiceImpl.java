package cn.bookmanager.service;

import cn.bookmanager.constant.CookieEnum;
import cn.bookmanager.entity.Admin;
import cn.bookmanager.entity.Book;
import cn.bookmanager.entity.Recommend;
import cn.bookmanager.entity.User;
import cn.bookmanager.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * @author lengqie
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AdminServiceImpl implements AdminService{

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin login(String name, String password) {
        return adminMapper.login(name, password);
    }

    @Override
    public Boolean isLogin(Admin admin, HttpSession session, HttpServletRequest request, HttpServletResponse response){
        final int login = adminMapper.isLogin(admin);
        // 登录成功 则 写入Cookie！
        if (login ==1){

            session.setAttribute(CookieEnum.SESSION_ADMIN.value(),admin);
            Cookie cookie_admin = new Cookie(CookieEnum.COOKIE_ADMIN.value(),admin.getName());
            cookie_admin.setMaxAge(60 * 60 * 24 * 7);
            cookie_admin.setPath("/");
            response.addCookie(cookie_admin);

            return true;
        }
        return false;
    }

    @Override
    public Admin getAdmin(String name) {
        return adminMapper.getAdminByName(name);
    }

}
