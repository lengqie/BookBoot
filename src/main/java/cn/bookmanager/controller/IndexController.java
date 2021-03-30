package cn.bookmanager.controller;

import cn.bookmanager.entity.Books;
import cn.bookmanager.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 首页信息
 * @author lengqie
 */
@RestController()
@RequestMapping("/")
public class IndexController {

    @Autowired
    IndexService indexService;

    @GetMapping("/allBooks")
    public List<?> getAllbooks(){
        return indexService.getAllBooks();
    }
    @GetMapping(value = "/allBooks/order")
    public List<?> getAllBooksOrderByHot(){
        return indexService.getAllBooksOrderByHot();
    }

    @GetMapping("/search")
    public List<?> getBookByName(String name){
        return indexService.getBookByName(name);
    }

    @GetMapping("/allType")
    public List<String> getAllType(){
        return indexService.geAllType();
    }

    @GetMapping("/searchByType")
    public List<Books> getBookByType(String type){
        return indexService.getBooksByType(type);
    }

}
