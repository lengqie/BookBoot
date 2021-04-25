package cn.bookmanager.config;

import cn.bookmanager.realm.AdminRealm;
import cn.bookmanager.realm.UserRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SessionsSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
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

        filterChainDefinitionMap.put("/401","anon");
        filterChainDefinitionMap.put("/301","anon");

        // admin
        // roles
        filterChainDefinitionMap.put("/admin/**","roles[admin]");

        //向数据库中添加书籍
        filterChainDefinitionMap.put("/book/*/name/*/type/*","roles[admin]");
        filterChainDefinitionMap.put("/book/*","roles[admin]");
        filterChainDefinitionMap.put("/book/recommend/*","roles[admin]");

        // anon
        filterChainDefinitionMap.put("/admin/login","anon");


        // user
        // roles
        // anon

        //swagger
        filterChainDefinitionMap.put("/swagger-ui/**","anon");
        filterChainDefinitionMap.put("/swagger-resources/**","anon");
        filterChainDefinitionMap.put("/v3/api-docs/**","anon");


        filterChainDefinitionMap.put("/**", "anon");
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return bean;
    }


}

