package com.easydao.earlybird.service.impl;

import com.easydao.earlybird.bean.Contact;
import com.easydao.earlybird.mapper.ContactMapper;
import com.easydao.earlybird.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactMapper contactMapper;


    @Override
    public void addCcontact(Contact contact) {
        contact.setCreate_date(new Date());
        contactMapper.addCcontact(contact);
    }

    @Override
    public void updateContact(Contact contact) {
        contact.setUpdate_date(new Date());
        contactMapper.updateContact(contact);
    }

    @Override
    public void deleteContact(Long id) {
        contactMapper.deleteContact(id);
    }

    @Override
    public List<Contact> getContactList(long user_id) {
        return contactMapper.getContactList(user_id);
    }
}
