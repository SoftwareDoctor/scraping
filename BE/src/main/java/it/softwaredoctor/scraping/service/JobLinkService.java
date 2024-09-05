/**
 * @Author: SoftwareDoctor andrea_italiano87@yahoo.com
 * @Date: 2024-08-27 13:40:50
 * @LastEditors: SoftwareDoctor andrea_italiano87@yahoo.com
 * @LastEditTime: 2024-09-05 09:53:34
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

    public String fetchDataFromUrl(String urlString) throws IOException {
        int retryCount = 0;
        int maxRetries = 3;
        long retryDelay = 2000; 

        while (retryCount < maxRetries) {
            try {
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                int responseCode = connection.getResponseCode();

                if (responseCode == 429) {
                    try {
                        Thread.sleep(retryDelay);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        throw new IOException("Interruzione durante l'attesa per retry", e);
                    }
                    retryCount++;
                    retryDelay *= 2; 
                } else if (responseCode == 200) {
                    try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                        return in.lines().collect(Collectors.joining("\n"));
                    }
                } else {
                    throw new IOException("Errore HTTP: " + responseCode);
                }
            } catch (IOException e) {
                if (retryCount == maxRetries - 1) {
                    throw e; 
                }
            }
        }

        throw new IOException("Numero massimo di retry superato");
    }

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
            
            String title = fetchDataFromUrl(joblink.getStringaLink());

            JobListing jobListing = JobListing.builder()
                    .jobLink(joblink)
                    .title(title)
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

