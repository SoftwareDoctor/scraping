/**
 * @Author: SoftwareDoctor andrea_italiano87@yahoo.com
 * @Date: 2024-08-27 13:40:50
 * @LastEditors: SoftwareDoctor andrea_italiano87@yahoo.com
 * @LastEditTime: 2024-08-28 12:03:02
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
import java.io.IOException;
import java.util.List;
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
            JobLink joblink = JobLink.builder()
                    .stringaLink(jobLinkDto.getStringaLink())
                    .build();

            String title = jobListingScrapingService.extractTitleFromUrl(joblink.getStringaLink());

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
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error saving job link", e);
        }
    }

}

