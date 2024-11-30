package gdgStudy.gdgSpring.comment.dto.response;

import gdgStudy.gdgSpring.comment.Comment;
import lombok.Getter;

@Getter
public class CommentResponseDto {

    private final String comment;

    public CommentResponseDto(Comment comment) {
        this.comment = comment.getComment();
    }
}
