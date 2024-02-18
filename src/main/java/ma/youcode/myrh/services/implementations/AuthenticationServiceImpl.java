package ma.youcode.myrh.services.implementations;


import lombok.RequiredArgsConstructor;
import ma.youcode.myrh.dao.request.SignUpRequest;
import ma.youcode.myrh.dao.request.SigninRequest;
import ma.youcode.myrh.dao.response.JwtAuthenticationResponse;
import ma.youcode.myrh.dtos.ProfileDTO;
import ma.youcode.myrh.models.JobSeeker;
import ma.youcode.myrh.models.Profile;
import ma.youcode.myrh.models.Role;
import ma.youcode.myrh.models.User;
import ma.youcode.myrh.repositories.IJobSeekerRepository;
import ma.youcode.myrh.repositories.IProfileRepository;
import ma.youcode.myrh.repositories.UserRepository;
import ma.youcode.myrh.services.AuthenticationService;
import ma.youcode.myrh.services.JwtService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final IJobSeekerRepository jobSeekerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;
    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        var user = User.builder().name(request.getName())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole()).build();
        JobSeeker jobSeeker = modelMapper.map(user, JobSeeker.class);
        jobSeekerRepository.save(jobSeeker);

        var jwt = jwtService.generateToken(user);
        System.out.println(jwt);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        var jwt = jwtService.generateToken(user);
        System.out.println(jwt);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
