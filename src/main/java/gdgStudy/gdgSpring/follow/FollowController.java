package gdgStudy.gdgSpring.follow;


import gdgStudy.gdgSpring.follow.dto.FollowDto;
import gdgStudy.gdgSpring.user.User;
import gdgStudy.gdgSpring.user.UserService;
import gdgStudy.gdgSpring.user.dto.response.UserResponseDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<String> follow(@PathVariable String myName, @PathVariable String friendName) {
        User fromUser = userService.getUserByUsername(myName)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 사용자입니다."));

        User toUser = userService.getUserByUsername(friendName)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 사용자입니다."));

        String result = followService.follow(fromUser, toUser);

        return ResponseEntity.ok(result);
    }

    // 팔로잉 리스트
    @GetMapping("/{username}/following")
    public ResponseEntity<List<FollowDto>> getFollowingList(@PathVariable String username, HttpSession session) {
        User fromUser = userService.getUserByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 사용자입니다."));

        UserResponseDto loggedUser = (UserResponseDto) session.getAttribute("user");

        if (loggedUser == null) {
            System.out.println("로그인된 사용자가 없습니다.");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        User requestUser = userService.getCurrentUser(loggedUser.getUsername());

        List<FollowDto> followingList =  followService.followingList(fromUser, requestUser);

        return ResponseEntity.ok(followingList);
    }

    // 팔로워 리스트
    @GetMapping("/{username}/follower")
    public ResponseEntity<List<FollowDto>> getFollowerList(@PathVariable String username, HttpSession session) {
        User toUser = userService.getUserByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 사용자입니다."));

        UserResponseDto loggedUser = (UserResponseDto) session.getAttribute("user");

        if (loggedUser == null) {
            System.out.println("로그인된 사용자가 없습니다.");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        User requestUser = userService.getCurrentUser(loggedUser.getUsername());


        List<FollowDto> followerList = followService.followerList(toUser, requestUser);

        return ResponseEntity.ok(followerList);
    }

    // 팔로우 취소
    @DeleteMapping("/{followId}")
    public ResponseEntity<String> unfollow(@PathVariable Long followId) {
        followService.unfollow(followId);

        return ResponseEntity.noContent().build();
    }

}
