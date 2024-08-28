/**
 * @Author: SoftwareDoctor andrea_italiano87@yahoo.com
 * @Date: 2024-08-28 08:27:18
 * @LastEditors: SoftwareDoctor andrea_italiano87@yahoo.com
 * @LastEditTime: 2024-08-28 12:03:02
 * @FilePath: src/main/java/it/softwaredoctor/scraping/service/ListTechService.java
 * @Description: 这是默认设置, 可以在设置》工具》File Description中进行配置
 */
package it.softwaredoctor.scraping.service;

import it.softwaredoctor.scraping.model.ListaTech;
import it.softwaredoctor.scraping.repository.ListTechRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ListTechService {
    
    private final ListTechRepository listTechRepository;
    
    public void createTechList(List<ListaTech> listTech) {
        Collections.sort(listTech, (t1, t2) -> t1.getNameTechnology().compareToIgnoreCase(t2.getNameTechnology()));
        listTechRepository.saveAll(listTech);
    }

    public List<ListaTech> getAllTechnologies() {
        return listTechRepository.findAll();
    }

    public void updateTechnology(Long id, String nameTecnology) {
        ListaTech technology = listTechRepository.findById(id).orElseThrow(() -> new RuntimeException("Technology not found"));
        technology.setNameTechnology(nameTecnology);
        listTechRepository.save(technology);
    }

    public void deleteTechnology(Long id) {
        listTechRepository.deleteById(id);
    }

    public void addTechnology(ListaTech tecnologia) {
        listTechRepository.save(tecnologia);
    }

    @Scheduled(cron = "0 0 1 * * ?") // Eseguire ogni giorno alle 1:00 AM
    public void reorderTechnologiesBatch() {
        List<ListaTech> allTechnologies = listTechRepository.findAll();
        List<ListaTech> sortedTechnologies = allTechnologies.stream()
                .sorted((t1, t2) -> t1.getNameTechnology().compareToIgnoreCase(t2.getNameTechnology()))
                .collect(Collectors.toList());
        listTechRepository.deleteAll(); 
        listTechRepository.saveAll(sortedTechnologies); 
    }
}
