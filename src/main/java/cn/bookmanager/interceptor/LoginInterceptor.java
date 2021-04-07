package cn.bookmanager.interceptor;

import cn.bookmanager.entity.Admin;
import cn.bookmanager.entity.User;
import cn.bookmanager.mapper.AdminMapper;
import cn.bookmanager.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.PrintWriter;

/**
 * 权限拦截器...
 * @author lengqie
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        String SESSION_NAME;
        String COOKIE_NAME;

        if (request.getServletPath().contains("/admin")) {
            SESSION_NAME ="session_admin";
            COOKIE_NAME = "cookie_admin";


        }
        else {
            SESSION_NAME ="session_user";
            COOKIE_NAME = "cookie_user";
        }

        final Cookie[] cookies = request.getCookies();

        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("utf-8");

        final PrintWriter writer = response.getWriter();

        // 无Cookie
        if (cookies == null ){
            // final Map map = ReturnMapUtils.getMap("401", "unauthorized");
            // String s = JSON.toJSONString(map);
            // writer.write(s);

            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }

        String cookieNameVal =null;
        for (Cookie cookie : cookies) {
            // 找到 cookie_admin_name
            if (COOKIE_NAME.equals(cookie.getName())) {
                cookieNameVal = cookie.getValue();
                break;
            }
        }
        // 没有 指定的Cookie
        if (cookieNameVal ==null || "".equals(cookieNameVal)) {
            // final Map map = ReturnMapUtils.getMap("401", "unauthorized");
            // String s = JSON.toJSONString(map);
            // writer.write(s);

            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }
        // 获取HttpSession对象
        HttpSession session = request.getSession();
        // 获取我们登录后存在session中的用户信息，如果为空，表示session已经过期

        Object obj = session.getAttribute(SESSION_NAME);
        if (null == obj) {
            if ("session_admin".equals(SESSION_NAME)){
                // 根据用户登录账号获取数据库中的用户信息
                Admin admin = adminMapper.getAdminByName(cookieNameVal);
                // 将用户保存到session中
                session.setAttribute(SESSION_NAME, admin);
            }
            else{
                User user = userMapper.getUserByName(cookieNameVal);
                // 将用户保存到session中
                session.setAttribute(SESSION_NAME, user);
            }
        }
        //已经登录
        // 关闭 response 防止 // getWriter() has already been called for this response
        response.reset();
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
