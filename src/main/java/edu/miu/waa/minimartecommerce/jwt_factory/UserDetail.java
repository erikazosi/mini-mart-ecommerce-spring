package edu.miu.waa.minimartecommerce.jwt_factory;

import edu.miu.waa.minimartecommerce.domain.user.Role;
import edu.miu.waa.minimartecommerce.domain.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class UserDetail implements UserDetails {
    private String username;
    private String password;
    private boolean isActive;
    private Set<Role> roles;
    private long userId;

    public UserDetail(User user) {
        username = user.getUsername();
        password = user.getPassword();
        roles = user.getRoles();
        userId = user.getId();
        isActive = user.isActive();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(role -> new SimpleGrantedAuthority( role.getRole())) // HERE !!!  "ROLE_ + "
                .collect(Collectors.toList());

    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public long getUserId(){
        return userId;
    }
}