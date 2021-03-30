package cn.bookmanager.service;

import cn.bookmanager.entity.Admin;
import cn.bookmanager.entity.User;

/** 用户的服务层
 * @author lengqie
 */
public interface UserService {

    /**
     * 判断 是否能够 登录
     * @param user
     * @return
     */
    Boolean isLogin(User user);
}
