package cn.bookmanager.controller;

import cn.bookmanager.entity.Admin;
import cn.bookmanager.service.AdminService;
import cn.bookmanager.utils.ReturnMapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Map;

/**
 * @author lengqie
 */

@RestController()
@RequestMapping("/admin")
public class AdminController {

    // 更加推荐 使用构造方法

    @Autowired
    AdminService adminService;

    @PostMapping("/login")
    public Map login(String name, String password){
        final Boolean login = adminService.isLogin(new Admin(name, password));

        if (login){
            final Map map = ReturnMapUtils.getMap("200","ok");
            return map;
        }
        final Map map = ReturnMapUtils.getMap("500","username or password error!");
        return map;
    }




}
