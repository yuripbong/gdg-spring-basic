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

    public User(UserSaveRequestDto userSaveRequestDto) {
        this.username = userSaveRequestDto.getUsername();
        this.password = userSaveRequestDto.getPassword();
        this.email = userSaveRequestDto.getEmail();
    }

    public void update(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
