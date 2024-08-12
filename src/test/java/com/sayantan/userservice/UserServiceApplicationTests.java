package com.sayantan.userservice;

import com.sayantan.userservice.security.repositories.JpaRegisteredClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;

import java.util.UUID;

@SpringBootTest
class UserServiceApplicationTests {
	@Autowired
	private JpaRegisteredClientRepository jpaRegisteredClientRepository;

	@Test
	void contextLoads() {
	}

//	@Test
//	public void addSampleRegisterClient() {
//		RegisteredClient oidcClient = RegisteredClient.withId(UUID.randomUUID().toString())
//                .clientId("oidc-client")
//                .clientSecret("$2a$12$RWxI5gmURkd0W8nrF6Eloe1Z5TzdvY611jzQpSStg5.cXRZIJDGfq")
//                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
//                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
//                .redirectUri("https://oauth.pstmn.io/v1/callback")
//                .postLogoutRedirectUri("https://oauth.pstmn.io/v1/callback")
//                .scope(OidcScopes.OPENID)
//                .scope(OidcScopes.PROFILE)
//                .scope("ADMIN")
//                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
//                .build();
//		jpaRegisteredClientRepository.save(oidcClient);
//	}
}
