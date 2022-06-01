package com.ssafy.final_project.security;

import io.jsonwebtoken.Claims;

import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.Key;
import java.util.Date;

import com.ssafy.util.Pair;

public class JwtUtils {
    /**
     * 토큰에서 username 찾기
     *
     * @param token 토큰
     * @return username
     */
    public static String getUsername(String token) {
    	
        // jwtToken에서 username을 찾습니다.
        return Jwts.parserBuilder()
                .setSigningKeyResolver(SigningKeyResolver.instance) //키에 맞는 키값을 가져오는 역할
                .build()
                .parseClaimsJws(token) //키를 통해 검증,만료확인 부적절시 익셉션 발생
                .getBody()
                .getSubject(); // username
    }

    /**
     * user로 토큰 생성
     * HEADER : alg, kid
     * PAYLOAD : sub, iat, exp
     * SIGNATURE : JwtKey.getRandomKey로 구한 Secret Key로 HS512 해시
     *
     * @param user 유저
     * @return jwt token
     */
    public static String createToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getUsername()); // subject
        Date now = new Date(); // 현재 시간
        Pair<String, Key> key = JwtKey.getRandomKey();
        //key 이름과 , key값
        
        // JWT Token 생성
        return Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                //만료시간 정하니깐 귀찮음.setExpiration(new Date(now.getTime() + JwtProperties.EXPIRATION_TIME)) // 토큰 만료 시간 설정
                .setHeaderParam(JwsHeader.KEY_ID, key.getFirst()) // kid
                .signWith(key.getSecond(),SignatureAlgorithm.HS512) // signature
                .compact();
    }
}
