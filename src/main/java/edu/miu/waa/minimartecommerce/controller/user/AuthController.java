package edu.miu.waa.minimartecommerce.controller.user;

import edu.miu.waa.minimartecommerce.dto.user.AuthRequest;
import edu.miu.waa.minimartecommerce.dto.user.AuthResponse;
import edu.miu.waa.minimartecommerce.service.user.IAuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final IAuthenticationService authenticationService;

    public AuthController(IAuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest dto){
        return new ResponseEntity<>(authenticationService.createAuthentication(dto), HttpStatus.OK);
    }
}
