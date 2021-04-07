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
     * @param name
     * @param password
     * @return 数据库查找 结果小于 1 则表示 查找失败
     */
    Admin login(@Param("name") String name, @Param("password") String password);

    /**
     * 管理员登录
     * @param admin
     * @return
     */
    int isLogin(Admin admin);

    /**
     * 通过 name 获取
     * @param name
     * @return
     */
    Admin getAdminByName(String name);

}
