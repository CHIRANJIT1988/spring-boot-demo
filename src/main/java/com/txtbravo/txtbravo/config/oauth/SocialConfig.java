package com.txtbravo.txtbravo.config.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.google.connect.GoogleConnectionFactory;

import javax.sql.DataSource;

@Configuration
@EnableSocial
public class SocialConfig implements SocialConfigurer
{
    @Value("${social.facebook.scope}")
    private String facebook_scope;

    @Value("${social.facebook.app.id}")
    private String facebook_app_id;

    @Value("${social.facebook.app.secret}")
    private String facebook_app_secret;

    @Value("${social.google.scope}")
    private String google_scope;

    @Value("${social.google.client.id}")
    private String google_client_id;

    @Value("${social.google.client.secret}")
    private String google_client_secret;


    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    @Bean
    public FacebookConnectionFactory facebookConnectionFactory()
    {
        FacebookConnectionFactory facebookConnectionFactory = new FacebookConnectionFactory(facebook_app_id, facebook_app_secret);
        facebookConnectionFactory.setScope(facebook_scope);

        return facebookConnectionFactory;
    }

    @Bean
    public GoogleConnectionFactory googleConnectionFactory()
    {
        GoogleConnectionFactory googleConnectionFactory = new GoogleConnectionFactory(google_client_id, google_client_secret);
        googleConnectionFactory.setScope(google_scope);

        return googleConnectionFactory;
    }

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer cfConfig, Environment env) {
        cfConfig.addConnectionFactory(facebookConnectionFactory());
        cfConfig.addConnectionFactory(googleConnectionFactory());
    }

    @Override
    public UserIdSource getUserIdSource() {

        //return new AuthenticationNameUserIdSource();
        return null;
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        return new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
    }
}