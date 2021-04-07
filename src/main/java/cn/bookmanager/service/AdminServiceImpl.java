package cn.bookmanager.service;

import cn.bookmanager.entity.Admin;
import cn.bookmanager.entity.Book;
import cn.bookmanager.entity.Recommend;
import cn.bookmanager.entity.User;
import cn.bookmanager.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @author lengqie
 */
@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private RecommendMapper recommendMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private RecordMapper recordMapper;

    @Autowired
    private IndexMapper indexMapper;


    @Override
    public Admin login(String name, String password) {
        final Admin admin = adminMapper.login(name, password);
        return admin;
    }

    @Override
    public Boolean isLogin(Admin admin, HttpSession session, HttpServletRequest request, HttpServletResponse response){
        final int login = adminMapper.isLogin(admin);
        // 登录成功 则 写入Cookie！
        if (login ==1){

            session.setAttribute("session_admin",admin);
            Cookie cookie_admin = new Cookie("cookie_admin",admin.getName());
            cookie_admin.setMaxAge(60 * 60 * 24 * 7);
            cookie_admin.setPath("/");
            response.addCookie(cookie_admin);

            return true;
        }
        return false;
    }

    @Override
    public Admin getAdmin() {
        return adminMapper.getAdminByName("root");
    }

    @Override
    public Boolean updateUser(User user) {
        final int i = userMapper.updateUser(user);
        return i == 1;
    }


    @Override
    public Boolean updateRecord(String recordId, int success) {
        return recordMapper.updateRecord(recordId,success) == 1;
    }

    @Override
    public List<Recommend> getAllRecommend() {
        return recommendMapper.getAllRecommend();
    }


    @Override
    public Recommend getRecommendById(String id) {
        return recommendMapper.getRecommendById(id);
    }


    @Override
    public Boolean addBook(String isbn, String name, String type, Date date) {
        final int i = bookMapper.addBook(isbn, name, type, date);
        return i==1;
    }
    /**
     * 这个地方 有脱裤子放屁之嫌
     * @param book
     * @param isbn
     * @return
     */
    @Override
    public Boolean updateBook(Book book,String isbn) {
        final Book bookByIsbn = bookMapper.getBookByIsbn(isbn);

        return bookMapper.updateBook(book) == 1;
    }

    @Override
    public Boolean delBook(String isbn) {
        return bookMapper.delBook(isbn)!=1;
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
}
