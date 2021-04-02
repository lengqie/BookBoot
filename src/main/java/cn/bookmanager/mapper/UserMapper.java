package cn.bookmanager.mapper;

import cn.bookmanager.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @author lengqie
 */
@Mapper
@Repository
public interface UserMapper {

    /**
     * 登录
     * @param user
     * @return
     */
    int isLogin(User user);

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

    /**
     * 通过 name 获取
     * @param name
     * @return
     */
    User getUserByName(String name);

    void overdueCost(String id, double cost);

    /**
     * 逾期支付
     * @param id
     */
    void overduePay(String id);

    /**
     * 注册用户
     * @param id
     * @param name
     * @param password
     * @param date
     */
    void registered(String id, String name, String password, Date date);

    /**
     * 注册名称 不可重复！
     * @param name
     * @retnr
     */
    int isUniqueName(String name);


}
