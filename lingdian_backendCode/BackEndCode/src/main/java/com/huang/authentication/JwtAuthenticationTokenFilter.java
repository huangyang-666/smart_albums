package com.huang.authentication;

import com.huang.util.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description
 * @Author huangyang
 * @date 2021年06月30日 9:19
 */
//不需要修改
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        //获取JWT
        String authHeader = request.getHeader("Authorization");
        System.out.println(authHeader==null);
        logger.info("--------->"+authHeader);
        if (authHeader != null) {
            JwtUtils.tokenParser(authHeader);
        }
        filterChain.doFilter(request, response);
    }
}
