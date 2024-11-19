package gdgStudy.gdgSpring.post;

import gdgStudy.gdgSpring.post.dto.request.PostSaveRequestDto;
import gdgStudy.gdgSpring.post.dto.response.PostSaveResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // CREATE (생성)
    public PostSaveResponseDto createPost(PostSaveRequestDto postSaveRequestDto) {
        Post post = new Post(postSaveRequestDto);
        Post savedPost = postRepository.save(post);

        return new PostSaveResponseDto(savedPost);
    }

    // READ (조회)
    public List<PostSaveResponseDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();

        return posts.stream()
                .map(PostSaveResponseDto::new)
                .collect(Collectors.toList());
    }
}
