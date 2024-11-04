package gdgStudy.gdgSpring.user;

import gdgStudy.gdgSpring.user.dto.request.UserSaveRequestDto;
import gdgStudy.gdgSpring.user.dto.response.UserSaveResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<User> getUserById(Long id) {
        return (List<User>) userRepository.findById(id).orElse(null);
    }

    public List<User> getUsersByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // UPDATE (수정)
    public User updateUser(Long id, UserSaveRequestDto updateUserRequestDto) {
        User existingUser = userRepository.findById(id).orElse(null);

        if (existingUser != null) {
            User updateUser = User.builder()
                    .username(updateUserRequestDto.getUsername())
                    .password(updateUserRequestDto.getPassword())
                    .email(updateUserRequestDto.getPassword())
                    .build();

            return userRepository.save(updateUser);
        }

        return null;
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
