package cn.bookmanager.service;

import cn.bookmanager.entity.Book;
import cn.bookmanager.entity.Record;
import cn.bookmanager.entity.User;
import cn.bookmanager.mapper.BooksMapper;
import cn.bookmanager.mapper.RecordMapper;
import cn.bookmanager.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * 书籍一些操作 实现类
 * @author lengqie
 */

@Service
public class BooksServiceImpl implements BooksService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BooksMapper booksMapper;

    @Autowired
    private RecordMapper recordMapper;

    @Override
    public void addHot(String Isbn) {
        booksMapper.addHot(Isbn);
    }

    @Override
    public Book getBookByIsbn(String isbn) {
        System.out.println(booksMapper.getBookByIsbn(isbn));
        final int i = booksMapper.addHot(isbn);
        if (i == 1){
            return booksMapper.getBookByIsbn(isbn);
        }
        return null;
    }


    @Override
    public String borrowBooks(String isbn, String userId, Date time, int days){

        User u = userMapper.getUserById(userId);
        // 满了！
        if (u.getMax() == u.getCount()) {
            return "exceed the upper limit";
        }
        // 无💴！
        if (u.getBalance() <0){
            return "insufficient balance";
        }
        Book b = booksMapper.getBooksInfo(isbn);
        // 无📕！
        if (b.getNum() == 0){
            return "no remaining";
        }

        String recordId = UUID.randomUUID().toString().replace("-","");

        booksMapper.borrowBooks(recordId,isbn,userId,time,days);
        return "Ok";
    }

    @Override
    public String returnBooks(String recordId,String isbn, String userId,Date date) {
        final Record record = recordMapper.getRecordByRecordId(recordId);

        int days =record.getDays();

        final long now = date.getTime();
        int realDays = (int) ((now - record.getTime().getTime())/(1000*24*60*60));
        if (realDays > days){
            //
            // 逾期 按照天数 扣款
            // 有点问题！
            //
            double arrears = (days -realDays ) * 0.5;
            userMapper.overdueCost(userId,arrears);
            return -arrears + "￥ in arrears";
        }
        booksMapper.returnBooks(recordId,isbn,userId,date);

        return "ok";
    }
}
