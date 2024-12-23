package gdgStudy.gdgSpring.follow;

import gdgStudy.gdgSpring.user.User;
import gdgStudy.gdgSpring.user.UserService;
import gdgStudy.gdgSpring.user.dto.response.UserResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/follow")
@RestController
public class FollowController {

    private final UserService userService;
    private final FollowService followService;

    public FollowController(UserService userService, FollowService followService) {
        this.userService = userService;
        this.followService = followService;
    }

    // 팔로우
    @PostMapping("/{friendName}/{username}")
    public ResponseEntity<?> follow(@PathVariable String friendName, @PathVariable String username) {

        Optional<UserResponseDto> fromUser = userService.getUserByUsername(username);
        Optional<UserResponseDto> toUser = userService.getUserByUsername(friendName);

        followService.follow(fromUser, toUser);

        return ResponseEntity.ok().build();
    }

    // 팔로잉 조회
    @GetMapping("/{username}")
    public ResponseEntity<List<FollowDto>> getFollowingList(@PathVariable String username) {
        User fromUser = userService.getUserByUsername(username);
        User requestUser = userService.findUser();

        return ResponseEntity.ok().body(followService.followingList(fromUser, requestUser));
    }

    // 팔로워 조회
    @GetMapping("/{username}")
    public ResponseEntity<List<FollowDto>> getFollowerList(@PathVariable String username) {
        User toUser = userService.getUserByUsername(username);
        User requestUser = userService.getUserByUsername();

        return ResponseEntity.ok().body(followService.followerList(toUser, requestUser));
    }

    // 팔로우 취소
    @DeleteMapping("/{friendName}")
    public ResponseEntity<String> deleteFollow(@PathVariable String friendName) {

        return ResponseEntity.ok().body(followService.cancelFollow(userService.getUserByUsername()));
    }




}
