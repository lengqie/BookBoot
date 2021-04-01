package cn.bookmanager.service;

import cn.bookmanager.entity.Admin;
import cn.bookmanager.entity.Books;
import cn.bookmanager.entity.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author lengqie
 */
public interface AdminService {
    @Deprecated
    Admin login(String name, String password);

    Boolean isLogin(Admin admin, HttpSession session, HttpServletRequest request, HttpServletResponse response);

    Admin getAdmin();


    Boolean updateUser(User user);

    Boolean updateBook(Books books);

    Boolean updateRecord(String recordId, int success);
}
