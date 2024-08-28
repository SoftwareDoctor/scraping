/**
 * @Author: SoftwareDoctor andrea_italiano87@yahoo.com
 * @Date: 2024-08-27 13:36:28
 * @LastEditors: SoftwareDoctor andrea_italiano87@yahoo.com
 * @LastEditTime: 2024-08-27 13:44:02
 * @FilePath: src/main/java/it/softwaredoctor/scraping/dto/JobListingDto.java
 * @Description: 这是默认设置, 可以在设置》工具》File Description中进行配置
 */
package it.softwaredoctor.scraping.dto;

import it.softwaredoctor.scraping.model.JobListing;
import it.softwaredoctor.scraping.model.Technology;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Builder
@Data
public class JobListingDto {
    private UUID uuid;
    private String title;
    private List<Technology> technologies;

    public static JobListingDto toDto(JobListing jobListing) {
        JobListingDto jobListingDto = JobListingDto.builder()
                .uuid(jobListing.getUuid())
                .title(jobListing.getTitle())
                .build();

        if (jobListing.getTechnologies() != null) {
            jobListingDto.setTechnologies(jobListing.getTechnologies());
        }

        return jobListingDto;

    }
}


/*
public static JobListingDto toDto(JobListing jobListing) {
        JobListingDto dto = new JobListingDto();
        dto.setUuid(jobListing.getUuid());
        dto.setTitle(jobListing.getTitle());

        // Convertire la lista di oggetti Technology in una lista di nomi di tecnologie
        List<String> techNames = jobListing.getTechnologies().stream()
                                           .map(Technology::getName)
                                           .collect(Collectors.toList());
        dto.setTechnologies(techNames);

        return dto;
    }
 */