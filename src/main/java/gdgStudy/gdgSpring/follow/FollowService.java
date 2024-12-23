package gdgStudy.gdgSpring.follow;

import gdgStudy.gdgSpring.follow.dto.FollowDto;
import gdgStudy.gdgSpring.user.User;
import gdgStudy.gdgSpring.user.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    public FollowService(FollowRepository followRepository, UserRepository userRepository) {
        this.followRepository = followRepository;
        this.userRepository = userRepository;
    }

    // follow 요청
    public String follow(User from_user, User to_user) {

        // 자기 자신 follow X
        if (from_user.equals(to_user)) {
            throw new IllegalArgumentException("자기 자신을 팔로우할 수 없습니다.");
        }

        // 중복 follow X
        if (followRepository.findFollow(from_user, to_user).isPresent()) {
            throw new IllegalArgumentException("이미 팔로우 했습니다.");
        }

        Follow follow = Follow.builder()
                .toUser(to_user)
                .fromUser(from_user)
                .build();

        followRepository.save(follow);

        return "팔로우 성공";
    }

    // following 리스트
    public List<FollowDto> followingList(User selectUser, User requestUser) {

        List<Follow> list = followRepository.findByFromUser(selectUser);
        List<FollowDto> followList = new ArrayList<>();

        for (Follow follow : list) {
            followList.add(userRepository.findByUsername(follow.getToUser().getUsername())
                    .orElseThrow().toFollow(findStatus(follow.getToUser(), requestUser)));
        }

        return followList;
    }

    // follower 리스트
    public List<FollowDto> followerList(User selectUser, User requestUser) {

        List<Follow> list = followRepository.findByFromUser(selectUser);
        List<FollowDto> followList = new ArrayList<>();

        for (Follow follow : list) {
            followList.add(userRepository.findByUsername(follow.getFromUser().getUsername())
                    .orElseThrow().toFollow(findStatus(follow.getFromUser(), requestUser)));
        }

        return followerList;
    }

    // A와 B의 관계
    protected String findStatus(User selectUser, User requestUser) {

        if ((selectUser.getUsername()).equals(requestUser.getUsername())) {
            return "본인";
        }

        if (followRepository.findFollow(selectUser, requestUser).isEmpty()) {
            return "없음";
        }

        return "팔로잉";
    }

    // follow 취소
    public String cacelFollow(User user) {

        followRepository.deleteByFromUser(user);

        return "언팔로우 성공";
    }
}
