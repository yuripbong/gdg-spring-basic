package gdgStudy.gdgSpring.comment;

import gdgStudy.gdgSpring.comment.dto.request.CommentSaveRequestDto;
import gdgStudy.gdgSpring.post.Post;
import gdgStudy.gdgSpring.user.User;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity(name = "comments")
public class Comment {

    public Comment() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Comment(CommentSaveRequestDto commentSaveRequestDto, User user, Post post) {
        this.comment = commentSaveRequestDto.getComment();
        this.user = user;
        this.post = post;
    }

    public void update(String newContent) {
        this.comment = newContent;
    }
}

