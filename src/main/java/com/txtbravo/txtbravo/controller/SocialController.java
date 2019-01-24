package com.txtbravo.txtbravo.controller;

import com.txtbravo.txtbravo.entity.SocialProvider;
import com.txtbravo.txtbravo.model.Role;
import com.txtbravo.txtbravo.model.SocialUser;
import com.txtbravo.txtbravo.model.User;
import com.txtbravo.txtbravo.service.RoleService;
import com.txtbravo.txtbravo.service.SocialProviderService;
import com.txtbravo.txtbravo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.social.connect.Connection;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashSet;

@RestController
@RequestMapping("/social")
public class SocialController
{
    @Autowired
    private FacebookConnectionFactory facebookConnectionFactory;

    @Autowired
    private GoogleConnectionFactory googleConnectionFactory;

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    SocialProviderService socialProviderService;

    //@Autowired
    //private UsersConnectionRepository usersConnectionRepository;

    @RequestMapping(value = "/facebook", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Object facebook(@RequestParam String token)
    {
        AccessGrant accessGrant = new AccessGrant(token);
        Connection<Facebook> connection = facebookConnectionFactory.createConnection(accessGrant);

        Facebook facebook = connection.getApi();
        String [] fields = { "id","name","birthday","email","location","hometown","gender","first_name","last_name"};

        SocialUser social = facebook.fetchObject("me", SocialUser.class, fields);

        if(social != null)
        {
            SocialProvider provider = socialProviderService.findById(social.getId());

            if(provider != null)
            {
                return userService.findById(provider.getUser().getId());
            }

            Role userRole = roleService.findByRole("ROLE_USER");

            User user = new User(social.getEmail(), social.getFirst_name(), social.getLast_name(), new HashSet<>(Arrays.asList(userRole)));

            user = userService.save(user);

            provider = new SocialProvider(social.getId(), "facebook", token, user);

            socialProviderService.save(provider);

            return user;
        }

        //UserProfile userProfile = connection.fetchUserProfile();
        //usersConnectionRepository.createConnectionRepository(userProfile.getEmail()).addConnection(connection);

        return ResponseEntity.badRequest().build();
    }


    @RequestMapping(value = "/google", method = RequestMethod.POST)
    public String google(@RequestParam String token)
    {
        AccessGrant accessGrant = new AccessGrant(token);
        Connection<Google> connection = googleConnectionFactory.createConnection(accessGrant);

        Google google = connection.getApi();

        return google.userOperations().getUserInfo().getId();
    }
}