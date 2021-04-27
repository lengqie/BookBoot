package cn.bookmanager.realm;

import cn.bookmanager.entity.Admin;
import cn.bookmanager.mapper.AdminMapper;
import cn.bookmanager.service.AdminService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * Shiro AdminRealm
 * @author lengqie
 */
public class AdminRealm extends AuthorizingRealm {


    protected Logger logger = LoggerFactory.getLogger(AdminRealm.class);


    @Autowired
    AdminMapper adminMapper;

    @Autowired
    AdminService adminService;



    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        logger.debug("✨ UserRealm Authorization...\n"+"getName："+getName());

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        // 获取角色

        final String username = (String) principalCollection.getPrimaryPrincipal();

        //...数据库 确定用户名...
        if ( adminMapper.getAdminByName(username)==null){
            return null;
        }
        String role = "admin";

        Set<String> set  =new HashSet<>();
        set.add(role);


        // 根用户 直接写死
        if( adminMapper.getAdminByName(username).getId() ==0){
            set.add("root");
        }

        info.setRoles(set);

        logger.debug("🤔 >> AdminRealm："+info.getRoles());
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        logger.debug("🎶 AdminRealm Authentication...");

        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        final String username = token.getUsername();
        final String password = new String(token.getPassword());

        // rememberMe
        token.setRememberMe(true);

        final int login = adminMapper.isLogin(new Admin(username, password));
        if (login == 0 ) {
            throw new AuthenticationException("Wrong_user_name_or_password！！");
        }
        logger.debug("✔ >> AdminRealm：登录成功");
        return new SimpleAuthenticationInfo(token.getPrincipal(),password, this.getName());
    }
}
