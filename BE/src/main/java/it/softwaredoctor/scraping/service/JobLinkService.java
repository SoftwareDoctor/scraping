/**
 * @Author: SoftwareDoctor andrea_italiano87@yahoo.com
 * @Date: 2024-08-27 13:40:50
 * @LastEditors: SoftwareDoctor andrea_italiano87@yahoo.com
 * @LastEditTime: 2024-09-06 10:18:37
 * @FilePath: src/main/java/it/softwaredoctor/scraping/service/JobLinkService.java
 * @Description: 这是默认设置, 可以在设置》工具》File Description中进行配置
 */
package it.softwaredoctor.scraping.service;

import it.softwaredoctor.scraping.dto.JobLinkDto;
import it.softwaredoctor.scraping.model.JobLink;
import it.softwaredoctor.scraping.model.JobListing;
import it.softwaredoctor.scraping.model.Technology;
import it.softwaredoctor.scraping.repository.JobLinkRepository;
import it.softwaredoctor.scraping.repository.JobListingRepository;
import it.softwaredoctor.scraping.repository.TechnologyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class JobLinkService {

    private final JobListingScrapingService jobListingScrapingService;
    private final JobLinkRepository jobLinkRepository;
    private final JobListingRepository jobListingRepository;
    private final TechnologyRepository technologyRepository;



    @Transactional
    public UUID saveLink(JobLinkDto jobLinkDto) throws IOException {
        try {
            Optional<JobLink> existingJobLinkOpt = jobLinkRepository.findByStringaLink(jobLinkDto.getStringaLink());
            if (existingJobLinkOpt.isPresent()) {
                throw new RuntimeException("JobLink already exists with the provided URL");
            }
            JobLink joblink = JobLink.builder()
                    .stringaLink(jobLinkDto.getStringaLink())
                    .build();

            String titleNew = jobListingScrapingService.extractTitleFromUrl(jobLinkDto.getStringaLink());
            JobListing jobListing = JobListing.builder()
                    .jobLink(joblink)
                    .title(titleNew)
                    .build();

            List<String> extractedTechnologies = jobListingScrapingService.extractTechnologiesFromUrl(joblink.getStringaLink());

            List<Technology> technologiesToSave = extractedTechnologies.stream()
                    .distinct()
                    .map(techName -> {
                        Technology technology = Technology.builder()
                                .name(techName)
                                .jobListing(jobListing)
                                .build();
                        return technology;
                    })
                    .collect(Collectors.toList());
            technologyRepository.saveAll(technologiesToSave);
            jobListing.setTechnologies(technologiesToSave);
            jobLinkRepository.save(joblink);
            jobListingRepository.save(jobListing);
            return joblink.getUuid();
        } catch (IOException e) {
            System.out.println("error: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error saving job link", e);
        }
    }


}

