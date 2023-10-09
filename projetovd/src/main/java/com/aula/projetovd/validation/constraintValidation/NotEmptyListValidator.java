package com.aula.projetovd.validation.constraintValidation;

import java.util.List;

import com.aula.projetovd.validation.NotEmptyList;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotEmptyListValidator 
        implements ConstraintValidator<NotEmptyList, List> {

    @Override
    public boolean isValid(final List list, final ConstraintValidatorContext context) {
        return list != null && !list.isEmpty();
    }

    @Override
    public void initialize(NotEmptyList constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
    
}
