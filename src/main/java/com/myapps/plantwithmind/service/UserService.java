package com.myapps.plantwithmind.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.myapps.plantwithmind.model.SiteUser;
import com.myapps.plantwithmind.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	public void register(SiteUser user){
		user.setRole("ROLE_USER");
		userRepository.save(user);
	}
	
	public void save(SiteUser user){
		userRepository.save(user);
	}
	
	public SiteUser findByEmail(String email){
		return userRepository.findByEmail(email);
	}
	
	public UserDetails loadUserByUsername(String email)
			throws UsernameNotFoundException {

		SiteUser user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("user not found");
		}

		List<GrantedAuthority> auth = AuthorityUtils
				.commaSeparatedStringToAuthorityList(user.getRole());

		String password = user.getPassword();

		boolean enabled = true;

		return new User(email, password, enabled, true, true, true, auth);

	}
	public void delete(SiteUser user){
		 userRepository.delete(user);
	}
	
	public Optional<SiteUser> findById(Long id){
		return userRepository.findById(id);
	}

	public Iterable<SiteUser> findAll() {
		return userRepository.findAll();
	}
}
