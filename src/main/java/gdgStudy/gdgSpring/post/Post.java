package gdgStudy.gdgSpring.post;

import gdgStudy.gdgSpring.common.BaseTimeEntity;
import gdgStudy.gdgSpring.post.dto.request.PostSaveRequestDto;
import gdgStudy.gdgSpring.user.User;
import gdgStudy.gdgSpring.user.dto.request.UserSaveRequestDto;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity(name = "posts")
public class Post extends BaseTimeEntity {

    // 기본 생성자
    public Post() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long post_id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Post(PostSaveRequestDto postSaveRequestDto, User user) {
        this.title = postSaveRequestDto.getTitle();
        this.content = postSaveRequestDto.getContent();
        this.user = user;
    }

    // 게시글 수정
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
