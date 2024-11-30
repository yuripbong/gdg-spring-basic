package gdgStudy.gdgSpring.post;

import gdgStudy.gdgSpring.comment.Comment;
import gdgStudy.gdgSpring.common.BaseTimeEntity;
import gdgStudy.gdgSpring.post.dto.request.PostSaveRequestDto;
import gdgStudy.gdgSpring.user.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Getter
@Entity(name = "posts")
public class Post extends BaseTimeEntity {

    // 기본 생성자
    public Post() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Comment> comments;

    // 각 case별로 생성자가 만들어짐, User 객체 자체를 받아올 필요는 없음?
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
