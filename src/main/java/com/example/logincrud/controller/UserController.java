package com.example.logincrud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.logincrud.model.User;
import com.example.logincrud.repository.UserRepository;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // =========================
    // Show Registration Page
    // =========================
    @GetMapping("/")
    public String showRegister(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    // =========================
    // Register User
    // =========================
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {

        user.setRole("USER"); // Default role
        userRepository.save(user);

        return "redirect:/login";
    }

    // =========================
    // Show Login Page
    // =========================
    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    // =========================
    // Login Logic (Role Based)
    // =========================
    @PostMapping("/login")
    public String loginUser(@RequestParam String email,
                            @RequestParam String password,
                            Model model) {

        User user = userRepository.findByEmailAndPassword(email, password);

        if (user != null) {

            if (user.getRole().equalsIgnoreCase("ADMIN")) {
                model.addAttribute("users", userRepository.findAll());
                return "admin-dashboard";
            } else {
                model.addAttribute("username", user.getName());
                return "user-dashboard";
            }

        } else {
            model.addAttribute("error", "Invalid Email or Password!");
            return "login";
        }
    }

    // =========================
    // Show Admin Dashboard
    // =========================
    @GetMapping("/admin")
    public String showAdminDashboard(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "admin-dashboard";
    }

    // =========================
    // Delete User
    // =========================
    @GetMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable Long id) {

        userRepository.deleteById(id);

        return "redirect:/admin";   // FIXED redirect
    }

    // =========================
    // Show Update Form
    // =========================
    @GetMapping("/admin/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));

        model.addAttribute("user", user);
        return "edit-user";
    }

    // =========================
    // Update User
    // =========================
    @PostMapping("/admin/update")
    public String updateUser(@ModelAttribute("user") User user) {

        userRepository.save(user);

        return "redirect:/admin";   // FIXED redirect
    }

    // =========================
    // Show User Dashboard
    // =========================
    @GetMapping("/user")
    public String showUserDashboard(@RequestParam String name, Model model) {

        model.addAttribute("username", name);
        return "user-dashboard";
    }
}