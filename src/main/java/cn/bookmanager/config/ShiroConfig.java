package cn.bookmanager.config;

import cn.bookmanager.realm.AdminRealm;
import cn.bookmanager.realm.UserRealm;
import javax.servlet.Filter;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SessionsSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;

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
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager securityManager,
                                              ShiroCNInvalidRequestFilter invalidRequestFilter
                                              ) {
        ShiroFilterFactoryBean  bean = new ShiroFilterFactoryBean();

        bean.setSecurityManager(securityManager());

        // ??????????????? StatusController
        bean.setLoginUrl("/302");
        bean.setUnauthorizedUrl("/401");

        // ?????????
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<>();

        // HttpStatus
        filterChainDefinitionMap.put("/401","anon");
        filterChainDefinitionMap.put("/302","anon");

        filterChainDefinitionMap.put("/","anon");

        //swagger
        filterChainDefinitionMap.put("/swagger-ui/**","anon");
        filterChainDefinitionMap.put("/swagger-resources/**","anon");
        filterChainDefinitionMap.put("/v3/api-docs/**","anon");

        filterChainDefinitionMap.put("/admins/login","anon");

        filterChainDefinitionMap.put("/books","anon");
        filterChainDefinitionMap.put("/books/isbn/*","anon");
        filterChainDefinitionMap.put("/books/page/**","anon");
        filterChainDefinitionMap.put("/books/type","anon");
        filterChainDefinitionMap.put("/books/type/*","anon");
        filterChainDefinitionMap.put("/books/hot","anon");
        filterChainDefinitionMap.put("/books/hot/*","anon");
        filterChainDefinitionMap.put("/books/name/**","anon");

        filterChainDefinitionMap.put("/users/register","anon");
        filterChainDefinitionMap.put("/users/login","anon");
        filterChainDefinitionMap.put("/my/*","anon");

        filterChainDefinitionMap.put("/**", "authc");
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        Map<String, Filter> filters =new LinkedHashMap<>();
        filters.put("invalid", invalidRequestFilter);

        bean.setFilters(filters);

        List<String> globalFilters = new LinkedList<>();
        globalFilters.add("invalid");
        bean.setGlobalFilters(globalFilters);

        return bean;
    }

    /**
     *
     * ???????????????Shiro?????????(???@RequiresRoles,@RequiresPermissions),?????????SpringAOP????????????Shiro????????????,???????????????????????????????????????
     * ??????????????????bean(DefaultAdvisorAutoProxyCreator???AuthorizationAttributeSourceAdvisor)?????????????????????
     * Enable Shiro Annotations for Spring-configured beans. Only run after the lifecycleBeanProcessor(???????????????Shiro??????lifecycle?????????bean??????) has run
     * ??????????????????????????????????????????????????????
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
     * cookie??????
     * @return
     */
    public SimpleCookie rememberMeCookie() {
        // ??????cookie???????????????login.html?????????<input type="checkbox" name="rememberMe"/>
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        // ??????cookie????????????????????????????????????????????????
        cookie.setMaxAge(86400);
        return cookie;
    }

    /**
     * cookie????????????
     * @return
     */
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        // rememberMe cookie???????????????
        // [-36, 11, -43, -122, 97, 75, 82, -51, 10, 76, 13, -54, -90, -69, 29, 106]
        cookieRememberMeManager.setCipherKey(Base64.getDecoder().decode("3AvVhmFLUs0KTA3Kprsdag=="));
        return cookieRememberMeManager;
    }

}

