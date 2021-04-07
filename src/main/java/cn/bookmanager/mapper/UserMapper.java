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


    /**
     * 修改用户
     * @param user
     * @return 操作的 数量 一般是 1
     */
    int updateUser(User user);


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
     * @return
     */
    int isUniqueName(String name);


    /**创建 预约书
     *
     * @param isbn
     * @param userId
     * @param date
     * @return
     */
    int reserve(String isbn, String userId, Date date);

    /** 推荐购买
     * @param name
     * @param isbn
     * @param type
     * @param date
     * @return
     */
    int recommend(String name, String isbn,String type, Date date);

}
