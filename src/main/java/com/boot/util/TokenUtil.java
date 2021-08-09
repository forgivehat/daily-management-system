package com.boot.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class TokenUtil {

    public static String getToken(String  username,String password,String role){
        return JWT.create().withAudience(username,role)
                .sign(Algorithm.HMAC256(password));
    }
    /**
     * 获得请求头的token字段，解析token获得username和role,存入map中
     * @param request
     * @return map<String,String>
     */
    public static Map<String,String> getTokenParams (HttpServletRequest request){
        Map<String,String> map = new HashMap<>();
       String token = request.getHeader("token");
       String acc_username = JWT.decode(token).getAudience().get(0);
       String acc_role = JWT.decode(token).getAudience().get(1);
       map.put("username",acc_username);
       map.put("role",acc_role);
       return map;
    }

    /**
     * 返回token中的用户名
     * @param request
     * @return String
     */
   public static String getUsernameFromToken(HttpServletRequest request){
        return getTokenParams(request).get("username");
    }

    /**
     * 返回token中的用户类型
     * @param request
     * @return String
     */
    public static String getRoleFromToken(HttpServletRequest request){
        return getTokenParams(request).get("role");
    }
}
