package cn.bookmanager.realm;

import cn.bookmanager.config.ShiroConfig;
import cn.bookmanager.entity.User;
import cn.bookmanager.mapper.UserMapper;
import cn.bookmanager.service.UserService;
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
 * Shiro UserRealm
 * @author lengqie
 */
public class UserRealm extends AuthorizingRealm {

    protected Logger logger = LoggerFactory.getLogger(UserRealm.class);

    @Autowired
    UserMapper userMapper;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        logger.debug("✨ UserRealm Authorization...");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        // 获取角色
        final String username = (String) principalCollection.getPrimaryPrincipal();

        //...数据库 确定用户名...
        if ( userMapper.getUserByName(username)==null ){
            return null;
        }

        String role = "user";
        Set<String> set  =new HashSet<>();
        set.add(role);

        info.setRoles(set);

        logger.debug("🤔 >> UserRealm："+info.getRoles());

        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        logger.debug("🎶 UserRealm Authentication...");

        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;

        final String username = token.getUsername();
        final String password = new String(token.getPassword());

        final int login = userMapper.isLogin(new User(username, password));
        if (login == 0 ) {
            throw new AuthenticationException("Wrong_user_name_or_password！！");
        }

        logger.debug("✔ >> UserRealm：登录成功");

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(token.getPrincipal(),password,this.getName());
        return info;
    }
}
