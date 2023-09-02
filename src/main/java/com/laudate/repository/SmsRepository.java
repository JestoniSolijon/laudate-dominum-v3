package com.laudate.repository;

import com.laudate.entity.Sms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SmsRepository extends JpaRepository<Sms, String> {
}
