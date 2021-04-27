package cn.bookmanager.mapper;

import cn.bookmanager.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author lengqie
 */
@Mapper
@Repository
public interface BookMapper {

    /**
     * 添加书籍的热度
     * @param isbn ISBN
     * @return 操作的数量 成功返回1
     */
    int addHot(String isbn);

    /**
     * 查询全部书籍
     * @return Book s
     */
    List<Book> getAllBook();

    /**
     * 查询全部书籍 并排序 Top5
     * @return Book s
     */
    List<Book> getHotBook(int n);

    /**
     * 通过书籍查找
     * @param name Book Name
     * @return Book s
     */
    List<Book> getBookByName(String name);

    /**
     * 获取书籍的全部类型 根据热度 排序
     * @return 最火的五本书籍
     */
    List<String> geAllType();

    /**
     * 用过类型查找 书籍
     * @param type Book.Type
     * @return Book s
     */
    List<Book> getBookByType(String type);


    /**
     * 获取一本书的详情
     * @param isbn ISBN
     * @return Book Book
     */
    Book getBookByIsbn(String isbn);

    /**
     * 修改书籍
     * @param book Book
     * @return 操作的数量 成功返回1
     */
    int updateBook(Book book);


    /**
     * 添加书籍
     * @param isbn ISBN
     * @param name Book.Name
     * @param type Book.Type
     * @param date Book.Date
     * @return  操作的数量 成功返回1
     */
    int addBook(String isbn,String name,String type, Date date);

    /**
     * 修改书籍的状态
     * @param isbn Isbn
     * @param status Book
     * @return 操作的数量 成功返回1
     */
    int setBookStatus(String isbn,int status);

    /**
     * 借书
     * @param isbn ISBN
     * @param userId User.Id
     * @param createTime CreateTime
     * @param days Days
     */
    void borrowBook(String isbn, String userId, Date createTime, int days);

    /**
     * 还书
     * @param recordId  RecordId
     * @param isbn  Isbn
     * @param userId User.Id
     * @param updateTime UpdateTime
     */
    void returnBook(String recordId,String isbn,String userId,Date updateTime);


}
