package com.ssafy.final_project.config;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.ssafy.final_project.model.dto.UserInfoDto;
import com.ssafy.final_project.model.service.UserInfoService;
import com.ssafy.final_project.security.JwtAuthenticationFilter;
import com.ssafy.final_project.security.JwtAuthorizationFilter;
import com.ssafy.final_project.security.JwtProperties;
import com.ssafy.final_project.security.User;

/**
 * Security 설정 Config
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserInfoService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// basic authentication
		// 헤더에 id, pass워드 하는 방식
		http.httpBasic().disable(); // basic authentication filter 비활성화
		// csrf 토큰을사용하여 공격 막는 방식인데, 우린 안씀 ,
		http.csrf().disable();
		// remember-me
		// 기억 안함 따로
		http.rememberMe().disable();
		// CORS 허용하기 위함
		http.cors().configurationSource(corsConfigurationSource());
		// stateless
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		// jwt filter.. 실제 dao에 접근해서 유저 정보가져와서 하는 필터 앞에 추가 .
		http.addFilterBefore(new JwtAuthenticationFilter(authenticationManager()),
				UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(new JwtAuthorizationFilter(userService), BasicAuthenticationFilter.class);

		http.authorizeRequests()
				.antMatchers("/**").permitAll() //모든 요청 수락
				.antMatchers(HttpMethod.POST,"/notice").hasRole("ADMIN") //공지사항 작성에서는 어드민 권한
				.antMatchers(HttpMethod.PUT,"/notice").hasRole("ADMIN")
				.antMatchers(HttpMethod.DELETE,"/notice").hasRole("ADMIN")
				.antMatchers(HttpMethod.POST).authenticated()
				.antMatchers(HttpMethod.DELETE).authenticated()
				.antMatchers(HttpMethod.PUT).authenticated(); //세세하게 못하겠고 이정도만 하자

		// login. 해당 url로 요청할 시 로그인 과정을 거치게 된다.
		http.formLogin().loginPage("/login").defaultSuccessUrl("/").permitAll(); // 모두 허용

		// logout
		//사실상 안쓰는데,  잘동작하니 넘어 가겠다.
		http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/")
				.invalidateHttpSession(true).deleteCookies(JwtProperties.JWT_ACESS_NAME);
	}

	@Override
	public void configure(WebSecurity web) {
		// 정적 리소스 spring security 대상에서 제외
//        web.ignoring().antMatchers("/images/**", "/css/**"); // 아래 코드와 같은 코드입니다.
		web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
		web.ignoring().antMatchers("/swagger-ui/**");
	}

	/**
	 * UserDetailsService 구현
	 *
	 * @return UserDetailsService
	 */
	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		return username -> {
			UserInfoDto userDto = null;
			try {
				userDto = userService.read(username);
				if (userDto == null) {
					throw new UsernameNotFoundException(username);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new UsernameNotFoundException(username);
			}

			ArrayList<GrantedAuthority> authoritys = new ArrayList<GrantedAuthority>();
			if (userDto.getGrade() == 0) { // 관리자
				authoritys.add(new SimpleGrantedAuthority("ROLE_USER"));
				authoritys.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			} else if (userDto.getGrade() == 1) {
				// 일반 유저
				authoritys.add(new SimpleGrantedAuthority("ROLE_USER"));
			}

			User user = new User();
			user.setUsername(userDto.getUserId());
			user.setPassword(userDto.getUserPassword());
			user.setAuthorities(authoritys);
			user.setEnabled(userDto.getDeleted() > 0 ? false : true);
			return user;
		};
	}

	//CORS 해결
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();

		configuration.addAllowedOriginPattern("*");
		configuration.addAllowedHeader("*");
		configuration.addAllowedMethod("*");
		configuration.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}
