package gdgStudy.gdgSpring.user;

import gdgStudy.gdgSpring.user.dto.request.UserSaveRequestDto;
import gdgStudy.gdgSpring.user.dto.request.UserUpdateRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    // 의존성 주입 (Autowired 비추)
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // CREATE (생성)
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserSaveRequestDto userSaveRequestDto) {
        // User -> responseDto 반환으로 변경
        User createdUser = userService.createUser(userSaveRequestDto);
        return ResponseEntity.ok(createdUser);
    }

    // READ (조회)
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();

        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok(user);
        }
    }

    @GetMapping("/byusername/{username}")
    public ResponseEntity<List<User>> getUserByUsername(@PathVariable("username") String username) {
        Optional<List<User>> users = userService.getUsersByUsername(username);

        if (users.isPresent()) {
            return ResponseEntity.ok(users.get());
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // UPDATE (수정)
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody UserUpdateRequestDto updateUserRequestDto) {
        User user = userService.updateUser(id, updateUserRequestDto);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok(user);
        }
    }

    // DELETE (삭제)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        boolean deleted = userService.deleteUser(id);

        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
