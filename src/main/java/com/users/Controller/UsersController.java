package com.users.Controller;

import com.users.Models.User;
import com.users.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsersController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/")
    public String getPage() {
        return "Welcome";
    }

    @GetMapping(value = "/users")
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @PostMapping(value = "/save")
    public String saveUser(@RequestBody User user) {
        userRepository.save(user);
        return "User saved";
    }

    @PutMapping(value = "update/{id}")
    public String updateUser(@PathVariable long id, @RequestBody User user) {
        User updatedUser = userRepository.findById(id).get();
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setAge(user.getAge());
        updatedUser.setEmail(user.getEmail());
        userRepository.save(updatedUser);
        return "User updated";
    }

    @DeleteMapping(value = "/delete{id}")
    public String deleteUser(@PathVariable long id) {
        User deleteUser = userRepository.findById(id).get();
        userRepository.delete(deleteUser);
        return "Deleted user";
    }
}
