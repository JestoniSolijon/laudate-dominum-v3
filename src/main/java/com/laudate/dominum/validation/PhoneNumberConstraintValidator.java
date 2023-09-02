package com.laudate.dominum.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class PhoneNumberConstraintValidator implements ConstraintValidator<PhoneNumberValidation, String> {

    private String phonePrefix;

    @Override
    public void initialize(PhoneNumberValidation phoneNumberValidation) {
        phonePrefix = phoneNumberValidation.value();
    }

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext constraintValidatorContext) {

        if (phoneNumber == null) {
            return false;
        }

        Pattern phoneNumberPattern = Pattern.compile("\\d{11}");
        return phoneNumberPattern.matcher(phoneNumber).matches();
    }
}
