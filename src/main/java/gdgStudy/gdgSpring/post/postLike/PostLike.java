package gdgStudy.gdgSpring.post.postLike;

import gdgStudy.gdgSpring.post.Post;
import gdgStudy.gdgSpring.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
public class PostLike {

    public PostLike() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Builder
    public PostLike(Long id, User user, Post post) {
        this.id = id;
        this.user = user;
        this.post = post;
    }
}
