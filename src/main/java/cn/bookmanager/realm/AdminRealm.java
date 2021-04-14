package cn.bookmanager.realm;

import cn.bookmanager.entity.Admin;
import cn.bookmanager.entity.User;
import cn.bookmanager.mapper.AdminMapper;
import cn.bookmanager.mapper.UserMapper;
import cn.bookmanager.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class AdminRealm extends AuthorizingRealm {

    @Autowired
    AdminMapper adminMapper;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("✨ Authorization...");
        System.out.println("getName："+getName());


        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        // 获取角色
        final String username = (String) principalCollection.getPrimaryPrincipal();
        //... 数据库...
        String role = "admin";

        Set<String> set  =new HashSet<>();
        set.add(role);

        info.setRoles(set);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("🎶 Authentication...");

        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;

        final String username = token.getUsername();
        final String password = new String(token.getPassword());

        final int login = adminMapper.isLogin(new Admin(username, password));
        if (login == 0 ) {
            throw new AuthenticationException("Wrong_user_name_or_password！！");
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(token.getPrincipal(),password,this.getName());
        return info;
    }
}
