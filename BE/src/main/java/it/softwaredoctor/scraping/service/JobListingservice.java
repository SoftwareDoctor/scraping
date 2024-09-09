/**
 * @Author: SoftwareDoctor andrea_italiano87@yahoo.com
 * @Date: 2024-08-27 13:41:44
 * @LastEditors: SoftwareDoctor andrea_italiano87@yahoo.com
 * @LastEditTime: 2024-09-09 12:59:34
 * @FilePath: src/main/java/it/softwaredoctor/scraping/service/JobListingservice.java
 * @Description: 这是默认设置, 可以在设置》工具》File Description中进行配置
 */
package it.softwaredoctor.scraping.service;

import it.softwaredoctor.scraping.dto.JobListingDto;
import it.softwaredoctor.scraping.model.JobLink;
import it.softwaredoctor.scraping.model.JobListing;
import it.softwaredoctor.scraping.model.ListaTech;
import it.softwaredoctor.scraping.model.Technology;
import it.softwaredoctor.scraping.repository.JobLinkRepository;
import it.softwaredoctor.scraping.repository.JobListingRepository;
import it.softwaredoctor.scraping.repository.ListTechRepository;
import it.softwaredoctor.scraping.repository.TechnologyRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class JobListingservice {

    private final JobLinkRepository jobLinkRepository;
    private final JobListingRepository jobListingRepository;
    private final TechnologyRepository technologyRepository;
    private final ListTechRepository listTechRepository; 

    public List<JobListingDto> getAllJobListings (){
        List<JobListing> jobListings = jobListingRepository.findAll();

        return jobListings.stream()
                .map(JobListingDto::toDto)
                .toList();
    }

    public JobListingDto getJobListing(UUID uuidJobListing) {
        Optional<JobListing> jobListing = jobListingRepository.findByUuid(uuidJobListing);
        jobListing.get().getTechnologies().sort(Comparator.comparing(Technology::getName));
        return jobListing
                .map(JobListingDto::toDto)
                .orElseThrow(() -> new RuntimeException("JobListing not found"));
    }

//    public void updateJobListing(UUID id, JobListingDto jobListing) throws IOException {
//        JobListing existingJobListing = jobListingRepository.findByUuid(id)
//                .orElseThrow(() -> new EntityNotFoundException("JobListing not found"));
//
//        existingJobListing.setTitle(jobListing.getTitle());
//
//        List<Technology> currentTechnologies = new ArrayList<>(existingJobListing.getTechnologies());
//        existingJobListing.getTechnologies().clear();
//
//        if (jobListing.getTechnologies() != null) {
//            for (String technologyName : jobListing.getTechnologies()) {
//                Technology technology = technologyRepository.findByName(technologyName)
//                        .orElseThrow(() -> new EntityNotFoundException("Technology not found: " + technologyName));
//                existingJobListing.getTechnologies().add(technology);
//                technology.setJobListing(existingJobListing);
//            }
//        }
//
//        if (jobListing.getJobLink() != null) {
//            JobLink jobLink = jobLinkRepository.findByStringaLink(jobListing.getJobLink())
//                    .orElseThrow(() -> new EntityNotFoundException("JobLink not found: " + jobListing.getJobLink()));
//            existingJobListing.setJobLink(jobLink);
//        }
//        jobListingRepository.save(existingJobListing);
//    }

    public void updateJobListing(UUID id, JobListingDto jobListing) throws IOException {
        JobListing existingJobListing = jobListingRepository.findByUuid(id)
                .orElseThrow(() -> new EntityNotFoundException("JobListing not found"));

        existingJobListing.setTitle(jobListing.getTitle());

        // rimuovere tutte le tecnologie esistenti
        List<Technology> currentTechnologies = new ArrayList<>(existingJobListing.getTechnologies());
        existingJobListing.getTechnologies().clear();

        // Mappare per tenere traccia delle tecnologie già esistenti nel database
        Map<String, Technology> technologyMap = new HashMap<>();
        for (Technology tech : currentTechnologies) {
            technologyMap.put(tech.getName(), tech);
        }

        // Aggiungere le nuove tecnologie basate sui nomi forniti
        for (String technologyName : jobListing.getTechnologies()) {
            // Controllare se la tecnologia già esiste nel mapping
            Technology technology = technologyMap.get(technologyName);

            if (technology == null) {
                // La tecnologia non esiste, quindi recuperare la lista delle tecnologie disponibili
                ListaTech listaTech = listTechRepository.findBynameTechnology(technologyName)
                        .orElseThrow(() -> new EntityNotFoundException("Technology not found: " + technologyName));

                // Creare un nuovo oggetto Technology
                technology = new Technology();
                technology.setName(technologyName);
                technology.setJobListing(existingJobListing);

                // Aggiungere l'oggetto Technology alla lista
                technologyMap.put(technologyName, technology);
            } else {
                // Associare la tecnologia esistente al JobListing
                technology.setJobListing(existingJobListing);
            }
            // Aggiungere la tecnologia alla lista di tecnologie del JobListing solo se non esiste già
            if (!existingJobListing.getTechnologies().contains(technology)) {
                existingJobListing.getTechnologies().add(technology);
            }
        }

        // Salva il JobListing aggiornato
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

