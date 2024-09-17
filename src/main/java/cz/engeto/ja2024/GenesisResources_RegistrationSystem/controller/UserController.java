package cz.engeto.ja2024.GenesisResources_RegistrationSystem.controller;

import cz.engeto.ja2024.GenesisResources_RegistrationSystem.model.User;
import cz.engeto.ja2024.GenesisResources_RegistrationSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("api/v1")

public class UserController {

    private final UserService userService;

    public UserController(@Autowired final UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public void createUser(@RequestBody User user) {
        userService.createUser(user);
    }

    @GetMapping("user/{id}")
    public User getUserById(@PathVariable("id") Long userId) throws SQLException {
        return userService.getUserById(userId);
    }
}
