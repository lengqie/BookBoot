package cn.bookmanager.service.Impl;

import cn.bookmanager.constant.ErrorStatusEnum;
import cn.bookmanager.constant.StatusEnum;
import cn.bookmanager.entity.Book;
import cn.bookmanager.entity.Recommend;
import cn.bookmanager.entity.Record;
import cn.bookmanager.entity.User;
import cn.bookmanager.mapper.BookMapper;
import cn.bookmanager.mapper.RecommendMapper;
import cn.bookmanager.mapper.RecordMapper;
import cn.bookmanager.mapper.UserMapper;
import cn.bookmanager.service.BookService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 书籍一些操作 实现类
 * @author lengqie
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class BookServiceImpl implements BookService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private RecordMapper recordMapper;

    @Autowired
    private RecommendMapper recommendMapper;

    @Override
    public boolean addHot(String isbn) {
        return bookMapper.addHot(isbn) != 0;
    }



    @Override
    public List<Book> getAllBook() {

        return bookMapper.getAllBook();
    }

    @Override
    public Page<Book> getAllBookByPageInfo() {
        return bookMapper.getAllBookByPageInfo();
    }

    @Override
    public List<Book> getHotBook(int n) {

        return bookMapper.getHotBook(n);
    }

    @Override
    public List<Book>  getBookByName(String name) {
        final List<Book> books = bookMapper.getBookByName(name);
        for (Book book : books) {
            bookMapper.addHot(book.getIsbn());
        }
        return books;
    }

    @Override
    public Page<Book> getBookByNamePageInfo(String name) {
        return bookMapper.getBookByNamePageInfo(name);
    }

    @Override
    public List<String> geAllType() {
        return bookMapper.geAllType();
    }

    @Override
    public List<Book> getBookByType(String type) {
        return bookMapper.getBookByType(type);
    }

    @Override
    public Book getBookByIsbn(String isbn) {
        System.out.println(bookMapper.getBookByIsbn(isbn));
        final int i = bookMapper.addHot(isbn);
        if (i == 1){
            return bookMapper.getBookByIsbn(isbn);
        }
        return null;
    }

    @Override
    public Boolean addBook(String isbn, String name, String type, Date date) {
        final int i = bookMapper.addBook(isbn, name, type, date);
        return i==1;
    }
    /**
     * 这个地方 有脱裤子放屁之嫌
     * @param book Book
     * @param isbn Book.Isbn
     * @return 是否成功
     */
    @Override
    public Boolean updateBook(Book book,String isbn) {
        final Book bookByIsbn = bookMapper.getBookByIsbn(isbn);

        return bookMapper.updateBook(book) == 1;
    }

    @Override
    public Boolean delBook(String isbn) {
        return bookMapper.setBookStatus(isbn, StatusEnum.DELETE.getCode() )!=1;
    }

    @Override
    public Boolean downBook(String isbn) {
        return bookMapper.setBookStatus(isbn, StatusEnum.OFF_THE_SHELF.getCode() )!=1;
    }

    @Override
    public Boolean addBookFromRecommend(String id) {
        Recommend recommend = recommendMapper.getRecommendById(id);
        final String name = recommend.getName();
        final String isbn = recommend.getIsbn();
        final String type = recommend.getType();

        Date date = new Date();
        bookMapper.addBook(isbn, name, type, date);
        return true;
    }

    @Override
    public String borrowBook(String isbn, String userId, Date time, int days){

        User u = userMapper.getUserById(userId);
        //  超出最大额度
        // Alibaba错误码 数量超出限制
        if (u.getMax() == u.getCount()) {
            // return "A0425";
            return ErrorStatusEnum.OUT_OF_LIMIT.value();
        }
        // 已欠费
        // Alibaba错误码 账户余额不足 A0601
        if (u.getBalance() <0){
            return ErrorStatusEnum.INSUFFICIENT_BALANCE.value();
        }
        Book b = bookMapper.getBookByIsbn(isbn);
        // 书库存不足
        // Alibaba错误码 数量超出限制 A0425
        if (b.getNum() == 0){
            return ErrorStatusEnum.OUT_OF_LIMIT.value();
        }

        String recordId = UUID.randomUUID().toString().replace("-","");

        bookMapper.borrowBook(isbn,userId,time,days);
        // Alibaba错误码 00000 一切ok
        return ErrorStatusEnum.OK.value();
    }

    @Override
    public String returnBook(String recordId,String isbn, String userId,Date date) {
        final Record record = recordMapper.getRecordById(recordId);

        int days =record.getDays();

        final long now = date.getTime();
        int realDays = (int) ((now - record.getTime().getTime())/(1000*24*60*60));
        if (realDays > days){
            //
            // 逾期 按照天数 扣款
            // 有点问题！
            // double arrears = (days -realDays ) * 0.5;
            // userMapper.overdueCost(userId,arrears);
            // return -arrears + "￥ in arrears";
            // ali: 余额不足
            return ErrorStatusEnum.INSUFFICIENT_BALANCE.value();
        }
        bookMapper.returnBook(recordId,isbn,userId,date);

        return ErrorStatusEnum.OK.value();
    }

}
        // ali:00000 一切OK
