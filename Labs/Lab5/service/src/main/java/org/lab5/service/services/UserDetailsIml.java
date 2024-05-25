package org.lab5.service.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.lab5.dataAccess.dao.OwnerDao;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
public class UserDetailsIml implements UserDetails
{
    private Long id;
    private String username;
    private String email;
    private String password;
    private LocalDate birthdate;
    private List<GrantedAuthority> authority;

    public static UserDetailsIml build(OwnerDao owner)
    {
        var role = new SimpleGrantedAuthority("ROLE_" + owner.getRole().name());
        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(role);

        return new UserDetailsIml(
                owner.getId(),
                owner.getName(),
                owner.getEmail(),
                owner.getPassword(),
                owner.getBirthDate(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return authority;
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