package ma.youcode.myrh.services;

import jakarta.servlet.http.HttpServletRequest;
import ma.youcode.myrh.dtos.JobOfferDTO;
import ma.youcode.myrh.dtos.JobOfferDTO;
import ma.youcode.myrh.dtos.RecruiterDTO;
import ma.youcode.myrh.models.Recruiter;
import ma.youcode.myrh.models.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IJobOfferService {
     JobOfferDTO save(JobOfferDTO jobOfferDTO, long recruiterId);
     List<JobOfferDTO> findByRecruiterId(long id);

     List<JobOfferDTO> findByTitle(String title);
     Page<JobOfferDTO> findAllPageable(Pageable pageable);
     List<JobOfferDTO> findAll();
     List<JobOfferDTO> findAllByUserId(long userId);


     List<JobOfferDTO> findAllByStatus(String status, HttpServletRequest request);

     String updateStatus(long id, Status newStatus);
}
