package edu.miu.waa.minimartecommerce.controller.user;

import edu.miu.waa.minimartecommerce.domain.user.User;
import edu.miu.waa.minimartecommerce.service.user.IUserService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService){
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }
}
