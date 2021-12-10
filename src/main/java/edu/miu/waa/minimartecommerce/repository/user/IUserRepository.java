package edu.miu.waa.minimartecommerce.repository.user;

import edu.miu.waa.minimartecommerce.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, String>{
    Optional<User> findByEmailId(String email);

}
