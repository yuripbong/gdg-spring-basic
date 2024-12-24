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

        Optional<User> fromUser = userService.getUserByUsername(myName);
        Optional<User> toUser = userService.getUserByUsername(friendName);

        if (fromUser.isPresent() && toUser.isPresent()) {
            followService.follow(fromUser, toUser);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    // 팔로잉 리스트
    @GetMapping("/{username}/following")
    public ResponseEntity<List<FollowDto>> getFollowingList(@PathVariable String username) {
        Optional<User> fromUser = userService.getUserByUsername(username);

        if (fromUser.isPresent()) {
            return ResponseEntity.ok().body(followService.followingList(fromUser));
        }

        return ResponseEntity.notFound().build();
    }

    // 팔로워 리스트
    @GetMapping("/{username}/follower")
    public ResponseEntity<List<FollowDto>> getFollowerList(@PathVariable String username) {
        Optional<User> fromUser = userService.getUserByUsername(username);

        if (fromUser.isPresent()) {
            return ResponseEntity.ok().body(followService.followerList(fromUser));
        }

        return ResponseEntity.notFound().build();
    }

    // 팔로우 취소
    @DeleteMapping("/{friendName}/{username}")
    public ResponseEntity<String> unfollow(@PathVariable String friendName, @PathVariable String username) {
        Optional<User> fromUser = userService.getUserByUsername(username);
        Optional<User> toUser = userService.getUserByUsername(friendName);

        if (fromUser.isPresent() && toUser.isPresent()) {
            return ResponseEntity.ok().body(followService.unfollow(fromUser));
        }

        return ResponseEntity.notFound().build();
    }

}
