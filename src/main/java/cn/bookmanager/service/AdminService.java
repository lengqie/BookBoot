package cn.bookmanager.service;

import cn.bookmanager.entity.Admin;
import org.springframework.stereotype.Service;

/**
 *
 * @author lengqie
 */
public interface AdminService {
    @Deprecated
    Admin login(String name, String password);

    Boolean isLogin(Admin admin);
}
