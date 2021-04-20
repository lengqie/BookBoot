package cn.bookmanager.controller;


import cn.bookmanager.constant.CookieEnum;
import cn.bookmanager.entity.Book;
import cn.bookmanager.entity.User;
import cn.bookmanager.service.BookService;
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
    private BookService bookService;


    @PostMapping("/book")
    public void addBook(String isbn,String name ,String type, HttpServletResponse response){
        Date date = new Date();
        if (!bookService.addBook(isbn,name, type,date)) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        }
    }

    @DeleteMapping("/book/{isbn}")
    public void delBookByIsbn(@PathVariable String isbn){
        if (bookService.delBook(isbn)) {

        }
    }

    @PutMapping("/book/{isbn}")
    public void upBook(Book book,@PathVariable String isbn, HttpServletResponse response){
        if (!bookService.updateBook(book,isbn) ) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        }
    }

    @PostMapping("/book/recommend/{id}")
    public void addBookFromRecommend(@PathVariable String id){
        bookService.addBookFromRecommend(id);

    }

    @PutMapping("/book/hot")
    public void addHot(String isbn){

        bookService.addHot(isbn);
    }

    @PostMapping("/book/user/borrow")
    public void borrowBooks(String isbn, int days, HttpSession session){
        // 默认当天
        Date date =new Date();

        final User user = (User) session.getAttribute(CookieEnum.COOKIE_USER.value());

        // service never used
        bookService.borrowBooks(isbn, user.getId(), date, days);


        // return ReturnMapUtils.getMap("200",s);
    }
    @PostMapping("/book/user/return")
    public void returnBooks(String recordId,String isbn, HttpSession session){
        // 默认当天
        Date date =new Date();

        final User user = (User) session.getAttribute(CookieEnum.SESSION_ADMIN.value());

        bookService.returnBooks(isbn, user.getId(),recordId,date);
    }

    /**
     * 使用 PathVariable 放在最后 防止先被匹配
     * @param isbn Isbn
     * @return book
     */
    @GetMapping("/book/{isbn}")
    public Book getBookByIsbn(@PathVariable String isbn, HttpServletResponse response){
        final Book book = bookService.getBookByIsbn(isbn);
        if (book != null) {
            return book;
        }
        response.setStatus(HttpStatus.NOT_FOUND.value());
        return null;
    }

}
