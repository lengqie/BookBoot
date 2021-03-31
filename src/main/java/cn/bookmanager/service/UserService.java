package cn.bookmanager.service;

import cn.bookmanager.entity.User;

import java.util.Date;


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