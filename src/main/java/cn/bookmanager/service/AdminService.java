package cn.bookmanager.service;

import cn.bookmanager.entity.Admin;

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


    /**
     * 通过 name 获取
     * @return Admin
     */
    List<Admin> getAllAdmin();

    /**
     * 通过 name 获取
     * @param name Admin.Name
     * @param password Admin.Password
     * @param date Admin.Date
     * @return Admin
     */
    boolean addAdmin(String name, String password, Date date);

    /**
     * 更新管理员
     * @param id        id
     * @param name      name
     * @param password  password
     * @param date      date
     * @return
     */
    boolean updateAdmin(int id, String name, String password, Date date);
    /**
     * 删除管理员的状态
     * @param id        id
     * @param date      date
     * @return
     */
    boolean delAdmin(int id, Date date);


}
