package edu.miu.waa.minimartecommerce.repository.user;

import edu.miu.waa.minimartecommerce.domain.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role,Long> {
    Role findByRole(String name);
}
