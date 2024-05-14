package org.lab4.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
public class UserDetailsIml implements UserDetails
{
    private String username;
    private String email;
    private String password;

    //public static UserDetailsIml build(User user)

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return List.of();
    }

    @Override
    public String getPassword()
    {
        return "";
    }

    @Override
    public String getUsername()
    {
        return "";
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return false;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return false;
    }

    @Override
    public boolean isEnabled()
    {
        return false;
    }
}
