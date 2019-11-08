package com.qf.examsys.realm;

import com.qf.examsys.entity.User;
import com.qf.examsys.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.HashSet;
import java.util.List;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    @Lazy//使用redis缓存shiro中数据时，需要使用该注解
    UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String phone =(String) principalCollection.getPrimaryPrincipal();
        List<String> perms = userService.findAllPerms(phone);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(new HashSet<>(perms));
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        // 获取用户的身份信息
        String phone = (String) authenticationToken.getPrincipal();
        User user = userService.findUserByPhone(phone);
        SimpleAuthenticationInfo info = null;
        if (user == null) {
            info = new SimpleAuthenticationInfo("", "", this.getName());
        } else {       // 身份认证信息对象
            // 第一个参数：用户身份信息 用户名
            // 第二个参数：用户凭证信息 密码
            // 第三个参数：realm的名称
            //info = new SimpleAuthenticationInfo(name, user.getPassword(), this.getName());

            //如果md5中使用了盐值，需要在认证信息对象设置盐值
            info = new SimpleAuthenticationInfo(phone, user.getuPassword(),  this.getName());

        }
        return info;
    }
}
