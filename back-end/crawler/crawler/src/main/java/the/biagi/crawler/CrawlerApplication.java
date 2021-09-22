package the.biagi.crawler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import the.biagi.crawler.service.FilmeScraping;

import java.io.IOException;

@SpringBootApplication
public class CrawlerApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(CrawlerApplication.class, args);
	}

}
