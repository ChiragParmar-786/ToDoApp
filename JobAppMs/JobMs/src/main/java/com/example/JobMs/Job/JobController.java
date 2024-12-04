package com.example.JobMs.Job;

import com.example.JobMs.DTO.JobDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {


    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<JobDto>> getJobs()
    {
        return new ResponseEntity<>(jobService.findAll(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job)
    {
        Boolean sts = jobService.createJob(job);
        if(sts)
           return new ResponseEntity<>("Job added successfully",HttpStatus.OK);
        else
           return new ResponseEntity<>("Company not found for given Id",HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobDto> getJobById(@PathVariable Long id){
       JobDto jobDto = jobService.findJobById(id);
       if(jobDto!=null)
         return new ResponseEntity<>(jobDto, HttpStatus.OK);
       else
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id){
        Boolean sts = jobService.removeJobById(id);
        if(sts)
            return new ResponseEntity<>("Job deleted Successfully",HttpStatus.OK);
        else
            return new ResponseEntity<>("Job not found for given Id",HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id,@RequestBody Job job){
        Boolean sts = jobService.updateJobById(id,job);
        if(sts)
            return new ResponseEntity<>("Job Updated Successfully",HttpStatus.OK);
        else
            return new ResponseEntity<>("Job Not Found",HttpStatus.NOT_FOUND);
    }
}
