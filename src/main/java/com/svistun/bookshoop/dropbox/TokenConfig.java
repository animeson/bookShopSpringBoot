package com.svistun.bookshoop.dropbox;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class TokenConfig {
    @Value("${APP_REFRESH_TOKEN}")
    private String APP_REFRESH_TOKEN;
    @Value("${APP_SECRET}")
    private String APP_SECRET;
    @Value("${APP_KEY}")
    private String APP_KEY;

}
