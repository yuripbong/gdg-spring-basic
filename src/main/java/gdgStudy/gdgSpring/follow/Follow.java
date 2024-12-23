package gdgStudy.gdgSpring.follow;

import gdgStudy.gdgSpring.common.BaseTimeEntity;
import gdgStudy.gdgSpring.user.User;
import jakarta.persistence.*;
import lombok.Builder;

@Entity
public class Follow extends BaseTimeEntity {

    public Follow() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "from_user")
    private User fromUser;

    @ManyToOne
    @JoinColumn(name = "to_user")
    private User toUser;

    @Builder
    public Follow(Long id, User fromUser, User toUser) {
        this.id = id;
        this.fromUser = fromUser;
        this.toUser = toUser;
    }
}
