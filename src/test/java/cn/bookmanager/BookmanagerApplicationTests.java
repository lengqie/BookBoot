package cn.bookmanager;

import cn.bookmanager.entity.Admin;
import cn.bookmanager.mapper.AdminMapper;
import cn.bookmanager.service.AdminService;
import cn.bookmanager.service.BooksService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class BookmanagerApplicationTests {

    @Autowired
    AdminService adminService;

    @Test
    void contextLoads() {
        final Admin i = adminService.login("root","root");
        System.out.println(i);

        final Boolean login = adminService.isLogin(new Admin("root", "root"));
        System.out.println(login);
    }

    @Autowired
    BooksService booksService;
    @Test
    void test() {
        booksService.borrowBooks("110-120-112-11", "2020", new Date(),30);
    }

}
