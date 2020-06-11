package top.naccl.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 记录异常
 * @Author: Naccl
 * @Date: 2020-05-17
 */

@ControllerAdvice
public class ControllerExceptionHandler {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@ExceptionHandler(Exception.class)
	public ModelAndView exceptionHandler(HttpServletRequest request, Exception e) throws Exception {
		logger.error("Request URL : {}, Exception : {}", request.getRequestURL(), e);

		//如果有注解标识过这个异常，则让对应的注解处理此异常
		if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
			throw e;
		}

		ModelAndView mv = new ModelAndView();
//		mv.addObject("url", request.getRequestURL());
//		mv.addObject("exception", e);
		mv.setViewName("error/error");
		return mv;
	}
}
