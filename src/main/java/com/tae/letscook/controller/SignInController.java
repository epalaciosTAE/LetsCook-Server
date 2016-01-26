package com.tae.letscook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tae.letscook.constants.Constants;
import com.tae.letscook.model.edaman.Chef;
import com.tae.letscook.service.SignInService;

@Controller
public class SignInController {

	@Autowired
	private SignInService signInService;
	
	@RequestMapping (value=Constants.SIGN_IN_ENDPOINT, method=RequestMethod.POST)
	public @ResponseBody Chef signIn(@RequestParam String authcode){
		System.out.println("Login Controller - authorize user with auth code: " + authcode );
		return signInService.authorizeUser(authcode);
	}
}
