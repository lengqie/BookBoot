package cn.bookmanager.controller;

import cn.bookmanager.entity.Book;
import cn.bookmanager.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    private IndexService indexService;

    @GetMapping("/allBooks")
    public List<?> getAllBooks(){
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

    @GetMapping("/search/{type}")
    public List<Book> getBookByType(@PathVariable String type){
        return indexService.getBooksByType(type);
    }

}
