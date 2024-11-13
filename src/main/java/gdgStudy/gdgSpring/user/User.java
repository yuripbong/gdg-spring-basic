package gdgStudy.gdgSpring.user;

import gdgStudy.gdgSpring.user.dto.request.UserSaveRequestDto;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity(name = "users")
public class User {

    // NoArgsConstructor 없애고 만든 기본 생성자
    public User() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String nickname;

    public User(UserSaveRequestDto userSaveRequestDto) {
        this.username = userSaveRequestDto.getUsername();
        this.password = userSaveRequestDto.getPassword();
        this.email = userSaveRequestDto.getEmail();
        this.nickname = userSaveRequestDto.getNickname();
    }

    public void update(String username, String password, String email, String nickname) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
    }
}
