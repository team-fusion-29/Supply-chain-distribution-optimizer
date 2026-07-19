package com.example.demo.controller;

import com.example.demo.entity.Notification;
import com.example.demo.service.NotificationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    public String listNotifications(Model model) {

        model.addAttribute("notifications",
                notificationService.getAllNotifications());

        model.addAttribute("totalNotifications",
                notificationService.getAllNotifications().size());

        model.addAttribute("unreadNotifications",
                notificationService.getUnreadNotifications().size());

        model.addAttribute("latestNotifications",
                notificationService.getLatestNotifications());

        return "notification/list";
    }

    @GetMapping("/add")
    public String addNotificationForm(Model model) {

        model.addAttribute("notification", new Notification());

        return "notification/add";
    }

    @PostMapping("/save")
    public String saveNotification(@ModelAttribute Notification notification) {

        notificationService.saveNotification(notification);

        return "redirect:/notifications";
    }
    @GetMapping("/view/{id}")
    public String viewNotification(@PathVariable Long id, Model model) {

        model.addAttribute("notification",
                notificationService.getNotificationById(id));

        return "notification/view";
    }

    @GetMapping("/edit/{id}")
    public String editNotification(@PathVariable Long id, Model model) {

        model.addAttribute("notification",
                notificationService.getNotificationById(id));

        return "notification/edit";
    }

    @PostMapping("/update")
    public String updateNotification(@ModelAttribute Notification notification) {

        notificationService.updateNotification(notification);

        return "redirect:/notifications";
    }
    @GetMapping("/delete/{id}")
    public String deleteNotification(@PathVariable Long id) {

        notificationService.deleteNotification(id);

        return "redirect:/notifications";
    }

    @GetMapping("/read/{id}")
    public String markAsRead(@PathVariable Long id) {

        notificationService.markAsRead(id);

        return "redirect:/notifications";
    }

    @GetMapping("/status/{status}")
    public String getNotificationsByStatus(@PathVariable String status,
                                           Model model) {

        model.addAttribute("notifications",
                notificationService.getNotificationsByStatus(status));

        return "notification/list";
    }

    @GetMapping("/priority/{priority}")
    public String getNotificationsByPriority(@PathVariable String priority,
                                             Model model) {

        model.addAttribute("notifications",
                notificationService.getNotificationsByPriority(priority));

        return "notification/list";
    }

    @GetMapping("/latest")
    public String latestNotifications(Model model) {

        model.addAttribute("notifications",
                notificationService.getLatestNotifications());

        return "notification/list";
    }

}