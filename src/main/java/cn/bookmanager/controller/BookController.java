package cn.bookmanager.controller;


import cn.bookmanager.constant.CookieEnum;
import cn.bookmanager.constant.ErrorStatusEnum;
import cn.bookmanager.entity.Book;
import cn.bookmanager.entity.RequestPage;
import cn.bookmanager.entity.User;
import cn.bookmanager.service.BookService;
import cn.bookmanager.util.ReturnMapUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.shiro.authz.annotation.RequiresRoles;
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
@Tag(name = "BookController",description = "书籍的一些操作")
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
    @Tag(name = "BookController")
    @Operation(summary = "向书籍中添加书籍",description = "向书籍中添加书籍")
    @RequiresRoles({"admin"})
    @PostMapping("/book/{isbn}/name/{name}/type/{type}")
    public void addBook(
            @Parameter(description = "isbn") @PathVariable String isbn,
            @Parameter(description = "书名") @PathVariable String name ,
            @Parameter(description = "书籍类型") @PathVariable String type, HttpServletResponse response){

        Date date = new Date();
        if (!bookService.addBook(isbn,name, type,date)) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        }
    }

    /**
     * roles[admin] 删除书籍 将书籍的状态设置成 -1
     * @param isbn Book.Isbn
     */
    @Tag(name = "BookController", description = "删除书籍（讲书籍状态设置成 -1）")
    @Operation(summary = "删除书籍",description = "删除书籍（讲书籍状态设置成 -1）")
    @RequiresRoles({"admin"})
    @DeleteMapping("/book/{isbn}")
    public void delBookByIsbn(
            @Parameter(description = "Isbn") @PathVariable String isbn, HttpServletResponse response){

        if (! bookService.delBook(isbn)) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        }
    }

    // /**
    //  * 放弃使用  直接通过 修改书籍信息
    //  * roles[admin] 下架书籍书籍 将书籍的状态设置成 -1
    //  * @param isbn Book.Isbn
    //  */
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
    @Tag(name = "BookController", description = "更新书籍")
    @Operation(summary = "更新书籍",description = "更新书籍")
    @RequiresRoles({"admin"})
    @PutMapping("/books/{isbn}")
    public void updateBook(
            @Parameter(description = "Book") Book book,
            @Parameter(description = "Isbn") @PathVariable String isbn, HttpServletResponse response){

        if (!bookService.updateBook(book,isbn) ) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        }
    }

    /**
     * roles[admin] 将推荐书籍添加到书籍中
     * @param recommendId Recommend.Id
     */
    @Tag(name = "BookController",description = "将推荐书籍添加到书籍中")
    @Operation(summary = "将推荐书籍添加到书籍中",description = "将推荐书籍添加到书籍中")
    @RequiresRoles({"admin"})
    @PostMapping("/books/recommend/{recommendId}")
    public void addBookFromRecommend(
            @Parameter(description = "推荐Id") @PathVariable String recommendId, HttpServletResponse response){

        if (!bookService.addBookFromRecommend(recommendId)) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        }
    }

    /**
     * anon 增加书籍的热度 +1
     * @param isbn  Book.Isbn
     */
    @Tag(name = "BookController", description = "增加书籍的热度")
    @Operation(summary = "增加书籍的热度",description = "增加书籍的热度 +1")
    @RequiresRoles({"admin"})
    @PutMapping("/books/hot/{isbn}")
    public void addHot(
            @Parameter(description = "Isbn") @PathVariable String isbn){

        bookService.addHot(isbn);
    }

    /**
     *  roles[user] 借书
     * @param isbn  Book.Isbn
     * @param days  借书的时间（/天）
     * @param session session
     * @return Map
     */
    @Tag(name = "BookController",description = "借书")
    @Operation(summary = "借书",description = "借书")
    @RequiresRoles({"user"})
    @PostMapping("/books/{isbn}/days/{days}")
    public Map<String,String> borrowBook(
            @Parameter(description = "Isbn") @PathVariable String isbn,
            @Parameter(description = "天数") @PathVariable int days, HttpSession session, HttpServletResponse response){

        // 默认当天
        Date date =new Date();

        final User user = (User) session.getAttribute(CookieEnum.COOKIE_USER.value());

        final String s = bookService.borrowBook(isbn, user.getId(), date, days);
        if (ErrorStatusEnum.OK.value().equals(s)){
            response.setStatus(HttpStatus.ACCEPTED.value());
            return ReturnMapUtils.getMap("200");
        }
        if (ErrorStatusEnum.OUT_OF_LIMIT.value().equals(s)){
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return ReturnMapUtils.getMap(ErrorStatusEnum.OUT_OF_LIMIT.value(),"超出上限");
        }
        if (ErrorStatusEnum.INSUFFICIENT_BALANCE.value().equals(s)){
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return ReturnMapUtils.getMap(ErrorStatusEnum.INSUFFICIENT_BALANCE.value(),"余额不足");
        }
        return null;
    }

    /**
     * roles[user]还书
     * @param recordId  Record.Id
     * @param isbn      Book.Isbn
     * @param session   session
     */
    @Tag(name = "BookController",description = "还书")
    @Operation(summary = "还书",description = "还书")
    @RequiresRoles({"user"})
    @PostMapping("/books/{isbn}/records/{recordId}")
    public void returnBook(
            @Parameter(description = "记录Id") @PathVariable String recordId,
            @Parameter(description = "Isbn") @PathVariable String isbn, HttpSession session){

        // 默认当天
        Date date =new Date();

        final User user = (User) session.getAttribute(CookieEnum.SESSION_ADMIN.value());

        bookService.returnBook(isbn, user.getId(),recordId,date);
    }

    /**
     * 获取全部的书籍
     * @return book s
     */
    @Tag(name = "BookController",description = "获取全部的书籍")
    @Operation(summary = "获取全部的书籍",description = "获取全部的书籍")
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
     * 获取全部的书籍
     * @return book s
     */
    @Tag(name = "BookController",description = "分页查找查询书籍")
    @Operation(summary = "查询书籍",description = "分页查找")
    @GetMapping("/books/page/{page}/size/{size}")
    public PageInfo<Book> getAllBookPage(
            @Parameter(description = "页码") @PathVariable int page,
            @Parameter(description = "数量") @PathVariable int size){

        RequestPage requestPage = new RequestPage();
        requestPage.setPage(page);
        requestPage.setSize(size);

        PageHelper.startPage(requestPage.getPage(), requestPage.getSize());

        final Page<Book> allBook = bookService.getAllBookByPageInfo();
        return new PageInfo<>(allBook);

    }
    /**
     * 获取全部的书籍的类型
     * @return book s
     */
    @Tag(name = "BookController",description = "获取全部的书籍的类型")
    @Operation(summary = "获取全部的书籍的类型",description = "获取全部的书籍的类型")
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
     * 根据类型获取全部是书籍
     * @return book s
     */
    @Tag(name = "BookController",description = "根据类型获取全部是书籍")
    @Operation(summary = "根据类型获取全部是书籍",description = "根据类型获取全部是书籍")
    @GetMapping("/books/types/{type}")
    public List<Book>  getBookByType(
            @Parameter(description = "书籍Type") @PathVariable String type,HttpServletResponse response){
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
    @Tag(name = "BookController",description = "获取当前书籍中最火的五本")
    @Operation(summary = "获取当前书籍中最火的五本",description = "获取当前书籍中最火的五本")
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
    @Tag(name = "BookController",description = "获取当前书籍中最火的n本")
    @Operation(summary = "获取当前书籍中最火的n本",description = "获取当前书籍中最火的n本")
    @GetMapping("/books/hot/{n}")
    public List<Book> getAllBookByHotN(
            @Parameter(description = "数量") @PathVariable int n,HttpServletResponse response){

        // 合法性检测
        if (n <=0 ){
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
    //  */
    @Tag(name = "BookController",description = "查找书籍")
    @Operation(summary = "查找书籍",description = "查找书籍")
    @GetMapping("/books/name")
    public List<Book> searchBook(
            @Parameter(description = "关键词") String name, HttpServletResponse response){

        final List<Book> allBook = bookService.getBookByName(name);
        if (!allBook.isEmpty()) {
            return allBook;
        }
        response.setStatus(HttpStatus.NOT_FOUND.value());
        return null;
    }

    /**
     * 获取全部的书籍
     * @return book s
     */
    @Tag(name = "BookController",description = "分页查找查询书籍")
    @Operation(summary = "查询书籍",description = "分页查找")
    @GetMapping("/books/name/page/{page}/size/{size}")
    public PageInfo<Book> searchBookPage(
            @Parameter(description = "关键词") String  name,
            @Parameter(description = "页码") @PathVariable int page,
            @Parameter(description = "数量") @PathVariable int size, HttpServletResponse response){

        RequestPage requestPage = new RequestPage();
        requestPage.setPage(page);
        requestPage.setSize(size);

        PageHelper.startPage(requestPage.getPage(), requestPage.getSize());

        final Page<Book> allBook = bookService.getBookByNamePageInfo(name);
        return new PageInfo<>(allBook);

    }

    /**
     * 使用 PathVariable 放在最后 防止先被匹配
     * @param isbn Isbn
     * @return book
     */

    @Tag(name = "BookController")
    @Operation(summary = "获取书籍的详细信息",description = "获取书籍的详细信息")
    @GetMapping("/books/isbn/{isbn}")
    public Book getBookByIsbn(
            @Parameter(description = "Isbn") @PathVariable String isbn, HttpServletResponse response){
        final Book book = bookService.getBookByIsbn(isbn);
        if (book != null) {
            return book;
        }
        response.setStatus(HttpStatus.NOT_FOUND.value());
        return null;
    }

}
