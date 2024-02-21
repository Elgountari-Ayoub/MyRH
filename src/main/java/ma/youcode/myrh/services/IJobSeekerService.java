package ma.youcode.myrh.services;

import ma.youcode.myrh.dtos.*;
import ma.youcode.myrh.models.ResumeStatus;

import java.util.List;

public interface IJobSeekerService {
     JobSeekerDTO addProfile(JobSeeker_ProfileDTO jobSeeker_profileDTO);
     JobSeekerProfilesDTO findById(Long id);
     JobSeekerProfilesDTO findByEmail(String email);
//     ResumeDTO save(ResumeDTO jobOfferDTO, long jobfferId);
//     ResumeDTO updateStatus(long id, ResumeStatus newStatus);
//     List<ResumeDTO> findByJobOffer(long  id);
//     List<ResumeDTO> findAll();
//     List<ResumeDTO> findAllByRecruiterId(long id);
//     List<ResumeDTO> findAllByUserId(long id);
}
