package gdgStudy.gdgSpring.user;

import gdgStudy.gdgSpring.user.dto.request.UserSaveRequestDto;
import gdgStudy.gdgSpring.user.dto.request.UserUpdateRequestDto;
import gdgStudy.gdgSpring.user.dto.response.UserResponseDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    // 의존성 주입
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // CREATE (생성)
    public UserResponseDto createUser(UserSaveRequestDto userSaveRequestDto) {
        User user = new User(userSaveRequestDto); // 생성자 만듦 (새로운 엔티티)
        User savedUser = userRepository.save(user);
        return new UserResponseDto(savedUser);
    }

    // READ (조회)
    public List<UserResponseDto> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(UserResponseDto::new)
                .collect(Collectors.toList());
    }

    public Optional<UserResponseDto> getUserById(Long id) {

        return userRepository.findById(id)
                .map(UserResponseDto::new);
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // UPDATE (수정) - 옵셔널 처리
    @Transactional
    public UserResponseDto updateUser(Long id, UserUpdateRequestDto updateUserRequestDto) {
        User existingUser = userRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("해당 유저가 없습니다. id = " + id));

        // save로 수정 알아보기
        existingUser.update(
                updateUserRequestDto.getUsername(),
                updateUserRequestDto.getPassword(),
                updateUserRequestDto.getEmail());

        return new UserResponseDto(existingUser);
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

    // 로그인 인증
    public UserResponseDto authenticateUser(UserSaveRequestDto userSaveRequestDto) {
        Optional<User> userOpt = userRepository.findByUsername(userSaveRequestDto.getUsername());

        if (userOpt.isPresent() && userOpt.get().getPassword().equals(userSaveRequestDto.getPassword())) {
            return new UserResponseDto(userOpt.get());
        }

        return null; // 인증 실패
    }

    public User getCurrentUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("현재 사용자를 찾을 수 없습니다. username = " + username));
    }
}
