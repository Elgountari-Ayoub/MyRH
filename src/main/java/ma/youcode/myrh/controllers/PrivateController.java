package ma.youcode.myrh.controllers;


import com.stripe.net.OAuth;
import lombok.extern.slf4j.Slf4j;
import ma.youcode.myrh.dtos.MessageDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@CrossOrigin("*")
public class PrivateController {

    @GetMapping("/messages")
    public ResponseEntity<MessageDto> privateMessage(
            @AuthenticationPrincipal OAuth2User user){
        log.info(String.valueOf(user));
        return ResponseEntity.ok(new MessageDto("private content" + String.valueOf(user)));
    }
}
