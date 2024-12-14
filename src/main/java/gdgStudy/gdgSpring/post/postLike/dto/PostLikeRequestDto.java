package gdgStudy.gdgSpring.post.postLike.dto;

import lombok.Getter;

@Getter
public class PostLikeRequestDto {

    private Long userId;
    private Long postId;

    public PostLikeRequestDto(Long userId, Long postId) {
        this.userId = userId;
        this.postId = postId;
    }
}
