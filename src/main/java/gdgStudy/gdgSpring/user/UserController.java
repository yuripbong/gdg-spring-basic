package gdgStudy.gdgSpring.user;

import gdgStudy.gdgSpring.user.dto.request.UserSaveRequestDto;
import gdgStudy.gdgSpring.user.dto.response.UserSaveResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private UserService userService;

    // CREATE (생성)
    @PostMapping("/users")
    public ResponseEntity<UserSaveResponseDto> createUser(@RequestBody UserSaveRequestDto userDto) {
        UserSaveResponseDto createdUser = userService.createUser(userDto);
        return ResponseEntity.ok(createdUser);
    }

    // READ

}
