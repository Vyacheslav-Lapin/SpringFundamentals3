package com.apress.prospring4.ch7;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context.xml")
public class ContactTest {

    @Resource
    private ContactDao contactDao;

    @Test
    public void mainTest() throws Exception {

        // http://stackoverflow.com/questions/21282432/hibernateexception-access-to-dialectresolutioninfo-cannot-be-null-when-hiberna
        Configuration configuration = new Configuration();
        configuration.configure("com/jeecourse/config/hibernate.cfg.xml");

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        Contact contact = contactDao.findById(1L);
        contactDao.delete(contact);
        listContactsWithDetail(contactDao.findAllWithDetail());
    }

    private static void listContactsWithDetail(List<Contact> contacts) {
        System.out.println();
        System.out.println("Listing contacts with details:");

        for (Contact contact: contacts) {
            System.out.println(contact);

            if (contact.getContactTelDetails() != null) {
                for (ContactTelDetail contactTelDetail:
                        contact.getContactTelDetails()) {
                    System.out.println(contactTelDetail);
                }
            }

            if (contact.getHobbies() != null) {
                for (Hobby hobby: contact.getHobbies()) {
                    System.out.println(hobby);
                }
            }

            System.out.println();
        }
    }
}