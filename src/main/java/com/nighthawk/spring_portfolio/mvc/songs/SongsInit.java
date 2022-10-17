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

            // starting songs
            final String[] songsArray = {
                "<iframe style='border-radius:12px' src='https://open.spotify.com/embed/track/2qQpFbqqkLOGySgNK8wBXt?utm_source=generator' width='100%' height='352' frameBorder='0' allowfullscreen='0' allow='autoplay; clipboard-write; encrypted-media; fullscreen; picture-in-picture' loading='lazy'></iframe>",
                "<iframe style='border-radius:12px' src='https://open.spotify.com/embed/track/7BY005dacJkbO6EPiOh2wb?utm_source=generator' width='100%' height='352' frameBorder='0' allowfullscreen='0' allow='autoplay; clipboard-write; encrypted-media; fullscreen; picture-in-picture' loading='lazy'></iframe>",
                "<iframe style='border-radius:12px' src='https://open.spotify.com/embed/track/27PTYw4GXEbY08C8pui11I?utm_source=generator' width='100%' height='352' frameBorder='0' allowfullscreen='0' allow='autoplay; clipboard-write; encrypted-media; fullscreen; picture-in-picture' loading='lazy'></iframe>"
                
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

