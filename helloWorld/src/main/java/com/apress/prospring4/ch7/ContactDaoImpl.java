package com.apress.prospring4.ch7;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Repository("contactDao")
@Log
public class ContactDaoImpl implements ContactDao {

    @Resource(name = "sessionFactory")
    @Getter
    @Setter
    private SessionFactory sessionFactory;

    @Override
    @Transactional(readOnly = true)
    public List<Contact> findAll() {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from com.apress.prospring4.ch7.Contact c", Contact.class)
                .list();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Contact> findAllWithDetail() {
        //noinspection unchecked
        return sessionFactory.getCurrentSession().
                getNamedQuery("Contact.findAllWithDetail").list();
    }

    @Override
    @Transactional(readOnly = true)
    public Contact findById(Long id) {
        return (Contact) sessionFactory.getCurrentSession()
                .getNamedQuery("Contact.findById")
                .setParameter("id", id).uniqueResult();
    }

    @Override
    public Contact save(Contact contact) {
        sessionFactory.getCurrentSession().saveOrUpdate(contact);
        log.info("Contact saved with id: " + contact.getId());
        return contact;
    }

    @Override
    public void delete(Contact contact) {
        sessionFactory.getCurrentSession().delete(contact);
        log.info("Contact deleted with id: " + contact.getId());
    }
}
