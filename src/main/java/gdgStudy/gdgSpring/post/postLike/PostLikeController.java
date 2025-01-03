package gdgStudy.gdgSpring.post.postLike;

import gdgStudy.gdgSpring.post.postLike.dto.PostLikeRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/postLikes")
public class PostLikeController {

    private final PostLikeService postLikeService;

    public PostLikeController(PostLikeService postLikeService) {
        this.postLikeService = postLikeService;
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody @Validated PostLikeRequestDto postLikeRequestDto) {
        postLikeService.insert(postLikeRequestDto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody @Validated PostLikeRequestDto postLikeRequestDto) {
        postLikeService.delete(postLikeRequestDto);

        return ResponseEntity.ok().build();
    }
}
