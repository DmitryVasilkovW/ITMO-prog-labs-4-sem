package org.lab4.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtCore
{
    @Value("${lab4.app.secret}")
    private String secret;

    @Value("${lab4.app.lifetime}")
    private int lifetime;

//    public String generateToken(Authentication authentication)
//    {
//
//    }
}
