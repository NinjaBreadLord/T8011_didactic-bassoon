package com.nighthawk.spring_portfolio.mvc.songs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.*;

@RestController // annotation to simplify the creation of RESTful web services
@RequestMapping("/api/songs")
public class SongsApiController {

    // Autowired enables Control to connect HTML and POJO Object to database easily for CRUD operations
    @Autowired
    private SongsJpaRepository repository;

    /*
    GET List of Songs
     */
    @GetMapping("/all")
    public ResponseEntity<List<Songs>> getSongs() {
        return new ResponseEntity<>( repository.findAllByOrderBySongAsc(), HttpStatus.OK);
    }

    @PutMapping("/like/{id}")
    public ResponseEntity<Songs> setLike(@PathVariable long id) {
        /* 
        * Optional (below) is a container object which helps determine if a result is present. 
        * If a value is present, isPresent() will return true
        * get() will return the value.
        */
        Optional<Songs> optional = repository.findById(id);
        if (optional.isPresent()) {  // Good ID
            Songs song = optional.get();  // value from findByID
            song.setHaha(song.getHaha()+1); // increment value
            repository.save(song);  // save entity
            return new ResponseEntity<>(song, HttpStatus.OK);  // OK HTTP response: status code, headers, and body
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);  // Failed HTTP response: status code, headers, and body
    }

    // Updating boohoo
    @PutMapping("/jeer/{id}")
    public ResponseEntity<Songs> setJeer(@PathVariable long id) {
        Optional<Songs> optional = repository.findById(id);
        if (optional.isPresent()) {  // Good ID
            Songs song = optional.get();
            song.setBoohoo(song.getBoohoo()+1);
            repository.save(song);
            return new ResponseEntity<>(song, HttpStatus.OK);
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // Get a random song for 
    @GetMapping("/random")
    public ResponseEntity<Songs> dailySong() {

        // Get list of all songs
        List<Songs> songlist = repository.findAllByOrderBySongAsc();

        // Initialize random, and get a random value from song length
        Random rand = new Random();
        Songs selected = songlist.get(rand.nextInt(songlist.size()));

        // Return selected song as response entity
        return new ResponseEntity<>(selected, HttpStatus.OK);
    }
}
