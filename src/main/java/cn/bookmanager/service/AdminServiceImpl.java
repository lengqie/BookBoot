package cn.bookmanager.service;

import cn.bookmanager.entity.Admin;
import cn.bookmanager.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lengqie
 */
@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    AdminMapper adminMapper;


    @Override
    public Admin login(String name, String password) {
        final Admin admin = adminMapper.login(name, password);
        return admin;
    }

    @Override
    public Boolean isLogin(Admin admin) {
        final int login = adminMapper.isLogin(admin);
        if (login ==1){
            return true;
        }
        return false;
    }
}
