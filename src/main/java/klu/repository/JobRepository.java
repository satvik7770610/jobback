package klu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import klu.modal.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {
}