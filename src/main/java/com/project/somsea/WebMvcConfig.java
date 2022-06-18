package com.project.somsea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

//	@Autowired
//	@Qualifier(value = "signonInterceptor")
//	private HandlerInterceptor interceptor;

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
//		registry.addViewController("/shop/signonForm.do").setViewName("SignonForm");
	}
	
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(interceptor)
//				.addPathPatterns("/shop/editAccount.do", "/shop/listOrders.do",
//					"/shop/viewOrder.do", "/shop/newOrder.do");		
//	}
	
}