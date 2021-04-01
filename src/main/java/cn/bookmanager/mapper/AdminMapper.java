package cn.bookmanager.mapper;

import cn.bookmanager.entity.Admin;
import cn.bookmanager.entity.Books;
import cn.bookmanager.entity.Record;
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

    /**
     * 修改用户
     * @param user
     * @return 操作的 数量 一般是 1
     */
    int updateUser(User user);

    /**
     * 修改书籍
     * @param books
     * @return 操作的 数量 一般是 1
     */
    int updateBook(Books books);

    /**
     * 修改记录
     * @param recordId
     * @param  success
     * @return 操作的 数量 一般是 1
     */
    int updateRecord(String recordId, int success);

}
