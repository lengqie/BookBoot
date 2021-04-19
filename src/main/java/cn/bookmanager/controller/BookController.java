package cn.bookmanager.controller;


import cn.bookmanager.constant.CookieEnum;
import cn.bookmanager.entity.Book;
import cn.bookmanager.entity.User;
import cn.bookmanager.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * 书籍的...
 * @author lengqie
 */
@RestController
@RequestMapping
public class BookController {

    @Autowired
    private BooksService booksService;

    @PutMapping("/book/hot")
    public void addHot(String isbn){

        booksService.addHot(isbn);
    }
    @PostMapping("/user/borrow")
    public void borrowBooks(String isbn, int days, HttpSession session){
        // 默认当天
        Date date =new Date();

        final User user = (User) session.getAttribute(CookieEnum.COOKIE_USER.value());

        booksService.borrowBooks(isbn, user.getId(), date, days);


        // return ReturnMapUtils.getMap("200",s);
    }
    @PostMapping("/user/return")
    public void returnBooks(String recordId,String isbn, HttpSession session){
        // 默认当天
        Date date =new Date();

        final User user = (User) session.getAttribute(CookieEnum.SESSION_ADMIN.value());

        booksService.returnBooks(isbn, user.getId(),recordId,date);
    }

    /**
     * 使用 PathVariable 放在最后 防止先被匹配
     * @param isbn
     * @return book
     */
    @GetMapping("/book/{isbn}")
    public Book getBookByIsbn(@PathVariable String isbn, HttpServletResponse response){
        final Book book = booksService.getBookByIsbn(isbn);
        if (book != null) {
            return book;
        }
        response.setStatus(HttpStatus.NOT_FOUND.value());
        return null;
    }

}
