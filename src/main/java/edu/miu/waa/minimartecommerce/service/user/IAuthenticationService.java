package edu.miu.waa.minimartecommerce.service.user;


import edu.miu.waa.minimartecommerce.dto.user.AuthRequest;
import edu.miu.waa.minimartecommerce.dto.user.AuthResponse;

public interface IAuthenticationService {
    AuthResponse createAuthentication(AuthRequest jwtAuthRequest);
}
