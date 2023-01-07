package com.yang.erp.common.annotation;

import com.yang.erp.common.validation.EmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * <p>
 *  邮箱校验
 * </p>
 *
 * @author 曹申阳
 * @since 2022-09-25 15:31:10
 */
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
@Documented
public @interface ValidEmail {

    String message() default "{ValidEmail.email}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
