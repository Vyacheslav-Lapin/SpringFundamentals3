package com.apress.prospring4.ch7;

import java.util.List;

public interface ContactDao {

    List<Contact> findAll();

    default List<Contact> findAllWithDetail() {
        return findAll();
    }

    default Contact findById(Long id) {
        return findAll().stream()
                .filter(contact -> contact.getId().equals(id))
                .findAny()
                .orElseThrow(() ->
                        new RuntimeException("Ну не шмогла я, не шмогла...  :(("));
    }

    Contact save(Contact contact);
    void delete(Contact contact);
}