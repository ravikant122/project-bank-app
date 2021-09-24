package com.bankApp.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bankApp.model.service.UserService;
import com.bankApp.web.entities.User;
@Service
public class DetailService implements UserDetailsService {

	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userService.findByUsername(username);
		if(user==null)
			throw new UsernameNotFoundException("user not found");
		
		return new org.springframework.security.core.userdetails.User
				(user.getUsername(), user.getPassword(), 
						AuthorityUtils.createAuthorityList(new String[] {user.getProfile().toString()}));
	}

}
