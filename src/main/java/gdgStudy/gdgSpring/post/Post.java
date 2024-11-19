package gdgStudy.gdgSpring.post;

import gdgStudy.gdgSpring.common.BaseTimeEntity;
import gdgStudy.gdgSpring.post.dto.request.PostSaveRequestDto;
import gdgStudy.gdgSpring.user.User;
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
    private User user;

    public Post(PostSaveRequestDto postSaveRequestDto) {
        this.title = postSaveRequestDto.getTitle();
        this.content = postSaveRequestDto.getContent();
        this.user = postSaveRequestDto.getUser();
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
