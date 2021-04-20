package cn.bookmanager.service;

import cn.bookmanager.entity.Book;

import java.util.Date;
import java.util.List;

/**
 * 书籍相关的一些操作
 * @author lengqie
 */
public interface BookService {

    /**
     * 每查询一次 则 热度加一
     * @param isbn Isbn
     * @return 是否添加成功
     */
    boolean addHot(String isbn);

    /**
     * 通过Isbn查询书籍
     * @param isbn Isbn
     * @return Book
     */


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
     * 通过书名查询数据
     * @return Books
     */
    List<Book> getBookByName(String name);


    /**
     * 获取书籍的全部类型
     * @return Book.Type
     */
    List<String> geAllType();

    /**
     * 用过类型查找 书籍
     * @param type Book.Type
     * @return
     */
    List<Book> getBooksByType(String type);

    Book getBookByIsbn(String isbn);

    /**
     * 修改书籍
     * @param book new Book
     * @param isbn Isbn
     * @return 是否修改成功
     */
    Boolean updateBook(Book book,String isbn);

    /**
     * 添加书籍
     * @param isbn Isbn
     * @param name Book.Name
     * @param type Book.Type
     * @param date Date
     * @return 是否添加成功
     */
    Boolean addBook(String isbn, String name, String type, Date date);

    /**
     *  删除书籍
     * @param isbn Isbn
     * @return 是否删除成功
     */
    Boolean delBook(String isbn);

    /**
     * 从Recommend 表中 添加一本书
     * @param id Recommend.id
     * @return 是否成功
     */
    Boolean addBookFromRecommend(String id);

    /**
     * 借书
     * @param isbn Isbn
     * @param userId User.Id
     * @param time Time
     * @param days Days
     * @return 借书的不同状态
     */
    String borrowBooks(String isbn, String userId, Date time, int days);


    /**
     * 还书
     * @param recordId Record.Id
     * @param isbn Isbn
     * @param userId User.Id
     * @param date Date
     * @return 还书的不同状态
     */
    String returnBooks(String recordId,String isbn, String userId,Date date);

}
