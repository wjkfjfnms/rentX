package com.example.user.util;

import com.example.user.po.Users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Component
public class TokenUtils implements Serializable {
    private static final long serialVersionUID = -3L;
    /**
     * Token 有效时长7天
     */
    private static final Long EXPIRATION = 604800L;

    /** 生成 Token 字符串 必须 setAudience 接收者 setExpiration 过期时间 role 用户角色
     * @param users 用户信息
     * @return 生成的Token字符串 or null
     */
    public String createToken(Users users) {
        try {
            // Token 的过期时间
            Date expirationDate = new Date(System.currentTimeMillis() + EXPIRATION * 1000);
            // 生成 Token
            String token = Jwts.builder()
                    // 设置 Token 签发者 可选
                    .setIssuer("SpringBoot")
                    // 根据用户名设置 Token 的接受者
                    .setAudience(users.getEmail())
                    // 设置过期时间
                    .setExpiration(expirationDate)
                    // 设置 Token 生成时间 可选
                    .setIssuedAt(new Date())
                    // 通过 claim 方法设置一个 key = role，value = userRole 的值
                    .claim("role", users.getRole())
                    .claim("id", users.getId())
                    // 设置加密密钥和加密算法，注意要用私钥加密且保证私钥不泄露
                    .signWith(RsaUtils.getPrivateKey(), SignatureAlgorithm.RS256)
                    .compact();
//            System.out.println(token);
            Boolean boo=this.checkAdminRole(token);
//            System.out.println("结果："+boo);
            return String.format("Bearer %s", token);
        } catch (Exception e) {
            e.printStackTrace(); // 打印异常信息
            return null;
        }
    }

    /** 验证 Token ，并获取到用户名和用户权限信息
     * @param token Token 字符串
     * @return sysUser 用户信息
     */
    public Users validationToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    // 设置公钥解密，以为私钥是保密的，因此 Token 只能是自己生成的，如此来验证 Token
                    .setSigningKey(RsaUtils.getPublicKey())
                    .build().parseClaimsJws(token).getBody();
            assert claims != null;
            // 验证 Token 有没有过期 过期时间
            Date expiration = claims.getExpiration();
            // 判断是否过期 过期时间要在当前日期之后
            if (!expiration.after(new Date())) {
                return null;
            }
            Users sysUser = new Users();
//            System.out.println(claims);
            sysUser.setEmail(claims.getAudience());
            sysUser.setRole(claims.get("role").toString());
            sysUser.setId(Long.valueOf(claims.get("id").toString()));
//            System.out.println(claims.get("role").toString());
//            System.out.println(Long.valueOf(claims.get("id").toString()));
            return sysUser;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean checkAdminRole(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(RsaUtils.getPublicKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String role = (String) claims.get("role");
            if (role != null && role.equals("ADMIN")) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace(); // 打印异常信息
        }
        return false;
    }
}
