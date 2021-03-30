package cn.bookmanager.service;

import cn.bookmanager.entity.Books;

import java.util.List;

/**
 * @author lengqie
 */
public interface IndexService {
    /**
     * 获取全部书籍
     * @return
     */
    List<Books> getAllBooks();
    /**
     * 获取全部书籍 排序后输出
     * @return
     */
    List<Books> getAllBooksOrderByHot();

    /**
     * 查询数据
     * @return
     */
    List<Books> getBookByName(String name);
}