package cn.bookmanager.controller;


import cn.bookmanager.entity.Books;
import cn.bookmanager.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
