package com.example.demo.service;

import com.example.demo.entity.Contact;
import java.util.List;

public interface ContactService {

    // ==========================================
    // Save Contact Message
    // ==========================================

    Contact saveContact(Contact contact);

    List<Contact> getAllContacts();
    Contact getContact(Long id);
    Contact reply(Long id, String replyMessage);
    void deleteContact(Long id);
}
