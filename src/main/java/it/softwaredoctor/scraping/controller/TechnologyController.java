/**
 * @Author: SoftwareDoctor andrea_italiano87@yahoo.com
 * @Date: 2024-08-28 12:08:22
 * @LastEditors: SoftwareDoctor andrea_italiano87@yahoo.com
 * @LastEditTime: 2024-08-28 12:10:40
 * @FilePath: src/main/java/it/softwaredoctor/scraping/controller/TechnologyController.java
 * @Description: 这是默认设置, 可以在设置》工具》File Description中进行配置
 */
package it.softwaredoctor.scraping.controller;

import it.softwaredoctor.scraping.service.TecnologyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/technologies")
@RequiredArgsConstructor
public class TechnologyController {
    
    private final TecnologyService technologyService;
    
    @GetMapping("/count")
    public long getTechnologyCount(@RequestParam String name) {
        return technologyService.getTechnologyCount(name);
    }
    
}
