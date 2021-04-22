package cn.bookmanager.controller;


import cn.bookmanager.constant.CookieEnum;
import cn.bookmanager.constant.StatusEnum;
import cn.bookmanager.entity.Book;
import cn.bookmanager.entity.User;
import cn.bookmanager.service.BookService;
import cn.bookmanager.util.ReturnMapUtil;
import net.bytebuddy.asm.Advice;
import org.apache.commons.collections.MapUtils;
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
public class BookController {

    @Autowired
    private BookService bookService;


    /**
     * roles[admin] 向数据库中添加书籍
     * @param isbn     Book.Isbn
     * @param name     Book.Name
     * @param type     Book.Type
     * @param response response
     */
    @PostMapping("/book/{isbn}/{name}/{type}")
    public void addBook(@PathVariable String isbn,@PathVariable String name ,@PathVariable String type,@PathVariable HttpServletResponse response){
        Date date = new Date();
        if (!bookService.addBook(isbn,name, type,date)) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        }
    }

    /**
     * roles[admin] 删除书籍 将书籍的状态设置成 -1
     * @param isbn Book.Isbn
     */

    @DeleteMapping("/book/{isbn}")
    public void delBookByIsbn(@PathVariable String isbn){
        if (bookService.delBook(isbn)) {

        }
    }

    /**
     * roles[admin] 更新书籍
     * @param book  Book
     * @param isbn  Book.Isbn
     * @param response response
     */
    @PutMapping("/book/{isbn}")
    public void upBook(Book book,@PathVariable String isbn, HttpServletResponse response){
        if (!bookService.updateBook(book,isbn) ) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        }
    }

    /**
     * roles[admin] 将推荐书籍添加到书籍中
     * @param recommendId Recommend.Id
     */
    @PostMapping("/book/recommend/{recommendId}")
    public void addBookFromRecommend(@PathVariable String recommendId){
        bookService.addBookFromRecommend(recommendId);

    }

    /**
     * anon 增加时间的热度 +1
     * @param isbn  Book.Isbn
     */
    @PutMapping("/book/hot/{isbn}")
    public void addHot(@PathVariable String isbn){

        bookService.addHot(isbn);
    }

    /**
     *  roles[user] 借书
     * @param isbn  Book.Isbn
     * @param days  借书的时间（/天）
     * @param session session
     * @return Map
     */
    @PostMapping("/book/{isbn}/{days}")
    public Map<String,String> borrowBooks(@PathVariable String isbn, @PathVariable int days, HttpSession session, HttpServletResponse response){
        // 默认当天
        Date date =new Date();

        final User user = (User) session.getAttribute(CookieEnum.COOKIE_USER.value());

        // service never used
        final String s = bookService.borrowBooks(isbn, user.getId(), date, days);
        if (StatusEnum.OK.value().equals(s)){
            response.setStatus(HttpStatus.ACCEPTED.value());
            return ReturnMapUtil.getMap("200");
        }
        if (StatusEnum.OUT_OF_LIMIT.value().equals(s)){
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return ReturnMapUtil.getMap(StatusEnum.OUT_OF_LIMIT.value(),"超出上限");
        }
        if (StatusEnum.INSUFFICIENT_BALANCE.value().equals(s)){
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return ReturnMapUtil.getMap(StatusEnum.INSUFFICIENT_BALANCE.value(),"余额不足");
        }
        return null;
    }

    /**
     * 还书
     * @param recordId  Record.Id
     * @param isbn      Book.Isbn
     * @param session   session
     */
    @PostMapping("/book/{isbn}/{isbn}")
    public void returnBooks(@PathVariable String recordId,@PathVariable String isbn, HttpSession session){
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
