package cn.bookmanager.service;

import cn.bookmanager.entity.Books;
import cn.bookmanager.mapper.BooksMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 书籍一些操作 实现类
 * @author lengqie
 */

@Service
public class BooksServiceImpl implements BooksService {

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
}
