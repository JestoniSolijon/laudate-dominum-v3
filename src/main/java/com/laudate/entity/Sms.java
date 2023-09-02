package com.laudate.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "sms")
public class Sms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "api_key")
    private String apiKey;

    @Column(name = "api_url")
    private String apiUrl;

    @Column(name = "sender_name")
    private String senderName;

    @Column(name = "receiver")
    private String receiver;

    @Column(name = "message")
    private String message;

    public Sms() {}

    public Sms(String apiKey, String apiUrl, String senderName, String receiver, String message) {
        this.apiKey = apiKey;
        this.apiUrl = apiUrl;
        this.senderName = senderName;
        this.receiver = receiver;
        this.message = message;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Sms{" +
                "id=" + id +
                ", apiKey='" + apiKey + '\'' +
                ", apiUrl='" + apiUrl + '\'' +
                ", senderName='" + senderName + '\'' +
                ", receiver='" + receiver + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
