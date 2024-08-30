/**
 * @Author: SoftwareDoctor andrea_italiano87@yahoo.com
 * @Date: 2024-08-27 13:38:21
 * @LastEditors: SoftwareDoctor andrea_italiano87@yahoo.com
 * @LastEditTime: 2024-08-30 09:40:18
 * @FilePath: BE/src/main/java/it/softwaredoctor/scraping/repository/JobListingRepository.java
 * @Description: 这是默认设置, 可以在设置》工具》File Description中进行配置
 */
package it.softwaredoctor.scraping.repository;

import it.softwaredoctor.scraping.model.JobListing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JobListingRepository extends JpaRepository<JobListing, Long> {
    
    Optional<JobListing> findByUuid(UUID uuid);
    
    void deleteByUuid (UUID uuid);
    
   List<JobListing> findByTitleContaining(String title);

}

