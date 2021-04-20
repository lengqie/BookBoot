package cn.bookmanager.service;

import cn.bookmanager.entity.Admin;
import cn.bookmanager.entity.Book;
import cn.bookmanager.entity.Recommend;
import cn.bookmanager.entity.User;
import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 *
 * @author lengqie
 */
public interface AdminService {
    /**
     * 登录
     * @param name Admin.Name
     * @param password Admin.Password
     * @return Admin
     * @deprecated
     */
    Admin login(String name, String password);

    /**
     * 登录后 并设置Cookie
     * @param admin Admin
     * @param session HttpSession
     * @param request HttpSession
     * @param response HttpServletResponse
     * @return 登陆成功返回1
     */
    Boolean isLogin(Admin admin, HttpSession session, HttpServletRequest request, HttpServletResponse response);

    /**
     * 获取Admin
     * @param name Admin.Name
     * @return Admin
     */
    Admin getAdmin(String name);


}
