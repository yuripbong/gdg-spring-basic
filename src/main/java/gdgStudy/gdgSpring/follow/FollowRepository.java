package gdgStudy.gdgSpring.follow;

import gdgStudy.gdgSpring.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    // 팔로우 조회
    List<Follow> findByFromUser(User fromUser);
    // 팔로잉 조회
    List<Follow> findByToUser(User toUser);
    // 팔로우 취소
    void unfollowByFromUser(User fromUser);
    // fromUser가 toUser를 팔로우하는 관계 조회
    Optional<Follow> findByFromUserAndToUser(User fromUser, User toUser);
}
