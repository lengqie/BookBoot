package cn.bookmanager.service;

import cn.bookmanager.entity.Book;

import java.util.List;

/**
 * @author lengqie
 */
public interface IndexService {
    /**
     * 获取全部书籍
     * @return
     */
    List<Book> getAllBooks();
    /**
     * 获取全部书籍 排序后输出
     * @return
     */
    List<Book> getAllBooksOrderByHot();

    /**
     * 查询数据
     * @return
     */
    List<Book> getBookByName(String name);


    /**
     * 获取书籍的全部类型
     * @return
     */
    List<String> geAllType();

    /**
     * 用过类型查找 书籍
     * @param type
     * @return
     */
    List<Book> getBooksByType(String type);
}