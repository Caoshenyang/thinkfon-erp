package com.yang.erp.common.validation;

import com.yang.erp.common.annotation.PasswordMatch;
import com.yang.erp.domain.dto.UserDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Description: 密码校验
 * @author: 曹申阳
 * @date: 2022.01.25
 */
public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, UserDto> {

    @Override
    public void initialize(PasswordMatch constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserDto userDto, ConstraintValidatorContext constraintValidatorContext) {
        return userDto.getPassword().equals(userDto.getMatchingPassword());
    }
}
