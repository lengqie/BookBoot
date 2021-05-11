package cn.bookmanager;

import cn.bookmanager.entity.Admin;
import cn.bookmanager.mapper.AdminMapper;
import cn.bookmanager.service.AdminService;
import cn.bookmanager.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
class BookmanagerApplicationTests {

    @Autowired
    AdminService adminService;

    @Autowired
    AdminMapper adminMapper;

    @Test
    void contextLoads() {
        final Admin i = adminService.login("root","root");
        System.out.println(i);

        // Admin admin = adminMapper.getAdminByName("root");
        // System.out.println(admin);
    }

    @Autowired
    BookService bookService;
    @Test
    void SdfTest() throws ParseException {
        // booksService.borrowBooks("110-120-112-11", "2020", new Date(),30);
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        final Date d= sdf.parse("2021-3-1");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(d.getTime());
        System.out.println(date.getTime());

        System.out.println((date.getTime() - d.getTime())/(1000*60*60*24));
    }

    // @Autowired
    // BookService bookService;
    @Test
    void Page(){
        // booksService.borrowBooks("110-120-112-11", "2020", new Date(),30);
        System.out.println(bookService.getBookByName("红鞋"));
    }


}
