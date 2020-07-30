package com.excilys.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.excilys.dao.UserDao;
import com.excilys.models.User;

@Service
public class ServiceUser implements UserDetailsService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user =  userDao.findByUsername(username).orElse(null);
		if(user ==null) {
	    	   throw new UsernameNotFoundException("username not found");
	       }
	        return org.springframework.security.core.userdetails.User.builder()
	                .username(user.getUsername())
	                .password(user.getPassword())
	                .roles(user.getRoleName())
	                .build();
	}

}
