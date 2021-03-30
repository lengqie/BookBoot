package cn.bookmanager.service;

import cn.bookmanager.entity.Books;
import cn.bookmanager.entity.User;
import cn.bookmanager.mapper.BooksMapper;
import cn.bookmanager.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 书籍一些操作 实现类
 * @author lengqie
 */

@Service
public class BooksServiceImpl implements BooksService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    BooksMapper booksMapper;

    @Override
    public void addHot(String Isbn) {
        booksMapper.addHot(Isbn);
    }

    @Override
    public Books getOneBook(String Isbn) {
        booksMapper.addHot(Isbn);
        return booksMapper.getOneBook(Isbn);
    }


    @Override
    public String borrowBooks(String Isbn, String id, Date time, int days) {

        User u = userMapper.getUserInfo(id);
        // 满了！
        if (u.getDefaultCount() == u.getCount()) {
            return "exceed the upper limit";
        }
        // 无💴！
        if (u.getBalance() <0){
            return "insufficient balance";
        }
        Books b = booksMapper.getBooksInfo(Isbn);
        // 无📕！
        if (b.getNum() == 0){
            return "no remaining";
        }
        booksMapper.borrowBooks(Isbn,id,time,days);
        return "Ok";
    }
}
