package com.example.demo.users;


import com.example.demo.users.Dtos.UserDto;
import com.example.demo.users.Dtos.UserResponseDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class Controller {

    private UserService userService;

    public Controller(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public UserResponseDto create(@RequestBody UserDto userDto) {
        return this.userService.createUser(userDto);
    }

    @GetMapping()
    public List<UserResponseDto> getAllUsers() {
        return this.userService.getUsers();
    }

    @GetMapping("/{id}")
    public UserResponseDto getUserById(@PathVariable Long id) {
        return this.userService.getUserById(id);
    }

    @PutMapping("/update/{id}")
    public UserResponseDto updateUser(@PathVariable Long id, @RequestBody UserDto userdto) {
        return this.userService.updateUser(id, userdto);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        this.userService.deleteUser(id);
        return "User deleted successfully";
    }
}
