package cn.bookmanager.controller;

import cn.bookmanager.constant.CookieEnum;
import cn.bookmanager.constant.ErrorStatusEnum;
import cn.bookmanager.entity.User;
import cn.bookmanager.service.UserService;
import cn.bookmanager.util.Base64Utils;
import cn.bookmanager.util.Md5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author lengqie
 */

@Tag(name = "UserController")
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * roles[user] 获取用户自己的信息
     * @param request  request
     * @param response response
     */
    @Tag(name = "UserController")
    @Operation(summary = "获取用户自己的信息",description = "获取用户自己的信息")
    @RequiresRoles({"user"})
    @GetMapping("/")
    public User index(HttpServletRequest request,HttpServletResponse response){

        Subject subject = SecurityUtils.getSubject();
        System.out.println(subject.getPrincipal());

        for (Cookie cookie : request.getCookies()) {
            if (CookieEnum.COOKIE_USER.value().equals(cookie.getName())) {
                String name = cookie.getValue();
                name = Base64Utils.decoder(name);
                return userService.getUserByName(name);
            }
        }
        response.setStatus(HttpStatus.NOT_FOUND.value());
        return null;

    }

    /**
     * roles[user] 修改用户
     * @param user User
     * @param response response
     */
    @Tag(name = "UserController")
    @Operation(summary = "修改用户",description = "修改用户")
    @RequiresRoles(value = {"user,admin"},logical = Logical.OR)
    @PutMapping("/")
    public void updateUser(
            @Parameter(description = "用户") User user, HttpServletResponse response){

        if (!userService.updateUser(user) ) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        }
    }

    /**
     * anon 用户登录
     * @param name  User.Name
     * @param password  User.Password
     * @param session   session
     * @param request   request
     * @param response  response
     */
    @Tag(name = "UserController")
    @Operation(summary = "用户登录",description = "用户登录")
    @PostMapping("/login")
    public void login(
            @Parameter(description = "用户名") String name,
            @Parameter(description = "用户密码") String password, HttpSession session, HttpServletRequest request, HttpServletResponse response){

        password = Md5Utils.getMd5(password);

        final Boolean login = userService.isLogin(new User(name, password),session,request,response);

        Subject subject = SecurityUtils.getSubject();
        subject.login(new UsernamePasswordToken(name, password));

        if (!login){
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        }

    }

    /**
     * roles[user] 登出
     * @param session  session
     * @param response response
     */
    @Tag(name = "UserController")
    @Operation(summary = "登出",description = "登出")
    @RequiresUser
    @PostMapping("/logout")
    public void logout(HttpSession session, HttpServletResponse response) {

        //Shiro
        Subject subject  = SecurityUtils.getSubject();

        System.out.println(subject.getPrincipal() +  "logout...");

        session.removeAttribute(CookieEnum.SESSION_USER.value());
        Cookie cookieUsername = new Cookie(CookieEnum.COOKIE_USER.value(), "");
        cookieUsername.setMaxAge(0);
        cookieUsername.setPath("/");
        response.addCookie(cookieUsername);

        //Shiro
        subject.logout();

        // 302
        response.setStatus(HttpStatus.FOUND.value());
    }

    /**
     * roles[user] 用户支付
     * @param userId User.Id
     */
    @Tag(name = "UserController")
    @Operation(summary = "用户支付",description = "用户支付")
    @RequiresRoles({"user"})
    @PostMapping("/{userId}/pay")
    public void pay(
            @Parameter(description = "用户Id") @PathVariable String userId){
        userService.overduePay(userId);
    }

    /**
     * anon 注册
     * @param name      name
     * @param password  password
     * @param response  response
     */
    @Tag(name = "UserController")
    @Operation(summary = "注册",description = "注册")
    @PostMapping("/register")
    public void register(
            @Parameter(description = "用户名") String name,
            @Parameter(description = "用户密码") String password, HttpServletResponse response){

        password = Md5Utils.getMd5(password);

        final String s = userService.registered(name, password);

        // '409' : 'Conflict', //指令冲突
        if (ErrorStatusEnum.ALREADY_EXISTS.value().equals(s)){
            response.setStatus(HttpStatus.CONFLICT.value());
        }
    }
}
