package software.aditya.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import software.aditya.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    boolean existsByEmail(String email);
}
