/**
 * @Author: SoftwareDoctor andrea_italiano87@yahoo.com
 * @Date: 2024-08-27 13:36:28
 * @LastEditors: SoftwareDoctor andrea_italiano87@yahoo.com
 * @LastEditTime: 2024-08-29 12:06:01
 * @FilePath: BE/src/main/java/it/softwaredoctor/scraping/dto/JobListingDto.java
 * @Description: 这是默认设置, 可以在设置》工具》File Description中进行配置
 */
package it.softwaredoctor.scraping.dto;

import it.softwaredoctor.scraping.model.JobListing;
import it.softwaredoctor.scraping.model.Technology;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
@Data
public class JobListingDto {
    private UUID uuid;
    private String title;
    private String jobLink;
    private List<String> technologies;

    public static JobListingDto toDto(JobListing jobListing) {
        JobListingDto jobListingDto = JobListingDto.builder()
                .uuid(jobListing.getUuid())
                .title(jobListing.getTitle())
                .jobLink(jobListing.getJobLink().getStringaLink()) 
                .build();

        if (jobListing.getTechnologies() != null) {
            List <String> techNames = new ArrayList<String>();
            for(Technology tech: jobListing.getTechnologies() ) {
                techNames.add(tech.getName());
            }
            jobListingDto.setTechnologies(techNames);
        }

        return jobListingDto;

    }
    
    
}

/*
public static JobListingDto toDto(JobListing jobListing) {
    // Costruisci il JobListingDto con i campi principali
    JobListingDto jobListingDto = JobListingDto.builder()
            .uuid(jobListing.getUuid())
            .title(jobListing.getTitle())
            .jobLink(jobListing.getJobLink().getStringaLink())
            .build();

    // Verifica se ci sono tecnologie da aggiungere e usa lo stream per la conversione
    if (jobListing.getTechnologies() != null) {
        List<String> techNames = jobListing.getTechnologies().stream()
                                           .map(Technology::getName)
                                           .collect(Collectors.toList());
        jobListingDto.setTechnologies(techNames);
    }

    // Restituisce il DTO
    return jobListingDto;
}
 */

