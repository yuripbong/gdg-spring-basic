package gdgStudy.gdgSpring.comment;

import gdgStudy.gdgSpring.comment.dto.request.CommentRequestDto;
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
    public CommentResponseDto save(Long postId, CommentRequestDto commentRequestDto, Long userId) {
        Optional<User> optUser = userRepository.findById(userId);
        Optional<Post> optPost = postRepository.findById(postId);

        if (optUser.isPresent() && optPost.isPresent()) {
            Comment comment = new Comment(commentRequestDto, optUser.get(), optPost.get());
            Comment savedComment = commentRepository.save(comment);

            return new CommentResponseDto(Optional.of(savedComment));
        }
        return null;
    }

    // 조회
    public Optional<List<CommentResponseDto>> findAll(Long postId) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()) {
            Optional<List<CommentResponseDto>> commentResponseDto = commentRepository.findAll(postId)
                    .stream()
                    .map(comment -> new CommentResponseDto(comment))
                    .collect(Collectors.toList());
            return commentResponseDto;
        }
        return null;
    }

    // 수정
    @Transactional
    public CommentResponseDto update(Long postId, Long id, CommentRequestDto commentRequestDto) {
        Optional<Comment> optComment = commentRepository.findById(id);
        Optional<Post> optPost = postRepository.findById(postId);

        if (optComment.isPresent() && optPost.isPresent()) {
            optComment.get().update(
                    commentRequestDto.getComment()
            );
        }

        return new CommentResponseDto(optComment);
    }

    // 삭제
    public void delete(Long postId, Long commentId) {
        Optional<Comment> optComment = commentRepository.findById(commentId);
        Optional<Post> optPost = postRepository.findById(postId);

        if (optComment.isPresent() && optPost.isPresent()) {
            commentRepository.delete(optComment.get());
        }
    }
}
