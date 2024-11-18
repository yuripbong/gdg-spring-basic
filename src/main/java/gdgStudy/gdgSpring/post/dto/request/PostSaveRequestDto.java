package gdgStudy.gdgSpring.post.dto.request;

import gdgStudy.gdgSpring.user.User;
import lombok.Getter;

@Getter
public class PostSaveRequestDto {

    private String title;
    private String content;
    private User user;
}
