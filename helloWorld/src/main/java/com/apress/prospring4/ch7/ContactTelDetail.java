package com.apress.prospring4.ch7;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "contact_tel_detail")
@NoArgsConstructor
@ToString
@Getter
@Setter
public class ContactTelDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Version
    @Column(name = "VERSION")
    private int version;

    @Column(name = "TEL_TYPE")
    private String telType;

    @Column(name = "TEL_NUMBER")
    private String telNumber;

    @ManyToOne
    @JoinColumn(name = "CONTACT_ID")
    private Contact contact;

    public ContactTelDetail(String telType, String telNumber) {
        this.telType = telType;
        this.telNumber = telNumber;
    }
}