package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegistrationRequest;
import com.example.demo.entity.Customer;
import com.example.demo.entity.User;
import com.example.demo.entity.UserRole;
import com.example.demo.service.CustomerService;
import com.example.demo.service.UserService;
import com.example.demo.service.EmailService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Locale;
import java.util.Optional;

@Controller
public class AuthController {
    private final UserService userService;
    private final CustomerService customerService;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    public AuthController(UserService userService, CustomerService customerService, PasswordEncoder passwordEncoder, EmailService emailService) {
        this.userService = userService;
        this.customerService = customerService;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        if (!model.containsAttribute("loginRequest")) model.addAttribute("loginRequest", new LoginRequest());
        return "auth/login";
    }

    @PostMapping("/login")
    public String loginUser(@Valid @ModelAttribute("loginRequest") LoginRequest request,
                            BindingResult result, Model model, HttpServletRequest servletRequest) {
        if (result.hasErrors()) return "auth/login";

        Optional<User> userResult = userService.getUserByEmail(normalizeEmail(request.getEmail()));
        if (userResult.isEmpty() || !userResult.get().isActive()
                || userResult.get().getRole() != request.getSelectedRole()) {
            return loginError(model, "Invalid email, password, or account type.");
        }

        User user = userResult.get();
        if (!passwordMatches(request.getPassword(), user)) {
            return loginError(model, "Invalid email, password, or account type.");
        }

        // Upgrade legacy plaintext rows after a successful password verification.
        if (!user.getPassword().startsWith("$2")) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            userService.saveUser(user);
        }

        HttpSession oldSession = servletRequest.getSession(false);
        if (oldSession != null) oldSession.invalidate();
        servletRequest.getSession(true).setAttribute("loggedInUser", user);
        return "redirect:/dashboard";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        if (!model.containsAttribute("registrationRequest")) model.addAttribute("registrationRequest", new RegistrationRequest());
        return "auth/register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("registrationRequest") RegistrationRequest request,
                               BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            result.rejectValue("confirmPassword", "password.mismatch", "Passwords do not match.");
        }
        if (userService.emailExists(normalizeEmail(request.getEmail()))) {
            result.rejectValue("email", "email.duplicate", "An account already exists for this email.");
        }
        if (request.getRole() != UserRole.CUSTOMER) {
            result.rejectValue("role", "role.notSelfService", "Only customer accounts can be created through self-registration.");
        }
        if (result.hasErrors()) return "auth/register";

        User user = new User();
        user.setFirstName(request.getFirstName().trim());
        user.setLastName(request.getLastName().trim());
        user.setEmail(normalizeEmail(request.getEmail()));
        user.setPhoneNumber(request.getPhoneNumber().trim());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        user.setActive(true);
        userService.saveUser(user);
        emailService.sendRegistrationEmail(user);

        // A customer account must have a real customer record so its dashboard can
        // query actual shipments and notifications instead of fabricated data.
        if (user.getRole() == UserRole.CUSTOMER && customerService.getCustomerByEmail(user.getEmail()) == null) {
            Customer customer = new Customer();
            customer.setCustomerId("CUST-" + user.getId());
            customer.setFullName(user.getFirstName() + " " + user.getLastName());
            customer.setEmail(user.getEmail());
            customer.setPhone(user.getPhoneNumber());
            customer.setPassword(user.getPassword());
            customer.setCustomerType("INDIVIDUAL");
            customer.setStatus("ACTIVE");
            customerService.saveCustomer(customer);
        }

        redirectAttributes.addFlashAttribute("success", "Registration completed. Please sign in.");
        return "redirect:/login";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        HttpSession session = request.getSession(false);
        if (session != null) session.invalidate();
        redirectAttributes.addFlashAttribute("success", "You have been signed out.");
        return "redirect:/login";
    }

    private boolean passwordMatches(String rawPassword, User user) {
        return user.getPassword().startsWith("$2")
                ? passwordEncoder.matches(rawPassword, user.getPassword())
                : rawPassword.equals(user.getPassword());
    }

    private String loginError(Model model, String message) {
        model.addAttribute("error", message);
        return "auth/login";
    }

    private String normalizeEmail(String email) {
        return email.trim().toLowerCase(Locale.ROOT);
    }
}
