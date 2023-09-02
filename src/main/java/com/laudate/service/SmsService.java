package com.laudate.service;

import com.laudate.entity.Sms;
import com.laudate.repository.SmsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class SmsService {

    @Autowired
    private SmsRepository smsRepository;

    public void sendSms() {
        List<Sms> smsList = findAll();

        try {
            for (Sms sms : smsList) {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

                MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
                map.add("apikey", sms.getApiKey());
                map.add("number", sms.getReceiver());
                map.add("message", sms.getMessage());
                map.add("sendername", sms.getSenderName());

                HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(map, headers);
                RestTemplate restTemplate = new RestTemplate();
                ResponseEntity<String> responseEntity = restTemplate.exchange(
                        sms.getApiUrl(),
                        HttpMethod.POST,
                        requestEntity,
                        String.class
                );
            }
        } catch (HttpStatusCodeException e) {
            System.err.println("Failed to send SMS" + e);
        }

    }

    public List<Sms> findAll() {
        return smsRepository.findAll();
    }




}
