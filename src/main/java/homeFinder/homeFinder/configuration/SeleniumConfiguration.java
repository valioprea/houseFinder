package homeFinder.homeFinder.configuration;

import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class SeleniumConfiguration {

    @PostConstruct
    void postConstruct(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\FerendiaIT\\chromeDriver\\chromedriver_win32\\chromedriver.exe");
    }

    @Bean
    public ChromeDriver driver(){
        return new ChromeDriver();
    }
}
