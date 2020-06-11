package top.naccl.interceptor;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import top.naccl.annotation.OnlyAdmin;
import top.naccl.exception.UnauthorizedException;
import top.naccl.bean.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 管理员权限拦截器
 * @Author: Naccl
 * @Date: 2020-05-19
 */
public class AdminInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			OnlyAdmin onlyAdmin = ((HandlerMethod) handler).getMethodAnnotation(OnlyAdmin.class);
			if (onlyAdmin == null) {
				onlyAdmin = handlerMethod.getMethod().getDeclaringClass().getAnnotation(OnlyAdmin.class);
				if (onlyAdmin == null) {
					return true;
				}
			}
			User user = (User) request.getSession().getAttribute("user");
			if (user.getIdent() == 1) {
				return true;
			}
		}
		throw new UnauthorizedException();
//		response.sendRedirect("/error/401");
	}
}
