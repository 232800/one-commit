package com.common.utils;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

@Configuration
@PropertySource("classpath:application.yml")
public class TokenUtil {
	private static final long EXPIRE_TIME= 10*60*60*1000;//默认十小时
    private static final String TOKEN_SECRET="xbox";  //密钥盐
    private static Integer sessionTime;
    @Value("${sessionTime}")
	public void setSessionTime(Integer sessionTime) {
    	TokenUtil.sessionTime = sessionTime;
	}
	
	public Integer getSessionTime() {
		return sessionTime;
	}
    /**
     * 签名生成
     * @param user
     * @return
     */
    public static String sign(String authStr){
        String token = null;
        try {
        	System.out.println("当前会话保持时间"+sessionTime);
            Date expiresAt = new Date(System.currentTimeMillis() + (sessionTime !=null &&sessionTime>0?sessionTime*60*60*1000:EXPIRE_TIME));
            token = JWT.create()
                    .withIssuer("auth0")
                    .withClaim("authStr", authStr)
                    .withExpiresAt(expiresAt)
                    // 使用了HMAC256加密算法。
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));
        } catch (Exception e){
            e.printStackTrace();
        }
        return token;
    }
 
    /**
     * 签名验证
     * @param token
     * @return
     */
    public static boolean verify(String token,HttpServletRequest request){
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auth0").build();
            DecodedJWT jwt = verifier.verify(token);
            String userId = jwt.getClaim("authStr").asString();
            System.out.println("认证通过：");
            System.out.println("authStr: " + userId);
            System.out.println("过期时间：      " + jwt.getExpiresAt());
            request.setAttribute("user_id", userId);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
