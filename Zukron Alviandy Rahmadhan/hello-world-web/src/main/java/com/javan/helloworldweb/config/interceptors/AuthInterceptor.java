package com.javan.helloworldweb.config.interceptors;

import com.javan.helloworldweb.config.annotations.NeedToken;
import com.javan.helloworldweb.exceptions.UnauthenticatedException;
import com.javan.helloworldweb.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws UnauthenticatedException {
        // jika handler dari method
        if (handler instanceof HandlerMethod) {
            // ambil annotation di method
            NeedToken needToken = ((HandlerMethod) handler).getMethodAnnotation(NeedToken.class);

            // jika masih null mungkin dipasang di class
            if (needToken == null) {
                needToken = ((HandlerMethod) handler).getMethod().getDeclaringClass().getAnnotation(NeedToken.class);
            }

            Optional<String> authorization = Optional.ofNullable(request.getHeader("Authorization"));
            boolean isExpired = true;
            if (authorization.isPresent()) {
                String token = authorization.get().replace("Bearer ", "");
                isExpired = jwtUtil.isTokenExpired(token);
            }

            // jika ada annotation
            if (needToken != null) {
                if (!isExpired) {
                    return true;
                }

                throw new UnauthenticatedException();
            }
        }

        return true;
    }
}
