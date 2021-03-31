package cn.bookmanager.service;

import cn.bookmanager.entity.Record;
import cn.bookmanager.entity.User;

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

    Boolean isLogin(User user);


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
     * @retun
     */
    String registered(String name,String password);

}
