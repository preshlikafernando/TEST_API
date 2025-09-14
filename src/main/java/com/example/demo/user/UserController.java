import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService svc;

    public UserController(UserService svc) {
        this.svc = svc;
    }

    @GetMapping
    public List<User> all() {
        return svc.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> one(@PathVariable long id) {
        return svc.get(id)
            .map(u -> Map.of(
                "id", u.getId(),
                "name", u.getName()
            ))
            .map(ResponseEntity::ok)
            .orElseGet(() -> {
                Map<String, Object> error = new HashMap<>();
                error.put("status", 404);
                error.put("error", "Not Found");
                error.put("message", "User with id " + id + " not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            });
    }
}
