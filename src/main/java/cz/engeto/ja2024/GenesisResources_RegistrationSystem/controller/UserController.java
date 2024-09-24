package cz.engeto.ja2024.GenesisResources_RegistrationSystem.controller;

import cz.engeto.ja2024.GenesisResources_RegistrationSystem.model.User;
import cz.engeto.ja2024.GenesisResources_RegistrationSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

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

    @GetMapping("/user/{id}")
    public User getUserById(
            @PathVariable("id") long userId,
            @RequestParam(value = "detail", defaultValue = "false") boolean detail) throws SQLException {

        if (detail) {
            return userService.getUserByIdWithDetails(userId);
        } else {
            return userService.getUserById(userId);
        }
    }

    @GetMapping("/users")
    public List<User> getAllUsers(
            @RequestParam(value = "detail", defaultValue = "false") boolean detail) throws SQLException {

        if (detail) {
            return userService.getAllUsersWithDetails();
        } else {
            return userService.getAllUsers();
        }
    }

    @PutMapping("/user")
    public void updateUser(
            @RequestBody User user) {
        userService.updateUser(user);
    }

    @DeleteMapping("user/{id}")
    public void deleteUser(
            @PathVariable("id") long userId
    ) {
        userService.deleteUser(userId);
    }


}

