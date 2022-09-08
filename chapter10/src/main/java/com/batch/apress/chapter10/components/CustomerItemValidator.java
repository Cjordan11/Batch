package com.batch.apress.chapter10.components;

import java.util.Collections;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.batch.apress.chapter10.domain.CustomerUpdate;

@Component
public class CustomerItemValidator implements Validator<CustomerUpdate> {

	
	private final NamedParameterJdbcTemplate jdbcTemplate;

	protected static final String FIND_CUSTOMER = "SELECT COUNT(*) FROM CUSTOMER WHERE customer_id = :id";

	
	protected CustomerItemValidator(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Autowired
	public CustomerItemValidator(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public void validate(CustomerUpdate customer) throws ValidationException {
		Map<String, Long> parameterMap = Collections.singletonMap("id", customer.getCustomerId());

		Long count = jdbcTemplate.queryForObject(FIND_CUSTOMER, parameterMap, Long.class);

		if(count != null && count == 0) {
			throw new ValidationException(String.format("Customer id %s was not able to be found", customer.getCustomerId() ));
		}
	}
}
