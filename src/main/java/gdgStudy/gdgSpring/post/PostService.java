package gdgStudy.gdgSpring.post;

import gdgStudy.gdgSpring.post.dto.request.PostSaveRequestDto;
import gdgStudy.gdgSpring.post.dto.request.PostUpdateRequestDto;
import gdgStudy.gdgSpring.post.dto.response.PostSaveResponseDto;
import gdgStudy.gdgSpring.post.dto.response.PostUpdateResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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

    public Optional<PostSaveResponseDto> getPostByID(Long id) {

        return postRepository.findById(id)
                .map(PostSaveResponseDto::new);
    }

    // UPDATE (수정)
    @Transactional
    public PostUpdateResponseDto updatePost(Long id, PostUpdateRequestDto postUpdateRequestDto) {
        Post existingPost = postRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("해당 유저가 없습니다. id = " + id));

        existingPost.update(
                postUpdateRequestDto.getTitle(),
                postUpdateRequestDto.getContent()
        );

        return new PostUpdateResponseDto(existingPost);
    }

    // DELETE (삭제)
    public boolean deletePost(Long id) {
        Optional<Post> existingPost = postRepository.findById(id);

        if (existingPost.isPresent()) {
            postRepository.delete(existingPost.get());
            return true;
        } else {
            return false;
        }
    }
}
