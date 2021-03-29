package cn.bookmanager.controller;

import cn.bookmanager.entity.Admin;
import cn.bookmanager.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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
        Map<String,String> map =new HashMap<>(1);

        if (login){
            map.put("status","200");
            return map;
        }
        map.put("status","404");
        return map;

    }




}
