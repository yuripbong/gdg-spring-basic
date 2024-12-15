package gdgStudy.gdgSpring.post.postLike;

import gdgStudy.gdgSpring.post.Post;
import gdgStudy.gdgSpring.post.PostRepository;
import gdgStudy.gdgSpring.post.postLike.dto.PostLikeRequestDto;
import gdgStudy.gdgSpring.user.User;
import gdgStudy.gdgSpring.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class PostLikeService {

    private final PostLikeRepository postLikeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public PostLikeService(PostLikeRepository postLikeRepository, UserRepository userRepository, PostRepository postRepository) {
        this.postLikeRepository = postLikeRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    // 좋아요 누르기
    public void insert(PostLikeRequestDto postLikeRequestDto) {

        User user = userRepository.findById(postLikeRequestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다. id = " + postLikeRequestDto.getUserId()));

        Post post = postRepository.findById(postLikeRequestDto.getPostId())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id = " + postLikeRequestDto.getPostId()));

        // 이미 좋아요를 누른 경우 에러 반환
        if (postLikeRepository.findByUserAndPost(user, post).isPresent()) {
            throw new IllegalStateException("이미 좋아요를 누른 상태입니다.");
        }

        PostLike postLike = PostLike.builder()
                .user(user)
                .post(post)
                .build();
    }

    // 좋아요 취소
    public void delete(PostLikeRequestDto postLikeRequestDto) {
        User user = userRepository.findById(postLikeRequestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다. id = " + postLikeRequestDto.getUserId()));

        Post post = postRepository.findById(postLikeRequestDto.getPostId())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id = " + postLikeRequestDto.getPostId()));

        PostLike postLike = postLikeRepository.findByUserAndPost(user, post)
                .orElseThrow(() -> new IllegalArgumentException("해당 좋아요가 없습니다."));

        postLikeRepository.delete(postLike);
    }

}
