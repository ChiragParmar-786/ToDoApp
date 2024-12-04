package com.example.JobMs.Job;

import com.example.JobMs.DTO.JobDto;

import java.util.List;

public interface JobService {

    List<JobDto> findAll();

    Boolean createJob(Job job);

    JobDto findJobById(Long id);

    Boolean removeJobById(Long id);

    Boolean updateJobById(Long id, Job job);
}
