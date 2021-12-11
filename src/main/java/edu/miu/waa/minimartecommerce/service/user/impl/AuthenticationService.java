package edu.miu.waa.minimartecommerce.service.user.impl;

import edu.miu.waa.minimartecommerce.dto.user.AuthRequest;
import edu.miu.waa.minimartecommerce.dto.user.AuthResponse;
import edu.miu.waa.minimartecommerce.exceptionHandling.exceptions.AccountNotActivatedException;
import edu.miu.waa.minimartecommerce.exceptionHandling.exceptions.AuthenticationException;
import edu.miu.waa.minimartecommerce.jwt_factory.JwtUtil;
import edu.miu.waa.minimartecommerce.jwt_factory.UserDetail;
import edu.miu.waa.minimartecommerce.jwt_factory.UserDetailService;
import edu.miu.waa.minimartecommerce.service.user.IAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AuthenticationService implements IAuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserDetailService userDetailService;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthenticationService(AuthenticationManager authenticationManager, UserDetailService userDetailService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailService = userDetailService;
        this.jwtUtil = jwtUtil;
    }
    @Override
    public AuthResponse createAuthentication(AuthRequest jwtAuthRequest) {
        authenticate(jwtAuthRequest.getUsername().toLowerCase(), jwtAuthRequest.getPassword());
        final UserDetail userDetail = (UserDetail) userDetailService
                .loadUserByUsername(jwtAuthRequest.getUsername());
        String token = jwtUtil.generateToken(userDetail);
        return new AuthResponse(
                userDetail.getUserId(), token, userDetail.getUsername(),
                userDetail.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
    }

    private void authenticate(String username, String password) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException ex){
            throw new AccountNotActivatedException("User account not activated",ex);
        } catch (InternalAuthenticationServiceException | BadCredentialsException ex){
            throw new AuthenticationException("Either username or password is incorrect", ex);
        }
    }
}
