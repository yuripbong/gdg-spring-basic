package gdgStudy.gdgSpring.user.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserSaveResponseDto {

    private final String username;
    private final String email;

    public UserSaveResponseDto(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
