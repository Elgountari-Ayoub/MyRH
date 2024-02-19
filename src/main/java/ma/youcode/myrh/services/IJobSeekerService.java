package ma.youcode.myrh.services;

import ma.youcode.myrh.dtos.JobSeekerDTO;
import ma.youcode.myrh.dtos.JobSeeker_ProfileDTO;
import ma.youcode.myrh.dtos.ProfileDTO;
import ma.youcode.myrh.dtos.ResumeDTO;
import ma.youcode.myrh.models.ResumeStatus;

import java.util.List;

public interface IJobSeekerService {
     JobSeekerDTO addProfile(JobSeeker_ProfileDTO jobSeeker_profileDTO);
//     ResumeDTO save(ResumeDTO jobOfferDTO, long jobfferId);
//     ResumeDTO updateStatus(long id, ResumeStatus newStatus);
//     List<ResumeDTO> findByJobOffer(long  id);
//     List<ResumeDTO> findAll();
//     List<ResumeDTO> findAllByRecruiterId(long id);
//     List<ResumeDTO> findAllByUserId(long id);
}
