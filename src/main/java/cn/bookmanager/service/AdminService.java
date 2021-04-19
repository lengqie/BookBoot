package cn.bookmanager.service;

import cn.bookmanager.entity.Admin;
import cn.bookmanager.entity.Book;
import cn.bookmanager.entity.Recommend;
import cn.bookmanager.entity.User;
import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 *
 * @author lengqie
 */
public interface AdminService {
    /**
     * 登录
     * @param name Admin.Name
     * @param password Admin.Password
     * @return Admin
     */
    @Deprecated
    Admin login(String name, String password);

    /**
     * 登录
     * @param admin Admin
     * @param session HttpSession
     * @param request HttpSession
     * @param response HttpServletResponse
     * @return 登陆成功返回1
     */
    Boolean isLogin(Admin admin, HttpSession session, HttpServletRequest request, HttpServletResponse response);

    /**
     * 获取Admin
     * @return
     */
    Admin getAdmin();

    Boolean updateUser(User user);

    Recommend getRecommendById(String id);
    Boolean updateRecord(String recordId, int success);
    List<Recommend> getAllRecommend();


    Boolean updateBook(Book book,String isbn);
    Boolean addBook(String isbn, String name, String type, Date date);
    Boolean delBook(String isbn);

    Boolean addBookFromRecommend(String id);


}
