package gdgStudy.gdgSpring.comment.dto.request;

import lombok.Getter;

@Getter
public class CommentSaveRequestDto {

    private String comment;
    private Long userId;
    private Long postId;
}
