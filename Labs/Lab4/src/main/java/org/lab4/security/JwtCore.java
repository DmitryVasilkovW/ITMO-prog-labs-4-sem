package org.lab4.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtCore
{
    @Value("${lab4.app.secret}")
    private String secret;

    @Value("${lab4.app.lifetime}")
    private int lifetime;

    public String generateToken(Authentication authentication)
    {
        UserDetailsIml userDetails = (UserDetailsIml) authentication.getPrincipal();

        try
        {
            Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(new Date())
                    .setExpiration(new Date((new Date()).getTime() + lifetime))
                    .signWith(SignatureAlgorithm.HS256, secret).compact();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + lifetime))
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    public String getNameFromJwt(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

}
