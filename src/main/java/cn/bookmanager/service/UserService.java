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
     * 判断 是否能够 登录
     * @param user
     * @return
     */

    Boolean isLogin(User user, HttpSession session, HttpServletRequest request, HttpServletResponse response);


    /**
     * 获取用户信息
     * @param id
     * @return
     */
    User getUserById(String id);

    /**
     * 获取用户信息
     * @param name
     * @return
     */
    User getUserByName(String name);



    /**
     * 逾期扣款
     * @param id
     * @param cost
     */
    void overdueCost(String id, double cost);

    /**
     * 逾期支付
     * @param id
     */
    void overduePay(String id);


    /**
     * 注册用户
     * @param name
     * @param password
     * @return
     */
    String registered(String name,String password);

    /**预约
     * @param isbn
     * @param userId
     * @param date
     * @return
     */
    boolean reserve(String isbn, String userId, Date date);

    /**创建 一条新记录
     * @param name
     * @param isbn
     * @param type
     * @param date
     * @return
     */
    boolean recommend(String name, String isbn,String type, Date date);


}
