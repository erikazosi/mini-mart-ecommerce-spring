package edu.miu.waa.minimartecommerce.data_loader;

import edu.miu.waa.minimartecommerce.domain.user.Role;
import edu.miu.waa.minimartecommerce.domain.user.User;
import edu.miu.waa.minimartecommerce.repository.user.IRoleRepository;
import edu.miu.waa.minimartecommerce.repository.user.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class DataLoader implements ApplicationRunner {
    private final IRoleRepository roleRepository;
    private final IUserRepository userRepository;

    public DataLoader(IRoleRepository roleRepository, IUserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (roleRepository.count() == 0) {
            roleRepository.saveAll(
                    Arrays.asList(
                        new Role("ADMIN"),
                        new Role("SELLER"),
                        new Role("BUYER")
                    )
                );
        }

        if(userRepository.count() == 0){
            Set<Role> roles = Stream.of(roleRepository.findByRole("ADMIN"))
                    .collect(Collectors.toSet());
            User user = new User("Admin", "", "MiniMart",
                    "admin@minimart.com", "Qwerty12345", roles);
            user.setAdminApproved(true);
            user.setActive(true);
            userRepository.save(user);
        }
    }
}
