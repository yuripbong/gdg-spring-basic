package gdgStudy.gdgSpring.post;

import gdgStudy.gdgSpring.post.dto.request.PostSaveRequestDto;
import gdgStudy.gdgSpring.post.dto.response.PostSaveResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 등록
    @PostMapping("/api/posts")
    public ResponseEntity<PostSaveResponseDto> createPost(@RequestBody PostSaveRequestDto postSaveRequestDto) {
        PostSaveResponseDto postSaveResponseDto = postService.createPost(postSaveRequestDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(postSaveResponseDto);
    }

    // READ (조회)
    @GetMapping("/api/posts")
    public ResponseEntity<List<PostSaveResponseDto>> getAllUsers() {
        List<PostSaveResponseDto> postList = postService.getAllPosts();

        return ResponseEntity.ok(postList);
    }

    @GetMapping("/api/posts/{id}")
    public ResponseEntity<PostSaveResponseDto> getPostById(@PathVariable Long id) {
        Optional<PostSaveResponseDto> postSaveResponseDto = postService.getPostByID(id);

        if (postSaveResponseDto.isPresent()) {
            return ResponseEntity.ok(postSaveResponseDto.get());
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE (삭제)
    @DeleteMapping("/api/posts/{id}")
    public ResponseEntity<PostSaveResponseDto> deletePost(@PathVariable Long id) {
        boolean deleted = postService.deletePost(id);

        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
