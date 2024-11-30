package gdgStudy.gdgSpring.user.dto.response;

import gdgStudy.gdgSpring.user.User;
import lombok.Getter;

@Getter
public class UserResponseDto {

    private final String username;
    private final String email;
    private final String password;
    private final String nickname;

    public UserResponseDto(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.nickname = user.getNickname();
    }
}
