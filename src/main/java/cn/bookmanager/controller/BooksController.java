package cn.bookmanager.controller;


import cn.bookmanager.entity.Books;
import cn.bookmanager.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 书籍的...
 * @author lengqie
 */
@RestController
@RequestMapping("/books")
public class BooksController {

    @Autowired
    BooksService booksService;

    @PostMapping("/addHot")
    public String addHot(String Isbn){
        booksService.addHot(Isbn);
        return "ok";
    }
    @PostMapping("/borrow")
    public Map<String, String> borrowBooks(String Isbn, String id, int days){
        // 默认当天
        Date date =new Date();
        Map<String, String> map = new HashMap<>(1);
        final String s = booksService.borrowBooks(Isbn, id, date, days);

        map.put("msg",s);
        return map;
    }

    /**
     * 使用 PathVariable 放在最后 防止先被匹配
     * @param Isbn
     * @return
     */
    @GetMapping("/{Isbn}")
    public Books getOneBook(@PathVariable String Isbn){
        return booksService.getOneBook(Isbn);
    }

}
