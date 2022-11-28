package com.example.springblog.controllers;

import com.example.springblog.models.User;
import com.example.springblog.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private UserRepository usersDao;
    private PasswordEncoder passwordEncoder;

    public UserController(UserRepository usersDao, PasswordEncoder passwordEncoder)  {
        this.usersDao = usersDao;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/sign-up")
    public String showSignUp(Model model) {
        model.addAttribute("user", new User());
        return "users/sign-up";
    }

    @PostMapping("/sign-up")
    public String createUser(@ModelAttribute User user) {
        // Hash user's password and storing it in "hash" variable.
        String hash = passwordEncoder.encode(user.getPassword());
        // Setting user's password to hashed version.
        user.setPassword(hash);
        // Saving user to database.
        usersDao.save(user);
        return "redirect:/login";
    }


}
