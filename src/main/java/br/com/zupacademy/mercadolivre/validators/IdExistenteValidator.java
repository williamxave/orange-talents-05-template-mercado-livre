package br.com.zupacademy.mercadolivre.validators;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IdExistenteValidator implements ConstraintValidator<IdExistente, Object> {

    private String domainAttribute;
	private Class<?> klass;

    @PersistenceContext
	private EntityManager manager;

    @Override
    public void initialize(IdExistente params) {
        domainAttribute = params.fieldName();
		klass = params.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        Query query = manager.createQuery("select 1 from " +klass.getName()+  " where " +domainAttribute+ "=:value");
		query.setParameter("value", value);
		List<?> list = query.getResultList();
		return !list.isEmpty();
    }
}
