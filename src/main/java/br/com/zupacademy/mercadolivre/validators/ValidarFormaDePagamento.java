package br.com.zupacademy.mercadolivre.validators;
 
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.NotNull;

@Documented
@Constraint(validatedBy = ValidarFormaDePagamentoValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@ReportAsSingleViolation
public @interface ValidarFormaDePagamento {

  Class<? extends Enum<?>> enumClazz();

  String message() default "Forma de pagamento não é válida";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}