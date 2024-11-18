package gdgStudy.gdgSpring.post.dto.response;

import gdgStudy.gdgSpring.post.Post;
import gdgStudy.gdgSpring.user.User;
import lombok.Getter;

@Getter
public class PostSaveResponseDto {
    private final String title;
    private final String content;
    private final User user;

    public PostSaveResponseDto(Post post) {
        this.title = post.getTitle();
        this.content = post.getContent();
        this.user = post.getUser();
    }
}
