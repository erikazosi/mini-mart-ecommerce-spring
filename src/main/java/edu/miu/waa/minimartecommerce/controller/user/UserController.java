package edu.miu.waa.minimartecommerce.controller.user;

import edu.miu.waa.minimartecommerce.domain.user.User;
import edu.miu.waa.minimartecommerce.dto.ResponseMessage;
import edu.miu.waa.minimartecommerce.dto.user.UserDto;
import edu.miu.waa.minimartecommerce.service.user.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping("/save/seller")
    public ResponseEntity<ResponseMessage> saveSeller(@Valid @RequestBody UserDto dto){
        ResponseMessage response = userService.saveUsers(dto, true);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @PostMapping("/save/buyer")
    public ResponseEntity<ResponseMessage> saveBuyer(@Valid @RequestBody UserDto dto){
        ResponseMessage response = userService.saveUsers(dto, false);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }
}
