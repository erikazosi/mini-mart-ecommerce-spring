package edu.miu.waa.minimartecommerce.jwt_factory;

import edu.miu.waa.minimartecommerce.domain.user.User;
import edu.miu.waa.minimartecommerce.repository.user.IUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailService  implements UserDetailsService {
    private final IUserRepository userRepository;

    public UserDetailService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmailId(username);
        user.orElseThrow(() -> new UsernameNotFoundException("Not FOUND..."));
        return new UserDetail(user.get());
    }
}
