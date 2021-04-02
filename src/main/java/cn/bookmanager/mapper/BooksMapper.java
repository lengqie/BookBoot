package cn.bookmanager.mapper;

import cn.bookmanager.entity.Book;
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
    Book getBooksInfo(String Isbn);

    /**
     * 添加书籍的热度
     * @param isbn
     * return 返回1 则表示修改成功
     */
    int addHot(String isbn);

    /**
     * 获取一本书的详情
     * @param isbn
     * @return
     */
    Book getBookByIsbn(String isbn);
    /**
     * 借书
     * @param recordId
     * @param isbn
     * @param userId
     * @param createTime
     * @param days
     * @return
     */
    void borrowBooks(String recordId, String isbn, String userId, Date createTime, int days);

    /**
     * 还书
     * @param recordId
     * @param isbn
     * @param userId
     * @param modifiedTime
     */
    void returnBooks(String recordId,String isbn,String userId,Date modifiedTime);


}
