package cn.bookmanager.mapper;

import cn.bookmanager.entity.Admin;
import cn.bookmanager.entity.Books;
import cn.bookmanager.entity.Record;
import cn.bookmanager.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    User getOneUser(String id);

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
     * @param id
     * @param name
     * @param password
     */
    void registered(String id,String name,String password);

}
