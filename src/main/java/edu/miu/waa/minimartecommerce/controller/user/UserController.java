package edu.miu.waa.minimartecommerce.controller.user;

import com.fasterxml.jackson.annotation.JsonView;
import edu.miu.waa.minimartecommerce.domain.user.User;
import edu.miu.waa.minimartecommerce.dto.ResponseMessage;
import edu.miu.waa.minimartecommerce.dto.user.UserDto;
import edu.miu.waa.minimartecommerce.service.user.IUserService;
import edu.miu.waa.minimartecommerce.view.View;
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

    @JsonView(View.UserListView.class)
    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping("/seller")
    public ResponseEntity<ResponseMessage> saveSeller(@Valid @RequestBody UserDto dto){
        ResponseMessage response = userService.saveUsers(dto, true);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @PostMapping("/buyer")
    public ResponseEntity<ResponseMessage> saveBuyer(@Valid @RequestBody UserDto dto){
        ResponseMessage response = userService.saveUsers(dto, false);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    /* ---- Admin ----- */
    @JsonView(View.UserListView.class)
    @GetMapping("/seller/unapproved/get-all")
    public ResponseEntity<List<User>> findAllUnapprovedSellers(){
        return ResponseEntity.ok(userService.getAllUnapprovedSellers());
    }

    @GetMapping("/seller/{id}/approve")
    public ResponseEntity<ResponseMessage> approveSeller(@PathVariable long id){
        ResponseMessage response = userService.approveSellers(id);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }
}
