package ma.youcode.myrh.controllers;

import lombok.extern.slf4j.Slf4j;
import ma.youcode.myrh.config.JwtAuthenticationFilter;
import ma.youcode.myrh.config.WebClientConfig;
import ma.youcode.myrh.dtos.UrlDto;
import ma.youcode.myrh.services.Oauth2Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@CrossOrigin("http://localhost:4200")
//@RequestMapping("/auth")
public class AuthController {
    private final WebClientConfig webClientConfig ;
    private final JwtAuthenticationFilter jwtAuthenticationFilter ;
    private final Oauth2Service oauth2Service ;

     @Value("${spring.security.oauth2.resourceserver.opaquetoken.client-id}")
    private String clientId ;

     @Value("${spring.security.oauth2.resourceserver.opaquetoken.client-secret}")
    private String clientSecret ;

    public AuthController(WebClientConfig webClientConfig, JwtAuthenticationFilter jwtAuthenticationFilter, Oauth2Service oauth2Service) {
        this.webClientConfig = webClientConfig;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.oauth2Service = oauth2Service;
    }

    @GetMapping("/auth/url")
        public ResponseEntity<UrlDto> auth(){
         return ResponseEntity.ok(new UrlDto(oauth2Service.generateLinkOfGoogleForm()));
     }

     @GetMapping("/auth/callback")
     public ResponseEntity<String> callback(@RequestParam("code") String code) throws Exception {
        String token = oauth2Service.generateToken(code);
        log.info(token);
         return ResponseEntity.status(HttpStatus.OK).body(token);

     }

}
