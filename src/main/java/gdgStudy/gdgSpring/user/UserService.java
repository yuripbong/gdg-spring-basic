package gdgStudy.gdgSpring.user;

import gdgStudy.gdgSpring.user.dto.request.UserSaveRequestDto;
import gdgStudy.gdgSpring.user.dto.request.UserUpdateRequestDto;
import gdgStudy.gdgSpring.user.dto.response.UserSaveResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    // CREATE (생성)
    public User createUser(UserSaveRequestDto userSaveRequestDto) {
        User user = new User(userSaveRequestDto);
        return userRepository.save(user);
    }

    // READ (조회)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getUsersByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // UPDATE (수정)
    @Transactional
    public User updateUser(Long id, UserUpdateRequestDto updateUserRequestDto) {
        User existingUser = userRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("해당 유저가 없습니다. id = " + id));

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
