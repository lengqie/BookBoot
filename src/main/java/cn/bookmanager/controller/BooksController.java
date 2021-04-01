package cn.bookmanager.controller;


import cn.bookmanager.entity.Books;
import cn.bookmanager.entity.User;
import cn.bookmanager.service.BooksService;
import cn.bookmanager.utils.ReturnMapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

/**
 * 书籍的...
 * @author lengqie
 */
@RestController
@RequestMapping
public class BooksController {

    @Autowired
    BooksService booksService;

    @PostMapping("/books/addHot")
    public Map<String, String> addHot(String Isbn){

        booksService.addHot(Isbn);
        final Map map = ReturnMapUtils.getMap("200","ok");
        return map;
    }
    @PostMapping("/user/borrow")
    public Map<String, String> borrowBooks(String Isbn, int days, HttpSession session){
        // 默认当天
        Date date =new Date();

        final User user = (User) session.getAttribute("session_user");

        final String s = booksService.borrowBooks(Isbn, user.getId(), date, days);

        final Map map = ReturnMapUtils.getMap("200",s);
        return map;
    }
    @PostMapping("/user/return")
    public Map<String, String> returnBooks(String recordId,String Isbn,HttpSession session){
        // 默认当天
        Date date =new Date();

        final User user = (User) session.getAttribute("session_user");

        final String s = booksService.returnBooks(Isbn, user.getId(),recordId,date);
        final Map map = ReturnMapUtils.getMap("200","ok");
        return map;
    }

    /**
     * 使用 PathVariable 放在最后 防止先被匹配
     * @param Isbn
     * @return
     */
    @GetMapping("/books/{Isbn}")
    public Books getOneBook(@PathVariable String Isbn){
        return booksService.getOneBook(Isbn);
    }

}
