package com.easydao.earlybird.service;

import com.easydao.earlybird.bean.Contact;

import java.util.List;

public interface ContactService {

    void addCcontact(Contact contact);

    void updateContact(Contact contact);

    void deleteContact(Long id);

    List<Contact> getContactList(long user_id);
}
