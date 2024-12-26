package gdgStudy.gdgSpring.follow;


import gdgStudy.gdgSpring.follow.dto.FollowDto;
import gdgStudy.gdgSpring.user.User;
import gdgStudy.gdgSpring.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/follow")
public class FollowController {

    // 의존성 주입
    private final FollowService followService;
    private final UserService userService;

    public FollowController(FollowService followService, UserService userService) {
        this.followService = followService;
        this.userService = userService;
    }

    // 팔로우
    @PostMapping("/{myName}/to/{friendName}")
    public ResponseEntity<?> follow(@PathVariable String myName, @PathVariable String friendName) {
        User fromUser = userService.getUserByUsername(myName)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 사용자입니다."));

        User toUser = userService.getUserByUsername(friendName)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 사용자입니다."));

        followService.follow(fromUser, toUser);

        return ResponseEntity.ok().build();
    }

    // 팔로잉 리스트
    @GetMapping("/{username}/following")
    public ResponseEntity<List<FollowDto>> getFollowingList(@PathVariable String username) {
        User fromUser = userService.getUserByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 사용자입니다."));

        followService.followingList(fromUser);

        return ResponseEntity.notFound().build();
    }

    // 팔로워 리스트
    @GetMapping("/{username}/follower")
    public ResponseEntity<List<FollowDto>> getFollowerList(@PathVariable String username) {
        User toUser = userService.getUserByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 사용자입니다."));

        followService.followerList(toUser);

        return ResponseEntity.notFound().build();
    }

    // 팔로우 취소
    @DeleteMapping("/{friendName}/{username}")
    public ResponseEntity<String> unfollow(@PathVariable String friendName, @PathVariable String username) {
        User fromUser = userService.getUserByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 사용자입니다."));

        User toUser = userService.getUserByUsername(friendName)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 사용자입니다."));

        followService.unfollow(fromUser, toUser);

        return ResponseEntity.ok().build();
    }

}
