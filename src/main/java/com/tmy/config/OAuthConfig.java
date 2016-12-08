package com.tmy.config;

import org.scribe.builder.ServiceBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tmy.oauth.api.QQApi;
import com.tmy.oauth.service.OAuthServiceDeractor;
import com.tmy.oauth.service.QQOAuthService;

@Configuration
public class OAuthConfig {
    
    private static final String CALLBACK_URL = "http://tianmaying.com/oauth/%s/callback";
    
    @Value("${oAuth.qq.state}") String state;
    @Value("${oAuth.qq.appId}") String qqAppId;
    @Value("${oAuth.qq.appKey}") String qqAppKey;
    
    @Bean
    public QQApi qqApi(){
        return new QQApi(state);
    }
    
    @Bean
    public OAuthServiceDeractor getQQOAuthService(){
        return new QQOAuthService(new ServiceBuilder()
                .provider(qqApi())
                .apiKey(qqAppId)
                .apiSecret(qqAppKey)
                .callback(String.format(CALLBACK_URL, OAuthTypes.QQ))
                .build());
    }

}
