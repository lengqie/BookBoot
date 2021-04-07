package cn.bookmanager.service;

import cn.bookmanager.entity.Book;
import cn.bookmanager.mapper.BookMapper;
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
    private IndexMapper indexMapper;

    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<Book> getAllBooks() {
        final List<Book> books = indexMapper.getAllBook();

        return books;
    }

    @Override
    public List<Book> getAllBooksOrderByHot() {
        final List<Book> books = indexMapper.getAllBookOrderByHot();

        return books;
    }

    @Override
    public List<Book>  getBookByName(String name) {
        final List<Book> books = indexMapper.getBookByName(name);
        for (Book book : books) {
            bookMapper.addHot(book.getIsbn());
        }
        return books;
    }

    @Override
    public List<String> geAllType() {
        return indexMapper.geAllType();
    }

    @Override
    public List<Book> getBooksByType(String type) {
        return indexMapper.getBookByType(type);
    }
}
