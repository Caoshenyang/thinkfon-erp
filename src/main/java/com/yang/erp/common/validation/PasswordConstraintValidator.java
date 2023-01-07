package com.yang.erp.common.validation;

import com.yang.erp.common.annotation.ValidPassword;
import lombok.RequiredArgsConstructor;
import org.passay.*;
import org.passay.spring.SpringMessageResolver;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

/**
 * @Description: 密码校验
 * @author: 曹申阳
 * @date: 2022.01.24
 */
@RequiredArgsConstructor
public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    private final SpringMessageResolver springMessageResolver;

    @Override
    public void initialize(ValidPassword constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        PasswordValidator validator = new PasswordValidator(springMessageResolver, Arrays.asList(
                // 长度为8-30
                new LengthRule(8, 30),
                // 至少有一个大写字母
//                new CharacterRule(EnglishCharacterData.UpperCase, 1),
                // 至少有一个小写字母
//                new CharacterRule(EnglishCharacterData.LowerCase, 1),
                // 至少有一个特殊字符
//                new CharacterRule(EnglishCharacterData.Special, 1),
                // 不允许5个连续的字母
//                new IllegalSequenceRule(EnglishSequenceData.Alphabetical, 5, false),
                // 不允许5个连续数字
//                new IllegalSequenceRule(EnglishSequenceData.Numerical, 5, false),
                // 不允许键盘上五个连续按键
//                new IllegalSequenceRule(EnglishSequenceData.USQwerty, 5, false),
                // 不允许空格
                new WhitespaceRule()));
        RuleResult result = validator.validate(new PasswordData(password));
        constraintValidatorContext.disableDefaultConstraintViolation();
        constraintValidatorContext.buildConstraintViolationWithTemplate(String.join(" ", validator.getMessages(result))).addConstraintViolation();
        return result.isValid();
    }
}
