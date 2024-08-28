/**
 * @Author: SoftwareDoctor andrea_italiano87@yahoo.com
 * @Date: 2024-08-27 13:36:47
 * @LastEditors: SoftwareDoctor andrea_italiano87@yahoo.com
 * @LastEditTime: 2024-08-27 13:39:48
 * @FilePath: src/main/java/it/softwaredoctor/scraping/model/JobLink.java
 * @Description: 这是默认设置, 可以在设置》工具》File Description中进行配置
 */
package it.softwaredoctor.scraping.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "joblink")
public class JobLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="uuid")
    private UUID uuid;

    @Column(name = "stringaLink")
    private String stringaLink;

    @OneToOne(mappedBy = "jobLink")
    private JobListing jobListing;

    @PrePersist
    public void generateUUID() {
        this.uuid = UUID.randomUUID();
    }
}

