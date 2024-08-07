package com.justice1k.JobBoard.Job;

import java.util.List;

public interface JobService {

    List<Job> findAll();

    void createJob(Job job);

    Job getJob(Long id);

    boolean deleteJob(Long id);

    boolean update(Long id,Job job);

}
