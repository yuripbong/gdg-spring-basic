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
    public UserSaveResponseDto createUser(UserSaveRequestDto userDto) {
        User user = User.builder()
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .email(userDto.getEmail())
                .build();

        User savedUser = userRepository.save(user);
        return new UserSaveResponseDto(savedUser.getUsername(), savedUser.getEmail());
    }

    // READ (조회)
    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        List<UserSaveResponseDto> userSaveResponseDtos = new ArrayList<>();
        for (User user : users) {
            userSaveResponseDtos.add(new UserSaveResponseDto(user.getUsername(), user.getEmail()));
        }

        return userSaveResponseDtos;

    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // UPDATE (수정)
    public User updateUser(Long id, User updateUser) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setUsername(updateUser.getUsername());
            existingUser.setPassward(updateUser.getPassword());

            return userRepository.save(existingUser);
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
