package com.axel.cv.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Configuration
public class FirebaseConfig {

    @Value("${app.firebase.service-account}")
    private String serviceAccount;

    @PostConstruct
    public void initialize() throws IOException {
        if (FirebaseApp.getApps().isEmpty()) {
            InputStream credentialsStream;

            if (serviceAccount.startsWith("classpath:")) {
                credentialsStream = getClass().getClassLoader()
                        .getResourceAsStream(serviceAccount.substring("classpath:".length()));
            } else if (serviceAccount.trim().startsWith("{")) {
                credentialsStream = new ByteArrayInputStream(
                        serviceAccount.getBytes(StandardCharsets.UTF_8));
            } else {
                credentialsStream = new ByteArrayInputStream(
                        serviceAccount.getBytes(StandardCharsets.UTF_8));
            }

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(credentialsStream))
                    .build();
            FirebaseApp.initializeApp(options);
        }
    }
}
