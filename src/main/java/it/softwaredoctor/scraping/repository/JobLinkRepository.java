/**
 * @Author: SoftwareDoctor andrea_italiano87@yahoo.com
 * @Date: 2024-08-27 13:38:00
 * @LastEditors: SoftwareDoctor andrea_italiano87@yahoo.com
 * @LastEditTime: 2024-08-28 12:07:30
 * @FilePath: src/main/java/it/softwaredoctor/scraping/repository/JobLinkRepository.java
 * @Description: 这是默认设置, 可以在设置》工具》File Description中进行配置
 */
package it.softwaredoctor.scraping.repository;

import it.softwaredoctor.scraping.model.JobLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobLinkRepository extends JpaRepository<JobLink, Long> {
}
