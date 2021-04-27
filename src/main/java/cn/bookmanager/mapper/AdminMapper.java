package cn.bookmanager.mapper;

import cn.bookmanager.entity.Admin;
import cn.bookmanager.entity.Book;
import cn.bookmanager.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

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

    /**
     * 通过 name 获取
     * @return Admin
     */
    List<Admin> getAllAdmin();

    /**
     * 通过 name 获取
     * @param name Admin.Name
     * @param password Admin.Password
     * @param date Admin.Date
     * @return Admin
     */
    int addAdmin(String name, String password, Date date);

    /**
     * 更新管理员
     * @param id        id
     * @param name      name
     * @param password  password
     * @param date      date
     * @return
     */
    int updateAdmin(int id, String name, String password, Date date);
 /**
     * 更新管理员的状态
     * @param id        id
     * @param status    status
     * @param date      date
     * @return
     */
    int updateAdminStatus(int id,int status, Date date);

}
