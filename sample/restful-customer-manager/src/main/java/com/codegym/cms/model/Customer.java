package com.codegym.cms.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "customer")
@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.NONE)
public class Customer {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy= GenerationType.AUTO)
    @XmlElement
    private Long id;

    @Column(name = "FIRST_NAME")
    @XmlElement
    private String firstName;

    @Column(name = "LAST_NAME")
    @XmlElement
    private String lastName;

    public Customer() {}

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format("Customer[id=%d, firstName='%s', lastName='%s']", id, firstName, lastName);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
