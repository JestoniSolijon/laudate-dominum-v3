package com.laudate.dominum.aspect;

import com.laudate.dominum.entity.Customer;
import com.laudate.dominum.service.SmsService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SMSAspect {

    @Autowired
    private SmsService smsService;

    public SMSAspect(SmsService smsService) {
        this.smsService = smsService;
    }
    @After("execution(* com.laudate.dominum.controller.PaymentController.savePayment(..))")
    public void sendNotification(JoinPoint joinpoint) {

        //smsService.sendSms();
    }
}
