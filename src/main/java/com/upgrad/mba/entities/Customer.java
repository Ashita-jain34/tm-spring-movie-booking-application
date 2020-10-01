package com.upgrad.mba.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "Customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int customerId;

    @Column(name = "first_name", length = 20, nullable = false)
    private String firstName;

    @Column(length = 20)
    private String lastName;

    @Column(length = 20, nullable = false, unique = true)
    private String username;

    @Column(length = 20, nullable = false)
    private String password;

    @Column(nullable = false)
    private LocalDateTime dateOfBirth;

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private Set<Booking> bookings;

    @ElementCollection (fetch = FetchType.EAGER)
    @CollectionTable(name = "customer_contact_number", joinColumns = @JoinColumn(name = "customer_id"))
    @Column(name = "mobile_number", nullable = false)
    private Set<Integer> phoneNumbers;

    // getters, setters and toString

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

    public Set<Integer> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(Set<Integer> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", phoneNumbers=" + phoneNumbers +
                '}';
    }
}
