/**
 * @Author: SoftwareDoctor andrea_italiano87@yahoo.com
 * @Date: 2024-08-27 13:35:39
 * @LastEditors: SoftwareDoctor andrea_italiano87@yahoo.com
 * @LastEditTime: 2024-08-28 12:07:30
 * @FilePath: src/main/java/it/softwaredoctor/scraping/controller/JobListingController.java
 * @Description: 这是默认设置, 可以在设置》工具》File Description中进行配置
 */
package it.softwaredoctor.scraping.controller;

import it.softwaredoctor.scraping.dto.JobLinkDto;
import it.softwaredoctor.scraping.dto.JobListingDto;
import it.softwaredoctor.scraping.service.JobLinkService;
import it.softwaredoctor.scraping.service.JobListingservice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/spy")
@RequiredArgsConstructor
public class JobListingController {

    private final JobLinkService jobLinkService;
    private final JobListingservice jobListingservice;

    @PostMapping("/new")
    public ResponseEntity<Void> createJobLink(@RequestBody JobLinkDto jobLink) {
        try {
            UUID uuidJobLink = jobLinkService.saveLink(jobLink);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{uuid}")
                    .buildAndExpand(uuidJobLink)
                    .toUri();
            return ResponseEntity.created(location).build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/")
    public ResponseEntity<List<JobListingDto>> getAllJobListings() {
        List<JobListingDto> jobListings = jobListingservice.getAllJobListings();
        return ResponseEntity.ok(jobListings);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<JobListingDto> getJobListing(UUID uuid) {
        JobListingDto jobListingDto = jobListingservice.getJobListing(uuid);
        return ResponseEntity.ok(jobListingDto);
    }

}

