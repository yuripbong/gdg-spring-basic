package gdgStudy.gdgSpring.post;

import gdgStudy.gdgSpring.post.dto.request.PostSaveRequestDto;
import gdgStudy.gdgSpring.post.dto.response.PostSaveResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

}
