package com.example.demo.users;


import com.example.demo.users.Dtos.UserDto;
import com.example.demo.users.Dtos.UserResponseDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class Controller {

    private UserService userService;

    public Controller(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserResponseDto create(@RequestBody UserDto userDto) {
        return this.userService.createUser(userDto);
    }

}
