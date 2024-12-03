package com.example.JobMs.Job;


import com.example.JobMs.Configuration.RestTemplateService;
import com.example.JobMs.DTO.Company;
import com.example.JobMs.DTO.JobDto;
import com.example.JobMs.DTO.Review;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImp implements JobService{


    JobRepository jobRepository;

    RestTemplateService restTemplateService;

    public JobServiceImp(JobRepository jobRepository,RestTemplateService restTemplateService) {
        this.jobRepository = jobRepository;
        this.restTemplateService=restTemplateService;
    }


    @Override
    public List<JobDto> findAll() {

        List<Job> jobs = jobRepository.findAll();
        List<JobDto> jobDtos = new ArrayList<>();

        return jobs.stream().map(this::convertJobDto).collect(Collectors.toList());
    }

    @Override
    public Boolean createJob(Job job) {
        Company company;
        try {
            company = restTemplateService.restTemplate().getForObject("http://localhost:8082/companies/" + job.getCompanyId(), Company.class);
        } catch (Exception e) {
            return false;
        }

        if (job.getCompanyId() != null && company != null) {
            jobRepository.save(job);
            return true;
        } else {
            return false;
        }

    }

    @Override
    public JobDto findJobById(Long id) {

        Job job = jobRepository.findById(id).orElse(null);
        if(job != null)
        {
            return convertJobDto(job);
        }

        return null;
    }

    @Override
    public Boolean removeJobById(Long id) {
       if(jobRepository.existsById(id)){
           jobRepository.deleteById(id);
           return true;
       }
       else{
           return false;
       }
    }

    @Override
    public Boolean updateJobById(Long id, Job job) {
        Optional<Job> optionalJob = jobRepository.findById(id);

            if(optionalJob.isPresent())
            {
                Job jobToUpdate = optionalJob.get();
                jobToUpdate.setDescription(job.getDescription());
                jobToUpdate.setTitle(job.getTitle());
                jobToUpdate.setMinSalary(job.getMinSalary());
                jobToUpdate.setMaxSalary(job.getMaxSalary());
                jobToUpdate.setLocation(job.getLocation());
                jobRepository.save(jobToUpdate);
                return true;
            }
        return false;
    }

    private JobDto convertJobDto(Job job)
    {


        Company company = restTemplateService.restTemplate().getForObject("http://localhost:8082/companies/"+job.getCompanyId(), Company.class);

        List<Review> reviews = restTemplateService.restTemplate().exchange("http://localhost:8083/reviews?companyId="+job.getCompanyId(), HttpMethod.GET,null,new ParameterizedTypeReference<List<Review>>(){}).getBody();

         if(reviews !=null && company !=null) {
             company.setReviews(reviews);
         }

        return new JobDto(job.getId(), job.getTitle(),job.getDescription(),job.getMinSalary(),job.getMaxSalary(),job.getLocation(),company);
    }
}
