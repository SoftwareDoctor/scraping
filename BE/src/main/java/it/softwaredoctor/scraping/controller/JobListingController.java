/**
 * @Author: SoftwareDoctor andrea_italiano87@yahoo.com
 * @Date: 2024-08-27 13:35:39
 * @LastEditors: SoftwareDoctor andrea_italiano87@yahoo.com
 * @LastEditTime: 2024-09-06 10:42:37
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

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/spy")
@RequiredArgsConstructor
public class JobListingController {

    private final JobLinkService jobLinkService;
    private final JobListingservice jobListingservice;
    
    @PostMapping("/new")
    public ResponseEntity<Void> createJobLink(@RequestBody JobLinkDto jobLink) {
        int maxAttempts = 3;
        int attempt = 0;
        while (attempt < maxAttempts) {
            try {
                UUID uuidJobLink = jobLinkService.saveLink(jobLink);
                URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{uuid}")
                        .buildAndExpand(uuidJobLink)
                        .toUri();
                return ResponseEntity.created(location).build();
            } catch (IOException e) {
                if (e.getMessage().contains("429")) {
                    attempt++;
                    if (attempt >= maxAttempts) {
                        System.out.println("Max retries reached. Error: " + e.getMessage());
                        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
                    }
                    try {
                        Thread.sleep(2000); 
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                    }
                } else {
                    System.out.println("error: " + e.getMessage());
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                }
            }
        }
        // Fallback response in case all retries fail
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }
    
    


    @GetMapping("/")
    public ResponseEntity<List<JobListingDto>> getAllJobListings() {
        List<JobListingDto> jobListings = jobListingservice.getAllJobListings();
        return ResponseEntity.ok(jobListings);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<JobListingDto> getJobListing(@PathVariable UUID uuid) {
        JobListingDto jobListingDto = jobListingservice.getJobListing(uuid);
        return ResponseEntity.ok(jobListingDto);
    }

    @PutMapping("/job/{uuid}")
    public ResponseEntity<Void> updateJobListing(@PathVariable UUID uuid, @RequestBody JobListingDto jobListing) {
        try {
            jobListing.setUuid(uuid);
            jobListingservice.updateJobListing(jobListing);
            return ResponseEntity.noContent().build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    
    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteJobListing(@PathVariable UUID uuid) {
        jobListingservice.deleteByUUID(uuid);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/")
    public ResponseEntity<List<JobListingDto>> searchJobListingDto(@RequestParam (value = "title", required = true)  String title) {
        List<JobListingDto> jobListings = jobListingservice.listaJobListings(title);
        return ResponseEntity.ok(jobListings);
    }
    
}

