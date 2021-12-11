package edu.miu.waa.minimartecommerce.service.user.impl;

import edu.miu.waa.minimartecommerce.domain.user.Role;
import edu.miu.waa.minimartecommerce.domain.user.User;
import edu.miu.waa.minimartecommerce.dto.ResponseMessage;
import edu.miu.waa.minimartecommerce.dto.user.UserDto;
import edu.miu.waa.minimartecommerce.repository.user.IRoleRepository;
import edu.miu.waa.minimartecommerce.repository.user.IUserRepository;
import edu.miu.waa.minimartecommerce.service.user.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserService implements IUserService {
    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(IUserRepository userRepository, IRoleRepository roleRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsernameIgnoreCase(username);
    }

    @Override
    public ResponseMessage saveUsers(UserDto userDto, boolean seller) {
        if(!userRepository.existsByUsernameIgnoreCase(userDto.getUsername())){
            User user = modelMapper.map(userDto, User.class);
            Role role;
            if(seller){
                role = roleRepository.findByRole("SELLER");
                user.setAdminApproved(false);
            }
            else{
                role = roleRepository.findByRole("BUYER");
                user.setAdminApproved(true);
            }
            user.setActive(true);
            user.setRoles(Stream.of(role).collect(Collectors.toSet()));
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            userRepository.save(user);

            return new ResponseMessage("Saved Successfully.", HttpStatus.CREATED);
        }
        return new ResponseMessage(
                String.format("Duplicate user!! User with %s already exists.", userDto.getUsername()),
                HttpStatus.BAD_REQUEST);
    }

    @Override
    public List<User> getAllUnapprovedSellers() {
        return userRepository.findAllByAdminApproved(false);
    }

    @Override
    public ResponseMessage approveSellers(long id) {
        Optional<User> userOpt = findById(id);
        if(userOpt.isPresent()){
            User user = userOpt.get();
            user.setAdminApproved(true);
            userRepository.save(user);
            return new ResponseMessage(String.format("%s %s has been approved to be seller", user.getFirstname(), user.getLastname()),
                    HttpStatus.OK);
        }
        return new ResponseMessage("User not found!!", HttpStatus.BAD_REQUEST);
    }
}
