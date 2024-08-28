/**
 * @Author: SoftwareDoctor andrea_italiano87@yahoo.com
 * @Date: 2024-08-28 08:27:59
 * @LastEditors: SoftwareDoctor andrea_italiano87@yahoo.com
 * @LastEditTime: 2024-08-28 12:07:30
 * @FilePath: src/main/java/it/softwaredoctor/scraping/repository/ListTechRepository.java
 * @Description: 这是默认设置, 可以在设置》工具》File Description中进行配置
 */
package it.softwaredoctor.scraping.repository;
import it.softwaredoctor.scraping.model.ListaTech;
import it.softwaredoctor.scraping.model.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ListTechRepository extends JpaRepository<ListaTech, Long> {
   
}
