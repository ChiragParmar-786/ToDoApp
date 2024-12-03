package com.example.JobMs.Job;

import java.util.List;

public interface JobService {

    List<Job> findAll();

    Boolean createJob(Job job);

    Job findJobById(Long id);

    Boolean removeJobById(Long id);

    Boolean updateJobById(Long id, Job job);
}
