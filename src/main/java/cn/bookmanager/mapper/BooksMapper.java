package cn.bookmanager.mapper;

import cn.bookmanager.entity.Books;
import cn.bookmanager.entity.Record;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

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
     * @param isbn
     * @return
     */
    Books getBookByIsbn(String isbn);
    /**
     * 借书
     * @param recordId
     * @param isbn
     * @param userId
     * @param time
     * @param days
     * @return
     */
    void borrowBooks(String recordId, String isbn, String userId, Date time, int days);

    /**
     * 还书
     * @param recordId
     * @param isbn
     */
    void returnBooks(String recordId,String isbn,String userId);


}
