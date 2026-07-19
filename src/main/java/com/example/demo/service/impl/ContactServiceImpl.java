package com.example.demo.service.impl;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Contact;
import com.example.demo.repository.ContactRepository;
import com.example.demo.service.ContactService;
import com.example.demo.service.EmailService;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    // ==========================================
    // Repository
    // ==========================================

    private final ContactRepository contactRepository;
    private final EmailService emailService;

    // ==========================================
    // Constructor Injection
    // ==========================================

    public ContactServiceImpl(ContactRepository contactRepository, EmailService emailService) {

        this.contactRepository = contactRepository;
        this.emailService = emailService;

    }

    // ==========================================
    // Save Contact Message
    // ==========================================

    @Override
    public Contact saveContact(Contact contact) {

        Contact saved = contactRepository.save(contact);
        emailService.sendContactAcknowledgement(saved);
        return saved;

    }

    @Override public List<Contact> getAllContacts() { return contactRepository.findAllByOrderByCreatedAtDesc(); }
    @Override public Contact getContact(Long id) { return contactRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Contact message not found.")); }
    @Override public Contact reply(Long id, String replyMessage) {
        Contact contact = getContact(id);
        contact.setReplyMessage(replyMessage);
        contact.setRepliedAt(LocalDateTime.now());
        contact.setStatus("REPLIED");
        Contact saved = contactRepository.save(contact);
        emailService.sendContactReply(saved);
        return saved;
    }

    @Override
    public void deleteContact(Long id) {
        contactRepository.delete(getContact(id));
    }
}
