package com.svistun.bookshoop.config.dropbox;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.UnknownHostException;

@Component
public class DbxClientProvider {
    private final String accessToken;
    private final DbxRequestConfig config;
    @Autowired
    public DbxClientProvider(TokenAccessService tokenAccessService, TokenConfig tokenConfig)
            throws UnknownHostException {

        this.accessToken = tokenAccessService.getAccessToken();
        this.config = new DbxRequestConfig(tokenConfig.getAppName());

    }

    public DbxClientV2 getClient() {
        return new DbxClientV2(config, accessToken);
    }

}

