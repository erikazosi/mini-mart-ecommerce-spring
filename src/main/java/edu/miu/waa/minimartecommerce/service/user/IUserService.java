package edu.miu.waa.minimartecommerce.service.user;

import edu.miu.waa.minimartecommerce.domain.user.User;
import edu.miu.waa.minimartecommerce.dto.ResponseMessage;
import edu.miu.waa.minimartecommerce.dto.user.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<User> findAll();

    Optional<User> findByUsername(String username);

    ResponseMessage saveUsers(UserDto userDto);
}
