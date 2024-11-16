package gdgStudy.gdgSpring.user;

import gdgStudy.gdgSpring.user.dto.request.UserSaveRequestDto;
import gdgStudy.gdgSpring.user.dto.request.UserUpdateRequestDto;
import gdgStudy.gdgSpring.user.dto.response.UserResponseDto;
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
}
