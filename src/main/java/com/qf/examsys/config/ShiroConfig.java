package com.qf.examsys.config;

import javax.servlet.Filter;
import com.qf.examsys.filter.MyLogoutFilter;
import com.qf.examsys.realm.MyRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    // shiro资源过滤配置
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 未登陆情况下，访问需要登陆后才能访问资源时，跳转到指定资源（比如登陆页面）
        shiroFilterFactoryBean.setLoginUrl("/AfterLogin.html");
        // 当没有权限访问某些资源时，跳转到的资源
        shiroFilterFactoryBean.setUnauthorizedUrl("/noPerm.html");

        // 存放自定义的filter
        LinkedHashMap<String, Filter> filtersMap = new LinkedHashMap<>();
        //配置自定义登出 覆盖 logout 之前默认的LogoutFilter
        filtersMap.put("logout", myLogoutFilter());
        shiroFilterFactoryBean.setFilters(filtersMap);

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        // authc:必须认证通过才可以访问;
        // anon: 匿名访问
        filterChainDefinitionMap.put("/bootstrap/**", "anon");
        filterChainDefinitionMap.put("/fonts/**", "anon");
        filterChainDefinitionMap.put("/layui/**", "anon");
        filterChainDefinitionMap.put("/time/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/BeforeLogin.html", "anon");
        filterChainDefinitionMap.put("/AfterLogin.html", "anon");

        filterChainDefinitionMap.put("/user/**", "anon");


        filterChainDefinitionMap.put("/after/**", "authc");
        filterChainDefinitionMap.put("/before/**", "authc");

        //退出时，指定logout过滤器
        filterChainDefinitionMap.put("/logout","logout");

        //必须放在所有权限设置的最后，匹配的是不满足前面匹配条件的资源
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;

    }

    // 安全管理对象
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
        defaultSecurityManager.setRealm(customRealm());
        return defaultSecurityManager;
    }

    // 自定义realm对象
    @Bean
    public MyRealm customRealm() {
        MyRealm customRealm = new MyRealm();
        // 设置密码匹配器
        // customRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return customRealm;
    }

    @Bean
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager());
        return advisor;
    }

    private MyLogoutFilter myLogoutFilter(){
        MyLogoutFilter logoutFilter = new MyLogoutFilter();
        logoutFilter.setRedirectUrl("/BeforeLogin.html");
        return logoutFilter;
    }
/*    @Bean
    public LogoutFilter MyLogOutFilter(){
        MyShiroLogoutFilter filter = new MyShiroLogoutFilter();
        filter.setRedirectUrl("/BeforeLogin.html");
        return filter;
    }*/

}
