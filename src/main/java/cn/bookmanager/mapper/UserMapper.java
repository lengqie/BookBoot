package cn.bookmanager.mapper;

import cn.bookmanager.entity.Admin;
import cn.bookmanager.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

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
    User getUserInfo(String id);

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

}
