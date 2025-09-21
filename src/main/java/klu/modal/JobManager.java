package klu.modal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import klu.repository.JobRepository;

@Service
public class JobManager {

    @Autowired
    JobRepository JR;

    public String addJob(Job job) {
        JR.save(job);
        return "Job posted successfully!";
    }

    public List<Job> getJobs() {
        return JR.findAll();
    }
}