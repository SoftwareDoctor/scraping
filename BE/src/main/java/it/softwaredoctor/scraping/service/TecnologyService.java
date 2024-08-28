/**
 * @Author: SoftwareDoctor andrea_italiano87@yahoo.com
 * @Date: 2024-08-28 12:07:44
 * @LastEditors: SoftwareDoctor andrea_italiano87@yahoo.com
 * @LastEditTime: 2024-08-28 12:08:26
 * @FilePath: src/main/java/it/softwaredoctor/scraping/service/TecnologyService.java
 * @Description: 这是默认设置, 可以在设置》工具》File Description中进行配置
 */
package it.softwaredoctor.scraping.service;

import it.softwaredoctor.scraping.repository.TechnologyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TecnologyService {

    private final TechnologyRepository technologyRepository;

    public long getTechnologyCount(String technologyName) {
        return technologyRepository.countByName(technologyName);
    }
}