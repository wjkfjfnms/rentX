package com.example.user.filter;

import com.example.user.po.Users;
import com.example.user.util.TokenUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class TokenFilter extends OncePerRequestFilter {
    @Resource
    TokenUtils tokenUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // 存储 Token 的 Headers Key与 Value，默认是 Authorization
        final String authorizationKey = "Authorization";
        String authorizationValue;
        try {
            authorizationValue = request.getHeader(authorizationKey);
        } catch (Exception e) {
            authorizationValue = null;
        }
        // Token 开头部分 默认 Bearer 开头
        String bearer = "Bearer ";
        if (authorizationValue != null && authorizationValue.startsWith(bearer)) {
            // 解析 HTTP 请求中的 Authorization 头，提取出其中的 JWT Token 部分
            String token = authorizationValue.substring(bearer.length());
            System.out.println("Tokenfilter43:"+token);
            //获取token中的信息
            Users sysUser = tokenUtils.validationToken(token);
            System.out.println("Tokenfilter46:"+sysUser);
            if (sysUser != null) {
                // Spring Security 角色名称默认使用 "ROLE_" 开头
                // authorities.add 可以增加多个用户角色，对于一个用户有多种角色的系统来说，
                // 可以通过增加用户角色表、用户--角色映射表，存储多个用户角色信息
                List<SimpleGrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority("ROLE_" + sysUser.getRole()));
                // 传入用户名、用户密码、用户角色。 这里的密码随便写的，用不上
                UserDetails userDetails = new User(sysUser.getEmail(), "password", authorities);
                System.out.println("Tokenfilter54:"+userDetails);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(userDetails.getUsername());
                System.out.println("Tokenfilter56:"+authentication);
                // 授权
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }
}
