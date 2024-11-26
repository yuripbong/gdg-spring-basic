package gdgStudy.gdgSpring.comment;

import gdgStudy.gdgSpring.comment.dto.request.CommentSaveRequestDto;
import gdgStudy.gdgSpring.comment.dto.request.CommentUpdateRequestDto;
import gdgStudy.gdgSpring.comment.dto.response.CommentResponseDto;
import gdgStudy.gdgSpring.comment.dto.response.CommentUpdateResponseDto;
import gdgStudy.gdgSpring.post.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    private final CommentService commentService;
    private final PostService postService;

    public CommentController(CommentService commentService, PostService postService) {
        this.commentService = commentService;
        this.postService = postService;
    }

    // 댓글 생성
    @PostMapping("/comments/{postId}")
    public ResponseEntity<CommentResponseDto> addComment(@PathVariable Long postId, @RequestBody CommentSaveRequestDto commentSaveRequestDto) {
        CommentResponseDto commentResponseDto = commentService.createComment(postId, commentSaveRequestDto);

        return ResponseEntity.ok(commentResponseDto);
    }

    // 댓글 수정
    @PutMapping("/comments/{id}")
    public ResponseEntity<?> updateComment(@PathVariable Long id, @RequestBody CommentUpdateRequestDto commentUpdateRequestDto) {
        CommentUpdateResponseDto commentUpdateResponseDto = commentService.updateComment(id, commentUpdateRequestDto);

        String message = (id == null) ? "잘못된 요청입니다." : "댓글이 수정되었습니다.";

        return ResponseEntity.ok(message);
    }

    // 댓글 삭제
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id, @RequestParam String nickname) {
        Long postId = commentService.deleteComment(id, nickname);

        String message = (postId == null) ? "작성자만 삭제 가능합니다." : "댓글이 삭제되었습니다.";

        return ResponseEntity.ok(message);
    }
}
