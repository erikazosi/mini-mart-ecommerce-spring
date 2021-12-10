package edu.miu.waa.minimartecommerce.service.user.impl;

import edu.miu.waa.minimartecommerce.domain.user.User;
import edu.miu.waa.minimartecommerce.dto.ResponseMessage;
import edu.miu.waa.minimartecommerce.dto.user.UserDto;
import edu.miu.waa.minimartecommerce.repository.user.IRoleRepository;
import edu.miu.waa.minimartecommerce.repository.user.IUserRepository;
import edu.miu.waa.minimartecommerce.service.user.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;

    public UserService(IUserRepository userRepository, IRoleRepository roleRepository){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByEmailId(username);
    }

    @Override
    public ResponseMessage saveUsers(UserDto userDto) {
        return null;
    }
}
