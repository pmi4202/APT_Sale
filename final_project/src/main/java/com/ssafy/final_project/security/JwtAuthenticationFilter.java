package com.ssafy.final_project.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JWT를 이용한 로그인 인증
 */
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    @Autowired
    ObjectMapper mapper = new ObjectMapper();
    
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
        this.authenticationManager = authenticationManager;
    }

    /**
     * 로그인 인증 시도
     */
    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws AuthenticationException {
        // 로그인할 때 입력한 username과 password를 가지고 authenticationToken를 생성한다.
    	
    	@SuppressWarnings("rawtypes")
		Map paramMap= null;
    	ObjectMapper mapper = new ObjectMapper();
    	
		try {
			 paramMap = mapper.readValue(request.getInputStream(),Map.class);
			
			System.out.println(paramMap.toString());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		  
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(	
        		paramMap.get("userid"),
        		paramMap.get("password"),
                new ArrayList<>()
        );
        
        //스프링 시큐리티에서는 athentication(securityContext 하위)를 가지고 한다...
         return authenticationManager.authenticate(authenticationToken);
    }

    /**
     * 인증에 성공했을 때 사용
     * JWT Token을 생성해서 쿠키에 넣는다.
     */
    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult
    ) throws IOException {
    	
        User user = (User) authResult.getPrincipal();
        String token = JwtUtils.createToken(user);
       
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put(JwtProperties.JWT_ACESS_NAME,token);
        resultMap.put("success",true);
        resultMap.put("msg","인증 성공 : 토큰 발급");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print( mapper.writeValueAsString(resultMap));
        response.flushBuffer();
       
    }

    @Override
    protected void unsuccessfulAuthentication( //인증에 실패하였을 경우
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException failed
    ) throws IOException {
    	
    	 Map<String,Object> resultMap = new HashMap<>();
         resultMap.put("success",false);
         resultMap.put("msg","인증 실패 ");
         response.setCharacterEncoding("UTF-8");
         response.getWriter().print( mapper.writeValueAsString(resultMap));
         response.flushBuffer();
    }
}