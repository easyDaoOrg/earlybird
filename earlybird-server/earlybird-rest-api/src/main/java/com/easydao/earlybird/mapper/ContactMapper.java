package com.easydao.earlybird.mapper;

import com.easydao.earlybird.bean.Contact;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ContactMapper {

    void addCcontact(Contact contact);

    void updateContact(Contact contact);

    void deleteContact(Long id);

    List<Contact> getContactList(long user_id);

    List<Contact> getOrderContactList(String[] ids);

}
