package cn.bookmanager.controller;

import cn.bookmanager.service.IndexService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "HttpStatusController",description = "HttpStatusController")
@RestController()
@RequestMapping("/")
public class HttpStatusController {

    /**
     * 没有权限
     * 只返回401
     */
    @Tag(name = "HttpStatusController")
    @Operation(summary = "401",description = "401")
    @GetMapping("/401")
    public String notFound(HttpServletResponse response){
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        return " 冇权限 401";
    }

    /**
     * 登录吧
     * 只返回302
     */
    @Tag(name = "HttpStatusController")
    @Operation(summary = "302",description = "302")
    @GetMapping("/302")
    public String found(HttpServletResponse response){
        response.setStatus(HttpStatus.FOUND.value());
        return " 冇登录 302";
    }

}
