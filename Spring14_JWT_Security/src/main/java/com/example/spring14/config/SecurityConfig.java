package com.example.spring14.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.savedrequest.CookieRequestCache;

import com.example.spring14.filter.JwtFilter;

import jakarta.servlet.http.Cookie;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Value("${jwt.name}") private String jwtName;
	
	@Autowired private JwtFilter jwtFilter;
	
	@Bean 
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity,
			AuthSuccessHandler successHandler,
			CookieRequestCache cookCache) throws Exception{
		String[] whiteList= {"/", "/play", "/user/loginform", "/user/login-fail", "/user/expired"};
		
		httpSecurity
		.csrf(csrf->csrf.disable())
		.authorizeHttpRequests(config ->
			config
				.requestMatchers(whiteList).permitAll()
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.requestMatchers("/staff/**").hasAnyRole("ADMIN", "STAFF")
				.anyRequest().authenticated()
		)
		.formLogin(config ->
			config
				.loginPage("/user/required-loginform")
				.loginProcessingUrl("/user/login")
				.usernameParameter("userName")
				.passwordParameter("password")
				.successHandler(new AuthSuccessHandler()) 
				.failureForwardUrl("/user/login-fail")
				.permitAll() 
		)
		.logout(config ->
			config
				.logoutUrl("/user/logout")
				.logoutSuccessHandler((request, response, auth)->{
					Cookie cook=new Cookie(jwtName, null);
					//쿠키를 삭제하기 위해 setMaxAge(0)
					cook.setMaxAge(0);
					cook.setPath("/");
					response.addCookie(cook);
					//쿠키 삭제후에 최상위 경로로 리다일렉트 이동
					response.sendRedirect(request.getContextPath()+"/");
				})
				.permitAll()
		)
		.exceptionHandling(config ->
			config.accessDeniedPage("/user/denied")
		)
		.sessionManagement(config -> 
			// 세션을 사용하지 않도록 설정한다.
			config.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		)
		.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
		.requestCache(config -> config.requestCache(cookCache));
		
		return httpSecurity.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationManager authenticationManager(HttpSecurity http,
			BCryptPasswordEncoder encoder, UserDetailsService service) throws Exception{ 
		return http.getSharedObject(AuthenticationManagerBuilder.class)
				.userDetailsService(service)
				.passwordEncoder(encoder)
				.and()
				.build();
	}
	
	// 쿠키 캐시를 bean 으로 만든다.
	@Bean
	CookieRequestCache getCookieRequestCache() {
		return new CookieRequestCache();
	}
}
