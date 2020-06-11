package top.naccl.interceptor;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import top.naccl.annotation.OnlyUser;
import top.naccl.exception.UnauthorizedException;
import top.naccl.bean.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 普通用户权限拦截器
 * @Author: Naccl
 * @Date: 2020-05-19
 */
public class UserInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			OnlyUser onlyUser = ((HandlerMethod) handler).getMethodAnnotation(OnlyUser.class);
			if (onlyUser == null) {
				onlyUser = handlerMethod.getMethod().getDeclaringClass().getAnnotation(OnlyUser.class);
				if (onlyUser == null) {
					return true;
				}
			}
			User user = (User) request.getSession().getAttribute("user");
			if (user.getIdent() == 0) {
				return true;
			}
		}
		throw new UnauthorizedException();
//		response.sendRedirect("/error/401");
	}
}
