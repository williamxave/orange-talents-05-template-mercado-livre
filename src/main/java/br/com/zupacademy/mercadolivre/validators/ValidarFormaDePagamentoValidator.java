package br.com.zupacademy.mercadolivre.validators;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidarFormaDePagamentoValidator implements ConstraintValidator <ValidarFormaDePagamento,String> {

    private List<String> valueList = null;
    private ConstraintValidatorContext context;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        this.context = context;
        return null == value || valueList.contains(value.toUpperCase());
    }

    @Override
    public void initialize(ValidarFormaDePagamento constraintAnnotation) {
        valueList = new ArrayList<>();
        Class<? extends Enum<?>> enumClass = constraintAnnotation.enumClazz();

        Enum[] enumValArr = enumClass.getEnumConstants();

        for(Enum enumVal : enumValArr) {
            valueList.add(enumVal.toString().toUpperCase());
        }

    }
}