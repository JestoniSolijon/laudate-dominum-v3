package com.laudate.entity;

import com.laudate.validation.PhoneNumberValidation;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
@Table(name = "inquiry")
public class Inquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull(message = "Firstname is required.")
    @Column(name = "first_name")
    private String firstName = "";

    @NotNull(message = "Lastname is required.")
    @Column(name = "last_name")
    private String lastName = "";

    @PhoneNumberValidation(value = "09", message = "Phone no. must start with '09' and have 11 digits.")
    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "subject")
    private String subject;

    @NotNull(message = "Message must not be empty.")
    @Column(name = "message")
    private String message = "";

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "inquiry_date")
    private Date inquiryDate;

    public Inquiry() {}

    public Inquiry(String firstName, String lastName, String contactNumber, String subject, String message) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactNumber = contactNumber;
        this.subject = subject;
        this.message = message;
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

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getInquiryDate() {
        return inquiryDate;
    }

    public void setInquiryDate(Date inquiryDate) {
        this.inquiryDate = inquiryDate;
    }

    @Override
    public String toString() {
        return "Inquiry{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", subject='" + subject + '\'' +
                ", message='" + message + '\'' +
                ", inquiryDate=" + inquiryDate +
                '}';
    }
}
