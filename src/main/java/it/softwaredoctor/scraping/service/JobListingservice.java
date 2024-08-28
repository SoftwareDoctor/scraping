/**
 * @Author: SoftwareDoctor andrea_italiano87@yahoo.com
 * @Date: 2024-08-27 13:41:44
 * @LastEditors: SoftwareDoctor andrea_italiano87@yahoo.com
 * @LastEditTime: 2024-08-27 13:42:48
 * @FilePath: src/main/java/it/softwaredoctor/scraping/service/JobListingservice.java
 * @Description: 这是默认设置, 可以在设置》工具》File Description中进行配置
 */
package it.softwaredoctor.scraping.service;

import it.softwaredoctor.scraping.dto.JobListingDto;
import it.softwaredoctor.scraping.model.JobListing;
import it.softwaredoctor.scraping.repository.JobListingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class JobListingservice {

    private final JobListingRepository jobListingRepository;

    public List<JobListingDto> getAllJobListings (){
        List<JobListing> jobListings = jobListingRepository.findAll();

        return jobListings.stream()
                .map(JobListingDto::toDto)
                .toList();
    }

    public JobListingDto getJobListing(UUID uuidJobListing) {
        Optional<JobListing> jobListing = jobListingRepository.findByUuid(uuidJobListing);
        return jobListing
                .map(JobListingDto::toDto)
                .orElseThrow(() -> new RuntimeException("JobListing not found"));
    }
}

