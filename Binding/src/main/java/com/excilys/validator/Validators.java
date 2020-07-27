package com.excilys.validator;

import javax.xml.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Validators {
	
	private static Logger logger = LoggerFactory.getLogger(Validator.class);
	
	public static void validatorName(String name) throws Exception {
		if (name.equals(null) || name.trim().length() == 0) {
			logger.info("There are no name entered in the case");
		    throw new Exception( "Name is required !" );
		}
	}

}
