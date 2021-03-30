package cn.bookmanager.service;

import cn.bookmanager.entity.Books;
import cn.bookmanager.mapper.BooksMapper;
import cn.bookmanager.mapper.IndexMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 首页
 * @author lengqie
 */
@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    IndexMapper indexMapper;

    @Autowired
    BooksMapper booksMapper;

    @Override
    public List<Books> getAllBooks() {
        final List<Books> books = indexMapper.getAllBooks();

        return books;
    }

    @Override
    public List<Books> getAllBooksOrderByHot() {
        final List<Books> books = indexMapper.getAllBooksOrderByHot();

        return books;
    }

    @Override
    public List<Books>  getBookByName(String name) {
        final List<Books> books = indexMapper.getBookByName(name);
        for (Books book : books) {
            booksMapper.addHot(book.getIsbn());
        }
        return books;
    }
}
