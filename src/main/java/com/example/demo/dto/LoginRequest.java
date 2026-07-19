package com.example.demo.dto;

import com.example.demo.entity.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LoginRequest {
    @NotBlank(message = "Email is required.")
    @Email(message = "Enter a valid email address.")
    private String email;

    @NotBlank(message = "Password is required.")
    private String password;

    @NotNull(message = "Select an account type.")
    private UserRole selectedRole;

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public UserRole getSelectedRole() { return selectedRole; }
    public void setSelectedRole(UserRole selectedRole) { this.selectedRole = selectedRole; }
}
