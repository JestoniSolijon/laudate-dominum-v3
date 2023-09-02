package com.laudate.dominum.repository;

import com.laudate.dominum.entity.Sms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SmsRepository extends JpaRepository<Sms, String> {
}
