package com.tmy.oauth.service;

import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPath;
import com.tmy.config.OAuthTypes;
import com.tmy.model.OAuthUser;
import com.tmy.model.User;

public class QQOAuthService extends OAuthServiceDeractor {
    
    private static final String PROTECTED_RESOURCE_URL = "https://graph.qq.com/oauth2.0/me";

    public QQOAuthService(OAuthService oAuthService) {
        super(oAuthService, OAuthTypes.QQ);
    }

    @Override
    public OAuthUser getOAuthUser(Token accessToken) {
        OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
        this.signRequest(accessToken, request);
        Response response = request.send();
        OAuthUser oAuthUser = new OAuthUser();
        oAuthUser.setoAuthType(getoAuthType());
        Object result = JSON.parse(response.getBody().substring(9, response.getBody().length() - 3));
        oAuthUser.setoAuthId(JSONPath.eval(result, "$.openid").toString());
        oAuthUser.setUser(new User());
        return oAuthUser;
    }

}
