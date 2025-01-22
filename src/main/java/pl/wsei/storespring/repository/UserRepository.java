package pl.wsei.storespring.repository;

import jakarta.validation.constraints.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wsei.storespring.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByLogin(@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Login must contain only letters and numbers") String login);
}
