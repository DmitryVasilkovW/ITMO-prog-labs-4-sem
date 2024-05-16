package org.lab4.app.tokenManager;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class TokenFilter extends OncePerRequestFilter
{
    @Autowired
    private JwtCore jwtCore;
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {
        String jwt = null;
        String username = null;
        UserDetails userDetails = null;
        UsernamePasswordAuthenticationToken authentication = null;

        try
        {
            String header = request.getHeader("Authorization");

            if (header != null && header.startsWith("Bearer "))
            {
                jwt = header.substring(7);
            }
            if (jwt != null)
            {
                try
                {
                    username = jwtCore.getNameFromJwt(jwt);
                }
                catch (ExpiredJwtException e)
                {
                    e.printStackTrace();
                }
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null)
                {
                    userDetails = userDetailsService.loadUserByUsername(username);
                    authentication = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try
        {
            filterChain.doFilter(request, response);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
