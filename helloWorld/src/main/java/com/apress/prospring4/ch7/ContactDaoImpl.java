package com.apress.prospring4.ch7;

import lombok.extern.java.Log;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;
import org.hibernate.SessionFactory;
import javax.annotation.Resource;
import java.util.List;

@Transactional
@Repository("contactDao")
@Log
public class ContactDaoImpl implements ContactDao {

    private SessionFactory sessionFactory;

    @Transactional(readOnly=true)
    public List<Contact> findAll() {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from com.apress.prospring4.ch7.Contact c", Contact.class)
                .list();
    }

    @Transactional(readOnly=true)
    public List<Contact> findAllWithDetail() {
        return sessionFactory.getCurrentSession().
            getNamedQuery("Contact.findAllWithDetail").list();
    }

    @Transactional(readOnly=true)
    public Contact findById(Long id) {
        return (Contact) sessionFactory.getCurrentSession().
            getNamedQuery("Contact.findById").
            setParameter("id", id).uniqueResult();
    }

    public Contact save(Contact contact) {
        sessionFactory.getCurrentSession().saveOrUpdate(contact);
        log.info("Contact saved with id: " + contact.getId());
        return contact;
    }

    public void delete(Contact contact) {
        sessionFactory.getCurrentSession().delete(contact);
        log.info("Contact deleted with id: " + contact.getId());
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Resource(name="sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
