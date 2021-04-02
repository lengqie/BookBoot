package cn.bookmanager.controller;


import cn.bookmanager.entity.Books;
import cn.bookmanager.entity.User;
import cn.bookmanager.service.BooksService;
import cn.bookmanager.utils.ReturnMapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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
    public Map addHot(String Isbn){

        booksService.addHot(Isbn);
        return ReturnMapUtils.getMap("200","ok");
    }
    @PostMapping("/user/borrow")
    public Map borrowBooks(String Isbn, int days, HttpSession session){
        // 默认当天
        Date date =new Date();

        final User user = (User) session.getAttribute("session_user");

        final String s = booksService.borrowBooks(Isbn, user.getId(), date, days);

        return ReturnMapUtils.getMap("200",s);
    }
    @PostMapping("/user/return")
    public Map returnBooks(String recordId, @RequestParam("") String Isbn, HttpSession session){
        // 默认当天
        Date date =new Date();

        final User user = (User) session.getAttribute("session_user");

        final String s = booksService.returnBooks(Isbn, user.getId(),recordId,date);
        return ReturnMapUtils.getMap("200","ok");
    }

    /**
     * 使用 PathVariable 放在最后 防止先被匹配
     * @param Isbn
     * @return
     */
    @GetMapping("/books/{Isbn}")
    public Map getBookByIsbn(@PathVariable String Isbn, HttpServletResponse response){
        final Books book = booksService.getBookByIsbn(Isbn);
        if (book == null) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return ReturnMapUtils.getMap("404","not found");
        }
        return ReturnMapUtils.getMap("200","ok",book);
    }

}
