package com.weylan.blog.intercepter;

import com.alibaba.fastjson.JSON;
import com.weylan.blog.annotation.AuthorizationAnnotation;
import com.weylan.blog.annotation.CurrentUser;
import com.weylan.blog.common.enums.ResEnum;
import com.weylan.blog.common.util.token.JwtTokenFactory;
import com.weylan.blog.model.Res;
import com.weylan.blog.model.user.vo.UserContent;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author shiweinan
 */
@Component
public class AuthenticationInterceptor extends HandlerInterceptorAdapter implements HandlerMethodArgumentResolver {

    @Autowired
    private JwtTokenFactory<UserContent> tokenFactory;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method actionMethod = handlerMethod.getMethod();
        AuthorizationAnnotation authorization = AnnotationUtils.findAnnotation(actionMethod, AuthorizationAnnotation.class);
        if (authorization == null) {
            return true;
        }
        String token = request.getHeader("X-token");
        if (StringUtils.isBlank(token)) {
            writeFailedRes(response);
            return false;
        }
        try {
            UserContent userContent = tokenFactory.getUserInfoFromToken(token, UserContent.class);
            request.setAttribute("currentUser", userContent);
            return true;
        } catch (Exception e) {
           // e.printStackTrace();
            writeFailedRes(response);
        }

        return false;
    }

    private void writeFailedRes(HttpServletResponse response) throws IOException {
        response.setContentType("application/json; charset=utf-8");
        ServletOutputStream ops = response.getOutputStream();
        ops.print(JSON.toJSONString(new Res<>().error(ResEnum.TOKEN_VALIDATE_FAILED)));
    }

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().isAssignableFrom(UserContent.class)
                && methodParameter.hasParameterAnnotation(CurrentUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        return (UserContent) nativeWebRequest.getAttribute("currentUser", RequestAttributes.SCOPE_REQUEST);
    }
}
