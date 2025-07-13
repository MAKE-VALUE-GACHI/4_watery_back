package team.gachi.watery.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
@Slf4j
public class FirebaseConfig {

    @PostConstruct
    public void initialize() {
        try {
            // TODO 파일 관리 수정
            FileInputStream serviceAccount =
                    new FileInputStream("src/main/resources/firebase/firebase-adminsdk.json");

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }

        } catch (IOException e) {
            log.error("Firebase init errorMessage: {}", e.getMessage());
        }
    }
}
