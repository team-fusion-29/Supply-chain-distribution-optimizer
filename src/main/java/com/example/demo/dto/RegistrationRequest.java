package com.example.demo.dto;

import com.example.demo.entity.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RegistrationRequest {
    @NotBlank(message = "First name is required.") @Size(max = 50)
    private String firstName;
    @NotBlank(message = "Last name is required.") @Size(max = 50)
    private String lastName;
    @NotBlank(message = "Email is required.") @Email(message = "Enter a valid email address.")
    private String email;
    @NotBlank(message = "Phone number is required.")
    @Pattern(regexp = "^[0-9+() -]{7,15}$", message = "Enter a valid phone number.")
    private String phoneNumber;
    @NotBlank(message = "Password is required.")
    @Size(min = 8, max = 72, message = "Password must be 8 to 72 characters.")
    private String password;
    @NotBlank(message = "Confirm your password.")
    private String confirmPassword;
    @NotNull(message = "Select an account type.")
    private UserRole role;
    public String getFirstName() { return firstName; } public void setFirstName(String v) { firstName = v; }
    public String getLastName() { return lastName; } public void setLastName(String v) { lastName = v; }
    public String getEmail() { return email; } public void setEmail(String v) { email = v; }
    public String getPhoneNumber() { return phoneNumber; } public void setPhoneNumber(String v) { phoneNumber = v; }
    public String getPassword() { return password; } public void setPassword(String v) { password = v; }
    public String getConfirmPassword() { return confirmPassword; } public void setConfirmPassword(String v) { confirmPassword = v; }
    public UserRole getRole() { return role; } public void setRole(UserRole v) { role = v; }
}
