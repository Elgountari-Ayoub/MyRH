package ma.youcode.myrh.services;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import ma.youcode.myrh.dao.response.JwtAuthenticationResponse;
import ma.youcode.myrh.models.User;

import java.io.IOException;

public interface Oauth2Service {

    public GoogleTokenResponse getTokenResponse(String code) throws IOException;
    public User extractInfoUserFromPayload(GoogleTokenResponse response);
    public String generateLinkOfGoogleForm();
    public User saveUserIfNotExist(User user) throws Exception;

    public GoogleIdToken.Payload extractPayload(GoogleTokenResponse token) throws IOException;
    public String generateToken(String code) throws Exception;

}
