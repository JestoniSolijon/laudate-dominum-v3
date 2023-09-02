package com.laudate.entity;

import com.laudate.validation.PhoneNumberValidation;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @NotNull(message = "Username is required.")
    @Column(name = "username")
    private String userName = "";

    @NotNull(message = "Password is required.")
    @Column(name = "password")
    private String password = "";

    @NotNull(message = "First Name is required.")
    @Column(name = "first_name")
    private String firstName = "";

    @NotNull(message = "Last Name is required.")
    @Column(name = "last_name")
    private String lastName = "";

    @NotNull(message = "Phone Number is required.")
    @PhoneNumberValidation(value = "09", message = "Phone no. must start with '09' and have 11 digits.")
    @Column(name = "phone_number")
    private String phoneNumber = "";

    @Column(name = "email_address")
    private String emailAddress = "";

    @Column(name = "active")
    private byte active;

    public Users() {}

    public Users(@NotNull(message = "Username is required.") String userName, @NotNull(message = "Password is required.") String password, @NotNull(message = "First Name is required.") String firstName, @NotNull(message = "Last Name is required.") String lastName, @NotNull(message = "Phone Number is required.") String phoneNumber, String emailAddress, byte active) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.active = active;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public byte getActive() {
        return active;
    }

    public void setActive(byte active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", active=" + active +
                '}';
    }
}
