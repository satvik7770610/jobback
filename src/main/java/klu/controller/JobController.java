package klu.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import klu.modal.Job;
import klu.modal.JobManager;

@RestController
@RequestMapping("/job")
@CrossOrigin(origins = "http://localhost:5174")
public class JobController {

    @Autowired
    JobManager jm;

    @GetMapping("/jobs")
    public List<Job> getJobs() {
        return jm.getJobs();
    }

    @PostMapping("/post-job")
    public String postJob(@RequestBody Job j) {
        return jm.addJob(j);
    }
}