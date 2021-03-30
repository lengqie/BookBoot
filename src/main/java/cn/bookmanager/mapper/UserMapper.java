package cn.bookmanager.mapper;

import cn.bookmanager.entity.Admin;
import cn.bookmanager.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author lengqie
 */
@Mapper
public interface UserMapper {

    int isLogin(User user);
}
