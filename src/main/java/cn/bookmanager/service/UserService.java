package cn.bookmanager.service;

import cn.bookmanager.entity.Record;
import cn.bookmanager.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;


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

}
