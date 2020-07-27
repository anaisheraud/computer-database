package com.excilys.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import com.excilys.dao.DaoFactory;

@Configuration
@ComponentScan(basePackages = "com.excilys")
public class HibernateConfig {
	
	@Autowired
	DaoFactory daoFactory;
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionF = new LocalSessionFactoryBean();
		sessionF.setDataSource(daoFactory.getDs());
		sessionF.setPackagesToScan("com.excilys");
		return sessionF;
	}

}
