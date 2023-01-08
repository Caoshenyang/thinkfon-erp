package com.yang.erp.common.validation;

import com.yang.erp.common.annotation.PasswordMatch;
import com.yang.erp.vo.UserVo;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Description: 密码校验
 * @author: 曹申阳
 * @date: 2022.01.25
 */
public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, UserVo> {

    @Override
    public void initialize(PasswordMatch constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserVo userDto, ConstraintValidatorContext constraintValidatorContext) {
        return userDto.getPassword().equals(userDto.getMatchingPassword());
    }
}
