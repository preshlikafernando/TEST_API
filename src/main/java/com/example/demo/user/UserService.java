import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public List<User> list() {
        try {
            return repo.findAll();
        } catch (Exception e) {
            // Log error in real app
            return List.of(); // return empty list instead of throwing
        }
    }

    public Optional<User> get(long id) {
        try {
            return repo.findById(id);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
