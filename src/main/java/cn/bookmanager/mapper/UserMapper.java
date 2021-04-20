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
     * @param user User
     * @return 结果的数量 成功返回1
     */
    int isLogin(User user);

    /**
     * 获取用户信息
     * @param id User.Id
     * @return User
     */
    User getUserById(String id);
    /**
     * 通过 name 获取
     * @param name User.Name
     * @return User
     */
    User getUserByName(String name);

    /**
     * 修改用户
     * @param user User
     * @return 操作的数量 成功返回1
     */
    int updateUser(User user);

    /**
     * 逾期费用
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
     * @param id User.Id
     * @param name User.name
     * @param password User.Password
     * @param date User.*Time
     */
    void registered(String id, String name, String password, Date date);

    /**
     * 注册名称 不可重复！
     * @param name User.Name
     * @return 查询的数量 存在返回1 不支持
     */
    int isUniqueName(String name);


    /** 推荐购买
     * @param name Book.Name
     * @param isbn Isbn
     * @param type Book.Type
     * @param date Book.*Time
     * @return  操作的数量 成功返回1
     */
    int recommend(String name, String isbn,String type, Date date);

}
