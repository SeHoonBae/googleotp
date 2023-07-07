package com.example.googleotp.util;

import org.junit.jupiter.api.Test;

class OtpUtilsTest {

    @Test
    public void generateSecretKeyTest(){
        String secretKey = OtpUtils.generateSecretKey();
        System.out.println(secretKey);
    }

    @Test
    public void authenticateTest(){
        String secretKey = "ZFKONRID7TLITEGHB5JP7F6DJBBC5IZK";
        String lastCode = "832522";

        String code = OtpUtils.getTOTPCode(secretKey);
        if (!code.equals(lastCode)) {
            System.out.println("fail!");
            System.out.println(code);
        }else{
            System.out.println("success!");
        }

//        while (true) {
//            String code = OtpUtils.getTOTPCode(secretKey);
//            if (!code.equals(lastCode)) {
//                System.out.println(code);
//            }
//            lastCode = code;
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {};
//        }
    }

}