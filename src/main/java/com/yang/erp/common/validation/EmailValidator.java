package com.yang.erp.common.validation;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.yang.erp.common.annotation.ValidEmail;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: 邮箱校验
 * @author: 曹申阳
 * @date: 2022.01.24
 */
public class EmailValidator implements ConstraintValidator<ValidEmail, String> {
    private final static String EMAIL_PATTERN = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$";

    @Override
    public void initialize(ValidEmail constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        if (StringUtils.isBlank(email)) {
            return true;
        }
        return validateEmail(email);
    }

    private boolean validateEmail(final String email) {
        Pattern compile = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = compile.matcher(email);
        return matcher.matches();
    }
}
