package cn.bookmanager.service;

import cn.bookmanager.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;


/** 用户的服务层
 * @author lengqie
 */
public interface UserService {

    /**
     * 登录
     * @param user User
     * @param session HttpSession
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return 是否登录
     */
    Boolean isLogin(User user, HttpSession session, HttpServletRequest request, HttpServletResponse response);


    /**
     * 获取用户信息
     * @param id User.Id
     * @return User
     */
    User getUserById(String id);

    /**
     * 获取用户信息
     * @param name User.Name
     * @return User
     */
    User getUserByName(String name);

    /**
     * 修改User
     * @param user User
     * @return 是否修改成功
     */
    Boolean updateUser(User user);


    /**
     * 逾期扣款
     * @param id User.id
     * @param cost User.Cost
     */
    void overdueCost(String id, double cost);

    /**
     * 逾期支付
     * @param id User.id
     */
    void overduePay(String id);


    /**
     * 注册用户
     * @param name User.name
     * @param password User.Password
     * @return 状态
     */
    String registered(String name,String password);

    /**预约
     * @param isbn Isbn
     * @param userId User.Id
     * @param date Date
     * @return 是否成功
     */
    // @Deprecated
    // boolean reserve(String isbn, String userId, Date date);

}
