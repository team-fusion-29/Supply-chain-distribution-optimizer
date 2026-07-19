package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Contact;
import com.example.demo.service.ContactService;
import com.example.demo.service.CustomerService;
import com.example.demo.service.ShipmentService;
import com.example.demo.entity.User;
import com.example.demo.entity.UserRole;
import jakarta.servlet.http.HttpSession;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/contact")
public class ContactController {

    // ==========================================
    // Services
    // ==========================================

    private final ContactService contactService;
    private final ShipmentService shipmentService;
    private final CustomerService customerService;

    // ==========================================
    // Constructor Injection
    // ==========================================

    public ContactController(ContactService contactService,
                             ShipmentService shipmentService,
                             CustomerService customerService) {

        this.contactService = contactService;
        this.shipmentService = shipmentService;
        this.customerService = customerService;

    }

    // ==========================================
    // Save Contact Message
    // ==========================================

    @PostMapping("/save")
    public String saveContactMessage(@Valid @ModelAttribute("contact") Contact contact,
                                     BindingResult result,
                                     Model model,
                                     RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {

            // Reload statistics required for index page rendering
            long totalShipments = shipmentService.getTotalShipments();
            long totalCustomers = customerService.getTotalCustomers();

            model.addAttribute("totalShipments", totalShipments);
            model.addAttribute("totalCustomers", totalCustomers);

            // Return to index template showing validation errors
            return "index";

        }

        contactService.saveContact(contact);

        redirectAttributes.addFlashAttribute("successMessage",
                "Your message has been sent successfully. Our team will contact you soon.");

        return "redirect:/";

    }

    @GetMapping("/admin/messages")
    public String adminMessages(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null || user.getRole() != UserRole.ADMIN) return "redirect:/login";
        model.addAttribute("messages", contactService.getAllContacts());
        return "contact/admin-list";
    }

    @GetMapping("/admin/messages/{id}")
    public String viewMessage(@PathVariable Long id, HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null || user.getRole() != UserRole.ADMIN) return "redirect:/login";
        model.addAttribute("contact", contactService.getContact(id));
        return "contact/admin-view";
    }

    @PostMapping("/admin/messages/{id}/reply")
    public String reply(@PathVariable Long id, @RequestParam String replyMessage, HttpSession session,
                        RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null || user.getRole() != UserRole.ADMIN) return "redirect:/login";
        if (replyMessage == null || replyMessage.isBlank()) {
            redirectAttributes.addFlashAttribute("error", "A reply message is required.");
            return "redirect:/contact/admin/messages/" + id;
        }
        contactService.reply(id, replyMessage.trim());
        redirectAttributes.addFlashAttribute("success", "Reply sent and message marked as replied.");
        return "redirect:/contact/admin/messages/" + id;
    }

    @PostMapping("/admin/messages/{id}/delete")
    public String deleteMessage(@PathVariable Long id, HttpSession session,
                                RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null || user.getRole() != UserRole.ADMIN) return "redirect:/login";
        try {
            contactService.deleteContact(id);
            redirectAttributes.addFlashAttribute("success", "Contact message deleted successfully.");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", "Contact message was not found.");
        }
        return "redirect:/contact/admin/messages";
    }
}
