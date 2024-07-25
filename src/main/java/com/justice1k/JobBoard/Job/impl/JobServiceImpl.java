package com.justice1k.JobBoard.Job.impl;

import com.justice1k.JobBoard.Job.Job;
import com.justice1k.JobBoard.Job.JobRepository;
import com.justice1k.JobBoard.Job.JobService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    JobRepository jobRepository;



    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public Job getJob(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJob(Long id) {

        try {
            jobRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    @Override
    public boolean update(Long id,Job job) {
    Optional<Job> jobOptional = jobRepository.findById(id);

    if (jobOptional.isPresent()){
            Job newJob = jobOptional.get();
            newJob.setTitle(job.getTitle());
            newJob.setDescription(job.getDescription());
            newJob.setMinSalary(job.getMinSalary());
            newJob.setMaxSalary(job.getMaxSalary());
            newJob.setLocation(job.getLocation());
            jobRepository.save(newJob);
            return true;
        }
        return false;
    }


}
