package edu.miu.waa.minimartecommerce.data_loader;

import edu.miu.waa.minimartecommerce.domain.user.Role;
import edu.miu.waa.minimartecommerce.domain.user.User;
import edu.miu.waa.minimartecommerce.repository.user.IRoleRepository;
import edu.miu.waa.minimartecommerce.repository.user.IUserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static edu.miu.waa.minimartecommerce.constant.user.Role.*;

@Component
public class DataLoader implements ApplicationRunner {
    private final IRoleRepository roleRepository;
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(IRoleRepository roleRepository, IUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (roleRepository.count() == 0) {
            roleRepository.saveAll(
                    Arrays.asList(
                        new Role(ADMIN.toString()),
                        new Role(SELLER.toString()),
                        new Role(BUYER.toString())
                    )
                );
        }

        if(userRepository.count() == 0){
            Set<Role> roles = Stream.of(roleRepository.findByRole(ADMIN.toString()))
                    .collect(Collectors.toSet());
            String password = passwordEncoder.encode("Qwerty12345");
            User user = new User("Admin", "", "MiniMart",
                    "admin@minimart.com", password, roles);
            user.setAdminApproved(true);
            user.setActive(true);
            userRepository.save(user);
        }
    }
}
