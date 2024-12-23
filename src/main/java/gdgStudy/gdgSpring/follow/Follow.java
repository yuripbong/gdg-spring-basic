package gdgStudy.gdgSpring.follow;

import gdgStudy.gdgSpring.common.BaseTimeEntity;
import gdgStudy.gdgSpring.user.User;
import jakarta.persistence.*;

@Entity
public class Follow extends BaseTimeEntity {

    public Follow() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "from_user")
    private User from_user;

    @ManyToOne
    @JoinColumn(name = "to_user")
    private User to_user;
}
