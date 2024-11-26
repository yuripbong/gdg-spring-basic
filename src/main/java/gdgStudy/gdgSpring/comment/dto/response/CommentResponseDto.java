package gdgStudy.gdgSpring.comment.dto.response;

import gdgStudy.gdgSpring.comment.Comment;
import lombok.Getter;

@Getter
public class CommentResponseDto {

    private String comment;
    private String nickname;

    public CommentResponseDto(Comment comment) {
        this.comment = comment.getComment();
        this.nickname = comment.getUser().getNickname();
    }
}
