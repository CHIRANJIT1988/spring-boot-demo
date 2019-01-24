package com.txtbravo.txtbravo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.io.Serializable;
import java.security.Principal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
public class TokenController {

    @Autowired
    private DefaultTokenServices defaultTokenServices;

    /*@PostMapping("/token")
    public ResponseEntity<?> getToken(Principal principal) throws HttpRequestMethodNotSupportedException{

        HashMap<String, String> parameters = new HashMap<String, String>();
        parameters.put("client_id", "appid");
        parameters.put("client_secret", "myOAuthSecret");
        parameters.put("grant_type", "password");
        parameters.put("password", "password");
        parameters.put("scope", "read write");
        parameters.put("username", "bardhan.jit@gmail,.com");

        return tokenEndpoint.getAccessToken(principal, parameters);
    }*/


    @PostMapping("/token")
    public OAuth2AccessToken token(Principal principal) {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        Map<String, String> requestParameters = new HashMap<>();
        String clientId = "CHIRANJIT";
        boolean approved = true;
        Set<String> scope = new HashSet<>();
        scope.add("scope");
        Set<String> resourceIds = new HashSet<>();
        Set<String> responseTypes = new HashSet<>();
        responseTypes.add("code");
        Map<String, Serializable> extensionProperties = new HashMap<>();

        OAuth2Request oAuth2Request = new OAuth2Request(requestParameters, clientId,
                authorities, approved, scope,
                resourceIds, null, responseTypes, extensionProperties);


        User userPrincipal = new User(principal.getName(), "password", true, true, true, true, authorities);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userPrincipal, null, authorities);
        OAuth2Authentication auth = new OAuth2Authentication(oAuth2Request, authenticationToken);
        OAuth2AccessToken token = defaultTokenServices.createAccessToken(auth);
        return token;
    }
}
