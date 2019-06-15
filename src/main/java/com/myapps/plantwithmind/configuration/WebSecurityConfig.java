package com.myapps.plantwithmind.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.myapps.plantwithmind.service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http
		    .authorizeRequests()
		              .antMatchers("/", 
		            		       "/register",
		            		       "/h2-console/**",
		            		       "/crops",
		            		       "/crops/**",
		            		       "/about"
		            		       )
		              .permitAll()
		              .antMatchers("/js/*",
		            		       "/css/*",
		            		       "/images/*")
		                .permitAll()
		                .antMatchers("/profile/**",
		                		   "/admin",
		            		       "/manageuser",
		            		       "/edit/**",
		            		       "/delete/**",
		            		       "/deletecrop/**",
		            		       "/deletelivestock/**",
		            		       "/update/**",
		            		       "/managelivestock",
		            		       "/addcrop",
		            		       "/showaddcrop",
		            		       "/showaddlivestock",
		            		       "/editcrop/**",
		            		       "/editlivestock/**",
		            		       "/bookcrop/**",
		            		       "renderimage/{id}",
		            		       "/cropimage/**",
		            		       "/livestockimage/**",
		            		       "/livestocks/**")
		                .authenticated()
		                .anyRequest()
		                .denyAll()
		                .and()
		                .formLogin()
		                .loginPage("/login")
		                .defaultSuccessUrl("/")
		                .permitAll()
		                .and()
		                .logout()
		                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                         .logoutSuccessUrl("/login?logout")
		                .permitAll();

		http.csrf().disable();
        http.headers().frameOptions().disable();
		// @formatter:on
	}
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		// @formatter:off
		auth
		    .inMemoryAuthentication()
		    .withUser("abass")
		    .password("hello")
		    .roles("USER");
		
		// @formatter:on
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
	}
	
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//	    //@formatter:off
//	    super.configure(web);
//	    web.httpFirewall(allowUrlEncodedSlashHttpFirewall());
//	}
//	
//	private HttpFirewall allowUrlEncodedSlashHttpFirewall() {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	
	
	
}
