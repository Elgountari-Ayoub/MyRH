package ma.youcode.myrh.services.implementations;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import lombok.extern.slf4j.Slf4j;
import ma.youcode.myrh.models.JobSeeker;
import ma.youcode.myrh.models.Role;
import ma.youcode.myrh.models.User;
import ma.youcode.myrh.repositories.IJobSeekerRepository;
import ma.youcode.myrh.repositories.UserRepository;
import ma.youcode.myrh.services.JwtService;
import ma.youcode.myrh.services.Oauth2Service;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;

@Service
@Slf4j
public class Oauth2ServiceImpl implements Oauth2Service {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository ;

    @Autowired
    private IJobSeekerRepository jobSeekerRepository;
    @Autowired
    private  ModelMapper modelMapper;

    private final JwtService jwtService ;

    @Value("${spring.security.oauth2.resourceserver.opaquetoken.client-id}")
    private String clientId ;

    @Value("${spring.security.oauth2.resourceserver.opaquetoken.client-secret}")
    private String clientSecret ;

    public Oauth2ServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, JwtService jwtService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @Override
    public GoogleTokenResponse getTokenResponse(String code) throws IOException {
       return new GoogleAuthorizationCodeTokenRequest(
                new NetHttpTransport(), new GsonFactory(),
                clientId,
                clientSecret,
                code,
                "http://localhost:4200"
        ).execute();
    }

    @Override
    public User extractInfoUserFromPayload(GoogleTokenResponse response) {
        GoogleIdToken.Payload payload ;
        try {
            payload = extractPayload(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        var user = User.builder()
                .email(payload.getEmail())
                .name((String) payload.get("name"))
                .role(Role.USER.name())
                .password(passwordEncoder.encode("123456"))
                .build();
        return user;
    }

    @Override
    public String generateLinkOfGoogleForm() {
        return new GoogleAuthorizationCodeRequestUrl(
                clientId ,
                "http://localhost:4200",
                Arrays.asList("email","profile","openid")
        ).build();
    }

    @Override
    public User saveUserIfNotExist(User user) throws Exception {
        if (userRepository.findByEmail(user.getEmail()).isEmpty()) {
//          return userRepository.save(user);
            JobSeeker jobSeeker = modelMapper.map(user, JobSeeker.class);
            jobSeekerRepository.save(jobSeeker);
            return user;
        }else {
            return userRepository.findByEmail(user.getEmail()).get();
        }

        //return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public GoogleIdToken.Payload extractPayload(GoogleTokenResponse token) throws IOException {
        GoogleIdToken idToken = token.parseIdToken();
        //GoogleIdToken.Payload payload = idToken.getPayload();
        return idToken.getPayload();
    }

    @Override
    public String generateToken(String code) throws Exception {
        GoogleTokenResponse googleTokenResponse;
        String token ;

            googleTokenResponse = getTokenResponse(code);
            var user = extractInfoUserFromPayload(googleTokenResponse);
            var userSaved = saveUserIfNotExist(user);

            token = jwtService.generateToken(userSaved);
            log.info(token);
        return token;
    }


}
