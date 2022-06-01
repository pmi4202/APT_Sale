package com.ssafy.final_project.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ssafy.final_project.model.dto.UserInfoDto;
import com.ssafy.final_project.model.service.UserInfoService;

import io.jsonwebtoken.ExpiredJwtException;

//1. 쿠키에서 JWT 토큰을 구합니다.
//2. JWT Token을 파싱하여 username을 구합니다 . subject
//3. username으로 User를 구하고 Authentication을 생성합니다 .
//4 생성된 놈을 SecurityContext에 넣습니다. ..
//5. Exception 발생시 응답 쿠키를 null로
/**
 * JWT를 이용한 인증
 */
//여담 : spring 1.2 부터 delegationFilterProxy를 통해 필터에 스프링 컨텍스트가 지원하는 여러 기능을 사용할 수 있게되었다 한다. 
//ex 빈주입 
public class JwtAuthorizationFilter extends OncePerRequestFilter {

	private final UserInfoService userService;

	public JwtAuthorizationFilter(UserInfoService userService) {
		this.userService = userService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String token = null;
		try {

			String recivedToken = request.getHeader(JwtProperties.JWT_ACESS_NAME);

			token = recivedToken;
			// 헤더 정보에서 jwt관련 헤더 찾고 , 그 값을 가져옴
		} catch (Exception ignored) {
		}
		if (token != null) {
			try {
				Authentication authentication = getUsernamePasswordAuthenticationToken(token);
				// 로컬쓰레드로 할당되는 세큐리티컨텍스트에 어뗀티케이션 정보를 저장한다.
				// 다음 필터가 사용해야하니깐?
				SecurityContextHolder.getContext().setAuthentication(authentication);
			} catch (ExpiredJwtException e) {
				response.sendError(401, "Unauthorized : 토큰 만료");
				response.getOutputStream().flush();
				return ; // 토큰 만료 ,
			} catch (Exception e) {
				// UnsupportedJwtException,
				// MalformedJwtException, SignatureException, IllegalArgumentException
				response.sendError(401, "Unauthorized : 토큰 부적합");
				response.getOutputStream().flush();
				return;
			}
		}
		
		chain.doFilter(request, response);
	}

	/**
	 * JWT 토큰으로 User를 찾아서 UsernamePasswordAuthenticationToken를 만들어서 반환한다. User가 없다면
	 * null
	 */
	private Authentication getUsernamePasswordAuthenticationToken(String token) {
		// 토큰에서 유저네임을 찾아온다.

		// 로그인이 필요한 작업을 사용자가 할 경우 , 넘어온 토큰에서 유저 정보를 가져오고
		// DB에서 유저 정보를 가져와 그의 권한을 본다.
		String userName = JwtUtils.getUsername(token);
		if (userName != null) {
			// $$$$ 임시

			UserInfoDto tempUser;

			try {
				tempUser = userService.read(userName);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}

			ArrayList<GrantedAuthority> authoritys = new ArrayList<GrantedAuthority>();
			if (tempUser.getGrade() == 0) { // 관리자
				authoritys.add(new SimpleGrantedAuthority("ROLE_USER"));
				authoritys.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			} else if (tempUser.getGrade() == 1) {
				// 일반 유저
				authoritys.add(new SimpleGrantedAuthority("ROLE_USER"));
			}

			return new UsernamePasswordAuthenticationToken(tempUser.getUserId(), // principal
					null, authoritys);
		}
		return null; // 유저가 없으면 NULL
	}
}