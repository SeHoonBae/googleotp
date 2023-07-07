package com.example.googleotp.service;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;
import com.warrenstrange.googleauth.ICredentialRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OtpService {

    private final GoogleAuthenticator gAuth;
    private final Map<String, String> dataStore;

    public OtpService() {
        dataStore = new HashMap<>();
        gAuth = new GoogleAuthenticator();
        gAuth.setCredentialRepository(new ICredentialRepository() {
            @Override
            public String getSecretKey(String userName) {
                return dataStore.get(userName);
            }

            @Override
            public void saveUserCredentials(String userName, String secretKey, int validationCode, List<Integer> scratchCodes) {
                dataStore.put(userName, secretKey);
            }
        });
    }

    public String createCredentials(String userName) {
        final GoogleAuthenticatorKey key = gAuth.createCredentials(userName);
        return GoogleAuthenticatorQRGenerator.getOtpAuthURL("Kica", userName, key);
    }

    public boolean authenticateUser(String userName, int otp) {
        return gAuth.authorize(dataStore.get(userName), otp);
    }

}
