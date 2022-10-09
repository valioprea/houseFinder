package homeFinder.homeFinder.service;

import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@AllArgsConstructor
public class FinderService {

    private static final String URL = "https://www.olx.ro/d/imobiliare/case-de-vanzare/";

    private final ChromeDriver driver;

    @PostConstruct
    void postConstruct() {
        scrape("fagaras");
    }

    public void scrape(final String value) {

        driver.get(URL + value);

        List<WebElement> elements = driver.findElements(By.cssSelector("*[data-cy=\"l-card\"]"));

        for (WebElement linkTag : elements) {

            System.out.println(" *** BEGIN *** ");

            WebElement price = linkTag.findElement(By.cssSelector("*[data-testid=\"ad-price\"]"));
//            System.out.println("PRICE: " + price.getAttribute("innerHTML") + "XXX" + price.getAttribute("innerHTML").length());

            String initialPriceString = price.getAttribute("innerHTML");
            int lengthOfPriceString = initialPriceString.length();
            char currency = '€';
            String priceString = "";
            for (int i = 0; i < lengthOfPriceString; i++) {
                char currentChar = initialPriceString.charAt(i);
                if( currentChar != currency ) {
                    priceString = priceString + currentChar;
                } else {
                    break;
                }
            }

            int finalPrice = Integer.parseInt(priceString.replaceAll("\\s+",""));

            if(finalPrice<70000){
                // LOGIC FOR SCRAPING & CLICKING
                System.out.println("Casa poate fi a mea");
                linkTag.click();
            } else {
                System.out.println("E prea scumpa ");
            }

            System.out.println(" *** END *** ");
//            accessAdvert();
        }

        System.out.println("VALI ATATEA ANUNTURI SUNT: " + elements.size());
//        final WebElement words = driver.findElement(By.className("words"));
//        final List<WebElement> wordList = words.findElements(By.tagName("a"));
//        wordList.forEach(word -> System.out.println(word.getText()));

        driver.quit();
    }

}