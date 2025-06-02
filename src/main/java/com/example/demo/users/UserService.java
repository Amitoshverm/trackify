package com.example.demo.users;

import com.example.demo.users.Dtos.UserResponseDto;
import org.springframework.stereotype.Service;

import com.example.demo.users.Dtos.UserDto;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDto createUser(UserDto userDto) {
        User savedUser = new User();
        savedUser.setEmail(userDto.getEmail());
        savedUser.setName(userDto.getName());
        savedUser.setPassword(userDto.getPassword());

        this.userRepository.save(savedUser);

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(savedUser.getId());
        userResponseDto.setName(savedUser.getName());
        userResponseDto.setEmail(savedUser.getEmail());
        return userResponseDto;
    }

   public List<UserResponseDto> getUsers() {
        List<User> users = this.userRepository.findAll();
        List<UserResponseDto> userResponses = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            UserResponseDto userResponse = new UserResponseDto();
            userResponse.setId(user.getId());
            userResponse.setEmail(user.getEmail());
            userResponse.setName(user.getName());
            userResponses.add(userResponse);
        }
        return userResponses;
   }

   public UserResponseDto getUserById(Long id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        UserResponseDto userResponseDto = new UserResponseDto();
       userResponseDto.setId(user.getId());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setName(user.getName());
        return userResponseDto;
   }

   public UserResponseDto updateUser(Long id, UserDto userDto) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id : " + id));
        user.setPassword(userDto.getPassword());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());

        this.userRepository.save(user);

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setName(user.getName());
        userResponseDto.setEmail(user.getEmail());
        return userResponseDto;
   }

   public void deleteUser(Long id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id : " + id));
        this.userRepository.delete(user);
   }
}

