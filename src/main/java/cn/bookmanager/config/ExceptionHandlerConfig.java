package cn.bookmanager.config;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理
 * @author lengqie
 */
@ControllerAdvice
@RestController
public class ExceptionHandlerConfig {

    /**
     * AuthenticationException Shiro认证异常
     * @param e
     * @param response
     * @return
     */
    @ExceptionHandler(value = {AuthenticationException.class, AuthorizationException.class})
    public String authenticationExceptionHandler(Exception e, HttpServletResponse response){
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        String error = "👻 出现异常：(权限问题！)"+e;
        return error;
    }

    /**
     * Exception
     * @param e
     * @param response
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public String exceptionHandler(Exception e, HttpServletResponse response){
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        String error = "👻 出现异常："+e;
        return error;
    }
}
