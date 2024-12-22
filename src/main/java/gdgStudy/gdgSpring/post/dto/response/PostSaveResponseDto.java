package gdgStudy.gdgSpring.post.dto.response;

import gdgStudy.gdgSpring.post.Post;
import lombok.Getter;

@Getter
public class PostSaveResponseDto {
    private final String title;
    private final String content;

    public PostSaveResponseDto(Post post) {
        this.title = post.getTitle();
        this.content = post.getContent();
    }
}
