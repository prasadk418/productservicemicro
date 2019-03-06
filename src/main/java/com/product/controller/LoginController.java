package com.product.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.product.domain.Login;
import com.product.domain.User;
import com.product.repository.UserRepository;
import com.product.util.EnumConstants;
import com.product.util.PasswordEncryption;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class LoginController {
	
	private static final String ISSUER = "admin";

	@Autowired
	public UserRepository userRepo;

		
	@Value("${jwt.secret}")
	private String secret;
	
	
	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public ResponseEntity<?> userLogin(@RequestBody Login login)
			throws ServletException, IOException {
		
		if (login == null) 
			return new ResponseEntity<>(EnumConstants.EMPTY_JSON_STRING.toString(),
					HttpStatus.BAD_REQUEST);
		
			User user = userRepo.findByUsername(login.getUser_name());

			if (user == null) {
				return new ResponseEntity<>(
						EnumConstants.INVALID_USER.toString(),
						HttpStatus.UNAUTHORIZED);
			} else if (!PasswordEncryption.checkPwd(login.getPassword(), user.getPassword())) {
				return new ResponseEntity<>(
						EnumConstants.INVALID_PASSWORD.toString(),
						HttpStatus.UNAUTHORIZED);
			}

			
						
			String token = Jwts.builder().setSubject(user.getUsername())
					.setIssuedAt(new Date()).setIssuer(ISSUER)
					.signWith(SignatureAlgorithm.HS512, secret).compact();
			
			Map<String, Object> map = new HashMap<String, Object>();			
				map.put("token", token);
			
						
			return new ResponseEntity(map, HttpStatus.OK);
		} 


}
