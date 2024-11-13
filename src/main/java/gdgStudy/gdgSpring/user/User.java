package gdgStudy.gdgSpring.user;

import gdgStudy.gdgSpring.user.dto.request.UserSaveRequestDto;
import jakarta.persistence.*;
import lombok.*;

@Getter // 모든 필드의 getter 메소드 자동 생성
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 파라미터가 없는 기본 생성자 생성
@Entity(name = "users")
public class User {

    @Id // PK 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 1씩 자동 증가
    private Long id;

    @Column(nullable = false, unique = true) // 엔티티 클래스의 필드와 DB 테이블의 컬럼 간 매핑 지정
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String nickname;

    @Builder
    public User(String username, String password, String email, String nickname) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
    }

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
