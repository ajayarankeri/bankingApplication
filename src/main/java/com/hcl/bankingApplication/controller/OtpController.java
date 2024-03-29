package com.hcl.bankingApplication.controller;


import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcl.bankingApplication.service.MyEmailService;
import com.hcl.bankingApplication.service.OtpService;
import com.hcl.bankingApplication.utility.EmailTemplate;
/**
 * @author Nilesh Kesharwani
 * Jul 25, 2019
 */
@Controller
public class OtpController {
private final Logger logger = LoggerFactory.getLogger(this.getClass());
@Autowired
public OtpService otpService;
@Autowired
public MyEmailService myEmailService;
@GetMapping("/generateOtp")
public String generateOtp(){
//Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
String username = "Nilesh";
int otp = otpService.generateOTP(username);
logger.info("OTP :"+otp);
//Generate The Template to send OTP 
//EmailTemplate template = new EmailTemplate("SendOtp");
Map<String,String> replacements = new HashMap<String,String>();
replacements.put("user", username);
replacements.put("otpnum", String.valueOf(otp));
String message = "Otp for transaction is"+String.valueOf(otp);
//String message = template.getTemplate(replacements);
myEmailService.sendOtpMessage("monikanidhisingh@gmail.com", "OTP -Banking Application", message);
return  "Otp Generated Succesfully Please validate it.";
}

@RequestMapping(value ="/validateOtp", method = RequestMethod.GET)
public @ResponseBody String validateOtp(@RequestParam("otpnum") int otpnum){
final String SUCCESS = "Entered Otp is valid";
final String FAIL = "Entered Otp is NOT valid. Please Retry!";
		/*
		 * Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 * String username = auth.getName();
		 */
String username = "Nilesh";
logger.info(" Otp Number : "+otpnum);
//Validate the Otp 
if(otpnum >= 0){
int serverOtp = otpService.getOtp(username);
if(serverOtp > 0){
if(otpnum == serverOtp){
otpService.clearOTP(username);
return ("Entered Otp is valid");
}else{
return SUCCESS;
}
}else {
return FAIL;
}
}else {
return FAIL;
}
}
}