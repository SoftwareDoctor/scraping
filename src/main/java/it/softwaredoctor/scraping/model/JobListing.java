/**
 * @Author: SoftwareDoctor andrea_italiano87@yahoo.com
 * @Date: 2024-08-27 13:37:10
 * @LastEditors: SoftwareDoctor andrea_italiano87@yahoo.com
 * @LastEditTime: 2024-08-28 12:03:02
 * @FilePath: src/main/java/it/softwaredoctor/scraping/model/JobListing.java
 * @Description: 这是默认设置, 可以在设置》工具》File Description中进行配置
 */
package it.softwaredoctor.scraping.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "joblisting")
public class JobListing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="uuid")
    private UUID uuid;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "job_link_id", referencedColumnName = "id")
    private JobLink jobLink;

    @Column(name="title")
    private String title;

    @OneToMany(mappedBy = "jobListing", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Technology> technologies = new ArrayList<>();

    @PrePersist
    public void generateUUID() {
        this.uuid = UUID.randomUUID();
    }
}


