package com.excilys.testu;

import static org.junit.Assert.*;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.Test;

import com.excilys.mappers.DateMapper;

public class DateMappertestu {
	
	@Test
	public void TestlocalDateTosqlDate() {
		LocalDate localdate = null;
		Date datesql = null;
		assertEquals("Not date null !", datesql,DateMapper.localDateTosqlDate(localdate));
	}

}
