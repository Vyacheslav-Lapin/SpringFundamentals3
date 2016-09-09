package com.apress.prospring4.ch7;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "hobby")
@Data
public class Hobby implements Serializable {

    @Id
    @Column(name = "HOBBY_ID")
    private String hobbyId;

    @ManyToMany
    @JoinTable(name = "contact_hobby_detail",
            joinColumns = @JoinColumn(name = "HOBBY_ID"),
            inverseJoinColumns = @JoinColumn(name = "CONTACT_ID"))
    private Set<Contact> contacts = new HashSet<>();
}
