package cn.bookmanager.mapper;

import cn.bookmanager.entity.Books;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @author lengqie
 */
@Mapper
@Repository
public interface BooksMapper {

    /**
     * 获取书籍的信息
     * @param Isbn
     * @return
     */
    Books getBooksInfo(String Isbn);

    /**
     * 添加书籍的热度
     * @param isbn
     */
    void addHot(String isbn);

    /**
     * 获取一本书的详情
     * @param Isbn
     * @return
     */
    Books getOneBook(String Isbn);
    /**
     * 借书
     * @param id
     * @param Isbn
     * @param time
     * @param Days
     * @return
     */
    void borrowBooks(String Isbn, String id, Date time, int Days);
}
