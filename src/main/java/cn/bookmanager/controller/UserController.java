package cn.bookmanager.controller;

import cn.bookmanager.entity.Admin;
import cn.bookmanager.entity.Record;
import cn.bookmanager.entity.User;
import cn.bookmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lengqie
 */

@RestController()
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public Map login(String name, String password){
        final Boolean login = userService.isLogin(new User(name, password));
        Map<String,String> map =new HashMap<>(1);

        if (login){
            map.put("status","200");
            return map;
        }
        map.put("status","404");
        return map;

    }
    @PostMapping("/pay")
    public Map pay(String id){

        Map<String,String> map =new HashMap<>(1);

        userService.overduePay(id);

        map.put("status","200");
        return map;

    }

    @PostMapping("/register")
    public Map register(String name, String password){
        userService.registered(name,password);

        Map<String,String> map =new HashMap<>(1);
        map.put("status","404");

        return map;
    }
    // public List<Record> myBooks(String id){
    //     return userService.getMyBooks(id);
    // }
}
