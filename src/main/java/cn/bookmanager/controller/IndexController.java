package cn.bookmanager.controller;

import cn.bookmanager.entity.Book;
import cn.bookmanager.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 首页信息
 * @author lengqie
 */
@RestController()
@RequestMapping("/")
public class IndexController {

    @Autowired
    private IndexService indexService;


    /**
     * 只返回401
     */
    @GetMapping("/401")
    public String notFound(HttpServletResponse response){
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        return " 冇得权限 401！";
    }

}
