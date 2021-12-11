package edu.miu.waa.minimartecommerce.repository.user;

import edu.miu.waa.minimartecommerce.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long>{
    Optional<User> findByUsernameIgnoreCase(String username);

    boolean existsByUsernameIgnoreCase(String username);

    List<User> findAllByAdminApproved(boolean adminApproved);
}
