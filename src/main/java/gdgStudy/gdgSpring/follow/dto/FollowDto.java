package gdgStudy.gdgSpring.follow.dto;

import lombok.Getter;

@Getter
public class FollowDto {

    private String username;

    public FollowDto(String username) {
        this.username = username;
    }
}
