package org.lab4.app.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.lab4.app.models.OwnerDao;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;

@Data
@AllArgsConstructor
public class UserDetailsIml implements UserDetails
{
    private Long id;
    private String username;
    private String email;
    private String password;
    private LocalDate birthdate;

    public static UserDetailsIml build(OwnerDao owner)
    {
        return new UserDetailsIml(
                owner.getId(),
                owner.getName(),
                owner.getEmail(),
                owner.getPassword(),
                owner.getBirthDate());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return null;
    }

    @Override
    public String getPassword()
    {
        return password;
    }

    @Override
    public String getUsername()
    {
        return username;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }
}
