/**
 * @Author: SoftwareDoctor andrea_italiano87@yahoo.com
 * @Date: 2024-08-27 13:41:44
 * @LastEditors: SoftwareDoctor andrea_italiano87@yahoo.com
 * @LastEditTime: 2024-08-29 12:34:05
 * @FilePath: BE/src/main/java/it/softwaredoctor/scraping/service/JobListingservice.java
 * @Description: 这是默认设置, 可以在设置》工具》File Description中进行配置
 */
package it.softwaredoctor.scraping.service;

import it.softwaredoctor.scraping.dto.JobListingDto;
import it.softwaredoctor.scraping.model.JobLink;
import it.softwaredoctor.scraping.model.JobListing;
import it.softwaredoctor.scraping.model.Technology;
import it.softwaredoctor.scraping.repository.JobLinkRepository;
import it.softwaredoctor.scraping.repository.JobListingRepository;
import it.softwaredoctor.scraping.repository.TechnologyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class JobListingservice {

    private final JobLinkRepository jobLinkRepository;
    private final JobListingRepository jobListingRepository;
    private final TechnologyRepository technologyRepository;

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
    
    public void updateJobListing(JobListingDto jobListing) throws IOException {
        JobListing existingJobListing = jobListingRepository.findByUuid(jobListing.getUuid())
               .orElseThrow(() -> new RuntimeException("JobListing not found"));
        existingJobListing.setTitle(jobListing.getTitle());
        if(jobListing.getTechnologies() != null) {
            List<Technology> updatedTechnologies = jobListing.getTechnologies().stream()
         .map(technologyName -> technologyRepository.findByName(technologyName)
                 .orElseThrow(() -> new RuntimeException("Technology not found: " + technologyName)))
                    .collect(Collectors.toList());
            existingJobListing.setTechnologies(updatedTechnologies);
        }
        if (jobListing.getJobLink() != null) {
            JobLink jobLink = jobLinkRepository.findByStringaLink(jobListing.getJobLink())
                    .orElseThrow(() -> new RuntimeException("JobLink not found: " + jobListing.getJobLink()));
            existingJobListing.setJobLink(jobLink);
        }
        
        jobListingRepository.save(existingJobListing);
    }
    
    public void deleteByUUID(UUID uuidJobListing) {
        jobListingRepository.deleteByUUIDJobListing(uuidJobListing);
    }
    
    public List<JobListingDto> listaJobListings (String title){
        List<JobListing> lista = jobListingRepository.findByTitleContaining(title);

        List<JobListingDto> list = new ArrayList<>();
        
        for (JobListing jobListing : lista) {
            JobListingDto dto = JobListingDto.toDto(jobListing);
            list.add(dto);
        }
        return list; 
    }
}

