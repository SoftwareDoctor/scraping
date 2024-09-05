/**
 * @Author: SoftwareDoctor andrea_italiano87@yahoo.com
 * @Date: 2024-08-27 13:41:44
 * @LastEditors: SoftwareDoctor andrea_italiano87@yahoo.com
 * @LastEditTime: 2024-09-05 09:00:22
 * @FilePath: src/main/java/it/softwaredoctor/scraping/service/JobListingservice.java
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
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
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
                .orElseThrow(() -> new EntityNotFoundException("JobListing not found"));

        existingJobListing.setTitle(jobListing.getTitle());

        List<Technology> currentTechnologies = new ArrayList<>(existingJobListing.getTechnologies());
        existingJobListing.getTechnologies().clear();

        if (jobListing.getTechnologies() != null) {
            for (String technologyName : jobListing.getTechnologies()) {
                Technology technology = technologyRepository.findByName(technologyName)
                        .orElseThrow(() -> new EntityNotFoundException("Technology not found: " + technologyName));
                existingJobListing.getTechnologies().add(technology);
                technology.setJobListing(existingJobListing);
            }
        }

        if (jobListing.getJobLink() != null) {
            JobLink jobLink = jobLinkRepository.findByStringaLink(jobListing.getJobLink())
                    .orElseThrow(() -> new EntityNotFoundException("JobLink not found: " + jobListing.getJobLink()));
            existingJobListing.setJobLink(jobLink);
        }
        jobListingRepository.save(existingJobListing);
    }


    @Transactional
    public void deleteByUUID(UUID uuid) {
        jobListingRepository.deleteByUuid(uuid);
    }

    public List<JobListingDto> listaJobListings(String title) {
//        List<JobListing> lista = jobListingRepository.findByTitleContaining(title);
//
//        List<JobListingDto> listaDto = new ArrayList<>();
//        for (JobListing jobListing : lista) {
//            JobListingDto dto = JobListingDto.toDto(jobListing);
//            listaDto.add(dto);
//        }
//        return listaDto;
        
        String lowerCaseTitle = title.toLowerCase();
        List<JobListing> lista = jobListingRepository.findAll();
        List<JobListing> listaFiltrata = lista.stream()
                .filter(jobListing -> jobListing.getTitle().toLowerCase().contains(lowerCaseTitle))
                .collect(Collectors.toList());
        
        List<JobListingDto> listaDto = new ArrayList<>();

//        List<JobListingDto> listaDto = listaFiltrata.stream()
//                .map(JobListingDto::toDto)
//                .collect(Collectors.toList());
        
        for (JobListing jobListing : listaFiltrata) {
            JobListingDto dto = JobListingDto.toDto(jobListing);
            listaDto.add(dto);
        }
        return listaDto;
    }
    

//    public List<JobListingDto> jobListingsByTitle(String title){
//        List<JobListing> jobListings = jobListingRepository.findByTitleContaining(title);
//
//        return jobListings.stream()
//               .map(JobListingDto::toDto)
//               .collect(Collectors.toList());
//    }
}

