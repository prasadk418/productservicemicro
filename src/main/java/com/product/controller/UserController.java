package com.product.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.domain.User;
import com.product.service.UserService;
import com.product.util.EnumConstants;
import com.product.util.PasswordEncryption;

@RestController
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	private UserService userSerivce;

	@PostMapping(value="/add")
	public ResponseEntity<?> addBuilder(@RequestBody User user) throws ParseException
	{
		if(user==null)		
			return new ResponseEntity<>(EnumConstants.EMPTY_JSON_STRING.toString(), HttpStatus.BAD_REQUEST);
			user.setPassword(PasswordEncryption.encryptPwd(user.getPassword()));
		User b=userSerivce.addUser(user);
		if(b==null)
			return new ResponseEntity<>("User not saved due to technical Issues, Try again after sometime..!", HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<>("user saveed successfully", HttpStatus.OK);
	}
}
