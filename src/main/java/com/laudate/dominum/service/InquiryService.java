package com.laudate.dominum.service;

import com.laudate.dominum.entity.Inquiry;
import com.laudate.dominum.repository.InquiryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InquiryService {

    @Autowired
    private InquiryRepository inquiryRepository;

    public void save(Inquiry inquiry) {
        inquiryRepository.save(inquiry);
    }

    public List<Inquiry> getAllInquiry() {
        return inquiryRepository.findAll();
    }
}
