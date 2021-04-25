package cn.bookmanager.controller;

import cn.bookmanager.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * 首页信息
 * @author lengqie
 */
@RestController()
@RequestMapping("/")
public class HttpStatusController {

    /**
     * 没有权限
     * 只返回401
     */
    @GetMapping("/401")
    public String notFound(HttpServletResponse response){
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        return " 冇权限 401";
    }

    /**
     * 登录吧
     * 只返回302
     */
    @GetMapping("/302")
    public String found(HttpServletResponse response){
        response.setStatus(HttpStatus.FOUND.value());
        return " 冇登录 302";
    }

}
