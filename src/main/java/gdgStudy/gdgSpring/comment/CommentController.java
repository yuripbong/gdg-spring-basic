package gdgStudy.gdgSpring.comment;

import gdgStudy.gdgSpring.comment.dto.request.CommentRequestDto;
import gdgStudy.gdgSpring.comment.dto.response.CommentResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // 댓글 생성
    @PostMapping("/users/{userId}/posts/{id}/comments")
    public ResponseEntity<CommentResponseDto> save(@PathVariable Long userId, @PathVariable Long id, @RequestBody CommentRequestDto commentSaveRequestDto) {
        CommentResponseDto commentResponseDto = commentService.save(id, commentSaveRequestDto, userId);

        return ResponseEntity.ok(commentResponseDto);
    }

    // 댓글 읽어오기
    @GetMapping("/posts/{id}/comments")
    public ResponseEntity<List<CommentResponseDto>> read(@PathVariable Long id) {
        List<CommentResponseDto> commentList = commentService.findAll(id);

        return ResponseEntity.ok(commentList);
    }

    // 댓글 수정
    @PutMapping("/posts/{postsId}/comments/{id}")
    public ResponseEntity<CommentResponseDto> update(@PathVariable Long postsId, @PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto) {
        CommentResponseDto commentResponseDto = commentService.update(postsId, id, commentRequestDto);

        if (commentResponseDto != null) {
            return ResponseEntity.ok(commentResponseDto);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 댓글 삭제
    @DeleteMapping("posts/{postsId}/comments/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long postsId, @PathVariable Long id) {
        commentService.delete(postsId, id);

        return ResponseEntity.ok(id);
    }
}
