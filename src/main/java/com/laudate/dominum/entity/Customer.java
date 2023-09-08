package com.laudate.dominum.entity;

import com.laudate.dominum.validation.PhoneNumberValidation;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private int customerId;

    @NotNull(message = "Firstname is required.")
    @Column(name = "first_name")
    private String firstName = "";

    @NotNull(message = "Lastname is required.")
    @Column(name = "last_name")
    private String lastName = "";

    @Column(name = "street")
    private String street = "";

    @NotNull(message = "Barangay is required.")
    @Column(name = "barangay")
    private String barangay = "";

    @NotNull(message = "City is required.")
    @Column(name = "city")
    private String city = "";

    @NotNull(message = "Region is required.")
    @Column(name = "region")
    private String region = "";

    @Column(name = "zipcode")
    private String zipcode;

    @Column(name = "email_address")
    private String emailAddress;

    @PhoneNumberValidation(value = "09", message = "Phone no. must start with '09' and have 11 digits.")
    @Column(name = "phone_number")
    private String phoneNumber;


    @ManyToMany(mappedBy = "customer",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL})
    private List<Orders> orders;
/*    @ManyToMany(mappedBy = "customer",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<Orders> orders;*/

    @Column(name = "province")
    private String province;

    public Customer() {}

    public Customer(String firstName, String lastName, String street, String barangay, String city, String region, String zipcode, String emailAddress, String phoneNumber, String province) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.barangay = barangay;
        this.city = city;
        this.region = region;
        this.zipcode = zipcode;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.province = province;
    }


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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBarangay() {
        return barangay;
    }

    public void setBarangay(String barangay) {
        this.barangay = barangay;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", street='" + street + '\'' +
                ", barangay='" + barangay + '\'' +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", zipcode=" + zipcode +
                ", emailAddress='" + emailAddress + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", orders=" + orders +
                ", province='" + province + '\'' +
                '}';
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    public void add(Orders order) {

        if (orders == null) {
            orders = new ArrayList<>();
        }

        orders.add(order);
    }
}
