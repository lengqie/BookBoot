package cn.bookmanager.config;

import cn.bookmanager.interceptor.LoginInterceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**拦截器的配置...
 * @author lengqie
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(loginInterceptor)
                    .addPathPatterns("/user/*","/admin/*")
                    .excludePathPatterns("/admin/login","/admin/logout")
                    .excludePathPatterns("/user/login","/user/logout")
                    ;
        }
}
