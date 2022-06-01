package com.ssafy.final_project.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;

import com.ssafy.final_project.security.JwtProperties;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		final ApiInfo apiInfo = new ApiInfoBuilder().title("SSAFY REST 컨트롤러 API")
				.description("<h3>SSAFY 파이널 프로젝트 API</h3>")
				.contact(new Contact("SSAFY", "https://edu.ssafy.com", "ssafy@ssafy.com")).license("MIT License")
				.version("1.0").build();

		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo)
				.securityContexts(Arrays.asList(securityContext())).securitySchemes(Arrays.asList(apiKey()))
				.ignoredParameterTypes(Authentication.class).select()
				.apis(RequestHandlerSelectors.basePackage("com.ssafy.final_project.controller"))
				.paths(PathSelectors.ant("/**")).build(); // 캐사케이드 빌더 패턴
	}

	private ApiKey apiKey() {
		return new ApiKey("JWT", JwtProperties.JWT_ACESS_NAME, "header");
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).build();
	}

	private List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
	}

}
