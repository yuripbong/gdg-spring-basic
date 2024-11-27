package gdgStudy.gdgSpring.comment.dto.request;

import lombok.Getter;

@Getter
public class CommentRequestDto {

    private Long id;
    private String comment;
    private String user;
    private Long postId;
}
