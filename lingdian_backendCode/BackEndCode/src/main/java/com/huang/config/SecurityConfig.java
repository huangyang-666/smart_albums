package com.huang.config;

import com.huang.authentication.JwtAuthenticationTokenFilter;
import com.huang.authentication.MyLoginFailureHandler;
import com.huang.authentication.MyLoginSuccessHandler;
import com.huang.util.JwtAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Description
 * @Author huangyang
 * @date 2021年06月30日 9:19
 */
@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    登陆成功、登陆失败注入
    @Autowired
    private MyLoginSuccessHandler myLoginSuccessHandler;
    @Autowired
    private MyLoginFailureHandler myLoginFailureHandler;

    @Autowired
    private JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /** JWT拦截器*/
        JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter = new JwtAuthenticationTokenFilter();
        /** 将JWT拦截器添加到UsernamePasswordAuthenticationFilter之前*/
        http.addFilterBefore(jwtAuthenticationTokenFilter,UsernamePasswordAuthenticationFilter.class);

        http.formLogin()
                .loginPage("/logInInfo")
                .loginProcessingUrl("/login")
                .successHandler(myLoginSuccessHandler)
                .failureHandler(myLoginFailureHandler);
        http.authorizeRequests()
                // 此处的角色不需要`ROLE_` 前缀,实现UserDetailsService设置角色时需要`ROLE_` 前缀
                .antMatchers("/login","/picupload","/facedetect","/queryfaceSets","/queryallshowtimes","/addshowtime","/diftypedisplay",
                        "/showallimgs","/happymoments","/showmoments").hasRole("ADMIN")
                .antMatchers("/userRegister","/getExcelDownload","/login","/loginInfo","/logoutSuccess")
                .permitAll();
//                .anyRequest()
//                .authenticated();
        // 访问 /logout 表示用户注销，并清空session
        http.logout().logoutSuccessUrl("/logoutSuccess");
        // 关闭csrf
        http.csrf().disable();
        http.cors();
//         AccessDeniedHandler处理器 拒绝访问处理器
        http.exceptionHandling().accessDeniedHandler(jwtAccessDeniedHandler);
    }

    /**
     * 密码加盐加密
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        //Spring自带的每次会随机生成盐值，即使密码相同，加密后也不同
        return new BCryptPasswordEncoder();
    }

}
