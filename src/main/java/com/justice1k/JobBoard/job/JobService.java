package com.justice1k.JobBoard.job;

import java.util.List;
import java.util.Optional;

public interface JobService {

    List<Job> findAll();

    void createJob(Job job);

    Job getJob(Long id);

    boolean deleteJob(Long id);

    boolean update(Long id,Job job);

}
