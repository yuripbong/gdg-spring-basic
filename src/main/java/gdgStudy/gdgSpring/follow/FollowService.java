package gdgStudy.gdgSpring.follow;

import gdgStudy.gdgSpring.follow.dto.FollowDto;
import gdgStudy.gdgSpring.user.User;
import gdgStudy.gdgSpring.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    public FollowService(FollowRepository followRepository, UserRepository userRepository) {
        this.followRepository = followRepository;
        this.userRepository = userRepository;
    }

    // 팔로우
    public String follow(User fromUser, User toUser) {

        // 자기 자신 팔로우 X
        if (fromUser.equals(toUser)) {
            throw new IllegalArgumentException("자기 자신을 팔로우할 수 없습니다.");
        }

        // 중복 팔로우 X
        if (followRepository.findFollow(fromUser, toUser).isPresent()) {
            throw new IllegalArgumentException("이미 팔로우 했습니다.");
        }

        Follow follow = Follow.builder()
                .fromUser(fromUser)
                .toUser(toUser)
                .build();

        followRepository.save(follow);

        return "팔로우 성공";
    }

    // 팔로잉 리스트
    public List<FollowDto> followingList(User fromUser, User requestUser) {
        List<Follow> list = followRepository.findByFromUser(requestUser);
        List<FollowDto> followList = new ArrayList<>();

        for (Follow follow : list) {
            User toUser = follow.getToUser();
            FollowDto followDto = new FollowDto(
                    toUser.getUsername()
            );
            followList.add(followDto); // FollowDto 객체를 직접 생성하여 추가
        }

        return followList;
    }

    // 팔로워 리스트
    public List<FollowDto> followerList(User toUser, User requestUser) {
        List<Follow> list = followRepository.findByToUser(toUser);
        List<FollowDto> followerList = new ArrayList<>();

        for (Follow follow : list) {
            User fromUser = follow.getFromUser();
            FollowDto followDto = new FollowDto(
                    fromUser.getUsername()
            );
            followerList.add(followDto); // FollowDto 객체를 직접 생성하여 추가
        }

        return followerList;
    }

    // 팔로우 관계
    protected String findStatus(User selectUser, User requestUser) {
        if (selectUser.getUsername().equals(requestUser.getUsername())) {
            return "self";
        }
        if (followRepository.findFollow(selectUser, requestUser).isEmpty()) {
            return "none";
        }

        return "following";
    }

    // 팔로우 취소
    public void unfollow(Long followId) {
        Follow follow = followRepository.findById(followId)
                .orElseThrow(() -> new IllegalArgumentException("오류"));

        followRepository.delete(follow);
    }

}
