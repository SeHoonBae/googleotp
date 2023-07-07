package com.example.googleotp.controller;

import com.example.googleotp.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OtpController {

    @Autowired
    private OtpService otpService;

    @GetMapping("/test")
    public String test(){
        return "test";
    }

    @GetMapping("/register")
    public String register(@RequestParam("email") String email, Model model) {
        String otpAuthURL = otpService.createCredentials(email);
        model.addAttribute("otpAuthURL", otpAuthURL);
        return "register";
    }

    @PostMapping("/verify")
    public String verify(@RequestParam("email") String email, @RequestParam("otp") int otp, Model model) {
        boolean result = otpService.authenticateUser(email, otp);
        model.addAttribute("verificationResult", result);
        return "verify";
    }

}
