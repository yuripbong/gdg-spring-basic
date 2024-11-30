package gdgStudy.gdgSpring.comment.dto.response;

import gdgStudy.gdgSpring.comment.Comment;

public class CommentUpdateResponseDto {
    private String Comment;

    public CommentUpdateResponseDto(Comment comment) {
        this.Comment = comment.getComment();
    }
}
