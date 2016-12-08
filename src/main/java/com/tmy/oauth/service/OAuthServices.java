package com.tmy.oauth.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OAuthServices {
    
    @Autowired List<OAuthServiceDeractor> oAuthServiceDeractors;
    
    public OAuthServiceDeractor getOAuthService(String type){
        Optional<OAuthServiceDeractor> oAuthService = oAuthServiceDeractors.stream().filter(o -> o.getoAuthType().equals(type))
                .findFirst();
        if(oAuthService.isPresent()){
            return oAuthService.get();
        }
        return null;
    }
    
    public List<OAuthServiceDeractor> getAllOAuthServices(){
        return oAuthServiceDeractors;
    }

}
