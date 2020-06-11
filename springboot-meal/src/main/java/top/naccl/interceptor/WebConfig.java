package top.naccl.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description: 拦截器过滤配置
 * @Author: Naccl
 * @Date: 2020-05-17
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/admin/**", "/user/**");
		registry.addInterceptor(new AdminInterceptor()).addPathPatterns("/admin/**");
		registry.addInterceptor(new UserInterceptor()).addPathPatterns("/user/**");
	}
}
