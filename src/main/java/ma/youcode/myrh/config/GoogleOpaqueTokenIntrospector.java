package ma.youcode.myrh.config;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.youcode.myrh.dtos.UserInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.introspection.OAuth2IntrospectionAuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Slf4j
public class GoogleOpaqueTokenIntrospector implements OpaqueTokenIntrospector {

    private WebClient userInfoClient ;
    public GoogleOpaqueTokenIntrospector(WebClient userInfoClient) {
    }

    @Override
    public OAuth2AuthenticatedPrincipal introspect(String token) {
        UserInfo user = userInfoClient.get().uri(uriBuilder -> {
            return  uriBuilder.path("/oauth2/v3/userinfo").queryParam("access_token",token).build();
        }).retrieve().bodyToMono(UserInfo.class).block();
        Map<String , Object > attributes = new HashMap<>();
        attributes.put("sub", user.sub());
        attributes.put("name" , user.name());
        log.info(user.name());
        return new OAuth2IntrospectionAuthenticatedPrincipal(user.name() , attributes , null) ;

    }


}
