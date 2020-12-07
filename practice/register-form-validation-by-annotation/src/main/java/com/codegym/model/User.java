package com.codegym.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull(message = "First name should not be empty.")
    @Length(min = 5, max = 45, message = "First name should be between 5 and 45 characters.")
    @Column(name = "FIRST_NAME")
    private String firstName;

    @NotNull(message = "Last name should not be empty.")
    @Length(min = 5, max = 45, message = "First name should be between 5 and 45 characters.")
    @Column(name = "LAST_NAME")
    private String lastName;

    @Pattern(regexp = "(^$|^0[0-9]{9,10}$)", message = "Not a valid phone number.")
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Min(18)
    @Column(name = "AGE")
    private Integer age;

    @Email
    @Column(name = "EMAIL")
    private String email;

    public User() {
    }

    public User(int id, String firstName, String lastName, String phoneNumber, int age,
        String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", phoneNumber='" + phoneNumber + '\'' +
            ", age=" + age +
            ", email='" + email + '\'' +
            '}';
    }
}
