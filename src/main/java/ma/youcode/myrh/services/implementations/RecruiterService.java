package ma.youcode.myrh.services.implementations;

import ma.youcode.myrh.dao.response.JwtAuthenticationResponse;
import ma.youcode.myrh.dtos.RecruiterDTO;
import ma.youcode.myrh.dtos.ResponseMessage;
import ma.youcode.myrh.dtos.StatisticsDTO;
import ma.youcode.myrh.models.*;
import ma.youcode.myrh.repositories.*;
import ma.youcode.myrh.services.FilesStorageService;
import ma.youcode.myrh.services.IRecruiterService;
import ma.youcode.myrh.services.JwtService;
import ma.youcode.myrh.utils.EmailService;
import ma.youcode.myrh.utils.ValidationCodeGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.Option;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class RecruiterService implements IRecruiterService {

    @Autowired
    FilesStorageService storageService;
    @Autowired
    IJobOfferRepository jobOfferRepository;
    @Autowired
    IResumeRepository resumeRepository;

    @Autowired
    JwtService jwtService;

    @Autowired
    IRecruiterRepository recruiterRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @Autowired
    private IJobSeekerRepository jobSeekerRepository;
    private static final int VALIDATION_CODE_EXPIRATION_MINUTES = 3;

    @Override
    public JwtAuthenticationResponse save(RecruiterDTO recruiterDTO) {
        Optional<JobSeeker> jobSeeker = jobSeekerRepository.findById(recruiterDTO.getId());
        if (jobSeeker.isPresent()) {
            Recruiter recruiter = modelMapper.map(jobSeeker.get(), Recruiter.class);

            String code = ValidationCodeGenerator.generateValidationCode();
            recruiter.setCodeValidation(code);

            MultipartFile imageFile = recruiterDTO.getImage();
            try {
                storageService.save(imageFile);
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
            recruiter.setImage(imageFile.getOriginalFilename());

            userRepository.delete(jobSeeker.get());
            Recruiter savedRecruiter = recruiterRepository.saveAndFlush(recruiter);
            recruiterDTO = modelMapper.map(savedRecruiter, RecruiterDTO.class);

            sendValidationCodeByEmail(recruiter.getEmail(), code);


            recruiterDTO.setImageUrl(recruiter.getImage());

            Optional<User> user = userRepository.findById(savedRecruiter.getId().intValue());
            var jwt = jwtService.generateToken(user.get());

            return JwtAuthenticationResponse.builder().token(jwt).build();
        }


        return null;
    }

    @Override
    public RecruiterDTO findByEmail(String email) {
        Recruiter recruiter = recruiterRepository.findByEmail(email);
        return modelMapper.map(recruiter, RecruiterDTO.class);
    }

    @Override
    public Page<RecruiterDTO> findAll(Pageable pageable) {
        Page<Recruiter> recruiters = recruiterRepository.findAll(pageable);
        return recruiters.map(recruiter -> modelMapper.map(recruiter, RecruiterDTO.class));
    }

    @Override
    public StatisticsDTO statistics(long id) {
        StatisticsDTO statisticsDTO = new StatisticsDTO();
        List<JobOffer> jobOffers = jobOfferRepository.findByRecruiter(recruiterRepository.findRecruiterById(id));
        int pending = 0, accepted = 0, refused = 0;
        for (JobOffer jobOffer : jobOffers
        ) {
            if (jobOffer.getStatus() == Status.Pending) {
                pending++;
            } else if (jobOffer.getStatus() == Status.Accepted) {
                accepted++;
            }else {
                refused++;
            }
        }
        statisticsDTO.setPendingJobOffersCount(pending);
        statisticsDTO.setAcceptedJobOffersCount(accepted);
        statisticsDTO.setRefusedJobOffersCount(refused);

        statisticsDTO.setResumesCount(resumeRepository.findAllByJobOffer_Recruiter_Id(id).size());

        return statisticsDTO;
    }

    private void sendValidationCodeByEmail(String email, String code) {
        emailService.sendSimpleMessage(email, "Recruiter validation code", code);
    }

    public boolean validateAccount(long recruiterId, String code) {
        Optional<Recruiter> optionalRecruiter = recruiterRepository.findById(recruiterId);

        if (optionalRecruiter.isPresent()) {
            Recruiter recruiter = optionalRecruiter.get();

            // Check if the validation code is still valid
            LocalDateTime expirationTime = recruiter.getCodeValidationTimestamp().plusMinutes(VALIDATION_CODE_EXPIRATION_MINUTES);
            if (LocalDateTime.now().isBefore(expirationTime) && code.equals(recruiter.getCodeValidation())) {
                // Mark the account as validated
                recruiter.setIsValid(true);
                recruiterRepository.save(recruiter);

                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public String resendValidationCode(long recruiterId) {
        Optional<Recruiter> optionalRecruiter = recruiterRepository.findById(recruiterId);

        if (optionalRecruiter.isPresent()) {
            Recruiter recruiter = optionalRecruiter.get();

            // Check if the previous validation code has expired
            LocalDateTime expirationTime = recruiter.getCodeValidationTimestamp().plusMinutes(VALIDATION_CODE_EXPIRATION_MINUTES);

            if (LocalDateTime.now().isAfter(expirationTime)) {
                // Generate a new validation code
                String newCodeValidation = ValidationCodeGenerator.generateValidationCode();

                // Send the new validation code by email/SMS
                sendValidationCodeByEmail(recruiter.getEmail(), newCodeValidation);

                // Save the new validation code and timestamp in the database
                recruiter.setCodeValidation(newCodeValidation);
                recruiter.setCodeValidationTimestamp(LocalDateTime.now());
                recruiterRepository.save(recruiter);

                return "Nouveau code de validation envoyé avec succès.";
            } else {
                return "Impossible de renvoyer le code de validation avant l'expiration du code actuel.";
            }
        } else {
            return "Aucun utilisateur trouvé avec l'adresse e-mail fournie.";
        }
    }


}