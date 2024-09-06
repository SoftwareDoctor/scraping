/**
 * @Author: SoftwareDoctor andrea_italiano87@yahoo.com
 * @Date: 2024-08-27 13:42:17
 * @LastEditors: SoftwareDoctor andrea_italiano87@yahoo.com
 * @LastEditTime: 2024-09-06 09:51:04
 * @FilePath: src/main/java/it/softwaredoctor/scraping/service/JobListingScrapingService.java
 * @Description: 这是默认设置, 可以在设置》工具》File Description中进行配置
 */
package it.softwaredoctor.scraping.service;

import it.softwaredoctor.scraping.model.ListaTech;
import it.softwaredoctor.scraping.repository.JobListingRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.jsoup.select.Selector.select;


@RequiredArgsConstructor
@Service
public class JobListingScrapingService {

    private final JobListingRepository jobListingRepository;
    private final ListTechService listTechService;
    
    public String extractTitleFromUrl(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements element = doc.getElementsByClass("top-card-layout__title font-sans text-lg papabear:text-xl font-bold leading-open text-color-text mb-0 topcard__title");
        String title = element.text();
        System.out.println(title);
        return title;
    }


    public List<String> extractTechnologiesFromUrl(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        List<String> foundTechnologies = new ArrayList<>();
        Element descriptionDiv = doc.selectFirst("div.description__text.description__text--rich");
        if (descriptionDiv != null) {
            String descriptionText = descriptionDiv.text();
            System.out.println("Description Text: " + descriptionText);
            List<ListaTech> listaTech = listTechService.getAllTechnologies();
            for (ListaTech techName : listaTech) {
                String techNameLower = techName.getNameTechnology().toLowerCase();
                
                if (descriptionText.toLowerCase().contains(techNameLower)) {
                    foundTechnologies.add(techName.getNameTechnology());
                } else {
                    System.out.println("Tecnologia non trovata nel testo: " + techNameLower);
                }
            }
        } else {
            System.out.println("Elemento con la classe specificata non trovato.");
        }

        return foundTechnologies;
    }



//    public List<String> extractTechnologiesFromUrl(String url) {
//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\andrea.italiano\\Downloads\\chromedriver (1).exe");
//        ChromeOptions options = new ChromeOptions();
////        options.addArguments("--headless");  // Rimuovi se vuoi vedere il browser in azione
//        WebDriver driver = new ChromeDriver(options);
//        List<String> technologies = new ArrayList<>();
//        try {
//            driver.get(url);
//            
//            By modalLocator = By.id("ember464");
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
//            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(modalLocator));
//            button.click();
//
//            By modale = By.id("artdeco-modal artdeco-modal--layer-default job-details-skill-match-modal");
//            WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(modale));
//
//            // Trova tutti gli elementi della lista e estrai i testi
//            List<WebElement> skillItems = driver.findElements(By.className("job-details-skill-match-status-list__matched-skill text-body-small"));
//            for (WebElement item : skillItems) {
//                // Trova il div che contiene il nome della tecnologia
//                WebElement skillTextElement = item.findElement(By.cssSelector("div[aria-label]"));
//                if (skillTextElement != null) {
//                    // Ottieni solo il testo visibile all'interno del div
//                    String skillText = skillTextElement.getText().trim();
//                    // Aggiungi il testo al risultato se non è vuoto
//                    if (!skillText.isEmpty()) {
//                        technologies.add(skillText);
//                    }
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            driver.quit();
//        }
//        System.out.println("technologies: " + technologies.toString());
//        return technologies;
//    }

}

