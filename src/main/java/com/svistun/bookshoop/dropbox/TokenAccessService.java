package com.svistun.bookshoop.dropbox;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;
@Service
public class TokenAccessService {
    private final TokenConfig tokenConfig;
    private String accessToken;

    public TokenAccessService(TokenConfig tokenConfig) {
        this.tokenConfig = tokenConfig;
    }

    public String getAccessToken() throws UnknownHostException {
        if (accessToken == null) {
            refreshAccessToken();
        }
        return accessToken;
    }


    @Scheduled(fixedRate = 1800000)
    private void refreshAccessToken() {
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", "refresh_token");
        formData.add("refresh_token", tokenConfig.getAPP_REFRESH_TOKEN());
        formData.add("client_id", tokenConfig.getAPP_KEY());
        formData.add("client_secret", tokenConfig.getAPP_SECRET());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(formData, headers);

        ResponseEntity<AccessTokenResponse> response = restTemplate.postForEntity("https://api.dropbox.com/oauth2/token", request, AccessTokenResponse.class);
        accessToken = Optional.ofNullable(Objects.requireNonNull(response.getBody()).getAccess_token())
                .orElseThrow(() -> new RuntimeException("Access token not found"));
        System.out.println("Access token refreshed at " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
    }
}
