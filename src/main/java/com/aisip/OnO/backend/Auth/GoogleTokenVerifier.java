package com.aisip.OnO.backend.Auth;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class GoogleTokenVerifier {

    /*
    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId; // static 제거
     */

    @Value("${spring.security.oauth2.client.registration.google.client-id.android}")
    private String androidClientId;

    @Value("${spring.security.oauth2.client.registration.google.client-id.ios}")
    private String iosClientId;

    public JsonNode verifyToken(String accessToken, String platform) throws IOException {
        String clientId = getClientIdByPlatform(platform);

        String tokenInfoUrl = "https://www.googleapis.com/oauth2/v3/tokeninfo?access_token=" + accessToken;
        URL url = new URL(tokenInfoUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode tokenInfo = mapper.readTree(connection.getInputStream());

            // 검증된 토큰의 client_id가 기대하는 client_id와 일치하는지 확인
            if (!tokenInfo.get("aud").asText().equals(clientId)) {
                throw new SecurityException("Invalid client ID");
            }

            return tokenInfo;
        } else {
            throw new IOException("Invalid access token");
        }
    }

    private String getClientIdByPlatform(String platform) {
        switch (platform.toLowerCase()) {
            case "android":
                return androidClientId;
            case "ios":
                return iosClientId;
            default:
                throw new IllegalArgumentException("Invalid platform specified");
        }
    }
}