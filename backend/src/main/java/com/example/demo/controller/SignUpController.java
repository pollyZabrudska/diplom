package com.example.demo.controller;

import com.example.demo.model.UserRole;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class SignUpController {
    private final UserRepository userRepository;
    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    @GetMapping("/sign-up")
    public String addNewUser() {
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String addNewUser(@RequestParam String login,
                             @RequestParam String userName,
                             @RequestParam String userPhone,
                             @RequestParam String email,
                             @RequestParam String password) {
        if (userRepository.findByLogin(login) != null) {
            return "redirect:/sign-up";
        }
        if (userService.addUser(login, userName, userPhone, email
                , passwordEncoder.encode(password), UserRole.USER)) {
            return "redirect:/login";
        }
        return "redirect:/";
    }

}
