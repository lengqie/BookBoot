package cn.bookmanager.config;

import cn.bookmanager.realm.AdminRealm;
import cn.bookmanager.realm.UserRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SessionsSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

/**
 * @author lengqie
 */
@Configuration
public class ShiroConfig {

    /**
     * realm
     * SecurityManager
     * ShiroFilterFactoryBean
     */
    @Bean
    public UserRealm userRealm() {
        return new UserRealm();
    }
    @Bean
    public AdminRealm adminRealm() {
        return new AdminRealm();
    }

    @Bean
    public SessionsSecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        List<Realm> realms = new LinkedList<>();
        realms.add(userRealm());
        realms.add(adminRealm());
        securityManager.setRememberMeManager(rememberMeManager());

        securityManager.setRealms(realms);
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager securityManager) {
        ShiroFilterFactoryBean  bean = new ShiroFilterFactoryBean();

        bean.setSecurityManager(securityManager());

        // 重定向到了 StatusController
        bean.setLoginUrl("/302");
        bean.setUnauthorizedUrl("/401");

        // 拦截器
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<>();

        // HttpStatus
        filterChainDefinitionMap.put("/401","anon");
        filterChainDefinitionMap.put("/302","anon");

        //swagger
        filterChainDefinitionMap.put("/swagger-ui/**","anon");
        filterChainDefinitionMap.put("/swagger-resources/**","anon");
        filterChainDefinitionMap.put("/v3/api-docs/**","anon");

        //
        filterChainDefinitionMap.put("/admin/login","anon");

        filterChainDefinitionMap.put("/books","anon");
        filterChainDefinitionMap.put("/books/*","anon");
        filterChainDefinitionMap.put("/books/type","anon");
        filterChainDefinitionMap.put("/books/type/*","anon");
        filterChainDefinitionMap.put("/books/hot","anon");
        filterChainDefinitionMap.put("/books/hot/*","anon");

        filterChainDefinitionMap.put("/users/register","anon");
        filterChainDefinitionMap.put("/users/login","anon");

        filterChainDefinitionMap.put("/**", "authc");
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return bean;
    }

    /**
     *
     * 描述：开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator和AuthorizationAttributeSourceAdvisor)即可实现此功能
     * Enable Shiro Annotations for Spring-configured beans. Only run after the lifecycleBeanProcessor(保证实现了Shiro内部lifecycle函数的bean执行) has run
     * 不使用注解的话，可以注释掉这两个配置
     */

    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor
                = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * cookie对象
     * @return
     */
    public SimpleCookie rememberMeCookie() {
        // 设置cookie名称，对应login.html页面的<input type="checkbox" name="rememberMe"/>
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        // 设置cookie的过期时间，单位为秒，这里为一天
        cookie.setMaxAge(86400);
        return cookie;
    }

    /**
     * cookie管理对象
     * @return
     */
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        // rememberMe cookie加密的密钥
        // [-36, 11, -43, -122, 97, 75, 82, -51, 10, 76, 13, -54, -90, -69, 29, 106]
        cookieRememberMeManager.setCipherKey(Base64.getDecoder().decode("3AvVhmFLUs0KTA3Kprsdag=="));
        return cookieRememberMeManager;
    }

}

