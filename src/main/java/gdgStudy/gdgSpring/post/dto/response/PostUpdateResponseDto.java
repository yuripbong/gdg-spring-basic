package gdgStudy.gdgSpring.post.dto.response;

import gdgStudy.gdgSpring.post.Post;
import lombok.Getter;

@Getter
public class PostUpdateResponseDto {
    private final String title;
    private final String content;

    public PostUpdateResponseDto(Post post) {
        this.title = post.getTitle();
        this.content = post.getContent();
    }
}
