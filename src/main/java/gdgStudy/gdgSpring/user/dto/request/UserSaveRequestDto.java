package gdgStudy.gdgSpring.user.dto.request;

import lombok.Getter;

@Getter
public class UserSaveRequestDto {

    private String username;
    private String password;
    private String email;
}
