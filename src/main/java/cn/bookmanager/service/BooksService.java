package cn.bookmanager.service;

import cn.bookmanager.entity.Books;

import java.util.Date;

/**
 * 书籍相关的一些操作
 * @author lengqie
 */
public interface BooksService {

    /**
     * 为所选的书籍 增加热度
     * @param Isbn
     */
    void addHot(String Isbn);

    /**
     * 获取书的详情
     * 每查询一次 则 热度加一
     * @param Isbn
     * @return
     */
    Books getOneBook(String Isbn);
    /**
     * 借书
     * @param isbn
     * @param userId
     * @param time
     * @param days
     * @return
     */
    String borrowBooks(String isbn, String userId, Date time, int days);

    /**
     * 还书
     * @param recordId
     * @param isbn
     * @param userId
     * @param date
     * @return
     */
    String returnBooks(String recordId,String isbn, String userId,Date date);
}
