    package com.boot.interceptor;

    import com.auth0.jwt.JWT;
    import com.auth0.jwt.JWTVerifier;
    import com.auth0.jwt.algorithms.Algorithm;
    import com.auth0.jwt.exceptions.JWTDecodeException;
    import com.auth0.jwt.exceptions.JWTVerificationException;
    import com.boot.entity.Account;
    import com.boot.service.AccountService;
    import com.boot.util.VerifyToken;
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.method.HandlerMethod;
    import org.springframework.web.servlet.HandlerInterceptor;

    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    import java.lang.reflect.Method;


    public class AuthInterceptor implements HandlerInterceptor {
       @Autowired
       AccountService accountService;
        public static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

        @Override
        public boolean preHandle(HttpServletRequest request,
                                 HttpServletResponse response,
                                 Object handler) throws Exception {
            logger.info("token拦截...");
            //从请求头中取出token
            String token = request.getHeader("token");
            if (!(handler instanceof HandlerMethod)){
                return true;
            }
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            //检查需不需要验证token
            if (method.isAnnotationPresent(VerifyToken.class)){
                VerifyToken userVerifyToken = method.getAnnotation(VerifyToken.class);
                if(userVerifyToken.required()){
                    if (token == null){
                        throw  new RuntimeException("该请求没有token！请先获取token！");
                    }

                    //获取token中的acc_username
                    String acc_username;
                    try {
                        acc_username = JWT.decode(token).getAudience().get(0);
                    }catch (JWTDecodeException j){
                        throw new RuntimeException("token非法，没有此账号！");
                    }

                    Account account = accountService.findByUsername(acc_username);
                    if (account == null){
                        throw new RuntimeException("用户不存在，请重新登录！");
                    }

                    //验证Token
                    JWTVerifier jwtVerifier =
                            JWT.require(Algorithm.HMAC256(account.getPassword())).build();
                    try {
                        jwtVerifier.verify(token);
                    }catch (JWTVerificationException e){
                        throw new RuntimeException("校验token发生异常！");
                    }
                    return  true;
                }
            }
            return true;
        }
    }
