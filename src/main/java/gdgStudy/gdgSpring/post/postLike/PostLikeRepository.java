package gdgStudy.gdgSpring.post.postLike;

import gdgStudy.gdgSpring.post.Post;
import gdgStudy.gdgSpring.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

    Optional<PostLike> findByUserAndPost(User user, Post post);
}
