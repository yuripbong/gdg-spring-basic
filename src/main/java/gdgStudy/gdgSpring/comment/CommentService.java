package gdgStudy.gdgSpring.comment;

import gdgStudy.gdgSpring.comment.dto.request.CommentSaveRequestDto;
import gdgStudy.gdgSpring.comment.dto.response.CommentResponseDto;
import gdgStudy.gdgSpring.post.Post;
import gdgStudy.gdgSpring.post.PostRepository;
import gdgStudy.gdgSpring.user.User;
import gdgStudy.gdgSpring.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, UserRepository userRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }


    // 댓글 생성
    public CommentResponseDto createComment(Long userId, Long postId, CommentSaveRequestDto commentSaveRequestDto) {
        User user = userRepository.findById(commentSaveRequestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + commentSaveRequestDto.getUserId()));

        Post post = postRepository.findById(commentSaveRequestDto.getPostId())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id=" + commentSaveRequestDto.getPostId()));

        Comment comment = new Comment(commentSaveRequestDto, user, post);
        Comment savedComment = commentRepository.save(comment);

        return new CommentResponseDto(savedComment);
    }

    // 조회
    public List<CommentResponseDto> getAllComments(Long postId) {
        List<Comment> comments = commentRepository.findAllByPostId(postId);

        return comments.stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());
    }

    // 수정
    @Transactional
    public CommentResponseDto updateComment(Long userId, Long commentId, CommentSaveRequestDto commentSaveRequestDto) {
        Optional<Comment> optComment = commentRepository.findById(commentId);
        Optional<User> user = userRepository.findById(commentSaveRequestDto.getUserId());

        if (optComment.isPresent() && user.isPresent()) {
            Comment comment = optComment.get();
            comment.update(commentSaveRequestDto.getComment());
        }
        return new CommentResponseDto(optComment.orElse(null));
    }

    // 삭제
    public void deleteComment(Long userId, Long commentId) {
        Optional<Comment> optComment = commentRepository.findById(commentId);
        Optional<User> user = userRepository.findById(userId);

        if (optComment.isPresent() && user.isPresent()) {
            commentRepository.delete(optComment.get());
        }
    }
}
