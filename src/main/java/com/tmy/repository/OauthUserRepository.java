package com.tmy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmy.model.OAuthUser;

public interface OauthUserRepository extends JpaRepository<OAuthUser, Integer> {
    
    OAuthUser findByOAuthTypeAndOAuthId(String oAuthType, String oAuthId);

}
