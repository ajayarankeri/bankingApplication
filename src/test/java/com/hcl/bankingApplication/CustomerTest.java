package com.hcl.bankingApplication;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;

import com.hcl.bankingApplication.service.CustomerService;


@RunWith(MockitoJUnitRunner.class)
public class CustomerTest {
	
	@InjectMocks
	CustomerService customerService;
	
	

}
