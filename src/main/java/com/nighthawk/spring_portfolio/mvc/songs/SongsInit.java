package com.nighthawk.spring_portfolio.mvc.songs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component // Scans Application for ModelInit Bean, this detects CommandLineRunner
public class SongsInit {
    
    // Inject repositories
    @Autowired SongsJpaRepository repository;
    
    @Bean
    CommandLineRunner run() {  // The run() method will be executed after the application starts
        return args -> {
            // Fail safe data validations

            // starting songs - embed links for frontend
            final String[] songsArray = {
                "https://open.spotify.com/embed/track/5QDLhrAOJJdNAmCTJ8xMyW?utm_source=generator",
                "https://open.spotify.com/embed/track/1mWdTewIgB3gtBM3TOSFhB?utm_source=generator",
                "https://open.spotify.com/embed/track/1utDFK1ARQcuZSwapf3cfb?utm_source=generator",
                "https://open.spotify.com/embed/track/2gYj9lubBorOPIVWsTXugG?utm_source=generator",
                "https://open.spotify.com/embed/track/6wNKKoUQfLPmch7cqSFytV?utm_source=generator",
                "https://open.spotify.com/embed/track/1H3i6WXxrJB7LEoH5iStvb?utm_source=generator",
                "https://open.spotify.com/embed/track/6yGQ86UppYULzTCxAWgwVN?utm_source=generator",
                "https://open.spotify.com/embed/track/0RqzUS7AkBhQDrBxcGFeDv?utm_source=generator"
            };

            // make sure Song database is populated with starting songs
            for (String song : songsArray) {
                List<Songs> test = repository.findBySongIgnoreCase(song);  // JPA lookup
                if (test.size() == 0)
                    repository.save(new Songs(null, song,0,0)); //JPA save
            }
            
        };
    }
}

