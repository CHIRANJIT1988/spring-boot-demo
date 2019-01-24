package com.txtbravo.txtbravo.config.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;


@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	private static final String CLIENT_ID = "CHIRANJIT";
	private static final String CLIENT_SECRET = "jit1988";
	private static final String GRANT_TYPE_PASSWORD = "password";
	private static final String AUTHORIZATION_CODE = "authorization_code";
	private static final String REFRESH_TOKEN = "refresh_token";
	private static final String IMPLICIT = "implicit";
	private static final String SCOPE_READ = "read";
	private static final String SCOPE_WRITE = "write";
	private static final String TRUST = "trust";
	private static final int ACCESS_TOKEN_VALIDITY_SECONDS = 1*60*60;
	private static final int REFRESH_TOKEN_VALIDITY_SECONDS = 6*60*60;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	@Autowired
	private ClientDetailsService clientDetailsService;

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey("as466gf");
		return converter;
	}

	@Bean
	public TokenStore tokenStore() {

		//return new JwtTokenStore(accessTokenConverter());

		/**
		 * Store oauth_access_token
		 */
		String insertAccessTokenSql = "insert into oauth_access_token (token_id, token, authentication_id, user_name, client_id, authentication, refresh_token) values (?, ?, ?, ?, ?, ?, ?)";
		String selectAccessTokensFromUserNameAndClientIdSql = "select token_id, token from oauth_access_token where user_name = ? and client_id = ?";
		String selectAccessTokensFromUserNameSql = "select token_id, token from oauth_access_token where user_name = ?";
		String selectAccessTokensFromClientIdSql = "select token_id, token from oauth_access_token where client_id = ?";
		String insertRefreshTokenSql = "insert into oauth_refresh_token (token_id, token, authentication) values (?, ?, ?)";

		JdbcTokenStore jdbcTokenStore = new JdbcTokenStore(dataSource);

		jdbcTokenStore.setInsertAccessTokenSql(insertAccessTokenSql);
		jdbcTokenStore.setSelectAccessTokensFromUserNameAndClientIdSql(selectAccessTokensFromUserNameAndClientIdSql);
		jdbcTokenStore.setSelectAccessTokensFromUserNameSql(selectAccessTokensFromUserNameSql);
		jdbcTokenStore.setSelectAccessTokensFromClientIdSql(selectAccessTokensFromClientIdSql);
		jdbcTokenStore.setInsertRefreshTokenSql(insertRefreshTokenSql);

		return jdbcTokenStore;
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {

		/*configurer
				.inMemory()
				.withClient(CLIENT_ID)
				.secret(CLIENT_SECRET)
				.authorizedGrantTypes(GRANT_TYPE_PASSWORD, AUTHORIZATION_CODE, REFRESH_TOKEN, IMPLICIT )
				.scopes(SCOPE_READ, SCOPE_WRITE, TRUST)
				.accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS).
				refreshTokenValiditySeconds(REFRESH_TOKEN_VALIDITY_SECONDS);*/

		/**
		 * if you want to make use of the jdbctokenstore, please make use of below code
		 */
		configurer.jdbc(dataSource).withClient(CLIENT_ID)
				.secret(CLIENT_SECRET)
				.authorizedGrantTypes(GRANT_TYPE_PASSWORD, AUTHORIZATION_CODE, REFRESH_TOKEN, IMPLICIT )
				.scopes(SCOPE_READ, SCOPE_WRITE, TRUST)
				.accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS)
				.refreshTokenValiditySeconds(REFRESH_TOKEN_VALIDITY_SECONDS);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

		endpoints.tokenStore(tokenStore())
				.authenticationManager(authenticationManager)
				.pathMapping("/oauth/token", "/oauth/login")
				.accessTokenConverter(accessTokenConverter());
	}


	@Bean
	DefaultOAuth2RequestFactory defaultOAuth2RequestFactory() {
		return new DefaultOAuth2RequestFactory(clientDetailsService);
	}


	@Bean
	@Primary
	public DefaultTokenServices tokenServices() {
		DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
		defaultTokenServices.setTokenStore(tokenStore());
		defaultTokenServices.setSupportRefreshToken(true);
		defaultTokenServices.setTokenEnhancer(accessTokenConverter());
		return defaultTokenServices;
	}
}