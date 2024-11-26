package gdgStudy.gdgSpring.comment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPostId(Long postId);
    List<Comment> findAllByNickname(String nickname);
}
