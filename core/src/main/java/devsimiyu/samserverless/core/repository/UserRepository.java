package devsimiyu.samserverless.core.repository;

import devsimiyu.samserverless.core.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> { }
