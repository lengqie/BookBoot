package cn.bookmanager.mapper;

import cn.bookmanager.entity.Admin;
import cn.bookmanager.entity.Book;
import cn.bookmanager.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lengqie
 * 管理员登录
 */
@Mapper
@Repository
public interface AdminMapper {
    /**
     * 管理员登录
     * @param name Admin.Name
     * @param password Admin.Password
     * @return Admin
     */
    Admin login(@Param("name") String name, @Param("password") String password);

    /**
     * 管理员登录
     * @param admin Admin
     * @return 查询的数量 成功返回1
     */
    int isLogin(Admin admin);

    /**
     * 通过 name 获取
     * @param name Admin.Name
     * @return Admin
     */
    Admin getAdminByName(String name);

}
