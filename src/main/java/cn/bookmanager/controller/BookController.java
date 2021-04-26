package cn.bookmanager.controller;


import cn.bookmanager.constant.CookieEnum;
import cn.bookmanager.constant.ErrorStatusEnum;
import cn.bookmanager.entity.Book;
import cn.bookmanager.entity.User;
import cn.bookmanager.service.BookService;
import cn.bookmanager.util.ReturnMapUtil;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
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
    @RequiresRoles({"admin"})
    @PostMapping("/book/{isbn}/name/{name}/type/{type}")
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
    @RequiresRoles({"admin"})
    @DeleteMapping("/book/{isbn}")
    public void delBookByIsbn(@PathVariable String isbn){
        if (bookService.delBook(isbn)) {

        }
    }

    /**
     * 放弃使用  直接通过
     * roles[admin] 下架书籍书籍 将书籍的状态设置成 -1
     * @param isbn Book.Isbn
     */
    //
    // @PutMapping("/book/{isbn}")
    // public void downBookByIsbn(@PathVariable String isbn){
    //     if (bookService.downBook(isbn)) {
    //
    //     }
    // }

    /**
     * roles[admin] 更新书籍
     * @param book  Book
     * @param isbn  Book.Isbn
     * @param response response
     */
    @RequiresRoles({"admin"})
    @PutMapping("/books/{isbn}")
    public void updateBook(Book book,@PathVariable String isbn, HttpServletResponse response){
        if (!bookService.updateBook(book,isbn) ) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        }
    }

    /**
     * roles[admin] 将推荐书籍添加到书籍中
     * @param recommendId Recommend.Id
     */
    @RequiresRoles({"admin"})
    @PostMapping("/books/recommend/{recommendId}")
    public void addBookFromRecommend(@PathVariable String recommendId, HttpServletResponse response){
        if (!bookService.addBookFromRecommend(recommendId)) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        }
    }

    /**
     * anon 增加时间的热度 +1
     * @param isbn  Book.Isbn
     */
    @RequiresRoles({"admin"})
    @PutMapping("/books/hot/{isbn}")
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
    @RequiresRoles({"user"})
    @PostMapping("/books/{isbn}/days/{days}")
    public Map<String,String> borrowBook(@PathVariable String isbn, @PathVariable int days, HttpSession session, HttpServletResponse response){
        // 默认当天
        Date date =new Date();

        final User user = (User) session.getAttribute(CookieEnum.COOKIE_USER.value());

        // service never used
        final String s = bookService.borrowBook(isbn, user.getId(), date, days);
        if (ErrorStatusEnum.OK.value().equals(s)){
            response.setStatus(HttpStatus.ACCEPTED.value());
            return ReturnMapUtil.getMap("200");
        }
        if (ErrorStatusEnum.OUT_OF_LIMIT.value().equals(s)){
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return ReturnMapUtil.getMap(ErrorStatusEnum.OUT_OF_LIMIT.value(),"超出上限");
        }
        if (ErrorStatusEnum.INSUFFICIENT_BALANCE.value().equals(s)){
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return ReturnMapUtil.getMap(ErrorStatusEnum.INSUFFICIENT_BALANCE.value(),"余额不足");
        }
        return null;
    }

    /**
     * roles[user]还书
     * @param recordId  Record.Id
     * @param isbn      Book.Isbn
     * @param session   session
     */
    @RequiresRoles({"user"})
    @PostMapping("/books/{isbn}/records/{recordId}")
    public void returnBook(@PathVariable String recordId,@PathVariable String isbn, HttpSession session){
        // 默认当天
        Date date =new Date();

        final User user = (User) session.getAttribute(CookieEnum.SESSION_ADMIN.value());

        bookService.returnBook(isbn, user.getId(),recordId,date);
    }

    /**
     * 获取全部的书籍
     * @return book s
     */
    @GetMapping("/books")
    public List<Book> getAllBook(HttpServletResponse response){
        final List<Book> allBook = bookService.getAllBook();
        if (allBook != null) {
            return allBook;
        }
        response.setStatus(HttpStatus.NOT_FOUND.value());
        return null;
    }
    /**
     * 获取全部的书籍的类型
     * @return book s
     */
    @RequiresGuest()
    @GetMapping("/books/type")
    public List<String> getAllBookType(HttpServletResponse response){
        final List<String> type = bookService.geAllType();
        if (type != null) {
            return type;
        }
        response.setStatus(HttpStatus.NOT_FOUND.value());
        return null;
    }
    /**
     * 获取全部的书籍的类型
     * @return book s
     */
    @RequiresGuest()
    @GetMapping("/books/types/{type}")
    public List<Book>  getBookByType(HttpServletResponse response, @PathVariable String type){
        final List<Book> allBook = bookService.getBookByType(type);
        if (!allBook.isEmpty()) {
            return allBook;
        }
        response.setStatus(HttpStatus.NOT_FOUND.value());
        return null;
    }
    /**
     * 获取当前书籍中最火的五本
     * @return book s
     */

    @GetMapping("/books/hot")
    public List<Book> getAllBookByHot(HttpServletResponse response){
        final List<Book> allBook = bookService.getHotBook(5);
        if (!allBook.isEmpty()) {
            return allBook;
        }
        response.setStatus(HttpStatus.NOT_FOUND.value());
        return null;
    }
    /**
     * 获取当前书籍中最火的n本
     * @return book s
     */
    @GetMapping("/books/hot/{n}")
    public List<Book> getAllBookByHotN(HttpServletResponse response, @PathVariable int n){

        // 不能违法
        if (n >0 ){
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return null;
        }
        final List<Book> allBook = bookService.getHotBook(n);
        if (!allBook.isEmpty()) {
            return allBook;
        }
        response.setStatus(HttpStatus.NOT_FOUND.value());
        return null;
    }
    /**
     * anon 查找书籍 没有使用restful
     * @return book s
     */

    @GetMapping("/books/")
    public List<Book> searchBook(String re,HttpServletResponse response){
        final List<Book> allBook = bookService.getBookByName(re);
        if (!allBook.isEmpty()) {
            return allBook;
        }
        response.setStatus(HttpStatus.NOT_FOUND.value());
        return null;
    }
    /**
     * 使用 PathVariable 放在最后 防止先被匹配
     * @param isbn Isbn
     * @return book
     */

    @GetMapping("/books/{isbn}")
    public Book getBookByIsbn(@PathVariable String isbn, HttpServletResponse response){
        final Book book = bookService.getBookByIsbn(isbn);
        if (book != null) {
            return book;
        }
        response.setStatus(HttpStatus.NOT_FOUND.value());
        return null;
    }

}
