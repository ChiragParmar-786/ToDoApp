package com.example.JobMs.Mapper;

import com.example.JobMs.DTO.Company;
import com.example.JobMs.DTO.JobDto;
import com.example.JobMs.DTO.Review;
import com.example.JobMs.Job.Job;

import java.util.List;

public class JobMapper {

    public static JobDto getJobDto(Job job, Company company, List<Review> reviews){

        JobDto jobDto = new JobDto();
        jobDto.setId(job.getId());
        jobDto.setTitle(job.getTitle());
        jobDto.setDescription(job.getDescription());
        jobDto.setMinSalary(job.getMinSalary());
        jobDto.setMaxSalary(job.getMaxSalary());
        jobDto.setLocation(job.getLocation());
        company.setReviews(reviews);
        jobDto.setCompany(company);

        return  jobDto;

    }
}
