package com.coconut.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;

public class JwtUtil {

    private static final String SECRET = "SECRET";
    private static final long EXPIRATION = 100000000;

    public static String getJwt(Integer userId) {
        HashMap<String, Object> claim = new HashMap<>();
        claim.put("userId", userId);
        String jwt = Jwts.builder()
                .setClaims(claim)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        return jwt;
    }

    public static Integer getUserId(String jwt) {
        Claims claim = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(jwt)
                .getBody();
        return (Integer) claim.get("userId");
    }

}
