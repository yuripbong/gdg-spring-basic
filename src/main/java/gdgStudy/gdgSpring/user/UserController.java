package gdgStudy.gdgSpring.user;

import gdgStudy.gdgSpring.user.dto.request.UserSaveRequestDto;
import gdgStudy.gdgSpring.user.dto.request.UserUpdateRequestDto;
import gdgStudy.gdgSpring.user.dto.response.UserResponseDto;
import jakarta.servlet.http.HttpSession;
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
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserSaveRequestDto userSaveRequestDto) {
        UserResponseDto userResponseDto = userService.createUser(userSaveRequestDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userResponseDto);
    }

    // READ (조회)
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> userList = userService.getAllUsers();

        return ResponseEntity.ok(userList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable("id") Long id) {
        Optional<UserResponseDto> responseDto = userService.getUserById(id);

        if (responseDto.isPresent()) {
            return ResponseEntity.ok(responseDto.get());
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable("username") String username) {
        return userService.getUserByUsername(username)
                .map(user -> ResponseEntity.ok(user))
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE (수정)
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable("id") Long id, @RequestBody UserUpdateRequestDto updateUserRequestDto) {
        UserResponseDto user = userService.updateUser(id, updateUserRequestDto);

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
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(@RequestBody UserSaveRequestDto userSaveRequestDto, HttpSession session) {
        UserResponseDto userResponseDto = userService.authenticateUser(userSaveRequestDto);

        if (userResponseDto != null) {
            session.setAttribute("user", userResponseDto);
            return ResponseEntity.ok(userResponseDto);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        session.invalidate(); // 세션 무효화

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // 현재 로그인한 사용자 정보 조회
    @GetMapping("/current")
    public ResponseEntity<UserResponseDto> getCurrentUser(@SessionAttribute("user") UserResponseDto userResponseDto) {
        return ResponseEntity.ok(userResponseDto);
    }
}
