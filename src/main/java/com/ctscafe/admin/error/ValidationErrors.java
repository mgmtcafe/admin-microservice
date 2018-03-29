package com.ctscafe.admin.error;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.ctscafe.admin.utilities.JsonResponse;

public class ValidationErrors {

	private Map<String, String> err = new HashMap<String, String>();

	public ValidationErrors(Object object) {
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		Validator validator = validatorFactory.getValidator();

		Set<ConstraintViolation<Object>> violations = validator.validate(object);
		for (ConstraintViolation<Object> constraintViolation : violations) {
			String propertyPath = constraintViolation.getPropertyPath().toString();
			String message = constraintViolation.getMessage();
			err.put(propertyPath, message);
		}
	}

	public JsonResponse getErrors() {
		JsonResponse ack = new JsonResponse();
		if(err.size() != 0) {
			ack.setStatus("error");
			ack.setMessage(err);
		}
		else {
			ack.setStatus("success");
			ack.setMessage("validations not violated");
		}
		return ack;
	}

}
