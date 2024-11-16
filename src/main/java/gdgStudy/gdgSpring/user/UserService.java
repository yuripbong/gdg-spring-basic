package gdgStudy.gdgSpring.user;

import gdgStudy.gdgSpring.user.dto.request.UserSaveRequestDto;
import gdgStudy.gdgSpring.user.dto.request.UserUpdateRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    // 의존성 주입
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // CREATE (생성)
    public User createUser(UserSaveRequestDto userSaveRequestDto) {
        User user = new User(userSaveRequestDto); // 생성자 만듦 (새로운 엔티티)
        return userRepository.save(user);
    }

    // READ (조회)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public Optional<List<User>> getUsersByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // UPDATE (수정) - 옵셔널 처리
    @Transactional
    public User updateUser(Long id, UserUpdateRequestDto updateUserRequestDto) {
        User existingUser = userRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("해당 유저가 없습니다. id = " + id));

        // save로 수정 알아보기
        existingUser.update(
                updateUserRequestDto.getUsername(),
                updateUserRequestDto.getPassword(),
                updateUserRequestDto.getEmail(),
                updateUserRequestDto.getNickname());
        return existingUser;
    }

    // DELETE (삭제)
    public boolean deleteUser(Long id) {
        User existingUser = userRepository.findById(id).orElse(null);

        if (existingUser != null) {
            userRepository.delete(existingUser);
            return true;
        }

        return false;
    }
}
