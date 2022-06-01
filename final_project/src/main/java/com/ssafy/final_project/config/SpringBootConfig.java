package com.ssafy.final_project.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.BeanNameViewResolver;

import com.ssafy.util.dust.DustUtil;
import com.ssafy.util.dust.DustUtilImpl;
import com.ssafy.util.dust.DustUtilProxy;

@Configuration
@EnableAspectJAutoProxy // AOP 해라
@MapperScan(basePackages = { "com.ssafy.final_project.model.mapper" }) // 매퍼 스캔하라.
public class SpringBootConfig implements WebMvcConfigurer {


	@Bean // 빈 네임 뷰 리졸버 , 가상 뷰 ? 허허,,,, 빈에 이름을 지정하지 않을 시 반환되는 타입 첫글자만 소문자가 되어 결정된다.
	public BeanNameViewResolver beanNameViewResolver() {
		BeanNameViewResolver bnvr = new BeanNameViewResolver();
		bnvr.setOrder(0);
		return bnvr;
	}


	@Bean
	public DustUtil dustUtil() {
		DustUtil dustUtil = new DustUtilProxy(new DustUtilImpl());
		return dustUtil;
	}
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		
//		registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/");
//		registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/");
//		registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/");
//		
//	}

	

}
